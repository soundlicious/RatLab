package com.vlab.experiment.ratlabmvvm

import com.vlab.experiment.ratlabmvvm.di.testApp
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.mockito.MockitoAnnotations
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vlab.experiment.ratlabmvvm.di.remoteDataSourceModule
import com.vlab.experiment.ratlabmvvm.di.repositoryModyle
import com.vlab.experiment.ratlabmvvm.ui.user.UserDetailsViewModel
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.junit.rules.TestRule



class UserDetailsVMTest: KoinTest {

     private val viewModel: UserDetailsViewModel by inject()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin(testApp + repositoryModyle + remoteDataSourceModule)
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun should_inject_viewModel(){
        assertNotNull(viewModel)
    }
}