package com.vm.eznotes.viewModels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.vm.eznotes.BR

class AddNoteViewModel : BaseObservable() {
    @get:Bindable
    var name : String = ""
        set(value){
            field = value
            notifyPropertyChanged(BR.newnote)
        }
    @get:Bindable
    var istextnote : Boolean = true
        set(value){
            field = value
            notifyPropertyChanged(BR.newnote)
        }
    @get:Bindable
    var istodolist : Boolean = false
        set(value){
            field = value
            notifyPropertyChanged(BR.newnote)
        }
    @get:Bindable
    var isalarm : Boolean = false
        set(value){
            field = value
            notifyPropertyChanged(BR.newnote)
        }
    @get:Bindable
    var isnotification : Boolean = false
        set(value){
            field = value
            notifyPropertyChanged(BR.newnote)
        }
}