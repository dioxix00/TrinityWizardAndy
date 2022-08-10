package com.example.trinitywizard

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputStream: InputStream = assets.open("data.json")
        val json = inputStream.bufferedReader().use{it.readText()}
        val json2 = JSONArray(json)

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.rv_user)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<ItemsViewModel>()


        for (i in 0 until json2.length()) {
            val objects: JSONObject = json2.getJSONObject(i)
            val name = objects["firstName"].toString() + " " + objects["lastName"].toString()
            data.add(ItemsViewModel(R.drawable.circle, text=name))
        }

        val adapter = CustomAdapter(data)

        recyclerview.adapter = adapter
        adapter.setOnItemClickListener(object : CustomAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                Toast.makeText(this@MainActivity,".$position", Toast.LENGTH_SHORT).show()
            }

        })
    }

}