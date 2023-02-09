

package com.example.android.trackr.usecase

import com.example.android.trackr.db.dao.TaskDao
import javax.inject.Inject

/**
 * Archive tasks.
 */
class ArchiveUseCase @Inject constructor(
    private val taskDao: TaskDao
) {
    suspend operator fun invoke(taskIds: List<Long>) {
        taskDao.setIsArchived(taskIds, true)
    }
}
