

package com.example.android.trackr.compose.ui.detail

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor(

): ViewModel() {

    var taskId: Long = 0L
}
