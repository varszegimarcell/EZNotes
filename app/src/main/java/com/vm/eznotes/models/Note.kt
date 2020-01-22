package com.vm.eznotes.models

import java.util.*

open class Note{
    var Name : String = ""
    var notificationDate : Date? = null
    var alarmDate : Date? = null

    constructor()

    constructor(name : String) : this(){
        Name = name
    }
}