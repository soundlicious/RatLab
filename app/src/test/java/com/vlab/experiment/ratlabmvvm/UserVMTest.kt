package com.vlab.experiment.ratlabmvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.test.filters.SmallTest
import com.vlab.experiment.ratlabmvvm.data.models.typicode.UserModel
import com.vlab.experiment.ratlabmvvm.di.remoteDataSourceModule
import com.vlab.experiment.ratlabmvvm.di.repositoryModyle
import com.vlab.experiment.ratlabmvvm.di.testApp
import com.vlab.experiment.ratlabmvvm.di.testRepositoryModule
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

class UserVMTest: KoinTest{
    private val viewModel: UserViewModel by inject()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        StandAloneContext.startKoin(testApp + testRepositoryModule + remoteDataSourceModule)
    }

    @After
    fun after() {
        StandAloneContext.stopKoin()
    }

    @Test
    @SmallTest
    fun should_inject_viewModel(){
        Assert.assertNotNull(viewModel)
    }
}