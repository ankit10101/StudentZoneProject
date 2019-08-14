package com.example.studentzoneproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_find_route.*
import kotlinx.android.synthetic.main.activity_view_stops.*

class FindRouteActivity : AppCompatActivity() {

    var routeSource: String = ""
    var routeDestination: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_route)

        val dbReference = FirebaseDatabase.getInstance().reference.child("Stops")

        btnSearch.setOnClickListener {

            dbReference.child("${etSource.text}")
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (!dataSnapshot.exists()) {
                            Toast.makeText(baseContext, "No such stop exists", Toast.LENGTH_SHORT).show()
                        } else {
                            routeSource = dataSnapshot.child("RouteNo").value.toString()

                            dbReference.child("${etDestination.text}")
                                .addListenerForSingleValueEvent(object : ValueEventListener {
                                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                                        if (!dataSnapshot.exists()) {
                                            Toast.makeText(baseContext, "No such stop exists", Toast.LENGTH_SHORT)
                                                .show()
                                        } else {
                                            routeDestination = dataSnapshot.child("RouteNo").value.toString()
                                            Log.e("TAG", routeDestination)
                                            if (routeSource == routeDestination) {
                                                runOnUiThread {
                                                    tvRouteNumber.text = "You should take the route number $routeSource"
                                                }
                                            } else {
                                                Log.e("TAG", "Source:$routeSource")
                                                Log.e("TAG", "Destination:$routeDestination")
                                                runOnUiThread {
                                                    tvRouteNumber.text = "No route exist between the above stops"
                                                }
                                            }

                                        }
                                    }

                                    override fun onCancelled(databaseError: DatabaseError) {
                                        Log.d("TAG", databaseError.message);
                                    }
                                })
                        }
                    }

                    override fun onCancelled(databaseError: DatabaseError) {
                        Log.d("TAG", databaseError.message);
                    }
                })

        }
    }
}
