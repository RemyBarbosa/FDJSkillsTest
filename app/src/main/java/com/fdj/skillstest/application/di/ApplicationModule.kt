package com.fdj.skillstest.application.di

import com.fdj.skillstest.interface_adapter.di.interfaceAdapterModule
import com.fdj.skillstest.interface_adapter.di.mapperModule
import com.fdj.skillstest.interface_adapter.team.TeamDetailContract
import com.fdj.skillstest.interface_adapter.team.TeamDetailPresenter
import com.fdj.skillstest.interface_adapter.team.TeamListContract
import com.fdj.skillstest.interface_adapter.team.TeamListPresenter
import com.fdj.skillstest.team.data.remote.TeamRemoteDataSourceImpl
import com.fdj.skillstest.use_case.di.useCaseModule
import com.fdj.skillstest.use_case.team.data.source.TeamRemoteDataSource
import org.koin.dsl.module.module

val applicationModule = module {

    single { TeamRemoteDataSourceImpl(get(), get()) as TeamRemoteDataSource }

    factory<TeamListContract.Presenter> { TeamListPresenter(get()) }

    factory<TeamDetailContract.Presenter> { TeamDetailPresenter(get()) }
}

val applicationInjectionsModules = listOf(
    applicationModule,
    retrofitModule,
    retrofitDependenciesModule,
    interfaceAdapterModule,
    useCaseModule,
    mapperModule
)