package com.moviles.puntodos.ui.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.moviles.puntodos.R
import com.moviles.puntodos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val ciudades = resources.getStringArray(R.array.ciudades)
        val adapter = ArrayAdapter(this, R.layout.list_item, ciudades)
        with(binding.autoCompleteTextView){
            setAdapter(adapter)
        }

        binding.dateButton.setOnClickListener {
            val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
            datePicker.show(supportFragmentManager, getString(R.string.fecha_de_nacimiento))
        }

        binding.button.setOnClickListener{
            registro()
        }
    }


    private fun registro() {
        val name = binding.nameEditText.text.toString()
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        val repeatPassword = binding.passwordRepeatEditText.text.toString()
        val genre = if (binding.radioButton.isChecked) resources.getString(R.string.female) else resources.getString(R.string.male)
        val diaNacimiento = binding.dateButton.text.toString()
        val city = binding.autoCompleteTextView.text.toString()
        var favoritos = ""
        if (binding.checkBox.isChecked) favoritos += binding.checkBox.text.toString() + " "
        if (binding.checkBox2.isChecked) favoritos += binding.checkBox2.text.toString()+ " "
        if (binding.checkBox3.isChecked) favoritos += binding.checkBox3.text.toString()+ " "
        if (binding.checkBox4.isChecked) favoritos += binding.checkBox4.text.toString()+ " "
        if (binding.checkBox5.isChecked) favoritos += binding.checkBox5.text.toString()+ " "
        if (binding.checkBox6.isChecked) favoritos += binding.checkBox6.text.toString()+ " "
        val info = "Name: $name\nEmail: $email\ngenero: $genre\nfavoritos: $favoritos\nfecha de nacimiento: $diaNacimiento\n ciudad de nacimiento: $city"
        if (password == repeatPassword) {

            binding.passwordRepeatTextInputLayout.isErrorEnabled = false
            if (password.isEmpty()){
                binding.passwordTextInputLayout.error = getString(R.string.empt)

            } else {
                binding.passwordRepeatTextInputLayout.isErrorEnabled = false
                binding.resultTextView.text = info
            }
        } else {
            binding.passwordRepeatTextInputLayout.isErrorEnabled = false
            Toast.makeText(applicationContext, getString(R.string.err), Toast.LENGTH_LONG)
                .show()
            binding.passwordRepeatTextInputLayout.error = getString(R.string.err)
            Snackbar.make(
                binding.signUpLinearLayaout,
                getString(R.string.err),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        binding.dateButton.text = buildString {
            append(day)
            append("/")
            append(month)
            append("/")
            append(year)
        }
    }
}