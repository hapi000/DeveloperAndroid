

package com.example.android.trackr.compose.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.trackr.data.TaskStatus
import com.example.android.trackr.data.TaskSummary
import com.example.android.trackr.data.User
import com.example.android.trackr.usecase.GetOngoingTaskSummariesUseCase
import com.example.android.trackr.usecase.ToggleTaskStarStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.time.Clock
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val currentUser: User,
    val clock: Clock,
    getOngoingTaskSummariesUseCase: GetOngoingTaskSummariesUseCase,
    private val toggleTaskStarStateUseCase: ToggleTaskStarStateUseCase
) : ViewModel() {

    private val statusExpanded = MutableStateFlow(TaskStatus.values().associateWith { true })

    val statusGroups = combine(
        statusExpanded,
        getOngoingTaskSummariesUseCase(currentUser.id)
    ) { statuses, summaries ->
        statuses.mapValues { (status, expanded) ->
            TaskStatusGroup(
                expanded = expanded,
                summaries = summaries.filter { it.status == status }
            )
        }
    }

    fun toggleTaskStarState(taskId: Long) {
        viewModelScope.launch {
            toggleTaskStarStateUseCase(taskId, currentUser)
        }
    }

    fun toggleStatusExpanded(status: TaskStatus) {
        statusExpanded.value = statusExpanded.value.mapValues { (s, expanded) ->
            if (status == s) !expanded else expanded
        }
    }
}

class TaskStatusGroup(
    val expanded: Boolean,
    val summaries: List<TaskSummary>
)
