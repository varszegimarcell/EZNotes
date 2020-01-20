package com.vm.eznotes.activities

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.vm.eznotes.R
import com.vm.eznotes.adapters.ToDoListItemAdapter
import com.vm.eznotes.databinding.ActivityViewTodoListBinding
import com.vm.eznotes.models.Notes
import com.vm.eznotes.models.ToDoList
import com.vm.eznotes.models.ToDoListItem

import kotlinx.android.synthetic.main.activity_view_todo_list.*
import kotlinx.android.synthetic.main.activity_view_todo_list.fab
import kotlinx.android.synthetic.main.activity_view_todo_list.toolbar

class ViewTodoListActivity : AppCompatActivity() {

    private lateinit var binding : ActivityViewTodoListBinding
    private var list = ToDoList("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_todo_list)
        val name = intent.getStringExtra("name")
        list = Notes.getNoteItemByName(name) as ToDoList
        binding.list = list
        toolbar.title = list.Name
        setSupportActionBar(toolbar)

        var listView = findViewById<ListView>(R.id.todo_listview)
        listView.adapter = ToDoListItemAdapter(this, list.Items)

        listView.setOnItemClickListener{adapter, view, pos, id ->
            val checkbox = view.findViewById<CheckBox>(R.id.todo_checkbox)
            checkbox.isChecked = !checkbox.isChecked
        }

        add_todolist_item.setOnClickListener{view ->
            list.Items.add(ToDoListItem("New item"))
            listView.adapter = ToDoListItemAdapter(this, list.Items)
            binding.invalidateAll()
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
