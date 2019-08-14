package com.example.studentzoneproject

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intent:Intent
        tvViewStops.setOnClickListener {
            intent = Intent(this,ViewStopsActivity::class.java)
            startActivity(intent)
        }

        tvFindRoute.setOnClickListener {
            intent = Intent(this,FindRouteActivity::class.java)
            startActivity(intent)
        }

    }
}
