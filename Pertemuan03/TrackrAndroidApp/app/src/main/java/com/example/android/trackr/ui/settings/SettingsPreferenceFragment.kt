

package com.example.android.trackr.ui.settings

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.android.trackr.R

// TODO: Consider using Database to store dark mode preference instead of SharedPref.
class SettingsPreferenceFragment : PreferenceFragmentCompat(), Preference.OnPreferenceChangeListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val darkModePref =
            preferenceManager.findPreference<Preference>(resources.getString(R.string.dark_mode_key))
        darkModePref?.onPreferenceChangeListener = this

        return super.onCreateView(inflater, container, savedInstanceState)?.apply {
            when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                Configuration.UI_MODE_NIGHT_NO -> {
                    setBackgroundColor(resources.getColor(R.color.trackr_white_50, context.theme))
                }
                Configuration.UI_MODE_NIGHT_YES -> {
                    setBackgroundColor(resources.getColor(R.color.trackr_blue_700, context.theme))
                }
            }
        }
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }

    private fun refreshDarkModePreference(newValue: String) {
        val enabled = resources.getString(R.string.enabled_value)
        val disabled = resources.getString(R.string.disabled_value)
        val default = resources.getString(R.string.system_default_value)

        when (newValue) {
            enabled -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            disabled -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            default -> {
                when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                    Configuration.UI_MODE_NIGHT_NO -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                    Configuration.UI_MODE_NIGHT_YES -> {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                }
            }
        }
    }

    override fun onPreferenceChange(preference: Preference?, newValue: Any?): Boolean {
        refreshDarkModePreference(newValue as String)
        return true
    }
}