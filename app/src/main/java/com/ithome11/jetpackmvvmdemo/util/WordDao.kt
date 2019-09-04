package com.ithome11.jetpackmvvmdemo.util

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//DAO類需為 interface or abstract
@Dao
interface WordDao {
    // Query 填寫sql語法

    /**
     *  1.ORDER BY  Column_name  排序結果  ASC 代表由小往大  DESC 大到小
     *  2.getAllWords =返回包有List<Word> 的 LiveData
     **/
    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAllWords(): LiveData<List<WordEntity>>

    //Insert有保留語法
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(wordEntity: WordEntity)

    @Query("DELETE FROM word_table")
    suspend fun deleteAll()


}