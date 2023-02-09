

package com.example.android.trackr.usecase

import com.example.android.trackr.data.TaskDetail
import com.example.android.trackr.db.dao.TaskDao
import javax.inject.Inject

/**
 * Loads a [TaskDetail] specified by the task ID.
 */
class LoadTaskDetailUseCase @Inject constructor(
    private val taskDao: TaskDao
) {
    suspend operator fun invoke(taskId: Long) = taskDao.loadTaskDetailById(taskId)
}
