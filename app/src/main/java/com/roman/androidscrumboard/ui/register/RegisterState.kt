package com.roman.androidscrumboard.ui.register

import com.roman.androidscrumboard.models.User

sealed class RegisterState {
    class DefaultState(): RegisterState()
    class LoadingState(): RegisterState()
    class SuccessState(val user: User?): RegisterState()
    class ErrorState(val message: String): RegisterState()
}