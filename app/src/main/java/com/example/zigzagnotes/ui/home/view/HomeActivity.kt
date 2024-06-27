package com.example.zigzagnotes.ui.home.view

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.zigzagnotes.databinding.ActivityHomeBinding
import com.example.zigzagnotes.model.NoteModel
import com.example.zigzagnotes.room.database.DataBaseHelperImp
import com.example.zigzagnotes.room.database.DatabaseBuilder
import com.example.zigzagnotes.ui.AddNotesActivity
import com.example.zigzagnotes.ui.UpdateNotesActivity
import com.example.zigzagnotes.ui.adpter.NotesAdapter
import com.example.zigzagnotes.ui.dialog.DeleteDialog
import com.example.zigzagnotes.ui.dialog.SaveDialog
import com.example.zigzagnotes.ui.dialog.onClick
import com.example.zigzagnotes.ui.dialog.onClickDelete
import com.example.zigzagnotes.util.Constants
import com.example.zigzagnotes.util.ItemsCLickListner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeActivity : AppCompatActivity() ,ItemsCLickListner,onClickDelete {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var databaseHelper: DataBaseHelperImp
    private lateinit var notesAdapter: NotesAdapter
    private var notesList: MutableList<NoteModel> = mutableListOf()
    private var deleteDialog = DeleteDialog()
    lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notesDatabase = DatabaseBuilder.getInstance(this)
        databaseHelper = DataBaseHelperImp(notesDatabase)

        dialog = Dialog(this)
        deleteDialog.onDeleteDialog(this,this)

        setBottomVisiblity()
        getData()
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
           deleteDialog.showDialog()
        }
    }

    private fun setAdapter(){
       val linearLayoutManager = GridLayoutManager(this,2)
        binding.rvData.layoutManager = linearLayoutManager
        notesAdapter = NotesAdapter(this, notesList,this)
        binding.rvData.adapter = notesAdapter
        if (notesList.isEmpty()) {
            binding.lnPlaceholder.visibility = View.VISIBLE
            binding.rvData.visibility = View.GONE
        } else {
            binding.lnPlaceholder.visibility = View.GONE
            binding.rvData.visibility = View.VISIBLE
        }
        notesAdapter.notifyDataSetChanged()
    }
    private fun getData(){
        lifecycleScope.launch {
            try {
                val allNotes = withContext(Dispatchers.IO) {
                    databaseHelper.getAllNotes()

                }
                notesList.clear()
                notesList.addAll(allNotes)
                setAdapter()
                notesAdapter.notifyDataSetChanged()
            } catch (e: Exception) {

            }
        }
    }
    override fun selectItemCLick(position: Int, type: String) {
        if(type.equals(Constants.Delete)){
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    databaseHelper.deleteDataById(notesList[position].id)
                }
                withContext(Dispatchers.Main) {
                    notesList.removeAt(position)
                    notesAdapter.notifyItemRemoved(position)
                }
            }
        }else if(type.equals(Constants.DataShow)){
            startActivity(
                Intent(this@HomeActivity, UpdateNotesActivity::class.java)
                    .putExtra(Constants.ID, notesList[position].id)
            )
        }
    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    override fun onDeleteNotes() {

    }
}