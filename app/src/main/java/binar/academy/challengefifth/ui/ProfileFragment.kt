package binar.academy.challengefifth.ui

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import binar.academy.challengefifth.data.User
import binar.academy.challengefifth.databinding.FragmentProfileBinding
import binar.academy.challengefifth.viewmodel.UserViewModel
import java.util.*

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private var usernameValue = "default"
    private val viewModel: UserViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.setEmail()
        viewModel.loginCheck()
        viewModel.getUserData()
        setField()
        binding.logoutButton.setOnClickListener {
            Toast.makeText(requireContext(), "you're logout  $usernameValue", Toast.LENGTH_SHORT).show()
            viewModel.logout()
            findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToLoginTestingFragment())
        }
        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)
        var date: Any? = null
//        edit text listerner

        binding.textInputLayout3Profile.setEndIconOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val calendarShow = DatePickerDialog(
                    requireActivity(),
                    { view, mYear: Int, mMonth: Int, mDay: Int ->
                        binding.birthdayEditText.setText(mDay.toString() + "-" + (mMonth + 1).toString() + "-" + mYear.toString())
                        Log.d("profile", month.toString())
                        date =
                            mDay.toString() + "-" + (mMonth + 1).toString() + "-" + mYear.toString()
                    },
                    year,
                    month,
                    day
                )
                calendarShow.show()
            } else {
                date = binding.birthdayEditText.text.toString()
            }
        }

        binding.updateButton.setOnClickListener {

            Log.d("profile", binding.usernameEditText.text.toString())
            Log.d("profile", binding.fullnameEditText.text.toString())
            Log.d("profile", binding.birthdayEditText.text.toString())
            Log.d("profile", binding.addressEditText.text.toString())
                val usernameET= binding.usernameEditText.text.toString()
                val fulnameET = binding.fullnameEditText.text.toString()
                val birthdateET = binding.birthdayEditText.text.toString()
                val addressET = binding.addressEditText.text.toString()

            val user = User(
                username = usernameET,
                fullName = fulnameET,
                birthDate = birthdateET,
                address =  addressET
            )
            Log.d("testing", user.toString())
            viewModel.updateUserData(user)
            setField()
        }
    }

    private fun setField() {
        viewModel.username.observe(viewLifecycleOwner) {
            if (it != "null") {
                binding.usernameEditText.setText(it)
            }
        }
        viewModel.fullname.observe(viewLifecycleOwner) {
            if (it != "null") {
                binding.fullnameEditText.setText(it)
            }
        }
        viewModel.birth.observe(viewLifecycleOwner) {
            if (it != "null") {
                binding.birthdayEditText.setText(it)
            }
        }
        viewModel.address.observe(viewLifecycleOwner) {
            if (it != "null") {
                binding.addressEditText.setText(it)
            }
        }
    }

}