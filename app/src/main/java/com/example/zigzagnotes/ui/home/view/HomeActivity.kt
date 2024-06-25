package com.example.zigzagnotes.ui.home.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.zigzagnotes.databinding.ActivityHomeBinding
import com.example.zigzagnotes.model.NotesModel
import com.example.zigzagnotes.room.database.DataBaseHelperImp
import com.example.zigzagnotes.room.database.DatabaseBuilder
import com.example.zigzagnotes.ui.AddNotesActivity
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)



        // Sample data to insert
//        val notes = listOf(
//            NotesModel(id=0,)
////            NotesModel(id = 0, title = "Note 1", content = "Content for note 1"),
////            NotesModel(id = 0, title = "Note 2", content = "Content for note 2")
//        )

        setBottomVisiblity()
        onClickEventsHandel()
    }



    private fun onClickEventsHandel() {
        binding.addThought.setOnClickListener {
            val intent: Intent = Intent(
                this@HomeActivity, AddNotesActivity::class.java
            )
            startActivity(intent)
        }
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