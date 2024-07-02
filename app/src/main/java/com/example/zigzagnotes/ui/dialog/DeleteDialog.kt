package com.example.zigzagnotes.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.WindowManager
import com.example.zigzagnotes.databinding.PopupDeleteBinding
import com.example.zigzagnotes.databinding.PopupSaveChangesBinding

class DeleteDialog {
    private var dialog: Dialog? = null
    var context: Context? = null
    lateinit var binding : PopupDeleteBinding
    lateinit var onClick: onClickDelete

    fun onDeleteDialog(context: Context?,onClick: onClickDelete) {
        this.context = context
        dialog = Dialog(context!!)
        this.onClick=onClick
        dialog!!.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog!!.setCancelable(true)
        binding = PopupDeleteBinding.inflate(LayoutInflater.from(context))
        dialog!!.setContentView(binding.getRoot())
        val width = (context.getResources().getDisplayMetrics().widthPixels * 0.85).toInt()
        val height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog!!.getWindow()?.setLayout(width, height);
    }

    fun showDialog() {
        if (dialog != null) {
            dialog!!.show()
        }

        binding.tvDelete.setOnClickListener {
            dialog?.dismiss()
            onClick.onDeleteNotes()
        }

    }

}


interface onClickDelete {
    fun onDeleteNotes()
}
