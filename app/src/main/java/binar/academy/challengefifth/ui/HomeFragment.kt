package binar.academy.challengefifth.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import binar.academy.challengefifth.R
import binar.academy.challengefifth.adapter.RecyclerAdapter
import binar.academy.challengefifth.databinding.FragmentHomeBinding
import binar.academy.challengefifth.modal.Result
import binar.academy.challengefifth.viewmodel.RecyclerViewModel
import binar.academy.challengefifth.viewmodel.UserViewModel


class HomeFragment : Fragment() {
    private val viewModel : RecyclerViewModel by viewModels()
    private val viewModelUser : UserViewModel  by viewModels()
    private var _binding : FragmentHomeBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModelUser.setUsername()
        viewModelUser.setUsername()
        setUsername()
//        viewModelUser.getUserData()
        fetchAllData()

        binding.personImageView.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToProfileFragment())
        }
    }
    private fun setUsername(){
        viewModelUser.username.observe(viewLifecycleOwner){
            binding.welcomeTextView.text=it.toString()
        }
    }
    private fun fetchAllData(){
        viewModel.movie.observe(viewLifecycleOwner){resultMovie ->
            val adapter = RecyclerAdapter(resultMovie)
            val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            binding.homeRecyclerView.layoutManager=layoutManager
            binding.homeRecyclerView.adapter=adapter
        }
//        viewModelUser.username.observe(viewLifecycleOwner){
//            binding.welcomeTextView.text = it.toString()
//            Log.d("tag", it)
//        }
    }

//    private fun showList(data : List<Result>?){
//        val adapter = RecyclerAdapter(object : RecyclerAdapter.onClickListener{
//            override fun onClickItem(data: Result) {
//
//            }
//        })
//        adapter.submitData(data)
//        binding.homeRecyclerView.adapter=adapter
//    }
}