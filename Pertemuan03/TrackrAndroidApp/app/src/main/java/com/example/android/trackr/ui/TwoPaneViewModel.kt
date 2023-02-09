

package com.example.android.trackr.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class TwoPaneViewModel @Inject constructor() : ViewModel() {

    val isTwoPane = MutableStateFlow(false)

    private val detailPaneUpEventChannel = Channel<Unit>(capacity = Channel.CONFLATED)
    val detailPaneUpEvents = detailPaneUpEventChannel.receiveAsFlow()

    private val editTaskEventChannel = Channel<Long>(capacity = Channel.CONFLATED)
    val editTaskEvents = editTaskEventChannel.receiveAsFlow()

    fun onDetailPaneNavigateUp() {
        detailPaneUpEventChannel.trySend(Unit)
    }

    fun onEditTask(taskId: Long) {
        editTaskEventChannel.trySend(taskId)
    }
}
