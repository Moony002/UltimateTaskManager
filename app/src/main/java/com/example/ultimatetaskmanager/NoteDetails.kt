package com.example.ultimatetaskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ultimatetaskmanager.ui.theme.UltimateTaskManagerTheme
import kotlin.math.min


class NoteDetails: ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            UltimateTaskManagerTheme{
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    NoteDetailsPage(
                        modifier = Modifier.padding(innerPadding)
                    )

                }
            }
        }
    }
}

@Composable
fun NoteDetailsPage(modifier: Modifier = Modifier){

    var titleValue by remember { mutableStateOf("") }
    var contentValue by remember { mutableStateOf("")}

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(
//                modifier = Modifier.padding(16.dp),
                onClick = { /*TODO make something*/ }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Go Back Button",
                )
            }
            IconButton(
//                modifier = Modifier.padding(16.dp),
                onClick = {/* TODO make in do something */}
            ) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done Button"
                )
            }
        }

        TextField(
            value = titleValue,
            textStyle = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
            onValueChange = {titleValue = it},
            modifier = Modifier.fillMaxWidth()
                .defaultMinSize(minHeight = 48.dp),
            label = {Text("Title", fontSize = 24.sp, fontWeight = FontWeight.Bold)},
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            thickness = 1.dp,
            color = Color.Black
        )

        TextField(
            value = contentValue,
            textStyle = TextStyle(fontSize = 16.sp),
            onValueChange = {contentValue = it},
            modifier = Modifier.fillMaxSize(),
            label = { Text("Write Here", fontSize = 16.sp) },
            singleLine = false,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

    }
}

@Preview(showBackground = true)
@Composable
fun NoteDetailPagePreview(){
    UltimateTaskManagerTheme {
        NoteDetailsPage()
    }
}