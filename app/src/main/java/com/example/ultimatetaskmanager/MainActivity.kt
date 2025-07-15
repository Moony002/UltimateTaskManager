package com.example.ultimatetaskmanager

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ultimatetaskmanager.ui.theme.UltimateTaskManagerTheme

data class Note(val id: Int,
                val title: String,
                val content: String
)

val schedularList: List<Note> = listOf(
    Note(1, "Title 1", "Content 1"),
    Note(2, "Title 2", "Content 2"),
    Note(3, "Title 3", "Content 3"),
    Note(4, "Title 4", "Content 4")
)
var notesList: List<Note> = listOf()

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UltimateTaskManagerTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {  }
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = "Add Note")
                        }
                    },
                    floatingActionButtonPosition = FabPosition.Center,
                ) { innerPadding ->
                    MainPage(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainPage(modifier: Modifier = Modifier,
//             schedularNotes: List<Note> = schedularList,
//             notes: List<Note> = notesList
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Notes & Schedular",
            fontSize = 32.sp,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Schedular",
            fontSize = 24.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth()
        ){
            items(
                items = schedularList,
                key = {note -> note.id}
            ){
                note ->
                SchedularItem(note = note)
            }
        }
        Text(
            text = "Notes",
            fontSize = 24.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxSize()
                .padding(16.dp),
        )
        LazyRow {

        }
    }
}

@Composable
fun SchedularItem(note: Note) {
    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = note.title,
                fontSize = 20.sp,
            )
            Text(
                text = note.content,
                fontSize = 16.sp,
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun MainPagePreview() {
    UltimateTaskManagerTheme {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { }) {
                    Icon(Icons.Filled.Add, "Add")
                }
            },
            floatingActionButtonPosition = FabPosition.Center
        ) {
            MainPage()
        }
    }
}