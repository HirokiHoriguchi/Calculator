package com.example.calculatorlikeios

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // 計算値保存
    var displayNumber               : Int = 0
    var displaySecondNumber         : Int = 0
    var isCalculateButtonPushed     : Boolean = false

    // Toast エラー表示
    val overFlowError     : String    = "OVER_FLOW_ERROR"
    val zeroDevideError   : String    = "ZERO_DEVIDED_ERROR"

    // 演算処理判定
    private lateinit var calculateMethod  : String
    private val initFunction              : String    = "INIT"
    private val plusFunction              : String    = "PLUSFUNCTION"
    private val divisionFunction          : String    = "DIVISIONFUNCTION"
    private val subtractionFunction       : String    = "SUBTRACTIONFUNCTION"
    private val multipleFunction          : String    = "MULTIPLEFUNCTION"

    // 結果表示用UI
    private lateinit var display    : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSymbolListeners()
        setNumberListeners()
        display = findViewById(R.id.display)
        calculateMethod = initFunction
    }

    private fun setSymbolListeners() {
        // 記号
        btn_ac.setOnClickListener { displayClear() }
        btn_changeSymbol.setOnClickListener { changeSymbol() }
        btn_percent.setOnClickListener { calcPercent() }


        btn_division.setOnClickListener { divisionFunction() }
        btn_multiple.setOnClickListener { multipleFunction() }
        btn_subtraction.setOnClickListener { subtractionFunction() }
        btn_plus.setOnClickListener { plusFunction() }
        btn_equal.setOnClickListener { equalFunction() }
    }


    private fun setNumberListeners() {
        // 数字
        btn0.setOnClickListener { numBottunAction(0) }
        btn1.setOnClickListener { numBottunAction(1) }
        btn2.setOnClickListener { numBottunAction(2) }
        btn3.setOnClickListener { numBottunAction(3) }
        btn4.setOnClickListener { numBottunAction(4) }
        btn5.setOnClickListener { numBottunAction(5) }
        btn6.setOnClickListener { numBottunAction(6) }
        btn7.setOnClickListener { numBottunAction(7) }
        btn8.setOnClickListener { numBottunAction(8) }
        btn9.setOnClickListener { numBottunAction(9) }
    }

    fun numBottunAction(num: Int) {
        if (!isCalculateButtonPushed) {
            var display_text = display.text.toString()
            if (display_text.length < 9
                && display_text.length == 1
                && display_text.substring(0, 1) == "0") {
                display.text = ""
            } else if (display_text.length == 9) {
                return
            }
            display.text = display.text.toString() + num.toString()
            displayNumber = (display_text + num.toString()).toInt()
        } else {
            var display_text = display.text.toString()
            if (display_text.length < 9
                && display_text.length == 1
                && display_text.substring(0, 1) == "0") {
                display.text = ""
            } else if (display_text.length == 9) {
                return
            }
            display.text = display.text.toString() + num.toString()
            displaySecondNumber = (display_text + num.toString()).toInt()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        displayClear()
    }

    fun displayClear() {
        isCalculateButtonPushed = false
        display.text = "0"
        displayNumber = 0
        displaySecondNumber = 0
    }


    private fun changeSymbol() {
        var display_text = display.text
        if (display_text.toString() != "") {
            var changeSymbolNum: Int
            changeSymbolNum = (display_text.toString().toInt() * -1)
            display.text = changeSymbolNum.toString()
            displayNumber = changeSymbolNum.toInt()
            displaySecondNumber = 0
        } else {
            display.text = "0"
            displayNumber = 0
            displaySecondNumber = 0
        }
    }

    private fun calcPercent() {
        var display_text = display.text
        Log.d("dispNum", "dispNum :" + display_text)
        var changeSymbolNum: Int
        if (display_text.toString() != "") {
            changeSymbolNum = display_text.toString().toInt() / 100
            display.text = changeSymbolNum.toString()
            displayNumber = changeSymbolNum
            displaySecondNumber = 0
            isCalculateButtonPushed = true
        }
    }


    private fun equalFunction() {
        var result: Int = 0
        var devideByZero: Boolean = false
        Log.d("calNum", "firstNum :" + displayNumber + ", secondNum:" + displaySecondNumber)
        when (calculateMethod) {
            plusFunction -> {
                result = displayNumber + displaySecondNumber
            }
            subtractionFunction -> {
                result = displayNumber - displaySecondNumber
            }
            multipleFunction -> {
                result = displayNumber * displaySecondNumber
            }
            divisionFunction -> {
                if (displaySecondNumber != 0) {
                    result = displayNumber / displaySecondNumber
                } else {
                    devideByZero = true
                }
            }
        }
        calculateMethod = initFunction
        if (result.toString().length < 10 && !devideByZero) {
            display.text = result.toString()
            displayNumber = result
            displaySecondNumber = 0
            isCalculateButtonPushed = true
        } else if (devideByZero) {
            display.text = "0"
            displayNumber = 0
            displaySecondNumber = 0
            isCalculateButtonPushed = false
            showErrorToast(zeroDevideError)
        } else {
            display.text = "0"
            displayNumber = 0
            displaySecondNumber = 0
            isCalculateButtonPushed = false
            showErrorToast(overFlowError)
        }

    }

    private fun showErrorToast(errorMsg: String) {
        when (errorMsg) {
            overFlowError -> {
                val toast = Toast.makeText(this, "計算結果がオーバーフローしました。", Toast.LENGTH_LONG)
                toast.show()
            }
            zeroDevideError -> {
                val toast = Toast.makeText(this, "ゼロ除算につき計算不能です。", Toast.LENGTH_LONG)
                toast.show()
            }
        }
    }

    private fun plusFunction() {
        isCalculateButtonPushed = true
        display.text = ""
        calculateMethod = plusFunction
    }

    private fun subtractionFunction() {
        isCalculateButtonPushed = true
        display.text = ""
        calculateMethod = subtractionFunction

    }

    private fun multipleFunction() {
        isCalculateButtonPushed = true
        display.text = ""
        calculateMethod = multipleFunction
    }

    private fun divisionFunction() {
        isCalculateButtonPushed = true
        display.text = ""
        calculateMethod = divisionFunction
    }
}
