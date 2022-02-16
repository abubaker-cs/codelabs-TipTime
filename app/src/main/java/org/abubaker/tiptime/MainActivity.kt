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

        mBinding.calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    private fun calculateTip() {

        val stringInTextField = mBinding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()

        if (cost == null) {
            mBinding.tipResult.text = ""
            return
        }

        val tipPercentage = when (mBinding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost

        if (mBinding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        mBinding.tipResult.text = getString(R.string.tip_amount, formattedTip)

    }
}