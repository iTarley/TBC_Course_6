package com.example.tbc_course_6.authorizationFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.tbc_course_6.R
import com.example.tbc_course_6.databinding.ActivityLoggedOutBinding
import com.example.tbc_course_6.databinding.FragmentLoggedOutBinding
import com.google.firebase.auth.FirebaseAuth


class LoggedOutFragment : Fragment() {

    private var _binding: FragmentLoggedOutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoggedOutBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkUser()
        binding.firstLoginButton.setOnClickListener {
            login()
        }
        binding.firstRegisterButton.setOnClickListener {
            register()
        }
    }

    private fun checkUser(){
        if(FirebaseAuth.getInstance().currentUser !=null){
            goToProfile()
        }
    }

    private fun goToProfile() {
        findNavController().navigate(R.id.action_loggedOutFragment_to_activeFragment)
    }

    private fun login() {
        findNavController().navigate(R.id.action_loggedOutFragment_to_loginFragment)
    }

    private fun register() {
        findNavController().navigate(R.id.action_loggedOutFragment_to_registerFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}