package dev.ogabek.countrycodedetecter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var spinner: Spinner

    private lateinit var arrCountryCode: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        arrCountryCode = this.resources.getStringArray(R.array.DialingCountryCode)

        val code: String = getCountryCode()

        spinner = findViewById(R.id.spinner)

        if (spinner.isEmpty()) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arrCountryCode)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }

        }

    }

    private fun getCountryCode(): String {
        var countryId: String? = null
        var countryCode: String? = null

        val telephoneManager = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        countryId = telephoneManager.simCountryIso.uppercase()

        for(i in arrCountryCode.indices) {
            val arrDial = arrCountryCode[i].split(",").toTypedArray()
            if (arrDial[1].trim() == countryId.trim()) {
                countryCode = arrDial[0]
                break
            }
        }

        return countryCode!!

    }

}