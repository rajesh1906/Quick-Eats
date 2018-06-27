package com.kotlin.rajeshkumar.samplekotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var x:Int =1
    var value="hi"
    var bundle:Bundle = Bundle()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        list.layoutManager=LinearLayoutManager(this,LinearLayout.VERTICAL,false)
        val user = ArrayList<UserModel>()
        user.add(UserModel("Rajesh","Kasimkota"))
        user.add(UserModel("Kumar","Kasimkota"))
        user.add(UserModel("Raaz","Kasimkota"))
        list.adapter=Adapter(user)
        txtview.setOnClickListener { view: View? ->
            bundle.putString("value",value)
            val intent: Intent = Intent(this, Main2Activity::class.java)
            intent.putExtra("bundle",bundle)
            startActivity(intent)
        }
    }

}