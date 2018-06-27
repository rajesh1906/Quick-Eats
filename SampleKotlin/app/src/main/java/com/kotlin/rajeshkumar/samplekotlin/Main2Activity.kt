package com.kotlin.rajeshkumar.samplekotlin

import android.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() ,Communicator {
    override fun setData(value: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        var fragmentmanager:FragmentManager = fragmentManager
       var fragment= fragmentmanager.findFragmentById(R.id.fragment) as? SampleFragment
        fragment!!.changeData("hello Raaz")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
//        var bundle:Bundle = intent.getBundleExtra("bundle")

        txt.setText("hello rajesh click me")
        txt.setOnClickListener(View.OnClickListener {

        })
//        Log.e("bundle values is","<><"+bundle.getString("value"))
    }
}
