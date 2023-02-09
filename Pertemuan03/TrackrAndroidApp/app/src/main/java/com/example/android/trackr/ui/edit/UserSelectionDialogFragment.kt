

package com.example.android.trackr.ui.edit

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.example.android.trackr.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class UserSelectionDialogFragment : DialogFragment() {

    private val viewModel: TaskEditViewModel by hiltNavGraphViewModels(R.id.nav_task_edit_graph)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val users = viewModel.users
        return MaterialAlertDialogBuilder(requireContext())
            .setItems(users.map { it.username }.toTypedArray()) { _, which ->
                viewModel.updateOwner(users[which])
            }
            .create()
    }
}
