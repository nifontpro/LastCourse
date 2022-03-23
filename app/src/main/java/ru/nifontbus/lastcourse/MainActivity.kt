package ru.nifontbus.lastcourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.nifontbus.lastcourse.navigation.Route
import ru.nifontbus.lastcourse.ui.theme.LastCourseTheme
import ru.nifontbus.les1_presenter.Lesson1Screen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LastCourseTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.MAIN
                    ) {
                        composable(Route.MAIN) {
                            MainScreen(
                                onLesson1 = {
                                    navController.navigate(Route.LESSON1)
                                }
                            )
                        }
                        composable(Route.LESSON1) {
                            Lesson1Screen()
                        }
                    }
                }
            }
        }
    }
}