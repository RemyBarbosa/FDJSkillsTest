package com.fdj.skillstest.application.di

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module.module

val retrofitDependenciesModule = module {

    single { GsonBuilder().create() as Gson }
    single { OkHttpClient.Builder().addNetworkInterceptor(StethoInterceptor()).build() as OkHttpClient }
}

