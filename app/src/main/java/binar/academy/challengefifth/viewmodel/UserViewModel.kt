package binar.academy.challengefifth.viewmodel

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import binar.academy.challengefifth.data.User
import binar.academy.challengefifth.room.ApplicationDB
import java.util.concurrent.Executors

class UserViewModel(app : Application):AndroidViewModel(app) {
    val atEmail: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val atPassword: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val messageHandler = Handler(Looper.getMainLooper())

    private val sharedPrefFile = "kotlinsharedprefence"
    private var mDB: ApplicationDB? = null
    private val context by lazy {getApplication<Application>().applicationContext}
    val sharedPreferences: SharedPreferences= context.getSharedPreferences(
        sharedPrefFile,
        Context.MODE_PRIVATE
    )

    val editor : SharedPreferences.Editor=sharedPreferences.edit()
    val executor = Executors.newSingleThreadExecutor()
    val email : MutableLiveData<String> by lazy { MutableLiveData<String>()}
    val password : MutableLiveData<String> by lazy { MutableLiveData<String>()}
    val _validation : MutableLiveData<Int> by lazy { MutableLiveData<Int>()}
    val registervalidation : MutableLiveData<Int> by lazy { MutableLiveData<Int>()}
    val username : MutableLiveData<String> by lazy { MutableLiveData<String>()}
    val usernamenew : MutableLiveData<String> by lazy { MutableLiveData<String>()}
    val fullname : MutableLiveData<String> by lazy { MutableLiveData<String>()}
    val birth : MutableLiveData<String> by lazy { MutableLiveData<String>()}
    val address : MutableLiveData<String> by lazy { MutableLiveData<String>()}

    fun getInputan(putEmail:String, putPassword:String){
        atEmail.value=putEmail
        atPassword.value=putPassword
    }
    fun getValidation(): LiveData<Int>{
        return _validation
    }
    private fun runOnUiThread(action: Runnable){
        messageHandler.post(action)
    }

    fun registerAccount(user: User, confirmPassword:String){
        mDB = ApplicationDB.getInstance(context)
        if (user.password != confirmPassword){
            Toast.makeText(context, "Your password is not match", Toast.LENGTH_SHORT).show()
        }else{
            executor.execute {
                Log.d("viewModel", "success")
                val result = mDB?.userDao()?.addUser(user)
                runOnUiThread {
                    if(result != 0. toLong()){
                        Toast.makeText(context, "Register Success", Toast.LENGTH_SHORT).show()
                        registervalidation.postValue(1)
                    }else{
                        Toast.makeText(context, "Register Failed", Toast.LENGTH_SHORT).show()
                        registervalidation.postValue(0)
                    }
                }
            }
        }
    }

    fun loginAccount(){
        val emailResult = StringBuffer()
        val passwordResult = StringBuffer()
        val usernameResult = StringBuffer()
        mDB = ApplicationDB.getInstance(context)
        executor.execute {
            val user = mDB?.userDao()?. getUsername(atEmail.value)
            Log.d("this", user.toString())
            runOnUiThread(){
                user?.forEach {
                    emailResult.append(it.email)
                    passwordResult.append(it.password)
                    usernameResult.append(it.username)
                }
                email.value = emailResult.toString()
                password.value = passwordResult.toString()
                username.value = usernameResult.toString()

                if(atEmail.value == emailResult.toString() && atPassword.value == passwordResult.toString()){
                    Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()
                    _validation.postValue(1)
                    editor.putString("username_key", username.value)
                    editor.putString("password_key", password.value)
                    editor.apply()
                }else{
                    Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
                    _validation.postValue(0)
                }
            }
        }
    }

    fun setUsername(){
        val testingUser = sharedPreferences.getString("username_key", "defaultname")
        username.value = testingUser
    }

    fun loginCheck(){
        val shareUsernameValue = sharedPreferences.getString("username_key", "defaultname")
        if (shareUsernameValue != "defaultname") {
//            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
            _validation.postValue(1)
            username.value = shareUsernameValue
        }  else {
            _validation.postValue(0)
        }
    }

    fun updateUserData(user:User){
        mDB = ApplicationDB.getInstance(context)
        executor.execute {
            val result = mDB?.userDao()?.updateData(
                username = user.username,
                email = email.value,
                birthdate = user.birthDate,
                fullname = user.fullName,
                address = user.address
            )
            runOnUiThread {
                if (result != 0){
                    Toast.makeText(context,"Update Success",Toast.LENGTH_SHORT).show()
                    getUserData()
                    editor.putString("username_key", username.value)
                    editor.apply()
                }else{
                    Toast.makeText(context,"Update Failed",Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

   fun getUserData() {
        val usernameResult = StringBuffer()
        val fullnameResult = StringBuffer()
        val birthdayResult = StringBuffer()
        val addressResult = StringBuffer()

        var userName :String
        var fullName :String
        var birthDate : String
        var addressName : String
        mDB = ApplicationDB.getInstance(context)
        executor.execute {
            val user = mDB?.userDao()?.getUsername(email.value)
            runOnUiThread{
                user?.forEach { data->
//                    usernameResult.append(data.username)
//                    fullnameResult.append(data.fullName)
//                    birthdayResult.append(data.birthDate)
//                    addressResult.append(data.address)
                    userName = data.username.toString()
                    fullName = data.fullName.toString()
                    birthDate = data.birthDate.toString()
                    addressName = data.address.toString()

                }
                username.value = usernameResult.toString()
                fullname.value = fullnameResult.toString()
                birth.value = birthdayResult.toString()
                address.value = addressResult.toString()
//                editor.putString("username_key", username.value)
//                editor.apply()
            }

        }
    }
    fun logout(){
        editor.clear()
        editor.apply()
        _validation.postValue(0)
    }

}