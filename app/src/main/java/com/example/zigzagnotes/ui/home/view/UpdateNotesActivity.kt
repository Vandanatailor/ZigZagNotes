package com.example.zigzagnotes.ui.home.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.zigzagnotes.databinding.ActivityUpdateNotesBinding
import com.example.zigzagnotes.model.NoteModel
import com.example.zigzagnotes.ui.home.viewmodel.NoteViewModel
import com.example.zigzagnotes.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateNotesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateNotesBinding
    private var id: Int = 0
    private var title: String = ""
    private var description: String = ""
    private val viewModel: NoteViewModel by viewModels()
    private lateinit var noteModel : NoteModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        id = intent.getIntExtra(Constants.ID, 0)
        Log.d("gfgfgfgfgdddddddddd", "onCreate: "+id)
        dataObserver()
        getNotesById()
        onClickHandel()
    }

    private fun getNotesById(){
        viewModel.getDataById(id)
    }


    private fun dataObserver() {

            viewModel.getNoteData.observe(this@UpdateNotesActivity, Observer {
                Log.d("dataPleae", "dataObserver:111111 "+it)
                noteModel=it
                title = it.notes.title
                description = it.notes.description
                binding.title.setText(title)
                binding.tvTypeSome.setText(description)
            })

            viewModel.errorResponse.observe(this@UpdateNotesActivity) {
                if (it != null) {
                    showToast(it.errorMessage)
                    Log.d("PrintLog", "HomeKeyboard: " + it.errorMessage)
                }
            }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
    private fun onClickHandel() {
        binding.ivUpdateChanges.setOnClickListener {
               updateData()
        }

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.ivShare.setOnClickListener {
            shareData()
        }
    }

    private fun updateData() {
        var title = binding.title.text.toString()
        var description = binding.tvTypeSome.text.toString()
        Log.d("hhhhhhhh", "updateData: "+noteModel)
            if (title.isNotEmpty() && description.isNotEmpty()) {

                noteModel.notes.title = title
                noteModel.notes.description = description
                viewModel.updateNotes(noteModel)

                viewModel.errorResponse.observe(this, Observer { errorResponse ->
                    if (errorResponse != null) {
                        Toast.makeText(
                            this@UpdateNotesActivity,
                            errorResponse.errorMessage, Toast.LENGTH_SHORT
                        ).show()
                        Log.d("Error", "updateData: ${errorResponse.errorMessage}")

                    } else {
                        Toast.makeText(
                            this@UpdateNotesActivity,
                            "Update successfully", Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(
                            this@UpdateNotesActivity,
                            HomeActivity::class.java
                        )
                        startActivity(intent)
                    }
                })

            }
            else{
                Toast.makeText(
                    this@UpdateNotesActivity,
                    "Title and description can not be empty",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    private fun shareData() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
        shareIntent.putExtra(Intent.EXTRA_TEXT, title)
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, description)
        startActivity(Intent.createChooser(shareIntent, "Share..."))
    }
}

