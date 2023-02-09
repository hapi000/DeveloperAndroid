

package com.example.android.trackr.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.withTransaction
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.android.trackr.data.SeedData
import com.example.android.trackr.data.User
import com.example.android.trackr.db.AppDatabase
import com.example.android.trackr.db.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.Clock
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private lateinit var appDatabase: AppDatabase
    private lateinit var clock: Clock

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        appDatabase = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "trackr-db"
        )
            .fallbackToDestructiveMigration()
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    GlobalScope.launch(Dispatchers.IO) {
                        insertSeedData()
                    }
                }
            })
            .build()
        return appDatabase
    }

    suspend fun insertSeedData() {
        with(appDatabase) {
            withTransaction {
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

    @Provides
    fun provideCurrentUser(): User {
        // For simplicity of sample, assign first user as current user
        return SeedData.Users[0]
    }

    @Provides
    fun provideTaskDao(appDatabase: AppDatabase): TaskDao {
        return appDatabase.taskDao()
    }

    @Provides
    @Singleton
    fun provideClock(): Clock {
        clock = Clock.systemDefaultZone()
        return clock
    }
}
