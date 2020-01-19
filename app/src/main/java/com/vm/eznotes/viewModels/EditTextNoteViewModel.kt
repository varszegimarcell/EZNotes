package com.vm.eznotes.viewModels

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.vm.eznotes.BR

class EditTextNoteViewModel : BaseObservable(){
    @get:Bindable
    var text : String = ""
        set(value){
            field = value
            notifyPropertyChanged(BR.editnote)
        }
}