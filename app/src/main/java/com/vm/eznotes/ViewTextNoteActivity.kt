package com.vm.eznotes

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.vm.eznotes.databinding.ActivityViewTextNoteBinding

import kotlinx.android.synthetic.main.activity_view_text_note.*

class ViewTextNoteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityViewTextNoteBinding
    private var note = TextNote("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_text_note)
        val name = intent.getStringExtra("name")
        note = Notes.getNoteItemByName(name) as TextNote
        binding.note = note
        toolbar.title = note.Name
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
