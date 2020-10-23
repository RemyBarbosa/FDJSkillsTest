package com.fdj.skillstest.team.data.remote

import com.fdj.skillstest.entity.team.Team
import com.fdj.skillstest.interface_adapter.team.model.TeamRemote
import com.fdj.skillstest.use_case.base.Result
import com.fdj.skillstest.use_case.team.data.source.TeamRemoteDataSource
import java.io.IOException

class TeamRemoteDataSourceImpl(
    private val mapper: TeamRemote.Mapper,
    private val teamRetrofitDataSource: TeamRetrofitDataSource
) : TeamRemoteDataSource {

    override suspend fun getTeamList(search: String): Result<List<Team>> {
        val response = teamRetrofitDataSource.getTeamList(search)
        if (response.isSuccessful) {
            val body = response.body()
            val teamList = body?.teamList
            return if (teamList?.isNullOrEmpty() == false) {
                Result.Success(teamList.map { mapper.toEntity(it) })
            } else {
                Result.Error(NullPointerException("teams is null or empty"))
            }
        }
        return Result.Error(IOException("teams retrieval failed "))
    }

    override suspend fun getTeamDetail(name: String): Result<Team> {
        val response = teamRetrofitDataSource.getTeamDetail(name)
        if (response.isSuccessful) {
            val body = response.body()
            val teamList = body?.teamList
            return if (teamList?.isNullOrEmpty() == false) {
                Result.Success(mapper.toEntity(teamList.first()))
            } else {
                Result.Error(NullPointerException("teams is null or empty"))
            }
        }
        return Result.Error(IOException("teams retrieval failed "))
    }
}
