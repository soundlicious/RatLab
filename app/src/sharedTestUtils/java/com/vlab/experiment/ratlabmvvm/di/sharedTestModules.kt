package com.vlab.experiment.ratlabmvvm.di

import com.vlab.experiment.ratlabmvvm.data.Repository
import com.vlab.experiment.ratlabmvvm.utils.TestSchedulerProvider
import com.vlab.experiment.ratlabmvvm.utils.rx.SchedulerProvider
import org.koin.dsl.module.module

val testRxModule = module {
    single { TestSchedulerProvider() as SchedulerProvider }
}

val testRepositoryModule = module {
    single {TestRepository(get()) as Repository}
}


val testApp = listOf(appModule, testRxModule)

