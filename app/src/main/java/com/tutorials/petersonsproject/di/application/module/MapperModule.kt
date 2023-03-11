package com.tutorials.petersonsproject.di.application.module


import com.tutorials.petersonsproject.network.mapper.ApiMapper
import com.tutorials.petersonsproject.network.mapper.ApiMapperImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MapperModule {
//    @Provides
//    fun daoMapper(): DaoMapper = DaoMapperImpl()
//
    @Provides
    @Singleton
    fun provideApiMapper(): ApiMapper = ApiMapperImpl()
//
//
    interface Exposes {
//        fun daoMapper(): DaoMapper
        fun apiMapper(): ApiMapper
    }
}