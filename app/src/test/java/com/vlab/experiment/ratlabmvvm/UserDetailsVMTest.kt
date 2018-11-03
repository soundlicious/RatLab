package com.vlab.experiment.ratlabmvvm

import com.vlab.experiment.ratlabmvvm.di.testApp
import com.vlab.experiment.ratlabmvvm.ui.user.UserViewModel
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.mockito.MockitoAnnotations

class UserDetailsVMTest: KoinTest {

     private val userViewModel: UserViewModel by inject()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        startKoin(testApp)
    }

//    @After
//    fun after() {
//        stopKoin()
//    }

    @Test
    fun should_inject_viewModel(){
        assertNotNull(userViewModel)
    }
}