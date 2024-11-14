package com.android.currencyconverter

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity(){
    val TAG = "MainActivity"
    private val egyptianPound :String = "Egyptian Pound"
    private val americanPound :String = "American Pound"
    private val AED : String = "AED"
    private val GBP : String = "GBP"

    lateinit var fromCurrencyMenu : AutoCompleteTextView
    lateinit var toCurrencyMenu : AutoCompleteTextView
    lateinit var convertBtn : Button
    lateinit var amountEditText : TextInputEditText
    lateinit var resultEditText : TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializationViews()
        populateDropDownMenu()

        convertBtn.setOnClickListener {
            if(amountEditText.text.toString().isNotEmpty()){
            var amount = amountEditText.text.toString().toDouble()
            var result : Double = 0.0
            val from = fromCurrencyMenu.text.toString()
            val to = toCurrencyMenu.text.toString()
            if(from == to){
                result = amount
            } else {
                amount = when (from) {
                    "Egyptian Pound" -> amount * 0.021
                    "AED" -> amount * 0.27
                    "GBP" -> amount * 1.27
                    else -> amount
                }
                result = when (to) {
                    "Egyptian Pound" -> amount * 47.71
                    "AED" -> amount * 3.67
                    "GBP" -> amount * 0.79
                    else -> amount
                }
            }
            val finalResult = Math.ceil(result*100)/100
            resultEditText.setText(finalResult.toString())}
            else {

                val snackbar = Snackbar.make(amountEditText,"amount fieled required",Snackbar.LENGTH_SHORT)
                snackbar.show()
                snackbar.setAction("OK"){
                    Toast.makeText(this,"Please enter number in amount",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun populateDropDownMenu(){
        val listCurrency = listOf(egyptianPound,americanPound,AED,GBP)
        val adapter = ArrayAdapter(this,R.layout.drop_down_list,listCurrency)
        fromCurrencyMenu.setAdapter(adapter)
        toCurrencyMenu.setAdapter(adapter)
    }
    private fun initializationViews(){
        convertBtn = findViewById(R.id.convertButton)
        amountEditText= findViewById(R.id.amountEditText)
        resultEditText  = findViewById(R.id.resultEditText)
        fromCurrencyMenu = findViewById(R.id.fromCurrencyMenu)
        toCurrencyMenu = findViewById(R.id.toCurrencyMenu)
    }
}
