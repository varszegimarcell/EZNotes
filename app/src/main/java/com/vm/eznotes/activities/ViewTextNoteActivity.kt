package com.vm.eznotes.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.vm.eznotes.models.Notes
import com.vm.eznotes.R
import com.vm.eznotes.models.TextNote
import com.vm.eznotes.databinding.ActivityViewTextNoteBinding

import kotlinx.android.synthetic.main.activity_view_text_note.*

class ViewTextNoteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityViewTextNoteBinding
    private var note = TextNote("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_view_text_note
        )
        val name = intent.getStringExtra("name")
        note = Notes.getNoteItemByName(name) as TextNote
        binding.note = note
        toolbar.title = note.Name
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val intent = Intent(this, EditTextNoteActivity::class.java)
            intent.putExtra("name", name.toString())
            startActivity(intent)
            finish()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
