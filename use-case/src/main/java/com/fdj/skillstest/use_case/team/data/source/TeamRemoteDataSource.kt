package com.fdj.skillstest.use_case.team.data.source

import com.fdj.skillstest.entity.team.Team
import com.fdj.skillstest.use_case.base.Result

interface TeamRemoteDataSource {
    suspend fun getTeamList(search : String): Result<List<Team>>
    suspend fun getTeamDetail(name : String): Result<Team>
}
