

package com.example.android.trackr.data

import androidx.room.DatabaseView
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import java.time.Instant

@DatabaseView(
    """
        SELECT
            t.id, t.title, t.status, t.dueAt, t.orderInCategory, t.isArchived,
            o.id AS owner_id, o.username AS owner_username, o.avatar AS owner_avatar
        FROM tasks AS t
        INNER JOIN users AS o ON o.id = t.ownerId
    """
)
data class TaskSummary(
    val id: Long,

    val title: String,

    val status: TaskStatus,

    val dueAt: Instant,

    val orderInCategory: Int,

    val isArchived: Boolean = false,

    @Embedded(prefix = "owner_")
    val owner: User,

    @Relation(
        parentColumn = "id",
        entity = Tag::class,
        entityColumn = "id",
        associateBy = Junction(
            value = TaskTag::class,
            parentColumn = "taskId",
            entityColumn = "tagId"
        )
    )
    val tags: List<Tag>,

    val starred: Boolean,
)

@DatabaseView(
    """
        SELECT
            t.id, t.title, t.description, t.status, t.createdAt, t.dueAt, t.orderInCategory,
            t.isArchived,
            o.id AS owner_id, o.username AS owner_username, o.avatar AS owner_avatar,
            c.id AS creator_id, c.username AS creator_username, c.avatar as creator_avatar
        FROM tasks AS t
        INNER JOIN users AS o ON o.id = t.ownerId
        INNER JOIN users AS c ON c.id = t.creatorId
    """
)
data class TaskDetail(

    val id: Long,

    val title: String,

    val description: String,

    val status: TaskStatus,

    val createdAt: Instant,

    val dueAt: Instant,

    val orderInCategory: Int,

    val isArchived: Boolean,

    @Embedded(prefix = "owner_")
    val owner: User,

    @Embedded(prefix = "creator_")
    val creator: User,

    @Relation(
        parentColumn = "id",
        entity = Tag::class,
        entityColumn = "id",
        associateBy = Junction(
            value = TaskTag::class,
            parentColumn = "taskId",
            entityColumn = "tagId"
        )
    )
    val tags: List<Tag>,

    @Relation(
        parentColumn = "id",
        entity = User::class,
        entityColumn = "id",
        associateBy = Junction(
            value = UserTask::class,
            parentColumn = "taskId",
            entityColumn = "userId"
        )
    )
    val starUsers: List<User>
)
