package com.example.travelapp.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelapp.Activity.CategorieActivity
import com.example.travelapp.Adapter.AdapterClass
import com.example.travelapp.Adapter.ImageAdapter
import com.example.travelapp.ModelClass.StudentModelClass
import com.example.travelapp.R
import com.example.travelapp.databinding.FragmentHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase


class HomeFragment : Fragment() {


    private var Binding: FragmentHomeBinding? = null
    private lateinit var auth: FirebaseAuth
    lateinit var firebaseDatabase: DatabaseReference
    private val binding get() = Binding
    var list = arrayListOf<Int>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding?.root
        var refrence = FirebaseDatabase.getInstance().reference
        var list = ArrayList<StudentModelClass>()

        firebaseDatabase = FirebaseDatabase.getInstance().getReference()
        auth = Firebase.auth


        refrence.root.child("AdminTb").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (child in snapshot.children) {
                    var data: StudentModelClass? = child.getValue(StudentModelClass::class.java)
                    if (data != null) {
                        list.add(data)
                    }
                }
                var adpter = AdapterClass(this@HomeFragment, list)
                var manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding?.rcv?.layoutManager = manager
                binding?.rcv?.adapter = adpter
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        initview()
        return view
    }

    private fun initview() {
//        list.add(R.drawable.viewpager_img1)
//        list.add(R.drawable.viewpager_img2)
//        list.add(R.drawable.viewpager_img3)
//        list.add(R.drawable.viewpager_img4)
//
//        var adapter = ImageAdapter(list)
//        binding?.viewpager?.adapter = adapter

        Binding?.imgMountain?.setOnClickListener {

            var i=Intent(context,CategorieActivity::class.java)
            startActivity(i)
        }
    }

//    private fun imageSlider() {
////        HomeFragment.loutCity.setOnClickListener {
////            Log.e("hhhh", "imageSlider: ")
////        }
//
//        firebaseDatabase.child("slider").addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                imageList1.clear()
//                Log.e("TAG", "onDataChange: " + snapshot.childrenCount)
//                for (photo in snapshot.children) {
//                    var data = photo.getValue(ImageSliderModel::class.java)
//                    data?.let { it1 -> imageList1.add(it1) }
//                    data?.image = photo.child("image").value.toString()
//                }
//                imageAdapter.notifyDataSetChanged()
//                imageAdapter = ImageAdapter(imageList1, this@HomeFragment) {
//
//                    var intent = Intent(context, DisplayActivity::class.java)
//                    intent.putExtra("key", it.key)
//                    startActivity(intent)
//
//
//                }
//                Binding?.VPView?.adapter = imageAdapter
//                Binding?.wormDotsIndicator?.attachTo(Binding!!.VPView)
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//        })
//
//    }
}


