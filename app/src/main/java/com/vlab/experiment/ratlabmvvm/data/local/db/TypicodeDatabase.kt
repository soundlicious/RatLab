package com.vlab.experiment.ratlabmvvm.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vlab.experiment.ratlabmvvm.data.models.typicode.*

@Database(entities = [UserModel::class, AlbumModel::class,
    PhotoModel::class, PostModel::class, CommentModel::class], version = 1,  exportSchema = false)
abstract class TypicodeDatabase : RoomDatabase(){

    abstract fun typicodeDao(): TypicodeDao

    companion object {
        private var INSTANCE: TypicodeDatabase? = null

        fun getInstance(context: Context): TypicodeDatabase? {
            if(INSTANCE == null){
                synchronized(TypicodeDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        TypicodeDatabase::class.java,
                        "typicode.db")
                        .build()
                }
            }
            return INSTANCE
        }
        fun destroyInstance(){
            INSTANCE = null
        }
    }
}