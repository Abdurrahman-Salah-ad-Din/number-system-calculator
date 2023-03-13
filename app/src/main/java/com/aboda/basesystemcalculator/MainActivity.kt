package com.aboda.basesystemcalculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.aboda.basesystemcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var buttons: List<Button>
    private lateinit var listener: View.OnClickListener
    private val numberSystemConverter = NumberSystemConverter()
    private var mapper = 9


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initButtonsList()

        initListener()

        addCallbacks()

    }

    private fun initListener() {
        listener = View.OnClickListener {
            val button = it as Button
            if (binding.buttonHex.currentTextColor == Color.WHITE)
                binding.textViewHex.text = binding.textViewHex.text.toString().plus(button.text)
            else if (binding.buttonDec.currentTextColor == Color.WHITE)
                binding.textViewDec.text = binding.textViewDec.text.toString().plus(button.text)
            else if (binding.buttonOct.currentTextColor == Color.WHITE)
                binding.textViewOct.text = binding.textViewOct.text.toString().plus(button.text)
            else if (binding.buttonBin.currentTextColor == Color.WHITE)
                binding.textViewBin.text = binding.textViewBin.text.toString().plus(button.text)
        }
    }

    private fun initButtonsList() {
        buttons = listOf(
            binding.buttonZero,
            binding.buttonOne,
            binding.buttonTwo,
            binding.buttonThree,
            binding.buttonFour,
            binding.buttonFive,
            binding.buttonSix,
            binding.buttonSeven,
            binding.buttonEight,
            binding.buttonNine,
            binding.buttonA,
            binding.buttonB,
            binding.buttonC,
            binding.buttonD,
            binding.buttonE,
            binding.buttonF
        )
    }

    private fun addCallbacks() {
        binding.buttonClear.setOnClickListener {
            clearInput()
        }

        binding.buttonBin.setOnClickListener {
            clearInput()
            prepareButtonsForBinary()
            activeNumberSystemButton(it as Button)
            deactivateTheRemainingNumberSystemButtons(binding.buttonDec,
                binding.buttonHex,
                binding.buttonOct
            )
            mapper = 2
        }

        binding.buttonHex.setOnClickListener {
            clearInput()
            prepareButtonsForHex()
            activeNumberSystemButton(it as Button)
            deactivateTheRemainingNumberSystemButtons(binding.buttonDec,
                binding.buttonBin,
                binding.buttonOct
            )
            mapper = 16
        }

        binding.buttonOct.setOnClickListener {
            clearInput()
            prepareButtonsForOct()
            activeNumberSystemButton(it as Button)
            deactivateTheRemainingNumberSystemButtons(binding.buttonDec,
                binding.buttonHex,
                binding.buttonBin
            )
            mapper = 8
        }

        binding.buttonDec.setOnClickListener {
            clearInput()
            prepareButtonsForDec()
            activeNumberSystemButton(it as Button)
            deactivateTheRemainingNumberSystemButtons(binding.buttonBin,
                binding.buttonHex,
                binding.buttonOct
            )
            mapper = 9
        }

        binding.buttonEqual.setOnClickListener {
            when (mapper) {
                9 -> {
                    binding.textViewOct.text = numberSystemConverter.decimalToOct(
                        binding.textViewDec.text.toString()
                    )
                    binding.textViewBin.text = numberSystemConverter.decimalToBin(
                        binding.textViewDec.text.toString()
                    )
                    binding.textViewHex.text = numberSystemConverter.decimalToHex(
                        binding.textViewDec.text.toString()
                    )
                }
                8 -> {
                    val decimalValue = numberSystemConverter.octToDecimal(
                        binding.textViewOct.text.toString())
                    binding.textViewDec.text = decimalValue
                    binding.textViewHex.text = numberSystemConverter.decimalToHex(
                        decimalValue)
                    binding.textViewBin.text = numberSystemConverter.decimalToBin(
                        decimalValue
                    )
                }
                16 -> {
                    val decimalValue = numberSystemConverter.hexToDecimal(
                        binding.textViewHex.text.toString())
                    binding.textViewDec.text = decimalValue
                    binding.textViewBin.text = numberSystemConverter.decimalToBin(
                        decimalValue)
                    binding.textViewOct.text = numberSystemConverter.decimalToOct(
                        decimalValue)
                }
                else -> {
                    val decimalValue = numberSystemConverter.binaryToDecimal(
                        binding.textViewBin.text.toString())
                    binding.textViewDec.text = decimalValue
                    binding.textViewHex.text = numberSystemConverter.decimalToHex(
                        decimalValue)
                    binding.textViewOct.text = numberSystemConverter.decimalToOct(
                        decimalValue)
                }
            }
        }

        binding.buttonDelete.setOnClickListener {
            when (mapper) {
                9 -> {
                    deleteOneChar(binding.textViewDec)
                }
                16 -> {
                    deleteOneChar(binding.textViewHex)
                }
                8 -> {
                    deleteOneChar(binding.textViewOct)
                }
                else -> {
                    deleteOneChar(binding.textViewBin)
                }
            }
        }

        for (i in buttons.indices) {
            buttons[i].setOnClickListener (listener)
        }

    }

    private fun deleteOneChar(textView: TextView) {
        if (textView.text.isNotEmpty()) {
            textView.text = textView.text.substring(0,
                textView.text.length - 1)
        }
    }

    private fun activeNumberSystemButton(button: Button) {
        button.setBackgroundColor(ContextCompat.getColor(this,
            R.color.selected_button_system_background))
        button.setTextColor(ContextCompat.getColor(this,
            R.color.white))
    }

    private fun deactivateTheRemainingNumberSystemButtons(button1: Button, button2: Button, button3: Button) {
        disableSystemButton(button1)
        disableSystemButton(button2)
        disableSystemButton(button3)
    }

    private fun disableSystemButton(button: Button) {
        button.setTextColor(ContextCompat.getColor(this,
            R.color.number_color))
        button.setBackgroundColor(ContextCompat.getColor(this,
            R.color.white))
    }

    private fun prepareButtonsForBinary() {
        for (i in 2 until buttons.size) {
            buttons[i].isEnabled = false
        }
    }

    private fun prepareButtonsForHex() {
        for (i in buttons.indices) {
            buttons[i].isEnabled = true
        }
    }

    private fun prepareButtonsForOct() {
        for (i in 0..7) {
            buttons[i].isEnabled = true
        }
        for (i in 8 until buttons.size) {
            buttons[i].isEnabled = false
        }
    }

    private fun prepareButtonsForDec() {
        for (i in 0..9) {
            buttons[i].isEnabled = true
        }
        for (i in 10 until buttons.size) {
            buttons[i].isEnabled = false
        }
    }

    private fun clearInput() {
        binding.textViewDec.text = ""
        binding.textViewHex.text = ""
        binding.textViewBin.text = ""
        binding.textViewOct.text = ""
    }

}