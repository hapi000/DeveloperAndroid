

package com.example.android.trackr.ui.tasks

import com.example.android.trackr.data.Avatar
import com.example.android.trackr.data.TaskStatus
import com.example.android.trackr.data.TaskSummary
import com.example.android.trackr.data.User
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.time.Instant

class ListItemsCreatorTest {

    @Test
    fun execute_whenNoTaskSummaries() {
        val subject = ListItemsCreator(emptyList(), mutableMapOf(TaskStatus.IN_PROGRESS to true))
        val listItems = subject.execute()
        assertThat(listItems).hasSize(1)
        assertThat(listItems[0]).isInstanceOf(ListItem.TypeHeader::class.java)
        val header = (listItems[0] as ListItem.TypeHeader)
        assertThat(header.headerData.count).isEqualTo(0)
        assertThat(header.headerData.taskStatus).isEqualTo(TaskStatus.IN_PROGRESS)
    }

    @Test
    fun execute_whenExpandedState_returnsHeaderAndTaskSummary() {
        val subject = ListItemsCreator(
            listOf(item1),
            mutableMapOf(TaskStatus.IN_PROGRESS to true)
        )
        val listItems = subject.execute()
        assertThat(listItems).hasSize(2)
        assertThat(listItems[0]).isInstanceOf(ListItem.TypeHeader::class.java)
        assertThat(listItems[1]).isInstanceOf(ListItem.TypeTask::class.java)
    }

    @Test
    fun execute_whenCollapsedState_savesCountInHeader() {
        val subject = ListItemsCreator(
            listOf(item1),
            mutableMapOf(TaskStatus.IN_PROGRESS to false)
        )
        val listItems = subject.execute()
        val header = (listItems[0] as ListItem.TypeHeader)
        assertThat(header.headerData.count).isEqualTo(1)
    }

    @Test
    fun execute_returnsTasksInOrder() {
        val subject = ListItemsCreator(
            listOf(item1, item3, item2), // TaskDao gives us these sorted items.
            mutableMapOf(TaskStatus.IN_PROGRESS to true)
        )

        val listItems = subject.execute()
        assertThat((listItems[1] as ListItem.TypeTask).taskSummary).isEqualTo(item1)
        assertThat((listItems[2] as ListItem.TypeTask).taskSummary).isEqualTo(item3)
        assertThat((listItems[3] as ListItem.TypeTask).taskSummary).isEqualTo(item2)
    }

    companion object {
        private val user = User(1, "user", Avatar.DEFAULT_USER)
        val item1 = TaskSummary(
            id = 1,
            title = "task list item 1",
            dueAt = Instant.now(),
            owner = user,
            status = TaskStatus.IN_PROGRESS,
            tags = emptyList(),
            orderInCategory = 1,
            starred = false,
        )

        val item2 = TaskSummary(
            id = 2,
            title = "task list item 2",
            dueAt = Instant.now(),
            owner = user,
            status = TaskStatus.IN_PROGRESS,
            tags = emptyList(),
            orderInCategory = 3, // Note: out of order
            starred = false,
        )

        val item3 = TaskSummary(
            id = 3,
            title = "task list item 3",
            dueAt = Instant.now(),
            owner = user,
            status = TaskStatus.IN_PROGRESS,
            tags = emptyList(),
            orderInCategory = 2, // Note: out of order
            starred = false,
        )
    }
}
