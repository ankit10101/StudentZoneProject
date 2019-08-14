package com.example.studentzoneproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_view_stops.*

class ViewStopsActivity : AppCompatActivity() {

    val stopsList = ArrayList<String>()
    val stopsAdapter = StopsAdapter(stopsList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_stops)
        btnGo.setOnClickListener {
            stopsList.clear()
            FirebaseDatabase.getInstance().reference.child("${etCityName.text}").child("Route${etRouteNumber.text}")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (!dataSnapshot.exists()) {
                            stopsList.clear()
                            Toast.makeText(baseContext, "No such route exists", Toast.LENGTH_SHORT).show()
                        } else {
                            for (snapshot in dataSnapshot.children) {
                                val stop = snapshot.value.toString()
                                stopsList.add(stop)
                                runOnUiThread {
                                    tvHeader.visibility = View.VISIBLE
                                    stopsAdapter.notifyDataSetChanged()
                                }
                            }
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        Log.d("TAG", databaseError.message);
                    }
                })
            rvStops.layoutManager = LinearLayoutManager(this)
            rvStops.adapter = stopsAdapter
        }
    }
}
