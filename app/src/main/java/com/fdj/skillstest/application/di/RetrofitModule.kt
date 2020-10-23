package com.fdj.skillstest.application.di

import com.fdj.skillstest.team.data.remote.TeamRetrofitDataSource
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://www.thesportsdb.com/")
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
    }

    single { get<Retrofit>().create(TeamRetrofitDataSource::class.java)}
}

