package com.ithome11.jetpackmvvmdemo.util

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//database class 需使用抽象類並且繼承RoomDatabase
@Database(entities = arrayOf(WordEntity::class), version = 1)
public abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun WordDao(): WordDao

    companion object {

        //Volatile 用來避免編譯器優化
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context): WordRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            } else {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        WordRoomDatabase::class.java,
                        "word_database"
                    ).build()
                    INSTANCE = instance
                    return instance
                }
            }

        }
    }
}