package com.fdj.skillstest.team.data.remote

import com.fdj.skillstest.interface_adapter.team.model.TeamListRemote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface TeamRetrofitDataSource {

    @GET("/api/v1/json/1/search_all_teams.php")
    suspend fun getTeamList(@Query("l") search: String): Response<TeamListRemote>

    @GET("/api/v1/json/1/searchteams.php")
    suspend fun getTeamDetail(@Query("t") name: String): Response<TeamListRemote>
}
