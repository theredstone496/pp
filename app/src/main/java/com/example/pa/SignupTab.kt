package com.example.pa

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.text.Editable
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat


class SignupTab : Fragment() {

    private lateinit var nameET : EditText
    private lateinit var emailET : EditText
    private lateinit var passwordET : EditText

    private lateinit var nameWarning : TextView
    private lateinit var emailWarning : TextView
    private lateinit var passwordWarning : TextView

    private lateinit var signupBtn : Button

    private val emailRegex = Regex("""[\w.-]+@\w+(.[\w.-]+)+""")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_signuptab, container, false)

        nameET = view.findViewById(R.id.nameET)
        emailET = view.findViewById(R.id.emailET)
        passwordET = view.findViewById(R.id.passwordET)

        nameWarning = view.findViewById(R.id.nameWarning)
        emailWarning = view.findViewById(R.id.emailWarning)
        passwordWarning = view.findViewById(R.id.passwordWarning)

        signupBtn = view.findViewById(R.id.signupBtn)

        nameET.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.gray))
        emailET.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.gray))
        passwordET.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.gray))

        nameET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                checkName(nameET.text.toString())
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })

        emailET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                checkEmail(emailET.text.toString())
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })

        passwordET.addTextChangedListener(object: TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                checkPassword(passwordET.text.toString())
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable) {}
        })

        signupBtn.setOnClickListener { view ->
            if (checkName(nameET.text.toString()) &&
                checkEmail(emailET.text.toString()) &&
                checkPassword(passwordET.text.toString())) {
                // All entries are valid, "signup"
                val builder = AlertDialog.Builder(context!!)
                builder.setTitle(R.string.successAlertTitle)
                builder.setMessage(R.string.successAlertText)
                val alertDialog : AlertDialog = builder.create()
                alertDialog.setIcon(R.drawable.ic_baseline_check_circle_24)
                alertDialog.show()
            }
            else {
                // One or more entries are invalid, failed signup
                val builder = AlertDialog.Builder(context!!)
                builder.setTitle(R.string.failureAlertTitle)
                builder.setMessage(R.string.failureAlertText)
                val alertDialog : AlertDialog = builder.create()
                alertDialog.setIcon(R.drawable.ic_baseline_error_24)
                alertDialog.show()
            }
        }

        return view
    }

    // Requirement for name: 4-16 characters
    private fun checkName(name: String): Boolean {
        if (name=="") {
            // Nothing is entered, invalid but no error
            nameET.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.gray))
            nameWarning.text = ""
            return false
        }
        else if (name.length in 4..16) {
            // Valid name entered
            nameET.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.gray))
            nameWarning.text = ""
            return true
        }
        // Invalid name entered
        nameET.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.red))
        nameWarning.text = getString(R.string.nameLengthWarning)
        return false
    }

    // Requirement for email: valid with @ and . symbol
    private fun checkEmail(email: String): Boolean {
        if (email=="") {
            // Nothing is entered, invalid but no error
            emailET.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.gray))
            emailWarning.text = ""
            return false
        }
        else if (email.matches(emailRegex)) {
            // Valid email entered
            emailET.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.gray))
            emailWarning.text = ""
            return true
        }
        // Invalid email entered
        emailET.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.red))
        emailWarning.text = getString(R.string.emailWarning)
        return false
    }

    /* Requirement for passwords:
     * At least 8 characters
     * Contains at least 1 number
     * Contains at least 1 character
     */
    private fun checkPassword(password: String): Boolean {
        if (password=="") {
            // Nothing is entered, invalid but no error
            passwordET.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.gray))
            passwordWarning.text = ""
            return false
        }
        else if (password.length < 8) {
            // Too short
            passwordET.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.red))
            passwordWarning.text = getString(R.string.passwordLengthWarning)
            return false
        }
        else if (!password.contains(Regex("""\d"""))) {
            // No numbers
            passwordET.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.red))
            passwordWarning.text = getString(R.string.passwordNumberWarning)
            return false
        }
        else if (!password.contains(Regex("""[a-zA-Z]"""))) {
            // No letters
            passwordET.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.red))
            passwordWarning.text = getString(R.string.passwordLetterWarning)
            return false
        }
        // Valid password
        passwordET.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context!!, R.color.gray))
        passwordWarning.text = ""
        return true
    }


}