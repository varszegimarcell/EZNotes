package com.vm.eznotes.activities

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.vm.eznotes.*
import com.vm.eznotes.models.Notes
import com.vm.eznotes.models.TextNote
import com.vm.eznotes.models.ToDoList
import com.vm.eznotes.viewModels.AddNoteViewModel
import com.vm.eznotes.databinding.ActivityAddNoteBinding

import kotlinx.android.synthetic.main.activity_add_note.*
import java.util.*

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding

    private val newnote = AddNoteViewModel()
    private var notificationDate : String = ""
    private var alarmDate : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_add_note
        )
        binding.newnote = newnote

        toolbar.setTitle(R.string.new_note_view_title)
        setSupportActionBar(toolbar)

        val checkbox_notification = findViewById<CheckBox>(R.id.checkBox_notification)

        checkbox_notification.setOnClickListener{ viev ->
            binding.invalidateAll()
        }

        val checkbox_alarm = findViewById<CheckBox>(R.id.checkBox_alarm)

        checkbox_alarm.setOnClickListener{ viev ->
            binding.invalidateAll()
        }

        val c = Calendar.getInstance()
        var year = c.get(Calendar.YEAR)
        var month = c.get(Calendar.MONTH)
        var day = c.get(Calendar.DAY_OF_MONTH)
        var hour = c.get(Calendar.HOUR_OF_DAY)
        var minute = c.get(Calendar.MINUTE)

        var nyear = 0
        var nmonth = 0
        var nday = 0
        var nhour = 0
        var nminute = 0

        var ayear = 0
        var amonth = 0
        var aday = 0
        var ahour = 0
        var aminute = 0


        val button_notification = findViewById<Button>(R.id.button_notification)

        button_notification.setOnClickListener { view ->
            val date_dialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, day ->
                val time_dialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener{view, hour, minute ->
                    notificationDate = year.toString() + "." + (month + 1).toString() + "." + day.toString() + ". " + hour.toString() + ":" + minute.toString()
                    button_notification.text = notificationDate
                    nyear = year
                    nmonth = month
                    nday = day
                    nhour = hour
                    nminute = minute
                }, hour,minute, true)
                time_dialog.show()
            }, year, month, day)
            date_dialog.show()
        }

        val button_alarm = findViewById<Button>(R.id.button_alarm)

        button_alarm.setOnClickListener { view ->
            val date_dialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, day ->
                val time_dialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener{view, hour, minute ->
                    alarmDate = year.toString() + "." + (month + 1).toString() + "." + day.toString() + ". " + hour.toString() + ":" + minute.toString()
                    button_alarm.text = alarmDate
                    ayear = year
                    amonth = month
                    aday = day
                    ahour = hour
                    aminute = minute
                }, hour, minute, true)
                time_dialog.show()
            }, year, month, day)
            date_dialog.show()
        }

        fab.setOnClickListener { view ->
            if(newnote.name != null && newnote.name.isNotEmpty()){
                if(!Notes.isNameAlreadyInUse(newnote.name)){
                    if(newnote.istextnote){
                        var note = TextNote(newnote.name)
                        if(newnote.isnotification) note.notificationDate = Date( nyear, nmonth, nday, nhour, nminute)
                        if(newnote.isalarm) note.alarmDate = Date(ayear, amonth, aday, ahour, aminute)
                        Notes.Items.add(note)
                    }
                    if(newnote.istodolist){
                        var note = ToDoList(newnote.name)
                        if(newnote.isnotification) note.notificationDate = Date( nyear, nmonth, nday, nhour, nminute)
                        if(newnote.isalarm) note.alarmDate = Date(ayear, amonth, aday, ahour, aminute)
                        Notes.Items.add(note)
                    }
                    Toast.makeText(applicationContext,newnote.name + resources.getString(
                        R.string.created_successfully
                    ),Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Snackbar.make(view, resources.getString(R.string.name_already_in_use), Snackbar.LENGTH_LONG).show()
                }
            }
            else{
                Snackbar.make(view, resources.getString(R.string.no_name_entered), Snackbar.LENGTH_LONG).show()
            }
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}
