package com.vlab.experiment.ratlabmvvm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.vlab.experiment.ratlabmvvm.data.Repository
import com.vlab.experiment.ratlabmvvm.di.remoteDataSourceModule
import com.vlab.experiment.ratlabmvvm.di.testApp
import com.vlab.experiment.ratlabmvvm.di.testRepositoryModule
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
        StandAloneContext.startKoin(testApp + testRepositoryModule + remoteDataSourceModule)
    }

    @After
    fun after() {
        StandAloneContext.stopKoin()
    }

    @Test
    @SmallTest
    fun should_inject_repository(){
        assertNotNull(repositoryImpl)
    }

    @Test
    @SmallTest
    fun should_receive_users(){
        val userList = repositoryImpl.getUsers().blockingGet()
        System.out.println("userList size : " + userList.size)
        assertNotNull(userList)
    }

    @Test
    @SmallTest
    fun should_receive_album_photos_from_same_album(){
        for (i in 1..10) {
            val list = repositoryImpl.getUserPhotosAlbum(i.toString()).blockingGet()
            list.forEach {
                assertEquals(i.toString(), it.albumId)
            }
        }
    }

    @Test
    @SmallTest
    fun should_receive_user_albums_from_same_user(){
        for (i in 1..10) {
            val list = repositoryImpl.getUserAlbums(i.toString()).blockingGet()
            list.forEach {
                assertEquals(i.toString(), it.userId)
            }
        }
    }

    @Test
    @SmallTest
    fun `should receive user post from same user`(){
        for (i in 1..10) {
            val list = repositoryImpl.getUserPosts(i.toString()).blockingGet()
            list.forEach {
                assertEquals(i.toLong(), it.userId)
            }
        }
    }

    @Test
    @SmallTest
    fun `should receive post comment from same post`(){
        for (i in 1..10) {
            val list = repositoryImpl.getPostComment(i.toString()).blockingGet()
            list.forEach {
                assertEquals(i.toString(), it.postId)
            }
        }
    }

    @Test
    @SmallTest
    fun `received list from all routes should be empty`(){
        var char : Char = 'a'
        while (char != 'g'){
            var list: List<Any> = repositoryImpl.getPostComment(char.toString()).blockingGet()
            assert(list.isEmpty())
            list = repositoryImpl.getUserPosts(char.toString()).blockingGet()
            assert(list.isEmpty())
            list = repositoryImpl.getUserAlbums(char.toString()).blockingGet()
            assert(list.isEmpty())
            list = repositoryImpl.getUserPhotosAlbum(char.toString()).blockingGet()
            assert(list.isEmpty())
            char++
        }
    }
}