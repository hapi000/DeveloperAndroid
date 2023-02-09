

package com.example.android.trackr.usecase

import androidx.room.withTransaction
import com.example.android.trackr.data.User
import com.example.android.trackr.data.UserTask
import com.example.android.trackr.db.AppDatabase
import com.example.android.trackr.db.dao.TaskDao
import javax.inject.Inject

/**
 * Toggles the star state for the task.
 */
class ToggleTaskStarStateUseCase @Inject constructor(
    private val db: AppDatabase,
    private val taskDao: TaskDao = db.taskDao()
) {
    /**
     * Toggles the star state for the task.
     */
    suspend operator fun invoke(taskId: Long, currentUser: User) {
        db.withTransaction {
            val userTask = taskDao.getUserTask(taskId, currentUser.id)
            if (userTask != null) {
                taskDao.deleteUserTasks(listOf(userTask))
            } else {
                taskDao.insertUserTasks(listOf(UserTask(userId = currentUser.id, taskId = taskId)))
            }
        }
    }
}
