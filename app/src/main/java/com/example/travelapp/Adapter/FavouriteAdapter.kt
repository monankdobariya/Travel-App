package com.example.travelapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.travelapp.ModelClass.SuratModelClass
import com.example.travelapp.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class FavouriteAdapter(var  context: Context?,var liked:((Int , String) -> Unit),var invoke:((SuratModelClass) -> Unit)) : RecyclerView.Adapter<FavouriteAdapter.myAdapter>() {

    var suratDetailsList= ArrayList<SuratModelClass>()
        lateinit var firebaseDatabase: DatabaseReference

    class myAdapter(view : View) : RecyclerView.ViewHolder(view){
        var amount : TextView =view.findViewById(R.id.txtAmount)
        var rating : TextView =view.findViewById(R.id.txtRating)
        var thumbnail : ImageView =view.findViewById(R.id.imgThumbnail)
        var name : TextView =view.findViewById(R.id.txtName)
        var cvHotel: CardView =view.findViewById(R.id.cvHotel)
//        var imgLiked : ImageView =view.findViewById(R.id.imgLiked)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myAdapter {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.surat_details_item,parent,false)
        return myAdapter(view)
    }

    override fun getItemCount(): Int {
        return suratDetailsList.size
    }

    override fun onBindViewHolder(holder: myAdapter, position: Int) {
        firebaseDatabase = FirebaseDatabase.getInstance().getReference()

  Glide.with(context!!).load(suratDetailsList[position].thumbnail).into(holder.thumbnail)
        holder.amount.setText(suratDetailsList[position].amount)
        holder.rating.setText(suratDetailsList[position].rating)
        holder.name.setText(suratDetailsList[position].name)

        holder.cvHotel.setOnClickListener {
            invoke.invoke(suratDetailsList[position])

        }

//            holder.imgLiked.setImageResource(R.drawable.user)



//
//        holder.imgLiked.setOnClickListener {
//            if(suratDetailsList[position].fav==1)
//            {
//                liked.invoke(0,suratDetailsList[position].key)
//                holder.imgLiked.setImageResource(R.drawable.hearttt)
//                suratDetailsList[position].fav=0
//            }
//
//            else
//            {
//                liked.invoke(1,suratDetailsList[position].key)
//                holder.imgLiked.setImageResource(R.drawable.user)
//                suratDetailsList[position].fav=1
//            }


//        }
    }

    fun updateList(suratDetailsList: ArrayList<SuratModelClass>) {
this.suratDetailsList=suratDetailsList
        notifyDataSetChanged()
    }
}