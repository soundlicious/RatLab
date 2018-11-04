package com.vlab.experiment.ratlabmvvm.di

import androidx.room.Room
import com.vlab.experiment.ratlabmvvm.data.Repository
import com.vlab.experiment.ratlabmvvm.data.RepositoryImpl
import com.vlab.experiment.ratlabmvvm.data.local.db.TypicodeDatabase
import com.vlab.experiment.ratlabmvvm.ui.user.UserDetailsViewModel
import com.vlab.experiment.ratlabmvvm.ui.user.UserViewModel
import com.vlab.experiment.ratlabmvvm.utils.rx.AndroidSchedulerProvider
import com.vlab.experiment.ratlabmvvm.utils.rx.SchedulerProvider
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    viewModel { UserViewModel(get(), get()) }
    viewModel { UserDetailsViewModel(get(), get()) }
}

val repositoryModyle = module {
    factory { RepositoryImpl(get(), get()) as Repository }
}

val rxModule = module {
    single { AndroidSchedulerProvider() as SchedulerProvider }
}

val localDataSourceModule = module {
    single { Room.databaseBuilder(androidApplication(),
        TypicodeDatabase::class.java,
        "typicode.db")
        .build() }

    single { get<TypicodeDatabase>().typicodeDao() }
}

val appModules = listOf(appModule, rxModule, repositoryModyle, remoteDataSourceModule, localDataSourceModule)
