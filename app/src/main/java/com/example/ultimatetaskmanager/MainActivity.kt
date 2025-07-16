package com.example.ultimatetaskmanager

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
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

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val time: String?
)

val schedularList: List<Note> = listOf(
    Note(1, "Schedular 1", "Content 1\n sadjlasjkdlasjdklakjsdlajslkdjalsd", "8:00"),
    Note(2, "Schedular 2", "Content 2", "9:00"),
    Note(3, "Schedular 3", "Content 3", "10:00, June 10"),
    Note(4, "Schedular 4", "Content 4", "11:00"),
    Note(5, "Schedular 5", "Content 5", "12:00"),
    Note(6, "Schedular 6", "Content 6", "13:00"),
    Note(7, "Schedular 7", "Content 7", "14:00")
)
var notesList: List<Note> = listOf(
    Note(1, "Note 1", "Content 1", null),
    Note(2, "Note 2", "Content 2", null),
    Note(3, "Note 3", "Content 3", null),
    Note(4, "Note 4", "Content 4", null),
    Note(5, "Note 5", "Content 5", null),
    Note(6, "Note 6", "Content 6", null),
    Note(7, "Note 7", "Content 7", null)
)

class MainActivity : ComponentActivity() {
//    @OptIn(ExperimentalMaterial3Api::class)
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
fun MainPage(modifier: Modifier = Modifier) {
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
        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth()
                .height(296.dp),
        ) {
            items(
                items = schedularList,
                key = { note -> note.id }
            ){
                note -> NoteListItem( note = note )
            }
        }
        Text(
            text = "Notes",
            fontSize = 24.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
        )

        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth()
                .height(250.dp),
        ) {
            items(
                items = notesList,
                key = { note -> note.id }
            ){
                    note -> NoteListItem( note = note )
            }
        }

    }
}

@Composable
fun NoteListItem(note: Note){
    Card(
        modifier = Modifier.fillMaxSize()
            .padding(8.dp)
            .height(296.dp)
            .width(200.dp)
    ){
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = note.title,
                fontSize = 24.sp,
            )
            Text(
                text = note.content,
                fontSize = 16.sp,
                maxLines = 1
            )
            if(note.time != null){
                Text(
                    text = note.time,
                    fontSize = 16.sp
                )
            }
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