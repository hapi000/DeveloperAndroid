

package com.example.android.trackr.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.android.trackr.data.Tag
import com.example.android.trackr.data.Task
import com.example.android.trackr.data.TaskDetail
import com.example.android.trackr.data.TaskSummary
import com.example.android.trackr.data.TaskTag
import com.example.android.trackr.data.User
import com.example.android.trackr.data.UserTask
import com.example.android.trackr.db.dao.TaskDao

@Database(
    entities = [Task::class, Tag::class, User::class, TaskTag::class, UserTask::class],
    views = [TaskSummary::class, TaskDetail::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(AppDatabaseTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}
