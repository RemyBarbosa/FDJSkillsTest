package com.fdj.skillstest.interface_adapter.team.model

import com.google.gson.annotations.SerializedName

data class TeamListRemote(
    @SerializedName("teams") val teamList: List<TeamRemote>?
)
