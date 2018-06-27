package com.kotlin.rajeshkumar.samplekotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.zip.Inflater

/**
 * Created by Rajesh kumar on 17-09-2017.
 */
class SampleFragment: Fragment(),View.OnClickListener {
    override fun onClick(p0: View?)  {
        communicator.setData("hello Raaz")
    }

    lateinit var communicator:Communicator
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       val view = inflater!!.inflate(R.layout.fragment,container,false)
        return view
    }
    fun changeData(data:String){
        txt.setText(data)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        communicator = activity as Communicator
        txt.setOnClickListener(this)

    }
}