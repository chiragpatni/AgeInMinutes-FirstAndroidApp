package com.codingfreak.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        btnDatePicker.setOnClickListener {
            clickDataPicker()
        }
    }

    private fun clickDataPicker() {

        //This Gets a calendar using the default time zone and locale.
        //The calender returned is based on the current time in the default time zone with the default.

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->

                val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"

                // Selected date it set to the TextView to make it visible to user.
                tvSelectedDate.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                // The formatter will parse the selected date in to Date object
                // so we can simply get date in to milliseconds.

                val theDate = sdf.parse(selectedDate)

                val selectedDateToHours  = theDate!!.time / (60000*60)

                val selectedDateToMinutes = theDate.time / 60000

                val selectedDateToSeconds = theDate.time / 1000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateToHours = currentDate!!.time / (60000*60)

                val currentDateToMinutes = currentDate.time / 60000

                val currentDateToSeconds = currentDate.time / 1000

                val differenceInHours = currentDateToHours - selectedDateToHours

                val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes

                val differenceInSeconds = currentDateToSeconds - selectedDateToSeconds

                // Set the difference in hours, minutes and seconds to textView to show the user.
                tvSelectedDateInHours.text = differenceInHours.toString()

                tvSelectedDateInMinutes.text = differenceInMinutes.toString()

                tvSelectedDateInSeconds.text = differenceInSeconds.toString()

            }, year, month, day)

          //86400000 is milliseconds of 24 Hours
          //It is used to restrict the user to select today and future day.

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show() // It is used to show the datePicker Dialog.
    }
}