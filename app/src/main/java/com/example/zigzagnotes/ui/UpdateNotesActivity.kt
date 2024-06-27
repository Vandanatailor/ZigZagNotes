package com.example.zigzagnotes.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Update
import com.example.zigzagnotes.R
import com.example.zigzagnotes.databinding.ActivityUpdateNotesBinding
import com.example.zigzagnotes.model.NoteModel
import com.example.zigzagnotes.room.database.DataBaseHelperImp
import com.example.zigzagnotes.room.database.DatabaseBuilder
import com.example.zigzagnotes.ui.adpter.NotesAdapter
import com.example.zigzagnotes.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateNotesActivity : AppCompatActivity() {
    private lateinit var binding :ActivityUpdateNotesBinding
    private lateinit var databaseHelper: DataBaseHelperImp
    private var id : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUpdateNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        id=intent.getIntExtra(Constants.ID,0)
        val notesDatabase = DatabaseBuilder.getInstance(this)
        databaseHelper = DataBaseHelperImp(notesDatabase)
          getNoesDataById()
        onClickHandel()
    }

    private fun onClickHandel() {
        binding.eyeSave.setOnClickListener {
            updateData()
        }
    }

    private fun getNoesDataById(){
        lifecycleScope.launch {
            val note = withContext(Dispatchers.IO) {
                databaseHelper.getNotesById(id)
            }
            binding.title.setText(note.title)
            binding.tvTypeSome.setText(note.description)
        }
    }

    private fun updateData(){
        var title =binding.title.text.toString()
        var description =binding.tvTypeSome.text.toString()
        lifecycleScope.launch {
            databaseHelper.updateDataById(id, title =title,description=description )
            Log.d("gfgfgfgfg", "UpdateData: ")
        }
    }


}

