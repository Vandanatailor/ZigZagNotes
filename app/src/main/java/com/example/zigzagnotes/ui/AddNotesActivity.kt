package com.example.zigzagnotes.ui

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.zigzagnotes.databinding.ActivityAddNotesBinding
import com.example.zigzagnotes.model.NoteModel
import com.example.zigzagnotes.model.Notes
import com.example.zigzagnotes.room.database.DataBaseHelperImp
import com.example.zigzagnotes.room.database.DatabaseBuilder
import com.example.zigzagnotes.ui.dialog.SaveDialog
import com.example.zigzagnotes.ui.dialog.onClick
import com.example.zigzagnotes.ui.home.view.HomeActivity
import com.example.zigzagnotes.ui.home.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddNotesActivity : AppCompatActivity(), onClick {

    private lateinit var activityAddNotesBinding: ActivityAddNotesBinding
    private var saveDialog = SaveDialog()
    lateinit var dialog: Dialog
   // private lateinit var databaseHelper: DataBaseHelperImp
    private val viewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAddNotesBinding = ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(activityAddNotesBinding.root)

      //  val notesDatabase = DatabaseBuilder.getInstance(this)
      //  databaseHelper = DataBaseHelperImp(notesDatabase)

        dialog = Dialog(this)
        saveDialog.onSaveChangedDialog(this@AddNotesActivity, this)
        initViewModel()
        observer()
        saveChanges()

    }
    private fun initViewModel(){
      //  viewModel= ViewModelProvider(this)[NoteViewModel::class.java]
    }

   private fun observer(){
        viewModel.errorResponse.observe(this , Observer{
                if (it != null) {
                    showToast(it.errorMessage)
                    Log.d("PrintLog", "insertNotes: " + it.errorMessage)
                }
        })

   }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun insertNotes() {
        val title = activityAddNotesBinding.title.text.toString().trim()
        val description = activityAddNotesBinding.tvTypeSome.text.toString().trim()

        if (title.isNotEmpty() && description.isNotEmpty() ) {
            val notess=Notes(title = title, description = description)
            val note = NoteModel(notes = notess)
            val notesList = mutableListOf(note)

            lifecycleScope.launch {
                try {
                    //databaseHelper.insertAll(notesList)
                    viewModel.insert(notesList)
                    activityAddNotesBinding.title.text.clear()
                    activityAddNotesBinding.tvTypeSome.text.clear()
                    Toast.makeText(
                        this@AddNotesActivity, "Saved successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent: Intent = Intent(
                        this@AddNotesActivity, HomeActivity::class.java
                    )
                    startActivity(intent)
                } catch (e: Exception) {
                    Log.d("PrintLog", "insertNotes: " + e.message)
                }
            }
        } else {
            Toast.makeText(
                this,
                "Title and Description can not be empty",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun saveChanges() {
        activityAddNotesBinding.ivSaveChanges.setOnClickListener {
            saveDialog.showDialog()
        }

        activityAddNotesBinding.ivBack.setOnClickListener {
            finish()
        }
    }

    override fun onSaveData() {
        insertNotes()
    }
}