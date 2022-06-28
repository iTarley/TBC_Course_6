package com.example.tbc_course_6.models

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class DatabaseAuth {
    //ეს მაქვს გატანილი ცალკე კლასში, რადგან წესით ეს უნდა იყოს .gitignore რომ არავის ჰქონდეს წვდომა ბაზასთან
    //ეს არის საშინაო დავალება და რა თქმა უნდა მაგის გამო არ გავუწერ .gitignore
    val database = FirebaseDatabase.getInstance("https://tbc-course-6-1db9e-default-rtdb.europe-west1.firebasedatabase.app/").getReference("User")
    val auth = FirebaseAuth.getInstance()
}
