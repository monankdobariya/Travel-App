package com.example.travelapp.ModelClass

import android.net.Uri

class StudentModelClass {


    var ImageUri: String = ""
    var palce: String = ""
    var email: String = ""
    var phonenumber: String = ""
    var Discription: String = ""
    var city: String = ""
    var price: String = ""
    var Rating: String = ""
    var key: String = ""
    var Day:String=""
    var Favourite:String=""

    constructor(
        palce: String,
        email: String,
        phonenumber: String,
        discription: String,
        city: String,
        price: String,
        rating: String,
        key: String,
        Day:String,
        Favourite:String,
        ImageUri: Uri,

        ) {
        this.ImageUri = ImageUri.toString()
        this.palce = palce
        this.email = email
        this.phonenumber = phonenumber
        this.Discription = discription
        this.key = key
        this.city = city
        this.price = price
        this.Rating = rating
        this.Day= Day
        this.Favourite=Favourite

    }

    constructor() {

    }

}