package com.example.lessonsapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    var counter = 0
    lateinit var button: Button
    lateinit var userName: TextInputEditText
    lateinit var password: TextInputEditText
    lateinit var userNameLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { view, insets ->
            val systemBarsInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(
                systemBarsInsets.left,
                systemBarsInsets.top,
                systemBarsInsets.right,
                systemBarsInsets.bottom
            )
            WindowInsetsCompat.CONSUMED
        }

        button = findViewById(R.id.bLogin)
        userName = findViewById(R.id.tietUsername)
        password = findViewById(R.id.tietPassword)

        goToSongListScreen()

        button.setOnClickListener {
            if (userName.text.toString() == "eduardo" && password.text.toString() == "growup")
                goToSongListScreen()
            else
                showMessage(this, "Erro ao fazer login")
        }
    }

    private fun goToSongListScreen() {
        val intent = Intent(this, SongListActivity::class.java)
        startActivity(intent)
    }
}

fun showMessage(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}
