package com.example.pa

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

//for the information panel. contains supervend information and contact number
class InfoActivity : AppCompatActivity() {
    private lateinit var textView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.info_layout)
    }
}