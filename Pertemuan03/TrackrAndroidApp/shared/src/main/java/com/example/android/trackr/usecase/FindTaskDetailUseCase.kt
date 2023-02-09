

package com.example.android.trackr.usecase

import com.example.android.trackr.db.dao.TaskDao
import javax.inject.Inject

class FindTaskDetailUseCase @Inject constructor(
    private val taskDao: TaskDao
) {
    operator fun invoke(taskId: Long) = taskDao.findTaskDetailById(taskId)
}
