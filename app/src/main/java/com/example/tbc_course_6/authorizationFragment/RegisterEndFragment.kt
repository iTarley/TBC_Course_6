package com.example.tbc_course_6.authorizationFragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.tbc_course_6.R
import com.example.tbc_course_6.databinding.FragmentRegisterEndBinding
import com.example.tbc_course_6.models.DatabaseAuth
import com.example.tbc_course_6.models.User
import com.google.firebase.auth.FirebaseAuth


class RegisterEndFragment : Fragment() {

    private var _binding: FragmentRegisterEndBinding? = null
    private val binding get() = _binding!!

    private lateinit var email: String
    private lateinit var password: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterEndBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.textViewButton.setOnClickListener {
            redirect()
        }
        binding.registerEndSignUpButton.setOnClickListener {

            checker()
        }
        binding.backBtn.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }


    }


    private fun checker() {
        email = RegisterEndFragmentArgs.fromBundle(requireArguments()).email
        password = RegisterEndFragmentArgs.fromBundle(requireArguments()).password

        if (binding.usernameEditText.text.toString().isEmpty()) {
            binding.usernameInputLayout.error = getString(R.string.enter_your_username)
        } else {
            firebaseAuth(email, password)
        }
    }

    private fun firebaseAuth(email: String, password: String) {
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    sendToDb()
                    Toast.makeText(context, getString(R.string.success), Toast.LENGTH_SHORT).show()
                    login()
                } else {
                    Toast.makeText(context, getString(R.string.failed_sign_up), Toast.LENGTH_SHORT)
                        .show()


                }
            }
    }

    private fun sendToDb() {
        val databaseAuth = DatabaseAuth()

        val database = databaseAuth.database
        val auth = databaseAuth.auth

        val username = binding.usernameEditText.text.toString()
        val uid = FirebaseAuth.getInstance().uid ?: ""
        val userInfo = User(username, uid)
        database.child(auth.currentUser?.uid!!)
            .setValue(userInfo)

    }

    private fun redirect() {
        val intent: Intent =
            Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=dQw4w9WgXcQ"))
        startActivity(intent)
    }

    private fun login() {
        findNavController().navigate(R.id.action_registerEndFragment_to_activeFragment)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}