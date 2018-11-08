package com.vlab.experiment.ratlabmvvm

import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import com.vlab.experiment.ratlabmvvm.data.Repository
import com.vlab.experiment.ratlabmvvm.data.local.db.TypicodeDao
import com.vlab.experiment.ratlabmvvm.data.models.typicode.*
import com.vlab.experiment.ratlabmvvm.di.*
import org.junit.*
import org.junit.runner.RunWith
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import org.koin.test.KoinTest


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
    @SmallTest
    fun repository_is_injected(){
        Assert.assertNotNull(repositoryImpl)
    }

    @Test
    @SmallTest
    fun receive_user_online_data_when_local_db_is_empty(){
        val list = repositoryImpl.getUsers().blockingGet()
        Assert.assertEquals(2, list.size)
        Assert.assertEquals("1",list[0].id  )
        Assert.assertEquals("name1", list[0].name)
    }

    @Test
    @SmallTest
    fun receive_user_local_data_when_local_db_is_filled(){
        dao.saveUser(listOf(UserModel("0", "name0", "username0", "email0", "phone0", "website)")))
        val list = repositoryImpl.getUsers().blockingGet()
        Assert.assertEquals(1, list.size)
        Assert.assertEquals("0", list[0].id  )
        Assert.assertEquals( "name0", list[0].name )
    }

    @Test
    @SmallTest
    fun receive_albums_online_data_when_local_db_is_empty(){
        val list = repositoryImpl.getUserAlbums("1").blockingGet()
        Assert.assertEquals(2, list.size)
        Assert.assertEquals("1", list[0].id)
        Assert.assertEquals("title1", list[0].title)
    }

    @Test
    @SmallTest
    fun receive_albums_local_data_when_local_db_is_filled(){
        dao.saveAlbums(listOf(AlbumModel("0", "0", "title0")))
        val list = repositoryImpl.getUserAlbums("0").blockingGet()
        Assert.assertEquals(1, list.size)
        Assert.assertEquals("0", list[0].id )
        Assert.assertEquals("title0", list[0].title )
    }

    @Test
    @SmallTest
    fun receive_post_online_data_when_local_db_is_empty(){
        val list = repositoryImpl.getUserPosts("1").blockingGet()
        Assert.assertEquals(2, list.size)
        Assert.assertEquals("1", list[0].id)
        Assert.assertEquals("title1", list[0].title)
    }

    @Test
    @SmallTest
    fun receive_post_local_data_when_local_db_is_filled(){
        dao.savePost(listOf(PostModel(0, "0", "title0", "body0")))
        val list = repositoryImpl.getUserPosts("0").blockingGet()
        Assert.assertEquals(1, list.size)
        Assert.assertEquals("0", list[0].id)
        Assert.assertEquals("title0", list[0].title)
    }

    @Test
    @SmallTest
    fun receive_comment_online_data_when_local_db_is_empty(){
        val list = repositoryImpl.getPostComment("1").blockingGet()
        Assert.assertEquals(2, list.size)
        Assert.assertEquals("1", list[0].id)
        Assert.assertEquals("body1", list[0].body)
    }

    @Test
    @SmallTest
    fun receive_comment_local_data_when_local_db_is_filled(){
        dao.saveComments(listOf(CommentModel("0", "0", "name0", "email0", "body0")))
        val list = repositoryImpl.getPostComment("0").blockingGet()
        Assert.assertEquals(1, list.size)
        Assert.assertEquals("0", list[0].id)
        Assert.assertEquals("body0", list[0].body)
    }

    @Test
    @SmallTest
    fun receive_photo_online_data_when_local_db_is_empty(){
        val list = repositoryImpl.getUserPhotosAlbum("1").blockingGet()
        Assert.assertEquals(2, list.size)
        Assert.assertEquals("1", list[0].id)
        Assert.assertEquals("title1", list[0].title)
    }

    @Test
    @SmallTest
    fun receive_photo_local_data_when_local_db_is_filled(){
        dao.savePhotos(listOf(PhotoModel("0", "0", "title0", "url0", "tn0")))
        val list = repositoryImpl.getUserPhotosAlbum("0").blockingGet()
        Assert.assertEquals(1, list.size)
        Assert.assertEquals("0", list[0].id)
        Assert.assertEquals("title0", list[0].title)
    }
}