package com.fdj.skillstest.interface_adapter.team.model

import com.fdj.skillstest.entity.team.Team
import com.google.gson.annotations.SerializedName

data class TeamRemote(
    @SerializedName("strTeam") val name: String?,
    @SerializedName("strTeamBadge") val badgeUrl: String?,
    @SerializedName("strTeamBanner") val bannerUrl: String?,
    @SerializedName("strCountry") val country: String?,
    @SerializedName("strLeague") val league: String?,
    @SerializedName("strDescriptionFR") val description: String?
) {
    companion object {
        private const val DEFAULT_TEAM_NAME = "BEST TEAM EVER"
    }
    class Mapper {
        fun toEntity(teamRemote: TeamRemote): Team = Team(
            name = teamRemote.name ?: DEFAULT_TEAM_NAME,
            badgeUrl = teamRemote.badgeUrl,
            bannerUrl = teamRemote.bannerUrl,
            country = teamRemote.country,
            league = teamRemote.league,
            description = teamRemote.description
        )
    }
}
