package com.eaglesoft.calculator.ui.calc

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.eaglesoft.calculator.R
import com.eaglesoft.calculator.ui.history.HistoryActivity
import com.eaglesoft.calculator.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private var display: String? = ""
    private var currentOperator = ""
    private val result = ""
    private var viewModel: CalculatorViewModel? = null

    companion object {
        private val TAG = "MainActivity"
        fun getStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(CalculatorViewModel::class.java)

        butdelet.setOnClickListener { deletenumber() }
        input_box.setText(display)
    }

    private fun appendToLast(str: String?) {
        input_box!!.text.append(str)
    }

    fun onClickNumber(v: View) {
        val b = v as Button
        display += b.text
        appendToLast(display)
        display = ""
    }

    fun onClickOperator(v: View) {
        val b = v as Button
        display += b.text
        if (endsWithOperator()) {
            replace(display)
            currentOperator = b.text.toString()
            display = ""
        } else {
            appendToLast(display)
            currentOperator = b.text.toString()
            display = ""
        }
    }

    fun onClearButton(v: View?) {
        input_box!!.text.clear()
        result_box!!.text = ""
    }

    fun deletenumber() {
        input_box!!.text.delete(getinput().length - 1, getinput().length)
    }

    private fun getinput(): String {
        return input_box!!.text.toString()
    }

    private fun endsWithOperator(): Boolean {
        return getinput().endsWith("+") || getinput().endsWith("-") || getinput().endsWith("/") || getinput().endsWith(
            "x"
        )
    }

    private fun replace(str: String?) {
        input_box!!.text.replace(getinput().length - 1, getinput().length, str)
    }

    private fun operate(a: String, b: String, cp: String): Double {
        return when (cp) {
            "x" -> java.lang.Double.valueOf(a) * java.lang.Double.valueOf(b)
            "+" -> java.lang.Double.valueOf(a) + java.lang.Double.valueOf(b)
            "\u00F7" -> java.lang.Double.valueOf(a) / java.lang.Double.valueOf(b)
            "-" -> java.lang.Double.valueOf(a) - java.lang.Double.valueOf(b)
            else -> (-1).toDouble()
        }
    }


    fun equalresult(v: View?) {
        try {
            var input = getinput()
            if (!endsWithOperator()) {
                if (input.contains("x")) {
                    input = input.replace("x".toRegex(), "*")
                }
                if (input.contains("\u00F7")) {
                    input = input.replace("\u00F7".toRegex(), "/")
                }

                if (input.contains("*")) {
                    input = input.replace("*", " * ")
                }
                if (input.contains("+")) {
                    input = input.replace("+", " + ")
                }
                if (input.contains("/")) {
                    input = input.replace("/", " / ")
                }
                if (input.contains("-")) {
                    input = input.replace("-", " - ")
                }

                //MADS
                viewModel?.insertHistory(input)
                val sp =
                    input.split(" ")
                Log.e(TAG, "equalresult: $input $sp")

                val expression = ExpressionBuilder(input).build()
                val result = expression.evaluate()
                result_box!!.text = result.toString()
            } else result_box!!.text = ""
            println(result)
        } catch (e: Exception) {
            Toast.makeText(this, getString(R.string.error_invalid_input), Toast.LENGTH_SHORT).show()
            Log.e(TAG, "equalresult: ", e)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_calc, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_history -> {
                intent = HistoryActivity.getStartIntent(
                    this,
                    viewModel?._finalCalculation?.value as ArrayList<String>?
                )
                startActivity(intent)
            }
            R.id.menu_logout -> {
                FirebaseAuth.getInstance().signOut()
                intent = LoginActivity.getStartIntent(this)
                startActivity(intent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}