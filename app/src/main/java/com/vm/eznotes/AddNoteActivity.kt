package com.vm.eznotes

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.vm.eznotes.databinding.ActivityAddNoteBinding

import kotlinx.android.synthetic.main.activity_add_note.*
import java.util.*

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    private val newNote = AddNoteViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note)
        binding.newNote = newNote
        toolbar.setTitle(R.string.new_note_view_title)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val calendar = Calendar.getInstance()

        var year : Int = calendar.get(Calendar.YEAR)
        var month : Int = calendar.get(Calendar.MONTH)
        var day : Int = calendar.get(Calendar.DAY_OF_MONTH)
        var hours : Int = calendar.get(Calendar.HOUR_OF_DAY)
        var minutes : Int = calendar.get(Calendar.MINUTE)

        val button_notification = findViewById<Button>(R.id.button_notification)

        button_notification.setOnClickListener { view ->
            val date_dialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            }, year, month, day)
            date_dialog.show()
            val time_dialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener{view, hours, minutes ->

            }, hours, minutes, true)
            time_dialog.show()
            button_notification.text = year.toString() + "." + month.toString() + "." + day.toString() + " " + hours.toString() + ":" + minutes.toString()
        }
    }

}
