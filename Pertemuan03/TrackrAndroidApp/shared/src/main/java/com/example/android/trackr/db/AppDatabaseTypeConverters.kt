

package com.example.android.trackr.db

import androidx.room.TypeConverter
import com.example.android.trackr.data.Avatar
import com.example.android.trackr.data.TagColor
import com.example.android.trackr.data.TaskStatus
import java.time.Instant

/**
 * Adds converters for custom types for working with database column values.
 */
class AppDatabaseTypeConverters {

    @TypeConverter
    fun instantToLong(value: Instant): Long {
        return value.toEpochMilli()
    }

    @TypeConverter
    fun longToInstant(value: Long): Instant {
        return Instant.ofEpochMilli(value)
    }

    @TypeConverter
    fun taskStatusToInt(taskStatus: TaskStatus?): Int? {
        return taskStatus?.key
    }

    @TypeConverter
    fun intToTaskStatus(int: Int): TaskStatus? {
        return TaskStatus.fromKey(int)
    }

    @TypeConverter
    fun tagColorToString(color: TagColor?): String? {
        return color?.name
    }

    @TypeConverter
    fun stringToTagColor(string: String): TagColor {
        return TagColor.valueOf(string)
    }

    @TypeConverter
    fun avatarToString(avatar: Avatar): String {
        return avatar.name
    }

    @TypeConverter
    fun stringToAvatar(name: String): Avatar? {
        return Avatar.values().firstOrNull{ it.name == name}
    }
}