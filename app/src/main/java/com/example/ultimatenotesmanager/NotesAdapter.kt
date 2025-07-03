package com.example.ultimatenotesmanager

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(
    private val notes: MutableList<Note>,
    private val onItemClick: (Note, Int) -> Unit // Добавляем колбэк для клика
) :
    RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNoteTitle: TextView = itemView.findViewById(R.id.textViewNoteTitle)
        val textViewNoteContent: TextView = itemView.findViewById(R.id.textViewNoteContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.textViewNoteTitle.text = note.title
        holder.textViewNoteContent.text = note.content

        // Устанавливаем слушатель нажатий для всего элемента списка
        holder.itemView.setOnClickListener {
            onItemClick.invoke(note, position) // Вызываем колбэк с заметкой и её позицией
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun addNote(note: Note) {
        notes.add(0, note) // Добавляем новую заметку в начало списка
        notifyItemInserted(0) // Уведомляем адаптер об изменении
    }

    // Метод для обновления заметки (понадобится для редактирования)
    fun updateNote(position: Int, updatedNote: Note) {
        if (position >= 0 && position < notes.size) {
            notes[position] = updatedNote
            notifyItemChanged(position)
        }
    }
}