package com.vlab.experiment.ratlabmvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vlab.experiment.ratlabmvvm.data.Repository
import com.vlab.experiment.ratlabmvvm.di.testApp
import junit.framework.TestCase.assertEquals
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
import timber.log.Timber

class RepositoryTest:KoinTest{

    private val repository: Repository by inject()

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
    fun should_inject_repository(){
        assertNotNull(repository)
    }

    @Test
    fun should_receive_users(){
        var userList = repository.getUsers().blockingGet()
        assertNotNull(userList)
    }
}