package com.ithome11.jetpackmvvmdemo.util

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: WordRepository
    val allWords: LiveData<List<WordEntity>>

    init {
        //從WordRoomDatabase引用唯一的wordDao構築WordRepository
        val wordsDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()

        repository = WordRepository(wordsDao)
        allWords = repository.allWords
    }

    fun insertWord(word: WordEntity) = viewModelScope.launch {
        repository.insert(word)
    }

}