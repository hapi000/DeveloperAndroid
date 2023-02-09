

package com.example.android.trackr.usecase

import com.example.android.trackr.data.TaskStatus
import com.example.android.trackr.db.dao.TaskDao
import javax.inject.Inject

/**
 * Updates the task status of the tasks specified by the task IDs.
 */
class UpdateTaskStatusUseCase @Inject constructor(
    private val taskDao: TaskDao
) {
    suspend operator fun invoke(taskIds: List<Long>, status: TaskStatus) {
        taskDao.updateTaskStatus(taskIds, status)
    }
}
