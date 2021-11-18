package com.example.week_5_task_regform_ui_testing

import com.example.week_5_task_regform_ui_testing.Validation.RegValidation.validateEmail
import com.example.week_5_task_regform_ui_testing.Validation.RegValidation.validateGender
import com.example.week_5_task_regform_ui_testing.Validation.RegValidation.validatePhoneNumber
import junit.framework.TestCase
import org.junit.Test

object UnitTest {


    class ValidateRegistrationTest : TestCase() {

        // Mock input data function
        private val emailTrue: String = "terrymay@gmail.com"
        private val emailFalse: String = "terrymay@com"

        private val numberFalse: String = "0813553775"
        private val numberTrue: String = "+2348492015369"

        private val genderTrue: String = "Male"
        private val genderFalse: String = "Select Gender"

        // Validate correct email function
        @Test
        fun test_validateEmail_isTrue() {
            val result = validateEmail(emailTrue)
            assertTrue(result)
        }

        // Validate wrong email function
        @Test
        fun test_validateEmail_isFalse() {
            val result = validateEmail(emailFalse)
            assertFalse(result)
        }

        // Validate phone number function
        @Test
        fun test_validatePhoneNumber_isTrue() {
            val result = validatePhoneNumber(numberTrue)
            assertTrue(result)
        }

        // Validate wrong phone number
        @Test
        fun test_validatePhoneNumber_isFalse() {
            val result = validatePhoneNumber(numberFalse)
            assertFalse(result)
        }

        // Correct gender validation
        @Test
        fun test_validateGender_isTrue() {
            val result = validateGender(genderTrue)
            assertTrue(result)
        }

        //Wrong gender validation
        @Test
        fun test_validateGender_isFalse() {
            val result = validateGender(genderFalse)
            assertFalse(result)
        }
    }

}