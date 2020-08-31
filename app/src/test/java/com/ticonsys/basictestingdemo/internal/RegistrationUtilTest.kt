package com.ticonsys.basictestingdemo.internal

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class RegistrationUtilTest{

    @Test
    fun emptyUsernameReturnsFalse(){
        val result = RegistrationUtil.validateRegistrationInput(
            "",
            "123456",
            "123456"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun validUsernameAndCorrectlyRepeatedPasswordReturnsTrue(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Philip",
            "123456",
            "123456"
        )
        assertThat(result).isTrue()
    }

    @Test
    fun usernameAlreadyExistsReturnsFalse() {
        val result = RegistrationUtil.validateRegistrationInput(
            "Peter",
            "123456",
            "123456"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun emptyPasswordReturnsFalse(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Philip",
            "",
            "123456"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun repeatedPasswordNotSameReturnsFalse(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Philip",
            "123457",
            "123456"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun passwordLengthLessThanTwoDigitsReturnsFalse(){
        val result = RegistrationUtil.validateRegistrationInput(
            "Philip",
            "pass1",
            "pass1"
        )
        assertThat(result).isFalse()
    }


}