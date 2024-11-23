package com.example.sport;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import android.os.Bundle
import android.util.Log
import data.events

class FirebaseManager {

    private val db = Firebase.firestore

    fun fetchEvents(callback: (Map<Int, List<events>>) -> Unit) {
        db.collection("events")
            .get()
            .addOnSuccessListener { result ->
                val eventsByMonth = mutableMapOf<Int, MutableList<events>>()

                for (document in result) {
                    val event = document.toObject(events::class.java)
                    if (event.month.toIntOrNull() in 1..12) {
                        eventsByMonth.getOrPut(event.month.toInt()) { mutableListOf() }.add(event)
                    }
                }

                callback(eventsByMonth)
            }
            .addOnFailureListener { exception ->
                Log.e("Firestore", "Error getting documents", exception)
            }
    }
}

class EventFilterManager(private val firebaseManager: FirebaseManager) {

    fun getEventsForMonth(month: Int, callback: (List<events>) -> Unit) {
        firebaseManager.fetchEvents { eventsByMonth ->
            val events = eventsByMonth[month] ?: emptyList()
            callback(events)
        }
    }
}

