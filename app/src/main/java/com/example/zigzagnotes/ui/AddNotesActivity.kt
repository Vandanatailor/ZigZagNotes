package com.example.zigzagnotes.ui

import android.app.Dialog
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.zigzagnotes.R
import com.example.zigzagnotes.databinding.ActivityAddNotesBinding
import com.example.zigzagnotes.databinding.PopupSaveChangesBinding
import com.example.zigzagnotes.ui.dialog.SaveDialog

class AddNotesActivity : AppCompatActivity() {

    private lateinit var activityAddNotesBinding: ActivityAddNotesBinding
    private  var saveDialog= SaveDialog()
    lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAddNotesBinding=ActivityAddNotesBinding.inflate(layoutInflater)
        setContentView(activityAddNotesBinding.root)
        dialog = Dialog(this)
        saveDialog.onSaveChangedDialog(this@AddNotesActivity)
        saveChanges()

    }
    private fun saveChanges(){
        activityAddNotesBinding.ivSaveChanges.setOnClickListener {
               saveDialog.showDialog()
        }

        activityAddNotesBinding.ivBack.setOnClickListener {
            finish()
        }
    }

}