package com.example.ultimatenotesmanager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class NoteDetailActivity : AppCompatActivity() {

    private lateinit var editTextNoteTitle: EditText
    private lateinit var editTextNoteContent: EditText

    // Переменные для хранения исходного заголовка и содержимого (если заметка существует)
    private var originalTitle: String = ""
    private var originalContent: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_detail)

        editTextNoteTitle = findViewById(R.id.editTextNoteTitle)
        editTextNoteContent = findViewById(R.id.editTextNoteContent)

        // Получаем данные, переданные через Intent
        originalTitle = intent.getStringExtra("note_title") ?: ""
        originalContent = intent.getStringExtra("note_content") ?: ""

        // Если данные переданы, заполняем поля ввода
        if (originalTitle.isNotEmpty() || originalContent.isNotEmpty()) {
            editTextNoteTitle.setText(originalTitle)
            editTextNoteContent.setText(originalContent)
            supportActionBar?.title = "Редактировать заметку" // Меняем заголовок
        } else {
            supportActionBar?.title = "Новая заметка"
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        saveNoteAndExit()
        // Не вызываем super.onBackPressed() здесь, так как saveNoteAndExit() вызывает finish()
    }

    private fun saveNoteAndExit() {
        val currentTitle = editTextNoteTitle.text.toString().trim()
        val currentContent = editTextNoteContent.text.toString().trim()

        // Если заметка не изменилась, просто закрываем Activity без возврата RESULT_OK
        if (currentTitle == originalTitle && currentContent == originalContent) {
            setResult(Activity.RESULT_CANCELED) // Указываем, что изменений не было
            finish()
            return
        }

        // Если заметка изменилась или она новая, возвращаем данные
        val resultIntent = Intent()
        resultIntent.putExtra("note_title", currentTitle)
        resultIntent.putExtra("note_content", currentContent)

        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
}