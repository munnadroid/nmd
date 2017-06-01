package com.awecode.nmd.view.login

import android.os.Bundle
import android.widget.EditText
import com.awecode.nmd.MainActivity
import com.awecode.nmd.R
import com.awecode.nmd.util.PrefsHelper
import com.awecode.nmd.util.extensions.launchActivity
import com.awecode.nmd.util.extensions.toast
import com.awecode.nmd.view.base.BaseActivity
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.Email
import com.mobsandgeeks.saripaar.annotation.NotEmpty
import com.mobsandgeeks.saripaar.annotation.Order
import com.mobsandgeeks.saripaar.annotation.Password
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by munnadroid on 5/30/17.
 */

class LoginActivity : BaseActivity(), Validator.ValidationListener {

    override val layoutId: Int = R.layout.activity_login

    @NotEmpty(message = "Email cannot be empty.")
    @Order(1)
    @Email
    private var mEmailEditText: EditText? = null

    @NotEmpty(message = "Password cannot be empty.")
    @Order(2)
    @Password(scheme = Password.Scheme.ANY, min = 6, message = "Enter atleast 6 characters.")
    private var mPasswordEditText: EditText? = null

    val validator = Validator(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mEmailEditText = emailEditText
        mPasswordEditText = passwordEditText

        validator?.validationMode = Validator.Mode.IMMEDIATE
        validator?.setValidationListener(this)

        submitButton?.setOnClickListener { validator?.validate() }
    }

    override fun onValidationSucceeded() {
        val username = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (username == "admin@gmail.com"
                && password == "admin1") {
            PrefsHelper.setLoginStatus(true)
            launchActivity<MainActivity> { }
            finish()
        } else
            toast("Please enter valid credentials.")
    }

    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        // Since, we're using Mode.IMMEDIATE, we'll be notified as soon
        // as a view fails validation. So, the collection will always have
        // only one item.
        val error = errors?.get(0)
        val message = error?.getCollatedErrorMessage(this)
        val editText = error?.view as EditText
        editText.error = message
        editText.requestFocus()
    }


}
