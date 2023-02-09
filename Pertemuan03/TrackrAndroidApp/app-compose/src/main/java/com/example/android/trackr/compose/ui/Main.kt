

package com.example.android.trackr.compose.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.android.trackr.compose.ui.detail.TaskDetail
import com.example.android.trackr.compose.ui.detail.TaskDetailViewModel
import com.example.android.trackr.compose.ui.tasks.Tasks
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun Main() {
    TrackrTheme {
        ProvideWindowInsets {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "tasks",
            ) {
                composable("tasks") {
                    Tasks(
                        hiltViewModel(),
                        onTaskClick = { taskId ->
                            navController.navigate("detail/$taskId")
                        },
                        onAddTaskClick = {
                            // TODO
                        },
                        onArchiveClick = {
                            // TODO
                        },
                        onSettingsClick = {
                            // TODO
                        }
                    )
                }
                composable(
                    route = "detail/{taskId}",
                    arguments = listOf(navArgument("taskId") { type = NavType.LongType })
                ) { backStackEntry ->
                    TaskDetail(
                        hiltViewModel<TaskDetailViewModel>().apply {
                            taskId = backStackEntry.arguments?.getLong("taskId") ?: 0L
                        }
                    )
                }
            }
        }
    }
}
