package com.vm.eznotes

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

class AddNoteViewModel : BaseObservable() {
    @get:Bindable
    var name : String = ""
        set(value){
            field = value
            notifyPropertyChanged(BR.NewNote)
        }
    @get:Bindable
    var istextnote : Boolean = true
        set(value){
            field = value
            notifyPropertyChanged(BR.NewNote)
        }
    @get:Bindable
    var istodolist : Boolean = false
        set(value){
            field = value
            notifyPropertyChanged(BR.NewNote)
        }
    @get:Bindable
    var isnotification : Boolean = false
        set(value){
            field = value
            notifyPropertyChanged(BR.NewNote)
        }
    @get:Bindable
    var isalarm : Boolean = false
        set(value){
            field = value
            notifyPropertyChanged(BR.NewNote)
        }
}