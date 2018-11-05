package com.vlab.experiment.ratlabmvvm

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vlab.experiment.ratlabmvvm.data.Repository
import com.vlab.experiment.ratlabmvvm.data.RepositoryImpl
import com.vlab.experiment.ratlabmvvm.di.remoteDataSourceModule
import com.vlab.experiment.ratlabmvvm.di.repositoryModyle
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

class RepositoryImplTest:KoinTest{

    private val repositoryImpl: Repository by inject()

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
        assertNotNull(repositoryImpl)
    }

    @Test
    fun should_receive_users(){
        val userList = repositoryImpl.getUsers().blockingGet()
        System.out.println("userList size : " + userList.size)
        assertNotNull(userList)
    }

    @Test
    fun should_receive_album_photos_from_same_album(){
        for (i in 1..10) {
            var list = repositoryImpl.getUserPhotosAlbum(i.toString()).blockingGet()
            list.forEach {
                assertEquals(i.toString(), it.albumId)
            }
        }
    }

    @Test
    fun should_receive_user_albums_from_same_user(){
        for (i in 1..10) {
            var list = repositoryImpl.getUserAlbums(i.toString()).blockingGet()
            list.forEach {
                assertEquals(i.toString(), it.userId)
            }
        }
    }
}