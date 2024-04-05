package com.example.nanohttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.nanohttp.databinding.ActivityMainBinding
import com.example.nanohttp.server.MyNanoHTTPD
import fi.iki.elonen.NanoHTTPD
import java.io.IOException

class MainActivity : AppCompatActivity()
{
    private var nanoHTTPD: MyNanoHTTPD? = null
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding =ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.start.setOnClickListener {
            startServer()
            binding.servStatus.text = "Started Server";
        }
        binding.stop.setOnClickListener {
            stopServer()
            binding.servStatus.text = "Server Stopped";
        }

    }

    private fun startServer() {
        nanoHTTPD = MyNanoHTTPD()
        try {
            nanoHTTPD?.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false)
            Log.d("NanoHTTPD", "Server started")
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("NanoHTTPD", "Error starting server: ${e.message}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopServer()
    }

    private fun stopServer() {
        nanoHTTPD?.stop()
        Log.d("NanoHTTPD", "Server stopped")
    }

}