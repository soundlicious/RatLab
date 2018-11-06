package com.vlab.experiment.ratlabmvvm.di

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.vlab.experiment.ratlabmvvm.data.Repository
import com.vlab.experiment.ratlabmvvm.data.RepositoryImpl
import com.vlab.experiment.ratlabmvvm.data.local.db.TypicodeDatabase
import com.vlab.experiment.ratlabmvvm.data.network.TypicodeService
import com.vlab.experiment.ratlabmvvm.data.network.TypicodeTestImpl

import org.koin.dsl.module.module

val androidRepositoryTestModule = module {
    single { RepositoryImpl(get(), get()) as Repository }
    factory { TypicodeTestImpl() as TypicodeService }
}
val testLocalDB = module {
    single (override = true){
        // In-Memory database config
        Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), TypicodeDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    single { get<TypicodeDatabase>().typicodeDao() }
}