//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?,
//
//        ): View? {
//        Binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
//        val view = binding?.root
//        var refrence = FirebaseDatabase.getInstance().reference
//        var list = ArrayList<StudentModelClass>()
//
//        // Inflate the layout for this fragment
//        firebaseDatabase = FirebaseDatabase.getInstance().getReference()
//        auth = Firebase.auth
//        imageSlider()
//        topDestination()
//        workingClass()
//        addToCart()
//        appDrawer()
//        userPhoto()
//
//
//        Log.e("two", "topDestination: ")
//        return Binding.root
//
//    }
//
//    private fun userPhoto() {
//
//
////        homeBinding.imgUser.setOnClickListener {
////            val newGamefragment = ProfileFragment()
////            val fragmentTransaction = requireFragmentManager().beginTransaction()
////            fragmentTransaction.replace(R.id.fragmentDisplay, newGamefragment)
////            fragmentTransaction.addToBackStack(null)
////            fragmentTransaction.commit()
////        }
//
//
//        auth.currentUser?.let {
//            firebaseDatabase.child("user").child(it.uid)
//                .addValueEventListener(object : ValueEventListener {
//                    override fun onDataChange(snapshot: DataSnapshot) {
//
//                        var image = snapshot.child("images").value.toString()
//                        Glide.with(this@HomeFragment).load(image).into(Binding.imgUser)
//
//                    }
//
//                    override fun onCancelled(error: DatabaseError) {
//                    }
//                })
//        }
//    }
//
//    private fun appDrawer() {
//
//        Binding.imgMenu.setOnClickListener {
//            Binding.AppDrawer.openDrawer(GravityCompat.START)
//        }
//
//        auth.currentUser?.let {
//            firebaseDatabase.child("user").child(it.uid)
//                .addValueEventListener(object : ValueEventListener {
//                    override fun onDataChange(snapshot: DataSnapshot) {
//                        var name = snapshot.child("name").value.toString()
//                        var email = snapshot.child("email").value.toString()
//                        Binding.txtUsername.setText(name)
//                        Binding.txtEmail.setText(email)
//                    }
//
//                    override fun onCancelled(error: DatabaseError) {
//                    }
//                })
//        }
//
//        Binding.loutPrivacyPolicy.setOnClickListener {
//            val url = "https://himanshuxoxo.blogspot.com/2023/06/privacy-policy.html"
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//            startActivity(intent)
//            Binding.AppDrawer.openDrawer(GravityCompat.START)
//        }
//
//        Binding.loutShare.setOnClickListener {
//            val a = Intent(Intent.ACTION_SEND)
//            a.type = "text/plain"
//            a.putExtra(Intent.EXTRA_SUBJECT, "Sharing URL")
//            a.putExtra(
//                Intent.EXTRA_TEXT,
//                "https://play.google.com/store/apps/details?id=com.tripadvisor.tripadvisor&hl=en-IN"
//            )
//            startActivity(Intent.createChooser(a, "Share URL"))
//
//            Binding.AppDrawer.openDrawer(GravityCompat.START)
//
//
//        }
//
//        Binding.loutQuit.setOnClickListener {
//            System.exit(0)
//        }
//
////        APP VERSION
//        val pack: PackageManager = requireActivity().packageManager
//        val info: PackageInfo = pack.getPackageInfo(requireActivity().packageName, 0)
//        val version: String = info.versionName
//        Binding.txtVersion.text = version
//
//
//    }
//
//    fun addToCart() {
//        Binding.btnCart.setOnClickListener {
//            Toast.makeText(requireContext(), "Click Success.", Toast.LENGTH_SHORT).show()
//            var intent = Intent(context, CartActivity::class.java)
//            startActivity(intent)
//        }
//
//
//    }
//
//
//    private fun workingClass() {
//
//        auth.currentUser?.let {
//            firebaseDatabase.child("user").child(it.uid)
//                .addValueEventListener(object : ValueEventListener {
//                    override fun onDataChange(snapshot: DataSnapshot) {
//                        var name = snapshot.child("name").value.toString()
//
//                        Binding.txtName.setText(name)
//
//                    }
//
//                    override fun onCancelled(error: DatabaseError) {
//
//                    }
//
//                })
//
//        }
//    }
//
//    private fun topDestination() {
//
//        Log.e("one", "topDestination: ")
//
//        firebaseDatabase.child("top").addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                topDestination.clear()
//                Log.e("TAG", "onDataChange: " + snapshot.childrenCount)
//
//                for (photo in snapshot.children) {
//                    var data = photo.getValue(TopDestinationModelClass::class.java)
//                    data?.let { it1 -> topDestination.add(it1) }
//                    data?.image = photo.child("image").value.toString()
//                    data?.location = photo.child("location").value.toString()
//                    data?.key = photo.child("key").value.toString()
//                }
//
//                adapter = TopDestinationAdapter(topDestination, this@HomeFragment) {
//
//                    val args = Bundle()
//                    args.putString("key", it.key)
//                    args.putString("location", it.location)
//
////                    val newGamefragment = TopCityFragment()
////                    newGamefragment.setArguments(args).toString()
////                    val fragmentTransaction = requireFragmentManager().beginTransaction()
////                    fragmentTransaction.replace(R.id.fragmentDisplay, newGamefragment)
////                    fragmentTransaction.addToBackStack(null)
////                    fragmentTransaction.commit()
//
//                }
//
//                var manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//                Binding.rcvTopDestination.layoutManager = manager
//                Binding.rcvTopDestination.adapter = adapter
//
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//            }
//
//        })
//
//
//    }
//


//}
