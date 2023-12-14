package com.capstone.venu.utils

import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.capstone.venu.R

object ValidateType {
    const val REQUIRED = 0
    const val MIN_CHAR = 1
    const val EMAIL = 3
    const val CONFIRM_PASSWORD = 4
}

fun EditText.validate(
    username: String = "field",
    validateType: Int,
    msg: String? = null,
    passwordEditText: EditText? = null
): Boolean {
    val text = this.text.toString().trim()
    when (validateType) {
        ValidateType.REQUIRED -> {
            if (text.isEmpty()) {
                var errorMessage = msg
                if (errorMessage == null) {
                    context.getString(R.string.err_msg_required, username)
                }
                this.error = errorMessage
                return false
            }
        }

        ValidateType.EMAIL -> {
            if (!text.contains("@")) {
                var errorMessage = msg
                if (errorMessage == null) {
                    errorMessage = context.getString(R.string.err_msg_email)
                }
                this.error = errorMessage
                return false
            }
        }

        ValidateType.CONFIRM_PASSWORD -> {
            val passwordText = passwordEditText?.text.toString().trim()
            if (text != passwordText) {
                var errorMessage = msg
                if (errorMessage == null) {
                    errorMessage = context.getString(R.string.err_msg_password)
                }
                this.error = errorMessage
                passwordEditText?.error = errorMessage
                return false
            }
        }
    }
    return true
}

fun AppCompatActivity.showToast(msg: String, isLong: Boolean = false) {
    if (isLong) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    } else {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}