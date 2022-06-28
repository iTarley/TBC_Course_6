package com.example.tbc_course_6.authorizationFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.tbc_course_6.R
import com.example.tbc_course_6.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import java.time.temporal.TemporalAdjusters.next


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.registerNextButton.setOnClickListener {
            registration()
        }

        binding.backBtn.setOnClickListener {
            back()
        }

    }

    private fun back(){
        findNavController().navigate(R.id.action_registerFragment_to_loggedOutFragment)
    }

    private fun registration() {
        when {
            isEmailFiled() || !isEmailValid() -> {
                binding.emailRegistrationInputLayout.error = getString(R.string.enter_your_email)
            }
            else -> {
                binding.emailRegistrationInputLayout.error = null
            }
        }
        when {
            isPasswordFiled() -> {
                binding.passwordRegistrationInputLayout.error =
                    getString(R.string.enter_correct_password)
            }
            else -> {
                binding.passwordRegistrationInputLayout.error = null

            }
        }

        if (!isEmailFiled() && !isPasswordFiled() && isEmailValid()) {
            nextPage()
        }
    }

    private fun isEmailValid(): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(binding.emailRegistrationEditText.text.toString())
            .matches()

    private fun isEmailFiled(): Boolean =
        binding.emailRegistrationEditText.text.toString().isEmpty()

    private fun isPasswordFiled(): Boolean =
        binding.passwordRegistrationEditText.text.toString().isEmpty()

    private fun nextPage() {
        sendData(
            binding.emailRegistrationEditText.text.toString(),
            binding.passwordRegistrationEditText.text.toString()
        )
    }

    private fun sendData(email: String, password: String) {
        val action = RegisterFragmentDirections
            .actionRegisterFragmentToRegisterEndFragment(email, password)
        Navigation.findNavController(binding.root).navigate(action)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}