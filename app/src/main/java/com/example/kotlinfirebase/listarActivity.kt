package com.example.kotlinfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.kotlinfirebase.databinding.ActivityEnderecoBinding
import com.example.kotlinfirebase.databinding.ActivityListarBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import org.jetbrains.anko.uiThread

class listarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListarBinding
    private  val db = FirebaseFirestore.getInstance()
    private var usuarioID = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListarBinding.inflate(layoutInflater)
        setContentView(binding.root);


        binding.btVoltar.setOnClickListener { view ->
            val intent = Intent(this,telaPrincipalActivity::class.java);
            startActivity(intent);
        }

        usuarioID = FirebaseAuth.getInstance().currentUser!!.uid
        val reference = db.collection("Endereco").document(usuarioID)
            reference.get().addOnSuccessListener { document ->
                if (document.exists()) {
                    binding.tvCep.setText(document.data?.get("cep").toString())
                    binding.tvEstado.setText(document.data?.get("estado").toString())
                    binding.tvCidade.setText(document.data?.get("cidade").toString())
                    binding.tvLogradouro.setText(document.data?.get("logradouro").toString())
                    binding.tvBairro.setText(document.data?.get("bairro").toString())
                    binding.tvRua.setText(document.data?.get("rua").toString())
                    binding.tvNumero.setText(document.data?.get("numero").toString())
                }else{
                    Toast.makeText(this,"Erro",Toast.LENGTH_LONG).show()
                }
            }

    }
}