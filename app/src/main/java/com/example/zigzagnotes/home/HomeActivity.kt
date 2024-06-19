package com.example.zigzagnotes.home

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.zigzagnotes.R
import com.example.zigzagnotes.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBottomVisiblity()
    }
    private fun setBottomVisiblity() {
        binding.lvAddNotes.setOnClickListener {
            if(binding.rlAdd.visibility==View.VISIBLE){
                binding.rlAdd.visibility= View.GONE
            }else{
                binding.rlAdd.visibility= View.VISIBLE
            }

        }

    }


}