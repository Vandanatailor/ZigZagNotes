package com.example.zigzagnotes.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.WindowManager
import com.example.zigzagnotes.databinding.PopupSaveChangesBinding

class SaveDialog {
    private var dialog: Dialog? = null
    var context: Context? = null
    lateinit var popupSaveChangesBinding: PopupSaveChangesBinding
    lateinit var onClick: onClick

    fun onSaveChangedDialog(context: Context?,onClick: onClick) {

        this.context = context
        dialog = Dialog(context!!)
        this.onClick=onClick
        dialog!!.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog!!.setCancelable(true)
        popupSaveChangesBinding = PopupSaveChangesBinding.inflate(LayoutInflater.from(context))
        dialog!!.setContentView(popupSaveChangesBinding.getRoot())
        val width = (context.getResources().getDisplayMetrics().widthPixels * 0.85).toInt()
        val height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog!!.getWindow()?.setLayout(width, height);
    }

    fun showDialog() {
        if (dialog != null) {
            dialog!!.show()
        }

        popupSaveChangesBinding.tvSave.setOnClickListener {
            dialog?.dismiss()
            onClick.onSaveData()
        }

        popupSaveChangesBinding.tvDiscard.setOnClickListener {
            dialog?.dismiss()
        }

    }

}


interface onClick {
    fun onSaveData()
}
