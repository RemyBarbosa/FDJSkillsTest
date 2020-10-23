package com.fdj.skillstest.use_case.di

import com.fdj.skillstest.use_case.team.GetTeamDetailUseCase
import com.fdj.skillstest.use_case.team.GetTeamListUseCase
import com.fdj.skillstest.use_case.team.data.TeamRepository
import org.koin.dsl.module.module

val useCaseModule = module {
    single { GetTeamListUseCase(get()) }
    single { GetTeamDetailUseCase(get()) }

    single { TeamRepository(get()) }
}
