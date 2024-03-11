package com.mubs.checkpoint3_hsi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mubs.checkpoint3_hsi.model.PersonDataSource
import com.mubs.checkpoint3_hsi.ui.theme.Checkpoint3_hsiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Checkpoint3_hsiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CheckPoint3App()
                }
            }
        }
    }
}

@Composable
fun CheckPoint3App() {
    Column {
        HeaderPersonScreen()
        PersonList(persons = PersonDataSource.personList)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Checkpoint3_hsiTheme {
        CheckPoint3App()
    }
}