package com.vlab.experiment.ratlabmvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import com.vlab.experiment.ratlabmvvm.data.models.typicode.UserModel
import com.vlab.experiment.ratlabmvvm.di.remoteDataSourceModule
import com.vlab.experiment.ratlabmvvm.di.repositoryModyle
import com.vlab.experiment.ratlabmvvm.di.testApp
import com.vlab.experiment.ratlabmvvm.ui.user.UserViewModel
import org.junit.*
import org.junit.rules.TestRule
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import java.util.*

inline fun <reified T> lambdaMock(): T = mock(T::class.java)

class UserVMTest: KoinTest{
    private val viewModel: UserViewModel by inject()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        StandAloneContext.startKoin(testApp)
    }

    @After
    fun after() {
        StandAloneContext.stopKoin()
    }

    @Test
    fun should_inject_viewModel(){
        Assert.assertNotNull(viewModel)
    }
//
//    @Test
//    fun `when users are requested, should call client and return response`(){
//        viewModel.updateUserList()
//
//        val mockLifeCycleOwner = mock(LifecycleOwner::class.java)
//        val lifecycle = LifecycleRegistry(mockLifeCycleOwner)
//        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
//
//
//    }
}