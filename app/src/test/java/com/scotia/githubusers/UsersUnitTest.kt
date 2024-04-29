package com.scotia.githubusers

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.scotia.githubusers.ui.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UsersUnitTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        // Launch the activity for testing
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        // Finish the activity after testing
        scenario.close()
    }

    @Test
    fun testComposableFunction() {
        // Create a test composable function
        val testComposable: @Composable () -> Unit = {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(text = "Hello, world!")
            }
        }

        // Use the test composable within the activity
        scenario.onActivity { activity ->
            activity.setContent {
                testComposable()
            }
        }

        composeTestRule.onNodeWithText("Hello, world!").assertExists()
    }
}