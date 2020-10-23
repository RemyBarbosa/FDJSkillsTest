package com.fdj.skillstest.interface_adapter.di

import com.fdj.skillstest.interface_adapter.team.TeamManager
import org.koin.dsl.module.module

val interfaceAdapterModule = module {
    single { TeamManager(get(), get(), get(), get()) }
}
