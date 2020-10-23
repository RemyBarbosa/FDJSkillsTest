package com.fdj.skillstest.use_case.team

import com.fdj.skillstest.entity.team.Team
import com.fdj.skillstest.use_case.base.Result
import com.fdj.skillstest.use_case.team.data.TeamRepository

class GetTeamListUseCase(
    private val teamRepository: TeamRepository
) {
    suspend fun execute(search: String): Result<List<Team>> = teamRepository.getTeamList(search)
}