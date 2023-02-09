

package com.example.android.trackr.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.android.trackr.R
import com.example.android.trackr.data.TaskDetail
import com.example.android.trackr.databinding.TaskDetailFragmentBinding
import com.example.android.trackr.ui.TwoPaneViewModel
import com.example.android.trackr.ui.dataBindings
import com.example.android.trackr.ui.utils.repeatWithViewLifecycle
import com.example.android.trackr.utils.DateTimeUtils
import com.google.android.material.button.MaterialButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.Clock
import javax.inject.Inject

@AndroidEntryPoint
class TaskDetailFragment : Fragment(R.layout.task_detail_fragment) {

    private val taskDetailViewModel: TaskDetailViewModel by viewModels()
    private val twoPaneViewModel: TwoPaneViewModel by activityViewModels()

    private val args: TaskDetailFragmentArgs by navArgs()
    private val binding by dataBindings(TaskDetailFragmentBinding::bind)

    @Inject
    lateinit var clock: Clock

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskDetailViewModel.taskId.value = args.taskId
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = taskDetailViewModel
        binding.clock = clock

        binding.toolbar.setNavigationOnClickListener {
            twoPaneViewModel.onDetailPaneNavigateUp()
        }
        binding.toolbar.menu.findItem(R.id.action_edit).apply {
            val button = actionView as MaterialButton
            button.text = title
            button.icon = icon
            button.setOnClickListener {
                twoPaneViewModel.onEditTask(args.taskId)
            }
        }

        repeatWithViewLifecycle {
            launch {
                taskDetailViewModel.detail.collect {
                    updateContentDescriptions(it)
                }
            }
            launch {
                twoPaneViewModel.isTwoPane.collect { isTwoPane ->
                    if (isTwoPane) {
                        binding.toolbar.navigationIcon = null
                    } else {
                        binding.toolbar.setNavigationIcon(R.drawable.ic_chevron_start)
                    }
                }
            }
        }

        binding.edit?.setOnClickListener {
            twoPaneViewModel.onEditTask(args.taskId)
        }

        binding.star.setOnClickListener {
            taskDetailViewModel.toggleTaskStarState()
        }
    }

    private fun updateContentDescriptions(taskDetail: TaskDetail?) {
        taskDetail?.let {
            binding.dueAt.contentDescription = resources.getString(
                R.string.due_date_with_value,
                DateTimeUtils.formattedDate(resources, it.dueAt, clock)
            )

            binding.createdAt.contentDescription = resources.getString(
                R.string.creation_date_with_value,
                DateTimeUtils.formattedDate(resources, it.createdAt, clock)
            )

            binding.owner.contentDescription =
                resources.getString(R.string.owner_with_value, it.owner.username)
            binding.creator.contentDescription =
                resources.getString(R.string.creator_with_value, it.creator.username)
        }
    }
}
