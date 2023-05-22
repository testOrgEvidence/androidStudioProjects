package com.example.unitconverter

import android.app.ProgressDialog.show
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.unitconverter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.tvConvertedValue.text=""
        setContentView(binding.root)
        binding.btnConvert.setOnClickListener {

            var value = binding.etValue.text.toString().toDoubleOrNull()
            if (value == null) {
                Toast.makeText(this, "enter value", Toast.LENGTH_SHORT).show()
                binding.tvConvertedValue.text=""
            } else {
                var estimatedValue = when (binding.radioGroup.checkedRadioButtonId) {
                    R.id.btnMillimeter -> (value?.times(0.0338))
                    R.id.btnOunce -> (value?.times(29.574))
                    else -> ""

                }
                if (estimatedValue == "") {
                    binding.tvConvertedValue.text = ""
                    Toast.makeText(this, "please enter type", Toast.LENGTH_LONG).show()
                } else {
                     val type = when (binding.radioGroup.checkedRadioButtonId) {
                        R.id.btnMillimeter -> estimatedValue.toString().plus(" ounces")
                        else -> estimatedValue.toString().plus(" millimeter")
                     }
                    binding.tvConvertedValue.text = getString(R.string.`val`, type)
                    value = 0.0
                }
            }


        }
    }
}