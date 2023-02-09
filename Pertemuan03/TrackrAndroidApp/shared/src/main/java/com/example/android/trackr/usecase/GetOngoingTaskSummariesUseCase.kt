

package com.example.android.trackr.usecase

import com.example.android.trackr.db.dao.TaskDao
import javax.inject.Inject

class GetOngoingTaskSummariesUseCase @Inject constructor(
    private val taskDao: TaskDao
) {
    operator fun invoke(userId: Long) = taskDao.getOngoingTaskSummaries(userId)
}
