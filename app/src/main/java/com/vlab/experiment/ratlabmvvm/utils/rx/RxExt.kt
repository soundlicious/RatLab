package com.vlab.experiment.ratlabmvvm.utils.rx

import io.reactivex.Single

fun <T> Single<T>.with(schedulerProvider: SchedulerProvider): Single<T> = observeOn(schedulerProvider.ui()).subscribeOn(schedulerProvider.io())
