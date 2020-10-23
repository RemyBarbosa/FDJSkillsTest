package com.fdj.skillstest.interface_adapter.team

import com.fdj.skillstest.interface_adapter.team.model.TeamUIDetailModel
import com.fdj.skillstest.interface_adapter.team.model.TeamUIModel
import com.fdj.skillstest.use_case.base.Result
import com.fdj.skillstest.use_case.team.GetTeamDetailUseCase
import com.fdj.skillstest.use_case.team.GetTeamListUseCase

class TeamManager(
    private val getTeamListUseCase: GetTeamListUseCase,
    private val getTeamDetailUseCase: GetTeamDetailUseCase,
    private val teamUIMapper: TeamUIModel.Mapper,
    private val teamUIDetailMapper: TeamUIDetailModel.Mapper
) {

    suspend fun getTeamList(search: String): Result<List<TeamUIModel>> {
        val teamListResult = getTeamListUseCase.execute(search)
        return if (teamListResult is Result.Success) {
            Result.Success(teamListResult.data.map { teamUIMapper.fromEntity(it) })
        } else {
            teamListResult as Result.Error
        }
    }

    suspend fun getTeam(name: String): Result<TeamUIDetailModel> {
        val teamListResult = getTeamDetailUseCase.execute(name)
        return if (teamListResult is Result.Success) {
            Result.Success(teamUIDetailMapper.fromEntity(teamListResult.data))
        } else {
            teamListResult as Result.Error
        }
    }
}