package com.fdj.skillstest.interface_adapter.di

import com.fdj.skillstest.interface_adapter.team.model.TeamRemote
import com.fdj.skillstest.interface_adapter.team.model.TeamUIDetailModel
import com.fdj.skillstest.interface_adapter.team.model.TeamUIModel
import org.koin.dsl.module.module

val mapperModule = module {
    single { TeamUIModel.Mapper() }
    single { TeamUIDetailModel.Mapper() }
    single { TeamRemote.Mapper() }


}
