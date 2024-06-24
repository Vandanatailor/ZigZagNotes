package com.example.zigzagnotes

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.zigzagnotes.ui.home.view.HomeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        overFun()
    }
    private fun overFun(){
        Handler(Looper.getMainLooper()).postDelayed({
            val intent: Intent = Intent(
                this@MainActivity,
                HomeActivity::class.java
            )
            startActivity(intent)
            finish()
        }, 1800)
    }

}