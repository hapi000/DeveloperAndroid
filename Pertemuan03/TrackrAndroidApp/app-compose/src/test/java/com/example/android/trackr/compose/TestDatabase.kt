

package com.example.android.trackr.compose

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.android.trackr.data.SeedData
import com.example.android.trackr.db.AppDatabase
import kotlinx.coroutines.runBlocking

fun createTestDatabase(): AppDatabase {
    return Room
        .inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        )
        .allowMainThreadQueries()
        .build()
        .apply {
            runBlocking {
                with(taskDao()) {
                    insertUsers(SeedData.Users)
                    insertTags(SeedData.Tags)
                    insertTasks(SeedData.Tasks)
                    insertTaskTags(SeedData.TaskTags)
                    insertUserTasks(SeedData.UserTasks)
                }
            }
        }
}
