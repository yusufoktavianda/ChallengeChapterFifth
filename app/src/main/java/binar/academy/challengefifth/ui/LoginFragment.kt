package binar.academy.challengefifth.ui

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import binar.academy.challengefifth.R
import binar.academy.challengefifth.databinding.FragmentLoginBinding
import binar.academy.challengefifth.viewmodel.UserViewModel
import com.bumptech.glide.Glide

class LoginFragment : Fragment() {
    private var _binding : FragmentLoginBinding?=null
    val binding: FragmentLoginBinding get()= _binding!!
    private val viewModel : UserViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        viewModel.loginCheck()
//        showImage()
//        binding.loginButton.setOnClickListener {
//            val email = binding.emailEditText.text.toString()
//            val password = binding.passwordEditText.text.toString()
//            if(email.isEmpty()||password.isEmpty()){
//                Toast.makeText(requireContext(), "Complete field!", Toast.LENGTH_SHORT).show()
//            }else{
//                viewModel.getInputan(email,password)
//                viewModel.loginAccount()
//            }
//        }
//        binding.registershowTextView.setOnClickListener {
//            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
//        }
//        navigationUi()
//    }
//    fun navigationUi(){
//        viewModel.getValidation().observe(viewLifecycleOwner){ nav ->
//            if(nav== 1){
//                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
//                Log.d("login", "testing berhasil $nav")
//            }else if (nav==0){
//                Log.d("login", "testing $nav")
//            }
//        }
//    }

//    fun showImage(){
//        Glide
//            .with(requireContext())
//            .load("https://i.ibb.co/zJHYGBP/binarlogo.jpg")
//            .centerCrop()
//            .into(binding.loginImageView)
//    }
}
