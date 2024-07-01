package com.example.zigzagnotes.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
import com.example.zigzagnotes.ui.home.view.HomeActivity
import com.example.zigzagnotes.util.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateNotesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateNotesBinding
//    private lateinit var databaseHelper: DataBaseHelperImp
//    private var id: Int = 0
//    private var title: String = ""
//    private var description: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        id = intent.getIntExtra(Constants.ID, 0)
//        val notesDatabase = DatabaseBuilder.getInstance(this)
//        databaseHelper = DataBaseHelperImp(notesDatabase)
//        getNoesDataById()
//        onClickHandel()

    }

//    private fun onClickHandel() {
//        binding.ivUpdateChanges.setOnClickListener {
//              updateData()
//        }
//
//        binding.ivBack.setOnClickListener {
//            finish()
//        }
//
//        binding.ivShare.setOnClickListener {
//            shareData()
//        }
//    }
//
//    private fun getNoesDataById() {
//        lifecycleScope.launch {
//            val note = withContext(Dispatchers.IO) {
//                databaseHelper.getNotesById(id)
//            }
//            title = note.notes.title
//            description = note.notes.description
//            binding.title.setText(note.notes.title)
//            binding.tvTypeSome.setText(note.notes.description)
//        }
//    }
//
//    private fun updateData() {
//        var title = binding.title.text.toString()
//        var description = binding.tvTypeSome.text.toString()
//
//        lifecycleScope.launch {
//
//            if (title.isNotEmpty() && description.isNotEmpty()) {
//
//                try {
//                    val existingNote = databaseHelper.getNotesById(id)
//
//                    existingNote.notes.title = title
//                    existingNote.notes.description = description
//
//                    databaseHelper.updateData(existingNote)
//                    Toast.makeText(
//                        this@UpdateNotesActivity, "Update successfully", Toast.LENGTH_SHORT
//                    ).show()
//                    val intent: Intent = Intent(
//                        this@UpdateNotesActivity, HomeActivity::class.java
//                    )
//                    startActivity(intent)
//
//                } catch (e: Exception) {
//                    Log.d("anyExceptin", "updateData: " + e)
//                }
//            }else{
//                Toast.makeText(
//                    this@UpdateNotesActivity, "Title and description can not be empty", Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
//    }
//
//    private fun shareData() {
//        val shareIntent = Intent(Intent.ACTION_SEND)
//        shareIntent.setType("text/plain")
//        shareIntent.putExtra(Intent.EXTRA_TEXT, title)
//        shareIntent.putExtra(Intent.EXTRA_SUBJECT, description)
//        startActivity(Intent.createChooser(shareIntent, "Share..."))
//    }
}

