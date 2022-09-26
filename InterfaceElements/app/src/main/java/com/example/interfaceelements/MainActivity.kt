package com.example.interfaceelements

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.v(this.localClassName, "onCreate")
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

    fun listActivity(view: View) {
        startActivity(Intent(this, ListActivity::class.java))
    }
}