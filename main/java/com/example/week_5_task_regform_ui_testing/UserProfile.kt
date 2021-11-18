package com.example.week_5_task_regform_ui_testing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.week_5_task_regform_ui_testing.databinding.ActivityMainProfileBinding

class ProfileActivity : AppCompatActivity() {

        private lateinit var binding: ActivityMainProfileBinding

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = ActivityMainProfileBinding.inflate(layoutInflater)
            setContentView(binding.root)

            // Views initialization
            val tvName = binding.tvName
            val tvEmail = binding.tvEmail
            val tvPhoneNumber = binding.tvNumber
            val tvSex = binding.tvSex

            // Bundles
            val bundle = intent.extras
            val firstName: String? = bundle?.getString("FIRST_NAME")
            val lastName: String? = bundle?.getString("LAST_NAME")
            val email: String? = bundle?.getString("EMAIL")
            val phoneNumber: String? = bundle?.getString("PHONE_NUMBER")
            val sex: String? = bundle?.getString("SEX")

            // My view display content
            tvName.text = "$firstName $lastName"
            tvEmail.text = email
            tvPhoneNumber.text = phoneNumber
            tvSex.text = sex

        }
    }