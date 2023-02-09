

package com.example.android.trackr.ui.edit

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.example.android.trackr.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class TagSelectionDialogFragment : DialogFragment() {

    private val viewModel: TaskEditViewModel by hiltNavGraphViewModels(R.id.nav_task_edit_graph)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val allTags = viewModel.allTags
        val tags = viewModel.tags.value ?: emptyList()
        val checked = allTags.map { tag ->
            tag in tags
        }
        return MaterialAlertDialogBuilder(requireContext())
            .setMultiChoiceItems(
                allTags.map { it.label }.toTypedArray(),
                checked.toBooleanArray()
            ) { _, which, isChecked ->
                if (isChecked) {
                    viewModel.addTag(allTags[which])
                } else {
                    viewModel.removeTag(allTags[which])
                }
            }
            .create()
    }
}
