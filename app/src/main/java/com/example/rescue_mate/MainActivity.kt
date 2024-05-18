//package com.example.rescue_mate
//
//import android.Manifest
//import android.content.pm.PackageManager
//import android.location.Location
//import android.os.Bundle
//import android.telephony.SmsManager
//import android.view.KeyEvent
//import android.widget.Button
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import com.google.android.gms.location.FusedLocationProviderClient
//import com.google.android.gms.location.LocationServices
//import android.util.Log
//
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var fusedLocationClient: FusedLocationProviderClient
//
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////        setContentView(R.layout.activity_main)
////
////        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
////
////        val sendLocationButton: Button = findViewById(R.id.send_location_button)
////        sendLocationButton.setOnClickListener {
////            Log.d("LocationSMS", "Button clicked.")
////            sendLocationSMS()
////        }
////    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//
//        val sendLocationButton: Button = findViewById<Button>(R.id.send_location_button)
////        sendLocationButton.setOnClickListener {
////            Log.d("LocationSMS", "Send button clicked.")
////            println("udfhdbgvdvdgviudhiuviu----jvcjshvhckghdvch")
////            sendLocationSMS()
////        }
//
//        sendLocationButton.setOnClickListener {
//            try {
//                Log.d("LocationSMS", "Send button clicked.")
//                println("udfhdbgvdvdgviudhiuviu----jvcjshvhckghdvch")
//                sendLocationSMS()
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//
//    }
//
//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP || keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
//            sendLocationSMS()
//            return true
//        }
//        return super.onKeyDown(keyCode, event)
//    }
//
//    private fun sendLocationSMS() {
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//            && ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_COARSE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.SEND_SMS),
//                1
//            )
//            return
//        }
//        Log.d("LocationSMS", "Button clicked. Sending location SMS...")
//
//        fusedLocationClient.lastLocation
//            .addOnSuccessListener { location: Location? ->
//                location?.let {
//                    val message = "My current location is: https://www.google.com/maps/search/?api=1&query=${it.latitude},${it.longitude}"
//                    sendSMS("01708896595", message)  // Replace with your contact's phone number
//                }
//            }
//    }
//
////    private fun sendSMS(phoneNumber: String, message: String) {
////        if (ActivityCompat.checkSelfPermission(
////                this,
////                Manifest.permission.SEND_SMS
////            ) != PackageManager.PERMISSION_GRANTED
////        ) {
////            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 1)
////            return
////        }
////
////        // Print the SMS details to the console
////        //Log.d("LocationSMS", "Sending SMS to: $phoneNumber - Message: $message")
////        println("Sending SMS to: $phoneNumber - Message: $message")
////
////        // Send the SMS
////        val smsManager: SmsManager = SmsManager.getDefault()
////        smsManager.sendTextMessage(phoneNumber, null, message, null, null)
////    }
//
//    private fun sendSMS(phoneNumber: String, message: String) {
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                Manifest.permission.SEND_SMS
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 1)
//            return
//        }
//
//        try {
//            // Print the SMS details to the console
//            Log.d("LocationSMS", "Sending SMS to: $phoneNumber - Message: $message")
//            println("Sending SMS to: $phoneNumber - Message: $message")
//
//            // Send the SMS
//            val smsManager: SmsManager = SmsManager.getDefault()
//            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
//        } catch (e: Exception) {
//            // Log any exceptions
//           // Log.e("LocationSMS", "Error sending SMS: ${e.message}")
//            Log.e("LocationSMS", "Error sending SMS: ${e.message}")
//        }
//    }
//
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == 1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            sendLocationSMS()
//        }
//    }
//}


//------------------------------------------------------

