

package com.example.android.trackr.usecase

import com.example.android.trackr.db.dao.TaskDao
import javax.inject.Inject

/**
 * Loads all the existing tags as a list.
 */
class LoadTagsUseCase @Inject constructor(
    private val taskDao: TaskDao
) {
    suspend operator fun invoke() = taskDao.loadTags()
}
