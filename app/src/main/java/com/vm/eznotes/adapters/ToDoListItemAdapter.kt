package com.vm.eznotes.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import com.vm.eznotes.R
import com.vm.eznotes.models.ToDoListItem

class ToDoListItemAdapter(val context: Context, val itemsList : List<ToDoListItem>) : BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var inflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var listView : View
        if (convertView == null) {
            listView = inflater.inflate(R.layout.todolist_item_view, null)
            var box : CheckBox = listView.findViewById(R.id.todo_checkbox)
            box.isChecked = itemsList[position].Checked
            box.text = itemsList[position].Text
        }
        else{
            listView = convertView
        }
        return listView
    }

    override fun getItem(position: Int): Any {
        return  itemsList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return itemsList.count()
    }

}