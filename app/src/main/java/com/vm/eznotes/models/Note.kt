package com.vm.eznotes.models

import java.util.*

abstract class Note (var Name : String){
    var notificationDate : Date? = null
    var alarmDate : Date? = null
}