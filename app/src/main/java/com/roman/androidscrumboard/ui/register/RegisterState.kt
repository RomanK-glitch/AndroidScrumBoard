package com.roman.androidscrumboard.ui.register

sealed class RegisterState {
    class DefaultState(): RegisterState()
    class LoadingState(): RegisterState()
    class SuccessState<User>(val user: User?): RegisterState()
    class ErrorState<String>(val message: String): RegisterState()
}