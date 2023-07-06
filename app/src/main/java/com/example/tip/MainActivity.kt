package com.example.tip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import com.example.tip.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var tipPercentage = 10
    private var bill = 0
    private var split = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTipListener()
        setBillListener()
        setSplitListener()
    }

    private fun setBillListener() {
        binding.etBill.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                bill = p0.toString().toInt()
                countBill(bill, tipPercentage, split)
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun setTipListener() {
        binding.etTip.setText(tipPercentage.toString())
        binding.etTip.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tipPercentage = p0.toString().toInt()
                countBill(bill, tipPercentage, split)
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun setSplitListener() {
        binding.apply {
            ivMin.setOnClickListener {
                if (split > 1)
                    split -= 1
                tvSplit.text = split.toString()
                countBill(bill, tipPercentage, split)
            }
            ivPlus.setOnClickListener {
                split += 1
                tvSplit.text = split.toString()
                countBill(bill, tipPercentage, split)
            }
        }
    }

    private fun countBill(inputBill: Int, inputTipPercentage: Int, inputSplit: Int) {
        val totalTip: Double = inputBill * (inputTipPercentage * 0.01)
        val totalBill: Double = inputBill + totalTip
        val totalPerPerson: Double = totalBill / inputSplit
        val billPerPerson: Double = (inputBill / inputSplit).toDouble()
        val tipPerPerson: Double = totalTip / inputSplit
        binding.apply {
            tvTotalPerPerson.text = "$${totalPerPerson.toInt()}"
            tvTotalBill.text = "$${billPerPerson.toInt()}"
            tvTotalTip.text = "$${tipPerPerson.toInt()}"
        }
    }
}