//package com.example.rescue_mate
//
//import android.Manifest
//import android.content.Context
//import android.content.pm.PackageManager
//import android.location.Location
//import android.os.Bundle
//import android.telephony.SmsManager
//import android.telephony.TelephonyManager
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.app.ActivityCompat
//import com.google.android.gms.location.FusedLocationProviderClient
//import com.google.android.gms.location.LocationServices
//import android.util.Log
//import android.widget.Button
//import android.widget.Toast
//import androidx.core.content.ContextCompat
//
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var fusedLocationClient: FusedLocationProviderClient
//    private val REQUEST_PERMISSIONS = 1
//    private val receiverNumber = "01708896595" // Replace with your actual receiver number
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//
//        val sendLocationButton: Button = findViewById(R.id.send_location_button)
//        sendLocationButton.setOnClickListener {
//            requestPermissionsIfNeeded()
//        }
//    }
//
//    private fun requestPermissionsIfNeeded() {
//        val permissionsNeeded = mutableListOf<String>()
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            permissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
//        }
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
//            permissionsNeeded.add(Manifest.permission.SEND_SMS)
//        }
//
//        if (permissionsNeeded.isNotEmpty()) {
//            ActivityCompat.requestPermissions(this, permissionsNeeded.toTypedArray(), REQUEST_PERMISSIONS)
//        } else {
//            sendLocationSMS()
//        }
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == REQUEST_PERMISSIONS) {
//            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
//                sendLocationSMS()
//            } else {
//                Toast.makeText(this, "Permissions required to send location SMS.", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    private fun sendLocationSMS() {
//        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
//                location?.let {
//                    val message = "My current location is: https://www.google.com/maps/search/?api=1&query=${it.latitude},${it.longitude}"
//                    sendSMS(receiverNumber, message)
//                    Log.d("LocationSMS", "Sending SMS to: $receiverNumber - Message: $message") // Print message to console
//                }
//            }
//    }
//
//    private fun sendSMS(phoneNumber: String, message: String) {
//        try {
//            val smsManager: SmsManager = SmsManager.getDefault()
//            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
//            Toast.makeText(this, "SMS Sent!", Toast.LENGTH_SHORT).show()
//        } catch (e: Exception) {
//            Log.e("LocationSMS", "Error sending SMS: ${e.message}")
//            Toast.makeText(this, "Failed to send SMS. Please try again.", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun getMyPhoneNumber(): String? {
//        val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//        return if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
//            telephonyManager.line1Number
//        } else {
//            null
//        }
//    }
//}


//-------------------------------

package com.example.rescue_mate

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.telephony.SmsManager
import android.telephony.TelephonyManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private val REQUEST_PERMISSIONS = 1
    private val receiverNumber = "01710984034" // Replace with your actual receiver number
    private lateinit var locationCallback: LocationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val sendLocationButton: Button = findViewById(R.id.send_location_button)
        sendLocationButton.setOnClickListener {
            Log.d("LocationSMS", "Send Location button clicked")
            requestPermissionsIfNeeded()
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    sendLocationSMS(location)
                }
            }
        }
    }

    private fun requestPermissionsIfNeeded() {
        val permissionsNeeded = mutableListOf<String>()
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            permissionsNeeded.add(Manifest.permission.SEND_SMS)
        }

        if (permissionsNeeded.isNotEmpty()) {
            Log.d("LocationSMS", "Requesting permissions: $permissionsNeeded")
            ActivityCompat.requestPermissions(this, permissionsNeeded.toTypedArray(), REQUEST_PERMISSIONS)
        } else {
            requestLocation()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSIONS) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                Log.d("LocationSMS", "Permissions granted")
                requestLocation()
            } else {
                Log.d("LocationSMS", "Permissions denied")
                Toast.makeText(this, "Permissions required to send location SMS.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun requestLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Location permission not granted", Toast.LENGTH_SHORT).show()
            return
        }

        val locationRequest = LocationRequest.create().apply {
            interval = 10000 // 10 seconds
            fastestInterval = 5000 // 5 seconds
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    private fun sendLocationSMS(location: Location) {
        val message = "My current location is: https://www.google.com/maps/search/?api=1&query=${location.latitude},${location.longitude}"
        Log.d("LocationSMS", "Location obtained: $message")
        sendSMS(receiverNumber, message)
        fusedLocationClient.removeLocationUpdates(locationCallback) // Stop location updates after getting the location
    }

    private fun sendSMS(phoneNumber: String, message: String) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "SMS permission not granted", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
            Toast.makeText(this, "SMS Sent!", Toast.LENGTH_SHORT).show()
            Log.d("LocationSMS", "SMS sent to $phoneNumber")
        } catch (e: Exception) {
            Log.e("LocationSMS", "Error sending SMS: ${e.message}")
            Toast.makeText(this, "Failed to send SMS. Please try again.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getMyPhoneNumber(): String? {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Phone state permission not granted", Toast.LENGTH_SHORT).show()
            return null
        }

        val telephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return telephonyManager.line1Number
    }
}
