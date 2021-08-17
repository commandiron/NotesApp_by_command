package com.demirli.a20notesappexample.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.demirli.a20notesappexample.R
import com.demirli.a20notesappexample.model.Notes
import com.demirli.a20notesappexample.ui.fragment.ListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().add(R.id.container,ListFragment()).commit()
    }
}
