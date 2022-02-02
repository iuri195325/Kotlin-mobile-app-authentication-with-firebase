package com.example.kotlinfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinfirebase.databinding.ActivityEnderecoBinding
import com.example.kotlinfirebase.databinding.ActivityLoginBinding
import com.example.kotlinfirebase.databinding.ActivityMainBinding

class EnderecoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEnderecoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEnderecoBinding.inflate(layoutInflater)

        setContentView(binding.root);

    }
}