

package com.example.android.trackr.ui.edit

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.example.android.trackr.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class DiscardConfirmationDialogFragment : DialogFragment() {

    private val viewModel: TaskEditViewModel by hiltNavGraphViewModels(R.id.nav_task_edit_graph)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext(), R.style.MaterialAlertDialog)
            .setTitle(R.string.discard_confirmation)
            .setPositiveButton(R.string.discard) { _, _ ->
                dismiss()
                viewModel.discardChanges()
            }
            .setNegativeButton(R.string.keep_editing) { _, _ ->
                dismiss()
            }
            .create()
    }
}
