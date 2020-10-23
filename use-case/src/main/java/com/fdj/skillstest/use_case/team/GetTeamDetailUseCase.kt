package com.fdj.skillstest.use_case.team

import com.fdj.skillstest.entity.team.Team
import com.fdj.skillstest.use_case.base.Result
import com.fdj.skillstest.use_case.team.data.TeamRepository

class GetTeamDetailUseCase(
    private val teamRepository: TeamRepository
) {
    suspend fun execute(name: String): Result<Team> = teamRepository.getTeamDetail(name)
}