package com.vm.eznotes.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.vm.eznotes.viewModels.EditTextNoteViewModel
import com.vm.eznotes.fragments.EditDateDialogFragment
import com.vm.eznotes.R
import com.vm.eznotes.databinding.ActivityEditTextNoteBinding
import com.vm.eznotes.models.Notes
import com.vm.eznotes.models.TextNote
import com.vm.eznotes.viewModels.EditDateDialogViewModel

import kotlinx.android.synthetic.main.activity_edit_text_note.*
import java.util.*

class EditTextNoteActivity : AppCompatActivity(){

    private var note = TextNote("")
    private var editedNote = EditTextNoteViewModel()
    private var editedDate = EditDateDialogViewModel()
    private var originalDate = EditDateDialogViewModel()
    private lateinit var binding : ActivityEditTextNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_text_note)
        toolbar.setTitle(R.string.title_activity_edit_text_note)
        binding.editnote = editedNote

        val name = intent.getStringExtra("name")
        note = Notes.getNoteItemByName(name) as TextNote
        editedNote.text = note.Text
        if(note.alarmDate != null){
            originalDate.isalarm = true
            originalDate.ayear = note.alarmDate!!.year
            originalDate.amonth = note.alarmDate!!.month
            originalDate.aday  = note.alarmDate!!.day
            originalDate.ahour = note.alarmDate!!.hours
            originalDate.aminute = note.alarmDate!!.minutes
        }
        if(note.notificationDate != null){
            originalDate.isnotification = true
            originalDate.nyear = note.notificationDate!!.year
            originalDate.nmonth = note.notificationDate!!.month
            originalDate.nday  = note.notificationDate!!.day
            originalDate.nhour = note.notificationDate!!.hours
            originalDate.nminute = note.notificationDate!!.minutes
        }

        setSupportActionBar(toolbar)

        fab_alarm.setOnClickListener { view ->
            val dialog = EditDateDialogFragment(originalDate)
            dialog.show(supportFragmentManager, "EditDate")
        }

        fab_save.setOnClickListener { view ->
            if(editedDate.isnotification) Notes.getNoteItemByName(note.Name).notificationDate = Date(editedDate.nyear, editedDate.nmonth, editedDate.nday, editedDate.nhour, editedDate.nminute)
            else Notes.getNoteItemByName(note.Name).notificationDate = null
            if(editedDate.isalarm) Notes.getNoteItemByName(note.Name).alarmDate = Date(editedDate.ayear, editedDate.amonth, editedDate.aday, editedDate.ahour, editedDate.aminute)
            else Notes.getNoteItemByName(note.Name).alarmDate = null
            (Notes.getNoteItemByName(note.Name) as TextNote).Text = editedNote.text

            val intent = Intent(this, ViewTextNoteActivity::class.java)
            intent.putExtra("name", name.toString())
            startActivity(intent)
            finish()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun getFragmentResult(result : EditDateDialogViewModel){
        editedDate = result
    }

}
