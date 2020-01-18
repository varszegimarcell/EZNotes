package com.vm.eznotes

class Notes {
    companion object{
        var Items = mutableListOf<Note>()
        fun isNameAlreadyInUse(str : String) : Boolean {
            val iterator = Items.iterator()
            iterator.forEach { note ->
                if(note.Name == str) return true
            }
            return false
        }
    }
}