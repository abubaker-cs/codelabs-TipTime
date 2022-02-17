package org.abubaker.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import org.abubaker.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inflate the activity_main.xml file
        // mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        //
        mBinding.calculateButton.setOnClickListener {
            calculateTip()
        }

    }

    /**
     * Function to calculate percentage based on the selected tip-level and cost of the service.
     */
    private fun calculateTip() {

        // We are storing the user provided cost of the service and converting it toString()
        val stringInTextField = mBinding.costOfServiceEditText.text.toString()

        // Parses the string as a [Double] number and returns the result
        // or `null` if the string is not a valid representation of a number.
        val cost = stringInTextField.toDoubleOrNull()

        // If the user has not provided any value, then return a null/blank result
        if (cost == null) {
            mBinding.tipResult.text = ""
            return
        }

        // Check the value received from the RadioButton
        // Identify, how much %age the user as decided to offer as a tip, i.e. 15%, 18% or 20%
        val tipPercentage = when (mBinding.tipOptions.checkedRadioButtonId) {

            // 0.20 for 20%
            R.id.option_twenty_percent -> 0.20

            // 0.18 for 18%
            R.id.option_eighteen_percent -> 0.18

            // 0.15 for 15% (Default)
            else -> 0.15

        }

        // Formula: Tip = Selected %age * Cost
        var tip = tipPercentage * cost

        // Has the user decided to round the result?
        if (mBinding.roundUpSwitch.isChecked) {

            // Round the result after 0.00 value
            tip = kotlin.math.ceil(tip)

        }

        // Convert | Assign the user's system currency symbol to the "tip"/result
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        // Update the #tip_mount textField with the formatted tip value.
        mBinding.tipResult.text = getString(R.string.tip_amount, formattedTip)

    }
}