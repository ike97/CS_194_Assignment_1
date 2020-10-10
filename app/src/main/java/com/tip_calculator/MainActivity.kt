package com.tip_calculator

import android.animation.ArgbEvaluator
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.WindowId
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG = "MainActivity"
private const val INITIAL_TIP_PERCENT = 15
private const val INITIAL_SPLIT_NUM = 1

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //initializations...
        seekBarTip.progress = INITIAL_TIP_PERCENT
        tipPercentTxt.text = "$INITIAL_TIP_PERCENT%"
        updateSeekBarLabel(INITIAL_TIP_PERCENT)
        etSplit.setText("$INITIAL_SPLIT_NUM")
        //set appropriate listeners...
        seekBarTip.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                // Log.d(TAG, "$progress%")
                tipPercentTxt.text = "$progress%"
                updateSeekBarLabel(progress)
                computeTipAndTotal()
                splitBill()
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {}
            override fun onStartTrackingTouch(p0: SeekBar?) {}
        })

        etBase.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                computeTipAndTotal()
                splitBill()
            }
        })

        //set text changed listener on the split
        etSplit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                //only allow them to calculate split if total is entered
                if(etSplit.text.toString().isNotEmpty()){
                    splitBill()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        //set an onFocusChanged Listener
        etSplit.setOnFocusChangeListener {
                //check to see if view is empty and update to default 1
                view, b -> if(etSplit.text.toString().isEmpty()){
                    etSplit.setText("$INITIAL_SPLIT_NUM")
                    Toast.makeText(applicationContext,"Default number of people to split bill is 1", Toast.LENGTH_LONG).show()
                }
        }
    }


    //splits the total bill between multiple people
    private fun splitBill(){
        if(etBase.text.toString().isNotEmpty()) {
            if(etSplit.text.toString().isNotEmpty()){
                val totalAmount = totalAmountTxt.text.toString().toDouble()
                val splitNum = etSplit.text.toString().toInt()
                val perPerson = totalAmount / splitNum
                tvSplitAmount.text = "%.2f".format(perPerson)
            } else {
                tvSplitAmount.text = totalAmountTxt.text.toString()
                // assumes they still want to preserve the number of splits
            }
        } else if (etBase.text.toString().isEmpty()){
            tvSplitAmount.text = ""
        }
    }

    //updates label for seekbar based on progress
    private fun updateSeekBarLabel(progress: Int){
        val tipDescription: String
        when(progress){
            in 0..9 -> tipDescription = getString(R.string.poor)
            in 10..14 -> tipDescription = getString(R.string.acceptable)
            in 15..19 -> tipDescription = getString(R.string.good)
            in 20..24 -> tipDescription = getString(R.string.great)
            else -> tipDescription = getString(R.string.amazing)
        }
        //update the tip label description
        etSplit.clearFocus() //force focus away from the split
        seekBarLabel.text = tipDescription
        val color = ArgbEvaluator().evaluate(progress.toFloat()/seekBarTip.max,
            ContextCompat.getColor(this, R.color.colorWorstTip),
            ContextCompat.getColor(this, R.color.colorBestTip)
        ) as Int
        // update the color of the text
        seekBarLabel.setTextColor(color)
    }

    //called when the editText field for base amount is changed
    private fun computeTipAndTotal(){
        if(etBase.text.toString().isEmpty()){
            totalAmountTxt.text = ""
            tipAmountTxt.text = ""
            return
        }
        val baseAmount = etBase.text.toString().toDouble()
        val tipPercent = seekBarTip.progress
        val tipAmount = (baseAmount * tipPercent)/100
        val totalAmount = baseAmount + tipAmount
        totalAmountTxt.text = "%.2f".format(totalAmount)
        tipAmountTxt.text = "%.2f".format(tipAmount)
    }
}
