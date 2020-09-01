package com.roman.androidscrumboard.ui.logIn

import com.roman.androidscrumboard.models.User

sealed class LogInState {
    class DefaultState: LogInState()
    class SendingState: LogInState()
    class SuccessState<User>(val user: User?): LogInState()
    class ErrorState<String>(val message: String): LogInState()
}