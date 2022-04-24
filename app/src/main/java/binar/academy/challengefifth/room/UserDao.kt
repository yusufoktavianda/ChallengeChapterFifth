package binar.academy.challengefifth.room

import androidx.room.*
import binar.academy.challengefifth.data.User

@Dao
interface UserDao {
    @Insert
    fun addUser(user: User) : Long
    @Update
    fun updateUser(user: User)
    @Delete
    fun deleteUser(user: User)
    @Query("SELECT * FROM user WHERE email = :email")
    fun getUsername(email:String?=null):List<User>
    @Query("UPDATE User SET username = :username ,fullName=:fullname ,birthDate= :birthdate ,address= :address WHERE email= :email")
    fun updateData(email: String?=null, username:String?=null, fullname:String?=null, birthdate:String?=null, address:String?=null):Int
}