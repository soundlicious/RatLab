package com.vlab.experiment.ratlabmvvm.di

import com.vlab.experiment.ratlabmvvm.data.Repository
import com.vlab.experiment.ratlabmvvm.ui.user.UserDetailsViewModel
import com.vlab.experiment.ratlabmvvm.ui.user.UserViewModel
import com.vlab.experiment.ratlabmvvm.utils.rx.AndroidSchedulerProvider
import com.vlab.experiment.ratlabmvvm.utils.rx.SchedulerProvider
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    viewModel { UserViewModel(get(), get()) }
    viewModel { UserDetailsViewModel(get(), get()) }
    factory { Repository(get()) }
}

val rxModule = module {
    single { AndroidSchedulerProvider() as SchedulerProvider }
}

val appModules = listOf(appModule, rxModule, remoteDataSourceModule)
