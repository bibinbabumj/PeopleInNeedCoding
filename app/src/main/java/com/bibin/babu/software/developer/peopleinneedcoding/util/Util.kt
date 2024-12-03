package com.bibin.babu.software.developer.peopleinneedcoding.util

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.mShow(){
    visibility=View.VISIBLE
}
fun View.mHide(){
    visibility=View.GONE
}

fun Context.mToast(message:String){
    Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()

}