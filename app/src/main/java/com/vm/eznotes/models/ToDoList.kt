package com.vm.eznotes.models

class ToDoList(Name : String) : Note(Name)  {
    var Items = mutableListOf<ToDoListItem>()
}