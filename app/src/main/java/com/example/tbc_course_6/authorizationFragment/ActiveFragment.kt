package com.example.tbc_course_6.authorizationFragment

import android.content.Intent
import android.os.Bundle
import android.system.Os.accept
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tbc_course_6.MainActivity
import com.example.tbc_course_6.R
import com.example.tbc_course_6.databinding.FragmentActiveBinding
import com.example.tbc_course_6.databinding.FragmentLoginBinding
import com.example.tbc_course_6.models.DatabaseAuth
import com.example.tbc_course_6.models.User
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener


class ActiveFragment : Fragment() {

    private var _binding: FragmentActiveBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentActiveBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val databaseAuth = DatabaseAuth()

        val database = databaseAuth.database
        val auth = databaseAuth.auth


        binding.signOutBtn.setOnClickListener {
            signOut()
        }

        database.child(auth.currentUser?.uid!!).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userInfo = snapshot.getValue(User::class.java) ?:return
                binding.userNameTextView.text = userInfo.username
                binding.uidTextView.text = userInfo.uid
            }
            override fun onCancelled(error: DatabaseError) {
            }

        })




    }

    private fun signOut() {
        MaterialAlertDialogBuilder(view!!.context)
            .setTitle("Log Out")
            .setMessage(getString(R.string.sure))

            .setNegativeButton(getString(R.string.decline)) { dialog, which ->

            }
            .setPositiveButton(getString(R.string.accept)) { dialog, which ->
                FirebaseAuth.getInstance().signOut()
                logout()

            }

            .show()

    }

    private fun logout(){
        startActivity(Intent(activity, MainActivity::class.java))
        activity?.finish()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}