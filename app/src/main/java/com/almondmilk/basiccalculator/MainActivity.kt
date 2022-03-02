package com.almondmilk.basiccalculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private var btn0: Button? = null
    private var btn1: Button? = null
    private var btn2: Button? = null
    private var btn3: Button? = null
    private var btn4: Button? = null
    private var btn5: Button? = null
    private var btn6: Button? = null
    private var btn7: Button? = null
    private var btn8: Button? = null
    private var btn9: Button? = null
    private var btnAC: Button? = null
    private var btnDel: Button? = null
    private var btnPlus: Button? = null
    private var btnMinus: Button? = null
    private var btnMulti: Button? = null
    private var btnEquals: Button? = null
    private var btnDecimal: Button? = null
    private var btnDivide: Button? = null
    private var textViewResult: TextView? = null
    private var textViewHistory: TextView? = null
    private var number: String? = null
    var firstNumber = 0.0
    var lastNumber = 0.0
    var status: String? = null
    var operator = false
    var myFormatter = DecimalFormat("######.######")
    var history: String? = null
    var currentResult: String? = null
    var dot = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn0 = findViewById(R.id.btnZero)
        btn1 = findViewById(R.id.btnOne)
        btn2 = findViewById(R.id.btnTwo)
        btn3 = findViewById(R.id.btnThree)
        btn4 = findViewById(R.id.btnFour)
        btn5 = findViewById(R.id.btnFive)
        btn6 = findViewById(R.id.btnSix)
        btn7 = findViewById(R.id.btnSeven)
        btn8 = findViewById(R.id.btnEight)
        btn9 = findViewById(R.id.btnNine)
        btnAC = findViewById(R.id.btnAC)
        btnDel = findViewById(R.id.btnDEL)
        btnPlus = findViewById(R.id.btnPlus)
        btnMinus = findViewById(R.id.btnMinus)
        btnMulti = findViewById(R.id.btnMultiply)
        btnDivide = findViewById(R.id.btnDivide)
        btnEquals = findViewById(R.id.btnEquals)
        btnDecimal = findViewById(R.id.btnDecimal)
        textViewHistory = findViewById(R.id.textViewHistory)
        textViewResult = findViewById(R.id.textViewResult)
        btn0!!.setOnClickListener(View.OnClickListener { v: View? ->
            numberClick(
                "0"
            )
        })
        btn1!!.setOnClickListener(View.OnClickListener { v: View? ->
            numberClick(
                "1"
            )
        })
        btn2!!.setOnClickListener(View.OnClickListener { v: View? ->
            numberClick(
                "2"
            )
        })
        btn3!!.setOnClickListener(View.OnClickListener { v: View? ->
            numberClick(
                "3"
            )
        })
        btn4!!.setOnClickListener(View.OnClickListener { v: View? ->
            numberClick(
                "4"
            )
        })
        btn5!!.setOnClickListener(View.OnClickListener { v: View? ->
            numberClick(
                "5"
            )
        })
        btn6!!.setOnClickListener(View.OnClickListener { v: View? ->
            numberClick(
                "6"
            )
        })
        btn7!!.setOnClickListener(View.OnClickListener { v: View? ->
            numberClick(
                "7"
            )
        })
        btn8!!.setOnClickListener(View.OnClickListener { v: View? ->
            numberClick(
                "8"
            )
        })
        btn9!!.setOnClickListener(View.OnClickListener { v: View? ->
            numberClick(
                "9"
            )
        })
        btnAC!!.setOnClickListener(View.OnClickListener { v: View? ->
            number = null
            status = null
            textViewResult!!.setText("0")
            textViewHistory!!.setText("")
            firstNumber = 0.0
            lastNumber = 0.0
            dot = true
        })
        btnDecimal!!.setOnClickListener(View.OnClickListener { v: View? ->
            if (dot) {
                number = if (number == null) {
                    "0."
                } else {
                    "$number."
                }
            }
            textViewResult!!.setText(number)
            dot = false
        })
        btnDel!!.setOnClickListener(View.OnClickListener { v: View? ->
            number = number?.substring(0, number!!.length - 1)
            textViewResult!!.setText(number)
        })
        btnMulti!!.setOnClickListener(View.OnClickListener { v: View? ->
            history = textViewHistory!!.getText().toString()
            currentResult = textViewResult!!.getText().toString()
            textViewHistory!!.setText("$history$currentResult*")
            if (operator) {
                if (status === "subtraction") {
                    minus()
                } else if (status === "division") {
                    divide()
                } else if (status === "sum") {
                    plus()
                } else {
                    multiply()
                }
            }
            status = "multiplication"
            operator = false
            number = null
        })
        btnMinus!!.setOnClickListener(View.OnClickListener { v: View? ->
            history = textViewHistory!!.getText().toString()
            currentResult = textViewResult!!.getText().toString()
            textViewHistory!!.setText("$history$currentResult-")
            if (operator) {
                if (status === "multiplication") {
                    multiply()
                } else if (status === "division") {
                    divide()
                } else if (status === "sum") {
                    plus()
                } else {
                    minus()
                }
            }
            status = "subtraction"
            operator = false
            number = null
        })
        btnEquals!!.setOnClickListener(View.OnClickListener { v: View? ->
            if (operator) {
                if (status === "sum") {
                    plus()
                } else if (status === "division") {
                    divide()
                } else if (status === "multiplication") {
                    multiply()
                } else if (status === "subtraction") {
                    minus()
                } else {
                    firstNumber = textViewResult!!.getText().toString().toDouble()
                }
            }
            operator = false
        })
        btnPlus!!.setOnClickListener(View.OnClickListener { v: View? ->
            history = textViewHistory!!.getText().toString()
            currentResult = textViewResult!!.getText().toString()
            textViewHistory!!.setText("$history$currentResult+")
            if (operator) {
                if (status === "multiplication") {
                    multiply()
                } else if (status === "division") {
                    divide()
                } else if (status === "subtraction") {
                    minus()
                } else {
                    plus()
                }
            }
            status = "sum"
            operator = false
            number = null
        })
        btnDivide!!.setOnClickListener(View.OnClickListener { v: View? ->
            history = textViewHistory!!.getText().toString()
            currentResult = textViewResult!!.getText().toString()
            textViewHistory!!.setText("$history$currentResult/")
            if (operator) {
                if (status === "multiplication") {
                    multiply()
                } else if (status === "subtraction") {
                    minus()
                } else if (status === "sum") {
                    plus()
                } else {
                    divide()
                }
            }
            status = "division"
            operator = false
            number = null
        })
    }

    fun numberClick(view: String) {
        number = if (number == null) {
            view
        } else {
            number + view
        }
        textViewResult!!.text = number
        operator = true
    }

    fun plus() {
        lastNumber = textViewResult!!.text.toString().toDouble()
        firstNumber = firstNumber + lastNumber
        textViewResult!!.text = myFormatter.format(firstNumber)
        dot = true
    }

    fun minus() {
        if (firstNumber == 0.0) {
            firstNumber = textViewResult!!.text.toString().toDouble()
        } else {
            lastNumber = textViewResult!!.text.toString().toDouble()
            firstNumber = firstNumber - lastNumber
        }
        textViewResult!!.text = myFormatter.format(firstNumber)
        dot = true
    }

    fun multiply() {
        if (firstNumber == 0.0) {
            firstNumber = textViewResult!!.text.toString().toDouble()
        } else {
            lastNumber = textViewResult!!.text.toString().toDouble()
            firstNumber = firstNumber * lastNumber
        }
        textViewResult!!.text = myFormatter.format(firstNumber)
        dot = true
    }

    fun divide() {
        if (firstNumber == 0.0) {
            firstNumber = textViewResult!!.text.toString().toDouble()
        } else {
            lastNumber = textViewResult!!.text.toString().toDouble()
            firstNumber = firstNumber / lastNumber
        }
        textViewResult!!.text = myFormatter.format(firstNumber)
        dot = true
    }
}