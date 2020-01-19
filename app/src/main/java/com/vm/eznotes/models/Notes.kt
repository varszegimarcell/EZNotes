package com.vm.eznotes.models

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
        fun getNoteItemByName(str : String) : Note {
            val iterator = Items.iterator()
            iterator.forEach { note ->
                if(note.Name == str) return note
            }
            throw NoSuchElementException()
        }
    }
}