

package com.example.android.trackr.usecase

import com.example.android.trackr.db.dao.TaskDao
import javax.inject.Inject

/**
 * Loads all the existing users.
 */
class LoadUsersUseCase @Inject constructor(
    private val taskDao: TaskDao
) {
    suspend operator fun invoke() = taskDao.loadUsers()
}
