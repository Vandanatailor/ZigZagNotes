package com.example.zigzagnotes.ui.dialog

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.core.app.ActivityCompat.recreate
import androidx.core.content.ContextCompat
import com.example.zigzagnotes.R
import com.example.zigzagnotes.databinding.PopupDeleteBinding
import com.example.zigzagnotes.databinding.PopupSaveChangesBinding
import com.example.zigzagnotes.databinding.PopupSelectLanguageBinding
import com.example.zigzagnotes.util.LocaleHelper

class SelectLanguage {
    private var dialog: Dialog? = null
    var context: Context? = null
    lateinit var binding: PopupSelectLanguageBinding
    lateinit var onClick: onClickable
    //private lateinit var language: String
    //private var language: String = LocaleHelper.getCurrentLanguage()

    fun onSelectLangDialog(context: Context?, onClick: onClickable) {
        this.context = context
        dialog = Dialog(context!!)
        this.onClick = onClick
        dialog!!.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog!!.setCancelable(true)
        binding = PopupSelectLanguageBinding.inflate(LayoutInflater.from(context))
        dialog!!.setContentView(binding.getRoot())
        val width = (context.getResources().getDisplayMetrics().widthPixels * 0.85).toInt()
        val height = WindowManager.LayoutParams.WRAP_CONTENT
        dialog!!.getWindow()?.setLayout(width, height);

//        binding.tvHindi.setOnClickListener {
//            selectLanguage("hi",context)
//        }
//        binding.tvEnglish.setOnClickListener {
//            selectLanguage("en",context)
//        }
//        updateLanguageUI(context)
    }

//    private fun selectLanguage(languageCode: String,context: Context) {
//        language = languageCode
//        updateLanguageUI(context)
//    }

//    private fun updateLanguageUI(context: Context) {
//        if (language == "hi") {
//            binding.tvHindi.backgroundTintList = ContextCompat.getColorStateList(context, R.color.green)
//            binding.tvEnglish.backgroundTintList = null
//        } else {
//            binding.tvEnglish.backgroundTintList = ContextCompat.getColorStateList(context, R.color.green)
//            binding.tvHindi.backgroundTintList = null
//        }
//    }

    fun showDialog() {
        if (dialog != null) {
            dialog!!.show()
        }
//        binding.tvChanged.setOnClickListener {
//            dialog?.dismiss()
//            LocaleHelper.setLocale(context!!, language)
//            onClick.onLanguageSelect()
//        }
    }
}

    interface onClickable {
        fun onLanguageSelect()
    }
