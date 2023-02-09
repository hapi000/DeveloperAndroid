

package com.example.android.trackr.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.commitNow
import com.example.android.trackr.R
import com.example.android.trackr.databinding.SettingsFragmentBinding
import com.example.android.trackr.ui.dataBindings

class SettingsFragment : Fragment(R.layout.settings_fragment) {

    private val binding by dataBindings(SettingsFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // We need to access binding once in order for Data Binding to bind the views.
        binding.root
        if (childFragmentManager.findFragmentById(R.id.preference) == null) {
            childFragmentManager.commitNow {
                replace(R.id.preference, SettingsPreferenceFragment())
            }
        }
    }
}
