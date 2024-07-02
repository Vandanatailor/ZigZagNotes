package com.example.zigzagnotes.util

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import java.util.Locale

object LocaleHelper {

    private const val PREF_LANGUAGE = "pref_language"

    fun onAttach(context: Context): Context {
        val lang = getPersistedLanguage(context)
        return setLocale(context, lang)
    }

    fun setLocale(context: Context, languageCode: String): Context {
        persistLanguage(context, languageCode)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            updateResources(context, languageCode)
        } else {
            updateResourcesLegacy(context, languageCode)
        }
    }

    private fun persistLanguage(context: Context, language: String) {
        val preferences: SharedPreferences = context.getSharedPreferences("settings",
            Context.MODE_PRIVATE)
        preferences.edit().putString(PREF_LANGUAGE, language).apply()
    }

    fun getPersistedLanguage(context: Context): String {
        val preferences: SharedPreferences = context.getSharedPreferences("settings",
            Context.MODE_PRIVATE)
        return preferences.getString(PREF_LANGUAGE, Locale.getDefault().language) ?:
        Locale.getDefault().language
    }

    private fun updateResources(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(locale)

        return context.createConfigurationContext(configuration)
    }

    private fun updateResourcesLegacy(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val resources = context.resources
        val configuration = Configuration(resources.configuration)
        configuration.locale = locale

        resources.updateConfiguration(configuration, resources.displayMetrics)

        return context
    }

//    private const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"
//    private const val PREFERENCES_NAME = "com.example.zigzagnotes.preferences"
//
//    fun onAttach(context: Context): Context {
//        val lang = getPersistedData(context, Locale.getDefault().language)
//        return setLocale(context, lang)
//    }
//
//    fun setLocale(context: Context, language: String): Context {
//        persist(context, language)
//
//        val locale = Locale(language)
//        Locale.setDefault(locale)
//
//        val resources = context.resources
//        val configuration = Configuration(resources.configuration)
//        configuration.setLocale(locale)
//
//        return context.createConfigurationContext(configuration)
//    }
//
//    private fun persist(context: Context, language: String) {
//        val preferences: SharedPreferences =
//            context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
//        preferences.edit().putString(SELECTED_LANGUAGE, language).apply()
//    }
//
//    private fun getPersistedData(context: Context, defaultLanguage: String): String {
//        val preferences: SharedPreferences = context.getSharedPreferences(
//            PREFERENCES_NAME,
//            Context.MODE_PRIVATE
//        )
//        return preferences.getString(SELECTED_LANGUAGE, defaultLanguage) ?: defaultLanguage
//    }
//
//    fun getCurrentLanguage(context: Context): String {
//        val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
//        return preferences.getString(SELECTED_LANGUAGE, Locale.getDefault().language)
//            ?: Locale.getDefault().language
//    }

}