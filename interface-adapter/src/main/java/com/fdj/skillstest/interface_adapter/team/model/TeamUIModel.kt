package com.fdj.skillstest.interface_adapter.team.model

import com.fdj.skillstest.entity.team.Team

data class TeamUIModel(
    val name: String,
    val badgeUrl: String?
) {
    class Mapper {
        fun fromEntity(team: Team) =
            TeamUIModel(
                name = team.name,
                badgeUrl = team.badgeUrl
            )
    }
}
