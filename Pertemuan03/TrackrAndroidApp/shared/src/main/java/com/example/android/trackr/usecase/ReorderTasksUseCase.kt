

package com.example.android.trackr.usecase

import com.example.android.trackr.data.TaskStatus
import com.example.android.trackr.db.dao.TaskDao
import javax.inject.Inject

class ReorderTasksUseCase @Inject constructor(
    private val taskDao: TaskDao
) {
    suspend operator fun invoke(
        taskId: Long,
        status: TaskStatus,
        currentOrderInCategory: Int,
        targetOrderInCategory: Int
    ) {
        taskDao.reorderTasks(taskId, status, currentOrderInCategory, targetOrderInCategory)
    }
}
