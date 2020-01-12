package com.vm.eznotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class NoteItemAdapter(val context : Context, val itemsList : List<Note>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var infater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var gridView : View
        if (convertView == null){
            gridView = infater.inflate(R.layout.note_item_view, null)
            var textview : TextView = gridView.findViewById(R.id.note_item_name)
            textview.setText(itemsList[position].Name)
            var imageView : ImageView = gridView.findViewById(R.id.note_item_image)

            if(itemsList[position] is TextNote){
                imageView.setImageResource(R.drawable.ic_text_fields_black_48dp)
            }
            if(itemsList[position] is ToDoList){
                imageView.setImageResource(R.drawable.ic_playlist_add_check_black_48dp)
            }
        }
        else{
            gridView = convertView
        }
        return gridView
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