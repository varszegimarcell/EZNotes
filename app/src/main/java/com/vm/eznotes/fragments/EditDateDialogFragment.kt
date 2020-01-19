package com.vm.eznotes.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.vm.eznotes.R
import com.vm.eznotes.activities.EditTextNoteActivity
import com.vm.eznotes.databinding.DialogEditdateBinding
import com.vm.eznotes.viewModels.EditDateDialogViewModel
import java.util.*

class EditDateDialogFragment(val data : EditDateDialogViewModel) : DialogFragment(), View.OnClickListener {

    private lateinit var binding : DialogEditdateBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = activity?.layoutInflater
        binding = DataBindingUtil.inflate(inflater!!, R.layout.dialog_editdate, null, false)
        binding.data = data
        val builder = AlertDialog.Builder(context!!)
        builder.setView(binding.root)
            .setPositiveButton(
                R.string.ok,
                DialogInterface.OnClickListener { dialog, id ->
                    (activity as EditTextNoteActivity).getFragmentResult(binding.data!!)
                })
            .setNegativeButton(
                R.string.cancel,
                DialogInterface.OnClickListener { dialog, id ->
                    (activity as EditTextNoteActivity)!!.getFragmentResult(data)
                })

        val dialog = builder.create()
        return dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.dialog_editdate, container, false);
        super.onCreateView(inflater, container, savedInstanceState)
        v.findViewById<Button>(R.id.button_notification_edit).setOnClickListener(this)
        v.findViewById<Button>(R.id.button_alarm_edit).setOnClickListener(this)
        v.findViewById<CheckBox>(R.id.checkBox_notification_edit).setOnClickListener(this)
        v.findViewById<CheckBox>(R.id.checkBox_alarm_edit).setOnClickListener(this)
        return v
    }

    override fun onClick(v: View?) {
        val c = Calendar.getInstance()
        var year = c.get(Calendar.YEAR)
        var month = c.get(Calendar.MONTH)
        var day = c.get(Calendar.DAY_OF_MONTH)
        var hour = c.get(Calendar.HOUR_OF_DAY)
        var minute = c.get(Calendar.MINUTE)

        when(v?.id){
            R.id.button_notification_edit -> {
                val date_dialog = DatePickerDialog(view!!.context, DatePickerDialog.OnDateSetListener { view, year, month, day ->
                    val time_dialog = TimePickerDialog(view.context, TimePickerDialog.OnTimeSetListener{view, hour, minute ->
                        binding.data!!.nyear = year
                        binding.data!!.nmonth = month
                        binding.data!!.nday = day
                        binding.data!!.nhour = hour
                        binding.data!!.nminute = minute
                        binding!!.invalidateAll()
                    }, hour,minute, true)
                    time_dialog.show()
                }, year, month, day)
                date_dialog.show()
            }
            R.id.button_alarm_edit ->{
                val date_dialog = DatePickerDialog( view!!.context, DatePickerDialog.OnDateSetListener { view, year, month, day ->
                    val time_dialog = TimePickerDialog(view.context, TimePickerDialog.OnTimeSetListener{view, hour, minute ->
                        binding.data!!.ayear = year
                        binding.data!!.amonth = month
                        binding.data!!.aday = day
                        binding.data!!.ahour = hour
                        binding.data!!.aminute = minute
                        binding.invalidateAll()
                    }, hour, minute, true)
                    time_dialog.show()
                }, year, month, day)
                date_dialog.show()
            }
            R.id.checkBox_alarm_edit -> binding.invalidateAll()
            R.id.checkBox_notification_edit -> binding.invalidateAll()
        }
    }

}