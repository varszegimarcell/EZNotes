package com.vm.eznotes

class ToDoList(Name : String) : Note(Name)  {
    var Items = mutableListOf<ToDoListItem>()
}