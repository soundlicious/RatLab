package com.vlab.experiment.ratlabmvvm

import androidx.test.runner.AndroidJUnit4
import com.vlab.experiment.ratlabmvvm.data.Repository
import com.vlab.experiment.ratlabmvvm.data.local.db.TypicodeDao
import com.vlab.experiment.ratlabmvvm.data.local.db.TypicodeDatabase
import com.vlab.experiment.ratlabmvvm.data.models.typicode.UserModel
import com.vlab.experiment.ratlabmvvm.di.*
import org.junit.*
import org.junit.runner.RunWith
import org.koin.standalone.StandAloneContext
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.standalone.inject
import org.koin.test.KoinTest
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.junit.Rule



@RunWith(AndroidJUnit4::class)
class RepositoryAndroidTest : KoinTest{

    private val repositoryImpl: Repository by inject()
    private val dao: TypicodeDao by inject()

    @Before
    fun before() {
        StandAloneContext.startKoin(testApp + androidRepositoryTestModule + testLocalDB)
    }

    @After
    fun after() {
        StandAloneContext.stopKoin()
    }

    @Test
    fun repository_is_injected(){
        Assert.assertNotNull(repositoryImpl)
    }

    @Test
    fun receive_online_data_when_local_db_is_empty(){
        val list = repositoryImpl.getUsers().blockingGet()
        Assert.assertEquals(list.size, 2)
        Assert.assertEquals(list[0].id, "1" )
        Assert.assertEquals(list[0].name, "name1" )
    }

    @Test
    fun receive_local_data_when_local_db_is_filled(){
        dao.saveUser(listOf(UserModel("0", "name0", "username0", "email0", "phone0", "website)")))
        val list = repositoryImpl.getUsers().blockingGet()
        Assert.assertEquals(list.size, 1)
        Assert.assertEquals(list[0].id, "0" )
        Assert.assertEquals(list[0].name, "name0" )
    }
}