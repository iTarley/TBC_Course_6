package com.example.tbc_course_6.authorizationFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.tbc_course_6.R
import com.example.tbc_course_6.databinding.FragmentLoggedOutBinding
import com.example.tbc_course_6.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginLogInButton.setOnClickListener {
            login()
        }
        binding.backBtn.setOnClickListener {
            back()
        }
    }

    private fun back() {
        findNavController().navigate(R.id.action_loginFragment_to_loggedOutFragment)
    }

    private fun login() {
        val email = binding.emailLoginEditText.text.toString()
        val password = binding.passwordLoginEditText.text.toString()

        when {
            isEmailFiled() || !isEmailValid() -> {
                binding.emailLoginInputLayout.error = getString(R.string.enter_your_email)
            }
            else -> {
                binding.emailLoginInputLayout.error = null
            }
        }
        when {
            isPasswordFiled() -> {
                binding.passwordLoginInputLayout.error = getString(R.string.enter_correct_password)
            }
            else -> {
                binding.passwordLoginInputLayout.error = null

            }
        }
        if (!isEmailFiled() && !isPasswordFiled()) {
            authentication(email, password)
        }
    }

    private fun authentication(email: String, password: String) {
        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Welcome", Toast.LENGTH_SHORT).show()
                    authentication()

                } else {
                    binding.emailLoginInputLayout.error = getString(R.string.not_match)
                }
            }
    }

    private fun isEmailValid(): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(binding.emailLoginEditText.text.toString())
            .matches()

    private fun isEmailFiled(): Boolean =
        binding.emailLoginEditText.text.toString().isEmpty()

    private fun isPasswordFiled(): Boolean =
        binding.passwordLoginEditText.text.toString().isEmpty()

    private fun authentication() {
        findNavController().navigate(R.id.action_loginFragment_to_activeFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}