package com.example.travelapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelapp.Adapter.CategorieAdapter
import com.example.travelapp.ModelClass.MountainModalClass
import com.example.travelapp.databinding.ActivityCategorieBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CategorieActivity : AppCompatActivity() {

    lateinit var binding:ActivityCategorieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCategorieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initview()
    }

    private fun initview() {
        var reference = FirebaseDatabase.getInstance().reference

        var list = ArrayList<MountainModalClass>()
        reference.root.child("MountainTb").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (child in snapshot.children) {
                    var data: MountainModalClass? = child.getValue(MountainModalClass::class.java)
                    if (data != null) {
                        list.add(data)
                    }
                }
                var adpter = CategorieAdapter(this@CategorieActivity,list)
                var manager = LinearLayoutManager(this@CategorieActivity, LinearLayoutManager.VERTICAL,false)
                binding?.rcvCategorie?.layoutManager =manager
                binding.rcvCategorie.adapter = adpter

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}