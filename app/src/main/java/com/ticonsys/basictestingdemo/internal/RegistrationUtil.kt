package com.ticonsys.basictestingdemo.internal

object RegistrationUtil {

    private val existingUsers = listOf("Peter", "Carl")

    /**
     * the input is not valid if
     * ...the username/password is empty
     * ...the username already taken
     * ...the confirmed password is not same as the real password
     * ...the password contains less then 2 digits
     */
    fun validateRegistrationInput(
        username: String,
        password: String,
        confirmPassword: String
    ): Boolean{
        if(username.isEmpty() || password.isEmpty()){
            return false
        }

        if(username in existingUsers){
            return false
        }
        if(confirmPassword != password){
            return false
        }
        if(password.count { it.isDigit() } < 2){
            return false
        }

        return true
    }

}