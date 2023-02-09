

package com.example.android.trackr.usecase

import com.example.android.trackr.data.TaskDetail
import com.example.android.trackr.db.dao.TaskDao
import javax.inject.Inject

/**
 * Saves the specified [TaskDetail].
 */
class SaveTaskDetailUseCase @Inject constructor(
    private val taskDao: TaskDao
) {
    suspend operator fun invoke(detail: TaskDetail, topOrderInCategory: Boolean) {
        taskDao.saveTaskDetail(detail, topOrderInCategory)
    }
}
