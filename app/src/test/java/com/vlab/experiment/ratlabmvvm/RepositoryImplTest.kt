package com.vlab.experiment.ratlabmvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vlab.experiment.ratlabmvvm.data.Repository
import com.vlab.experiment.ratlabmvvm.data.RepositoryImpl
import com.vlab.experiment.ratlabmvvm.di.remoteDataSourceModule
import com.vlab.experiment.ratlabmvvm.di.repositoryModyle
import com.vlab.experiment.ratlabmvvm.di.testApp
import junit.framework.TestCase.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.mockito.MockitoAnnotations

class RepositoryImplTest:KoinTest{

    private val repositoryImpl: Repository by inject()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        StandAloneContext.startKoin(testApp + repositoryModyle + remoteDataSourceModule)
    }

    @After
    fun after() {
        StandAloneContext.stopKoin()
    }

    @Test
    fun should_inject_repository(){
        assertNotNull(repositoryImpl)
    }

    @Test
    fun should_receive_users(){
        val userList = repositoryImpl.getUsers().blockingGet()
        assertNotNull(userList)
    }
}