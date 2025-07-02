package com.example.ultimatenotesmanager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var notesAdapter: NotesAdapter
    private val notesList = mutableListOf<Note>()

    private lateinit var noteDetailLauncher: ActivityResultLauncher<Intent>
    private var currentNotePosition: Int = -1 // Для отслеживания редактируемой заметки

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerViewNotes: RecyclerView = findViewById(R.id.recyclerViewNotes)
        val buttonAddNote: FloatingActionButton = findViewById(R.id.buttonAddNote)

        // Инициализируем ActivityResultLauncher для NoteDetailActivity
        noteDetailLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val noteTitle = data?.getStringExtra("note_title") ?: ""
                val noteContent = data?.getStringExtra("note_content") ?: ""

                // Проверяем, была ли это новая заметка или редактирование существующей
                if (currentNotePosition == -1) { // Это новая заметка
                    if (noteTitle.isNotEmpty() || noteContent.isNotEmpty()) {
                        val newNote = Note(noteTitle, noteContent)
                        notesAdapter.addNote(newNote)
                    }
                } else { // Это редактирование существующей заметки
                    if (noteTitle.isNotEmpty() || noteContent.isNotEmpty()) { // Если заметка не стала пустой
                        val updatedNote = Note(noteTitle, noteContent)
                        notesAdapter.updateNote(currentNotePosition, updatedNote)
                    } else {
                        // Здесь можно добавить логику удаления заметки, если она стала пустой
                        // Например: notesList.removeAt(currentNotePosition) и notifyItemRemoved
                        // Пока что просто игнорируем, если оба поля пустые при редактировании
                    }
                }
            }
            currentNotePosition = -1 // Сбрасываем позицию после обработки
        }

        // Настройка RecyclerView с передачей колбэка для клика
        notesAdapter = NotesAdapter(notesList) { note, position ->
            // Это наш колбэк, который вызывается при клике на заметку
            currentNotePosition = position // Запоминаем позицию заметки для редактирования

            val intent = Intent(this, NoteDetailActivity::class.java).apply {
                putExtra("note_title", note.title)
                putExtra("note_content", note.content)
            }
            noteDetailLauncher.launch(intent) // Запускаем NoteDetailActivity
        }
        recyclerViewNotes.layoutManager = LinearLayoutManager(this)
        recyclerViewNotes.adapter = notesAdapter

        // Обработчик нажатия на кнопку добавления заметки
        buttonAddNote.setOnClickListener {
            currentNotePosition = -1 // Сбрасываем позицию, так как это новая заметка
            val intent = Intent(this, NoteDetailActivity::class.java)
            noteDetailLauncher.launch(intent)
        }
    }
}