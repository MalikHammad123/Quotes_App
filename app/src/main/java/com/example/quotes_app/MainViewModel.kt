package com.example.quotes_app

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context): ViewModel() {
    private var quotelist:Array<Quote> = emptyArray()
    private var index=0
    init {
        quotelist=loadQuotesFromAssests()
    }

    private fun loadQuotesFromAssests(): Array<Quote> {
    var inputstream=context.assets.open("quote.json")
        var size:Int=inputstream.available()
        val buffer=ByteArray(size)
        inputstream.read(buffer)
        inputstream.close()
        val json= String(buffer, Charsets.UTF_8)
        val gson= Gson()
        return gson.fromJson(json,Array<Quote>::class.java)
    }
    fun getquote()=quotelist[index]
    fun getNext()=quotelist[++index]
    fun getPrevious()=quotelist[--index]
}