package com.ithome11.jetpackmvvmdemo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.ithome11.jetpackmvvmdemo.util.WordDao
import com.ithome11.jetpackmvvmdemo.util.WordEntity
import com.ithome11.jetpackmvvmdemo.util.WordRoomDatabase
import junit.framework.Assert
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class WordDaoTest {

    //立即調用線程的背景操作
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var wordDao: WordDao
    private lateinit var db: WordRoomDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        //將db存在內存 當process結束時會清除
        db = Room.inMemoryDatabaseBuilder(context, WordRoomDatabase::class.java)
            // 允許在主線程查詢 僅用於測試
            .allowMainThreadQueries()
            .build()
        wordDao = db.wordDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndGetWord() = runBlocking {
        val word = WordEntity("word")
        wordDao.insert(word)
        val allWords = wordDao.getAllWords().waitForValue()
        Assert.assertEquals(allWords[0].word, word.word)
    }

    @Test
    fun getAllWords() = runBlocking {
        val word = WordEntity("aaa")
        wordDao.insert(word)
        val word2 = WordEntity("bbb")
        wordDao.insert(word2)
        val allWords = wordDao.getAllWords().waitForValue()
        Assert.assertEquals(allWords[0].word, word.word)
        Assert.assertEquals(allWords[1].word, word2.word)
    }

    @Test
    fun deleteAll() = runBlocking {
        val word = WordEntity("word")
        wordDao.insert(word)
        val word2 = WordEntity("word2")
        wordDao.insert(word2)
        wordDao.deleteAll()
        val allWords = wordDao.getAllWords().waitForValue()
        Assert.assertTrue(!allWords.isEmpty())
    }
}