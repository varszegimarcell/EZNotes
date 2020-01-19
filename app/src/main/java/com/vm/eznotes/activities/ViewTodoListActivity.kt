package com.vm.eznotes.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.vm.eznotes.R
import com.vm.eznotes.adapters.ToDoListItemAdapter
import com.vm.eznotes.databinding.ActivityViewTextNoteBinding
import com.vm.eznotes.databinding.ActivityViewTodoListBinding
import com.vm.eznotes.models.Notes
import com.vm.eznotes.models.TextNote
import com.vm.eznotes.models.ToDoList
import kotlinx.android.synthetic.main.activity_view_text_note.*

import kotlinx.android.synthetic.main.activity_view_todo_list.*
import kotlinx.android.synthetic.main.activity_view_todo_list.fab
import kotlinx.android.synthetic.main.activity_view_todo_list.toolbar

class ViewTodoListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityViewTodoListBinding
    private var list = ToDoList("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_view_text_note
        )
        val name = intent.getStringExtra("name")
        list = Notes.getNoteItemByName(name) as ToDoList
        binding.list = list
        toolbar.title = list.Name
        setSupportActionBar(toolbar)

        var listView = findViewById<ListView>(R.id.todo_listview)
        listView.adapter = ToDoListItemAdapter(this, list.Items)

        listView.setOnItemClickListener{adapter, view, pos, id ->
             
        }

        fab.setOnClickListener { view ->
            val intent = Intent(this, EditTextNoteActivity::class.java)
            intent.putExtra("name", name.toString())
            startActivity(intent)
            finish()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
