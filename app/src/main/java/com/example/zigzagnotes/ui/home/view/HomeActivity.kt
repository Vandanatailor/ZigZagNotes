package com.example.zigzagnotes.ui.home.view

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.zigzagnotes.databinding.ActivityHomeBinding
import com.example.zigzagnotes.model.NoteModel
import com.example.zigzagnotes.ui.adpter.NotesAdapter
import com.example.zigzagnotes.ui.dialog.DeleteDialog
import com.example.zigzagnotes.ui.dialog.onClickDelete
import com.example.zigzagnotes.ui.home.viewmodel.NoteViewModel
import com.example.zigzagnotes.util.Constants
import com.example.zigzagnotes.util.ItemsCLickListner
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), ItemsCLickListner, onClickDelete {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel: NoteViewModel by viewModels()
    private lateinit var dialog: Dialog
    private var deleteDialog= DeleteDialog()
    private lateinit var notesAdapter: NotesAdapter
    private var notesList: MutableList<NoteModel> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dialog = Dialog(this)
        deleteDialog.onDeleteDialog(this,this)
        setBottomVisiblity()
        observeData()
        getAllData()

    }

    private fun getAllData() {
        viewModel.getAll()
    }

    private fun onClickEventsHandel() {
        val intent: Intent = Intent(
            this@HomeActivity, AddNotesActivity::class.java
        )
        startActivity(intent)
    }

    private fun setBottomVisiblity() {
        binding.lvAddNotes.setOnClickListener {
            onClickEventsHandel()
        }

        binding.deleteAll.setOnClickListener {
            if(notesList.isEmpty()){
                Toast.makeText(this@HomeActivity, "There is no data for delete",
                    Toast.LENGTH_SHORT).show()
            }else{
                deleteDialog.showDialog()
            }
        }
    }

    private fun setAdapter() {
        val linearLayoutManager = GridLayoutManager(this, 2)
        binding.rvData.layoutManager = linearLayoutManager
        notesAdapter = NotesAdapter(this, notesList, this)
        binding.rvData.adapter = notesAdapter
        notesAdapter.notifyDataSetChanged()
        visiblityPLaceholder()
    }

    private fun visiblityPLaceholder() {
        if (notesList.isEmpty()) {
            binding.lnPlaceholder.visibility = View.VISIBLE
            binding.rvData.visibility = View.GONE
        } else {
            binding.lnPlaceholder.visibility = View.GONE
            binding.rvData.visibility = View.VISIBLE
        }
    }

    private fun observeData() {

        lifecycleScope.launch {
            viewModel.listNotData.observe(this@HomeActivity, Observer {
                val allNotes = it
                Log.d("gggggggggggggg", "observeData: " + it)
                notesList.clear()
                notesList.addAll(allNotes)
                setAdapter()
                notesAdapter.notifyDataSetChanged()
            })


            viewModel.errorResponse.observe(this@HomeActivity) {
                if (it != null) {
                    showToast(it.errorMessage)
                    Log.d("PrintLog", "HomeKeyboard: " + it.errorMessage)
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun selectItemCLick(position: Int, type: String) {
        if (type.equals(Constants.Delete)) {
            lifecycleScope.launch {
                viewModel.deleteById(notesList[position].id)
                withContext(Dispatchers.Main) {
                    notesList.removeAt(position)
                    notesAdapter.notifyItemRemoved(position)
                    visiblityPLaceholder()
                }
            }
        } else if (type.equals(Constants.DataShow)) {
            startActivity(
                Intent(this@HomeActivity, UpdateNotesActivity::class.java)
                    .putExtra(Constants.ID, notesList[position].id)
            )
        }
    }

    override fun onResume() {
        super.onResume()
        getAllData()
    }

    private  fun allDeleteNotes()
    {
        viewModel.deleteAllNotes()
        notesList.clear()
        notesAdapter.notifyDataSetChanged()
        visiblityPLaceholder()
    }
    override fun onDeleteNotes() {
             allDeleteNotes()
    }
}



