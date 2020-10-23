package com.fdj.skillstest.use_case.team.data

import com.fdj.skillstest.entity.team.Team
import com.fdj.skillstest.use_case.base.Result
import com.fdj.skillstest.use_case.team.data.source.TeamRemoteDataSource

class TeamRepository(
    private val teamRemoteDataSource: TeamRemoteDataSource
) {
    suspend fun getTeamList(search: String): Result<List<Team>> =
        teamRemoteDataSource.getTeamList(search)

    suspend fun getTeamDetail(name: String): Result<Team> =
        teamRemoteDataSource.getTeamDetail(name)
}
