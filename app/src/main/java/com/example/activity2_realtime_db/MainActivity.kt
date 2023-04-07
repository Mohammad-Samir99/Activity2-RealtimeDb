package com.example.activity2_realtime_db

import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database

import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var count:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Firebase.database
        val myRef = database.getReference()


        save.setOnClickListener {
            val name = PersonName.text.toString()
            val id = PersonID.text.toString()
            val age = PersonAge.text.toString()



            val person = hashMapOf(
                "name" to name,
                "id" to id,
                "age" to age
            )
            myRef .child("person").child("$count").setValue(person)
            count++
            Toast.makeText(applicationContext,"Success",Toast.LENGTH_SHORT).show()
        }
        get.setOnClickListener {
            // Read from the database
            myRef.addValueEventListener(object: ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val value = snapshot.getValue()
                    textData.text = value.toString()
                    Toast.makeText(applicationContext,"Success",Toast.LENGTH_SHORT).show()

                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(applicationContext,"Faille",Toast.LENGTH_SHORT).show()

                }

            })

        }

    }
}