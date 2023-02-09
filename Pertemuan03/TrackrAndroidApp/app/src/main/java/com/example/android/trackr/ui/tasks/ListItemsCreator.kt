

package com.example.android.trackr.ui.tasks

import com.example.android.trackr.data.TaskStatus
import com.example.android.trackr.data.TaskSummary


/**
 * Combines the results of [expandedStatesMap] and [taskSummaries] and returns a list of
 * [ListItem]s.
 * @param taskSummaries List of [TaskSummary]s. The list is expected to be sorted by its status and
 * order.
 * @param expandedStatesMap A [TaskStatus] to [Boolean] map, which determines the
 * collapsed/expanded state of a category of [TaskSummary]s
  */
class ListItemsCreator(
    private val taskSummaries: List<TaskSummary>,
    private val expandedStatesMap: Map<TaskStatus, Boolean>
) {
    fun execute(): List<ListItem> {
        val itemsToSubmit = mutableListOf<ListItem>()
        // `groupBy` preserves the order within `taskSummaries`.
        val statusToItemsMap = taskSummaries.groupBy { it.status }
        expandedStatesMap.forEach { (status, isExpanded) ->
            val sublist: List<TaskSummary>? = statusToItemsMap[status]
            itemsToSubmit.add(
                ListItem.TypeHeader(
                    HeaderData(
                        count = sublist?.size ?: 0,
                        taskStatus = status,
                        expanded = isExpanded
                    ),
                )
            )
            if (isExpanded && sublist != null) {
                sublist.map { ListItem.TypeTask(it) }.toCollection(itemsToSubmit)
            }
        }
        return itemsToSubmit
    }
}
