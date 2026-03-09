package com.example.quiz

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConfirmationActivity : AppCompatActivity() {

    private lateinit var tvFullName: TextView
    private lateinit var tvPhone: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvAppointmentType: TextView
    private lateinit var tvAppointmentDate: TextView
    private lateinit var tvAppointmentTime: TextView
    private lateinit var tvGender: TextView
    private lateinit var btnBackHome: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        initViews()
        displayAppointmentDetails()
        setupBackButton()
    }

    private fun initViews() {
        tvFullName = findViewById(R.id.tvFullName)
        tvPhone = findViewById(R.id.tvPhone)
        tvEmail = findViewById(R.id.tvEmail)
        tvAppointmentType = findViewById(R.id.tvAppointmentType)
        tvAppointmentDate = findViewById(R.id.tvAppointmentDate)
        tvAppointmentTime = findViewById(R.id.tvAppointmentTime)
        tvGender = findViewById(R.id.tvGender)
        btnBackHome = findViewById(R.id.btnBackHome)
    }

    private fun displayAppointmentDetails() {
        tvFullName.text = "Name: ${intent.getStringExtra("fullName")}"
        tvPhone.text = "Phone: ${intent.getStringExtra("phone")}"
        tvEmail.text = "Email: ${intent.getStringExtra("email")}"
        tvAppointmentType.text = "Appointment Type: ${intent.getStringExtra("appointmentType")}"
        tvAppointmentDate.text = "Date: ${intent.getStringExtra("appointmentDate")}"
        tvAppointmentTime.text = "Time: ${intent.getStringExtra("appointmentTime")}"
        tvGender.text = "Gender: ${intent.getStringExtra("gender")}"
    }

    private fun setupBackButton() {
        btnBackHome.setOnClickListener {
            finish() // Return to previous screen (BookAppointmentActivity)
        }
    }
}