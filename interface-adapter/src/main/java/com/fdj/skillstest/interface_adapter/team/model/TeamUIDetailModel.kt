package com.fdj.skillstest.interface_adapter.team.model

import com.fdj.skillstest.entity.team.Team

data class TeamUIDetailModel(
    val bannerUrl: String?,
    val country: String?,
    val league: String?,
    val description: String?
) {
    class Mapper {
        fun fromEntity(team: Team) =
            TeamUIDetailModel(
                bannerUrl = team.bannerUrl,
                country = team.country,
                league = team.league,
                description = team.description
            )
    }
}
