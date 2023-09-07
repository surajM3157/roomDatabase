package com.ibrajix.roomdatabase.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ibrajix.roomdatabase.R

class loginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var tvSignUp: TextView
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        tvSignUp = findViewById(R.id.tvSignUp)
        loginButton = findViewById(R.id.loginButton)

        tvSignUp.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Check conditions for username and password
            if (isValidUsername(username) && isValidPassword(password)) {
                val intent = Intent(this, UserDetailsActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "username or password Required", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Function to validate the username
    private fun isValidUsername(username: String): Boolean {
        // Add your validation logic for the username here
        // For example, check if it's not empty or meets certain criteria
        return username.isNotEmpty()
    }

    // Function to validate the password
    private fun isValidPassword(password: String): Boolean {
        // Add your validation logic for the password here
        // For example, check if it's not empty or meets certain criteria
        return password.isNotEmpty()
    }
}
