package com.example.interfaceelements

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class ListActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        Log.v(this.localClassName, "onCreate")

        val spinner: Spinner = findViewById(R.id.spinner)
        val arrayAdapter: ArrayAdapter<*> = ArrayAdapter.createFromResource(this, R.array.my_array, android.R.layout.simple_spinner_item)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter
    }

    override fun onStart() {
        super.onStart()

        Log.v(this.localClassName, "onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.v(this.localClassName, "onResume")
    }

    override fun onPause() {
        super.onPause()

        Log.v(this.localClassName, "onPause")
    }

    override fun onStop() {
        super.onStop()

        Log.v(this.localClassName, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.v(this.localClassName, "onDestroy")
    }

    fun buttonPressed(view: View) {
        Log.v(this.localClassName, "Button Pressed!")
    }

    fun imgButtonPressed(view: View) {
        Log.v(this.localClassName, "Image Button Pressed!")
    }
}