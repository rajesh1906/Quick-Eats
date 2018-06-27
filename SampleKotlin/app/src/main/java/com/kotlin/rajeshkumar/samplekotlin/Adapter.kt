package com.kotlin.rajeshkumar.samplekotlin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by Rajesh kumar on 07-09-2017.
 */
class Adapter(val userlist:ArrayList<UserModel>): RecyclerView.Adapter<Adapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.listitem, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        holder.bindItems(userlist[position])
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userlist.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: UserModel) {
            val textViewName = itemView.findViewById<TextView>(R.id.textViewUsername) as TextView
            val textViewAddress  = itemView.findViewById<TextView>(R.id.textViewAddress) as TextView
            textViewName.text = user.name
            textViewAddress.text = user.address
        }
    }
}