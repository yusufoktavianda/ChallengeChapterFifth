package binar.academy.challengefifth.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import binar.academy.challengefifth.R
import binar.academy.challengefifth.data.User
import binar.academy.challengefifth.databinding.FragmentRegisterBinding
import binar.academy.challengefifth.room.ApplicationDB
import binar.academy.challengefifth.viewmodel.UserViewModel
import com.bumptech.glide.Glide
import java.util.concurrent.Executors

class RegisterFragment : Fragment() {
    private var _binding : FragmentRegisterBinding?=null
    private val binding get() = _binding!!
    private var mDB : ApplicationDB?=null
    private val viewModel : UserViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mDB = ApplicationDB.getInstance(requireContext())
//        val executor = Executors.newSingleThreadExecutor()
        Glide
            .with(requireContext())
            .load("https://i.ibb.co/zJHYGBP/binarlogo.jpg")
            .centerCrop()
            .into(binding.registerImageView)
        binding.registerButton.setOnClickListener {
            val conPassword = binding.confirmpasswordEditTextRegister.text.toString()
            val objectUser = User(
                username= binding.usernameEditText.text.toString(),
                password = binding.passwordEditTextRegister.text.toString(),
                email = binding.emailEditText.text.toString(),
            )
            viewModel.registerAccount(objectUser,conPassword)
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginTestingFragment())
        }
        navigationUi()
    }

    fun navigationUi(){
        viewModel.getValidation().observe(viewLifecycleOwner){ nav ->
            if(nav != 0){
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginTestingFragment())
                Log.d("register","testing berhasil $nav")
            }else{
                Log.d("register","testing gagal $nav")
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        ApplicationDB.destroyInstance()
    }
}