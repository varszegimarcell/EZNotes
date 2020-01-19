package com.vm.eznotes.viewModels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.vm.eznotes.BR
import com.vm.eznotes.R

class EditDateDialogViewModel : BaseObservable(){
    var nyear = 0
    var nmonth = 0
    var nday = 0
    var nhour = 0
    var nminute = 0

    var ayear = 0
    var amonth = 0
    var aday  = 0
    var ahour = 0
    var aminute = 0

    @get:Bindable
    var isnotification : Boolean = false
        set(value){
            field = value
            notifyPropertyChanged(BR.data)
        }
    @get:Bindable
    var isalarm : Boolean = false
        set(value){
            field = value
            notifyPropertyChanged(BR.data)
        }
    val alarmDate : String
        @Bindable
        get(){
            if(aminute != 0) return ayear.toString() + "." + (amonth + 1).toString() + "." + aday.toString() + ". " + ahour.toString() + ":" + aminute.toString()
            else return "Not set"
        }
    val notificationDate : String
        @Bindable
        get(){
            if(nminute != 0) return nyear.toString() + "." + (nmonth + 1).toString() + "." + nday.toString() + ". " + nhour.toString() + ":" + nminute.toString()
            else return "Not set"
        }
}