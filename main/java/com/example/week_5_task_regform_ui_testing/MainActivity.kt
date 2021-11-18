package com.example.week_5_task_regform_ui_testing

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.week_5_task_regform_ui_testing.Validation.RegValidation.validateEmail
import com.example.week_5_task_regform_ui_testing.Validation.RegValidation.validateGender
import com.example.week_5_task_regform_ui_testing.Validation.RegValidation.validatePhoneNumber
import com.example.week_5_task_regform_ui_testing.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var spinner: Spinner
    private lateinit var regButton: Button
    private lateinit var etFirstName: TextInputEditText
    private lateinit var etLastName: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etPhoneNumber: TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize views here
        spinner = binding.spinner
        etFirstName = binding.firstName
        etLastName = binding.lastName
        etEmail = binding.emailAddress
        etPhoneNumber = binding.phoneNumber
        regButton = binding.btRegister

        // Selected spinner item listener
        spinner.onItemSelectedListener = this

        // Email and Phone Number validation in runtimes
        validateEmail()
        validatePhoneNumber()

        regButton.setOnClickListener {

            // Initialize User Inputs
            val firstName = etFirstName.text.toString()
            val lastName = etLastName.text.toString()
            val email = etEmail.text.toString()
            val phoneNumber = etPhoneNumber.text.toString()
            val sex = spinner.selectedItem.toString()

            // Validate All Fields
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || sex.isEmpty()) {
                Toast.makeText(this, "All Fields are Required", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else if (!validateEmail(email)) {
                etEmail.error = "Invalid Email"
                Toast.makeText(this, "Invalid Email", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else if (!validatePhoneNumber(phoneNumber)) {
                etPhoneNumber.error = "Invalid Phone Number"
                Toast.makeText(this, "Enter Nigerian Number", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else if (!validateGender(sex)) {
                Toast.makeText(this, "Select Gender", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            } else {
                // Create Bundle to Store Data
                val bundle = Bundle()
                bundle.apply {
                    putString("FIRST_NAME", firstName)
                    putString("LAST_NAME", lastName)
                    putString("EMAIL", email)
                    putString("PHONE_NUMBER", phoneNumber)
                    putString("SEX", sex)
                }
                // Navigate to Profile Activity
                val intent = Intent(this, ProfileActivity::class.java).apply {
                    putExtras(bundle)
                }

                startActivity(intent)
            }
        }


        // Set Up Spinner ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.Sex,
            android.R.layout.simple_spinner_item
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = it
        }
    }

    // Validate Email as Input Changes
    private fun validateEmail() {

        etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (validateEmail(etEmail.text.toString())) {
                    regButton.isEnabled = true
                } else {
                    etEmail.error = "Invalid Email"
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if (validateEmail(etEmail.text.toString())) {
                    regButton.isEnabled = true
                } else {
                    etEmail.error = "Invalid Email"
                }
            }

        })
    }

    //Phone number validation function as input changes
    private fun validatePhoneNumber() {

        etPhoneNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (validatePhoneNumber(etPhoneNumber.text.toString())) {
                    regButton.isEnabled = true

                } else {
                    etPhoneNumber.error = "Invalid Phone Number"
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if (validatePhoneNumber(etPhoneNumber.text.toString())) {
                    regButton.isEnabled = true
                } else {
                    etPhoneNumber.error = "Invalid Phone Number"
                }
            }

        })
    }

    //Get spinner item
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        parent?.getItemAtPosition(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


}
