package com.example.quiz

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class BookAppointmentActivity : AppCompatActivity() {

    private lateinit var etFullName: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText
    private lateinit var spinnerAppointmentType: Spinner
    private lateinit var tvSelectedDate: TextView
    private lateinit var tvSelectedTime: TextView
    private lateinit var radioGroupGender: RadioGroup
    private lateinit var radioMale: RadioButton
    private lateinit var radioFemale: RadioButton
    private lateinit var radioOther: RadioButton
    private lateinit var cbTerms: CheckBox
    private lateinit var btnConfirm: Button

    private var selectedDate = ""
    private var selectedTime = ""
    private var selectedGender = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_appointment)

        initViews()
        setupSpinner()
        setupDatePicker()
        setupTimePicker()
        setupConfirmButton()
    }

    private fun initViews() {
        etFullName = findViewById(R.id.etFullName)
        etPhone = findViewById(R.id.etPhone)
        etEmail = findViewById(R.id.etEmail)
        spinnerAppointmentType = findViewById(R.id.spinnerAppointmentType)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvSelectedTime = findViewById(R.id.tvSelectedTime)
        radioGroupGender = findViewById(R.id.radioGroupGender)
        radioMale = findViewById(R.id.radioMale)
        radioFemale = findViewById(R.id.radioFemale)
        radioOther = findViewById(R.id.radioOther)
        cbTerms = findViewById(R.id.cbTerms)
        btnConfirm = findViewById(R.id.btnConfirm)

        // Set default text for date and time
        tvSelectedDate.text = "Select Date"
        tvSelectedTime.text = "Select Time"
    }

    private fun setupSpinner() {
        val appointmentTypes = arrayOf(
            "Select Appointment Type",
            "Doctor Consultation",
            "Dentist Appointment",
            "Eye Specialist",
            "Skin Specialist",
            "General Checkup"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, appointmentTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerAppointmentType.adapter = adapter
    }

    private fun setupDatePicker() {
        tvSelectedDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    tvSelectedDate.text = selectedDate
                },
                year, month, day
            )
            datePickerDialog.show()
        }
    }

    private fun setupTimePicker() {
        tvSelectedTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(
                this,
                { _, selectedHour, selectedMinute ->
                    selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                    tvSelectedTime.text = selectedTime
                },
                hour, minute, true
            )
            timePickerDialog.show()
        }
    }

    private fun setupConfirmButton() {
        btnConfirm.setOnClickListener {
            if (validateInputs()) {
                // Get selected gender
                selectedGender = when (radioGroupGender.checkedRadioButtonId) {
                    R.id.radioMale -> "Male"
                    R.id.radioFemale -> "Female"
                    R.id.radioOther -> "Other"
                    else -> "Not Selected"
                }

                val intent = Intent(this, ConfirmationActivity::class.java)
                intent.putExtra("fullName", etFullName.text.toString())
                intent.putExtra("phone", etPhone.text.toString())
                intent.putExtra("email", etEmail.text.toString())
                intent.putExtra("appointmentType", spinnerAppointmentType.selectedItem.toString())
                intent.putExtra("appointmentDate", selectedDate)
                intent.putExtra("appointmentTime", selectedTime)
                intent.putExtra("gender", selectedGender)
                startActivity(intent)
            }
        }
    }

    private fun validateInputs(): Boolean {
        // Check if any field is empty
        when {
            TextUtils.isEmpty(etFullName.text) -> {
                etFullName.error = "Name is required"
                return false
            }
            TextUtils.isEmpty(etPhone.text) -> {
                etPhone.error = "Phone number is required"
                return false
            }
            TextUtils.isEmpty(etEmail.text) -> {
                etEmail.error = "Email is required"
                return false
            }
            spinnerAppointmentType.selectedItemPosition == 0 -> {
                Toast.makeText(this, "Please select appointment type", Toast.LENGTH_SHORT).show()
                return false
            }
            tvSelectedDate.text == "Select Date" -> {
                Toast.makeText(this, "Please select date", Toast.LENGTH_SHORT).show()
                return false
            }
            tvSelectedTime.text == "Select Time" -> {
                Toast.makeText(this, "Please select time", Toast.LENGTH_SHORT).show()
                return false
            }
            radioGroupGender.checkedRadioButtonId == -1 -> {
                Toast.makeText(this, "Please select gender", Toast.LENGTH_SHORT).show()
                return false
            }
            !cbTerms.isChecked -> {
                Toast.makeText(this, "Please accept terms and conditions", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
    }
}