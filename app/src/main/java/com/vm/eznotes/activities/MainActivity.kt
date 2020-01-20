package com.vm.eznotes.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.GridView
import android.widget.TextView
import com.vm.eznotes.adapters.NoteItemAdapter
import com.vm.eznotes.models.Notes
import com.vm.eznotes.R
import com.vm.eznotes.models.TextNote
import com.vm.eznotes.models.ToDoList

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        var notesGrid : GridView = findViewById(R.id.NotesGrid)

        notesGrid.adapter = NoteItemAdapter(
            this,
            Notes.Items
        )

        if(Notes.Items.count() > 0){
            var no_notes_message : TextView = findViewById(R.id.no_notes_message)
            no_notes_message.visibility = View.INVISIBLE
        }

        notesGrid.setOnItemClickListener{adapter, view, pos, id ->
            val name = view.findViewById<TextView>(R.id.note_item_name).text
            val item = Notes.getNoteItemByName(name.toString())
            if(item is TextNote){
                val intent = Intent(this, ViewTextNoteActivity::class.java)
                intent.putExtra("name", name.toString())
                startActivity(intent)
            }
            if(item is ToDoList){
                val intent = Intent(this, ViewTodoListActivity::class.java)
                intent.putExtra("name", name.toString())
                startActivity(intent)
            }
        }

        fab.setOnClickListener { view ->
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_about -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
