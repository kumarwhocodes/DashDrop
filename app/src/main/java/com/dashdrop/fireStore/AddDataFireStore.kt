package com.dashdrop.fireStore

import android.util.Log
import androidx.navigation.NavController
import com.dashdrop.data.model.DeliveryAddress
import com.dashdrop.data.model.Payment
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

