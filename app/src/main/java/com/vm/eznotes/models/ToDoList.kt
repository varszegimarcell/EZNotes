package com.vm.eznotes.models

class ToDoList(Name : String) : Note(Name)  {
    var Items = mutableListOf<ToDoListItem>()
    fun getToDoListItemByText(str : String) : ToDoListItem
    {
        var iterator = Items.iterator()
        iterator.forEach {item ->
            if(item.Text == str) return item
        }
        throw NoSuchElementException()
    }
}