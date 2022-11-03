package app.asgn.mvvmpractice.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import app.asgn.mvvmpractice.R
import app.asgn.mvvmpractice.viewmodels.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }
}