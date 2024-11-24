package com.example.lessonsapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {
    private lateinit var username: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var registerButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        username = findViewById(R.id.tietUsername)
        password = findViewById(R.id.tietPassword)
        email = findViewById(R.id.tietEmail)
        registerButton = findViewById(R.id.bRegister)

        registerButton.setOnClickListener {
            val user = username.text.toString()
            val pass = password.text.toString()
            val emailAddress = email.text.toString()

            if (user.isEmpty() || pass.isEmpty() || emailAddress.isEmpty()) {
                showMessage("Por favor, preencha todos os campos")
            } else {
                showMessage("Registrado com sucesso!")
                finish()
            }
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}