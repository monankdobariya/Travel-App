package com.example.travelapp.Adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travelapp.Fragment.HomeFragment
import com.example.travelapp.ModelClass.StudentModelClass
import com.example.travelapp.R

class AdapterClass(var homefragment: HomeFragment, var list: ArrayList<StudentModelClass>) :
    RecyclerView.Adapter<AdapterClass.Myviewholder>() {
    class Myviewholder (itemView: View) : RecyclerView.ViewHolder(itemView){
        var place: TextView= itemView.findViewById(R.id.palce)
        var price: TextView= itemView.findViewById(R.id.price)
        var img : ImageView = itemView.findViewById(R.id.img)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myviewholder {
        var View = LayoutInflater.from(parent.context).inflate(R.layout.item_file, parent, false)
        return Myviewholder(View)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Myviewholder, position: Int) {
        holder.place.text = list[position].palce
        holder.price.text = list[position].price
        Glide.with(homefragment).load(list[position].ImageUri).into(holder.img)

    }
}