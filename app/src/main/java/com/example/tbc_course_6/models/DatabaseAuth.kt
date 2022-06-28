package com.example.tbc_course_6.models

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class DatabaseAuth {

    val database = FirebaseDatabase.getInstance("https://tbc-course-6-1db9e-default-rtdb.europe-west1.firebasedatabase.app/").getReference("User")
    val auth = FirebaseAuth.getInstance()
}