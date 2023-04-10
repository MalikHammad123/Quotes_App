package com.example.quotes_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    private val quoteText:TextView
    get() = findViewById(R.id.quoteText)
    private val author:TextView
    get() = findViewById(R.id.quoteAuthor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel=ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)
        setQuote(mainViewModel.getquote())
    }
    fun setQuote(quote: Quote){
        quoteText.text=quote.text
        author.text=quote.author
    }

    fun onPrevious(view: View) {
        setQuote(mainViewModel.getPrevious())
    }
    fun onNext(view: View) {
        setQuote(mainViewModel.getNext())
    }
    fun onShare(view: View) {
        val intent=Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getquote().text)
        startActivity(intent)
    }

}