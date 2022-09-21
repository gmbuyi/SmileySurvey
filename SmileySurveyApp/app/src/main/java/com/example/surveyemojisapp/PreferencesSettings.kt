package com.example.surveyemojisapp


import android.os.Bundle
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat


class PreferencesSettings : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences_reasons)

        val listener = MyOnPreferencesChangeListener()

       for (key in MainActivity.listKey) {
            val pref = preferenceManager.findPreference<EditTextPreference>(key)
            pref?.summary = pref?.text?.uppercase()
            pref?.onPreferenceChangeListener = listener
        }
    }



    class MyOnPreferencesChangeListener : Preference.OnPreferenceChangeListener{

        override fun onPreferenceChange(preference: Preference, newValue: Any?): Boolean {
            preference.summary = newValue.toString()
            return true
        }

    }

}