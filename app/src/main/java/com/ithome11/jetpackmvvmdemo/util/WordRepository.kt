package com.ithome11.jetpackmvvmdemo.util

import androidx.lifecycle.LiveData

//DAO包含所有的讀/寫與其他方法 透過Repository訪問DAO取用需要的fun就好
class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<WordEntity>> = wordDao.getAllWords()

    suspend fun insert(word: WordEntity) {
        wordDao.insert(word)
    }

}