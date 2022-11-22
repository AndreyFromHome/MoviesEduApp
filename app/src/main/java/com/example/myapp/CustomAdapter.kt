package com.example.myapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

// В адаптере связываем список данных с героями сериала с нашего API и размещаем его в элементе Android Recycler View
class CustomAdapter(private val mList: List<MoviesItem>?,
                    val mItemClickListener:ItemClickListener) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    interface ItemClickListener {
        fun onItemClick(position: Int)
    }

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)

        return ViewHolder(view)
    }

    // Это одна ячейка, которую заполняет адаптер
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList?.get(position)
        Log.d("MyLog", "Текущая позиция ${mList?.get(position)}")
        Picasso.get().load(mList?.get(position)?.img).into(holder.imageView);

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList!!.size
    }

    // Holds the views for adding it to image and text
    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        init {
            ItemView.setOnClickListener {
            //    mItemClickListener.onItemClick(adapterPosition)
            // если mList не null, берём позицию на которую кликаем и берём там значение char_id, если здесь везде не null, вызываем let { ... }
                // в котором вызываем функцию onItemClick(it) в объекте ItemClickListener
            mList?.get(position)?.char_id?.let { it -> mItemClickListener.onItemClick(it)}
            }
        }
        //    val textView: TextView = itemView.findViewById(R.id.textView)
    }
}