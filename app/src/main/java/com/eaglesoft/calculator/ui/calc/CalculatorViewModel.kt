package com.eaglesoft.calculator.ui.calc

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eaglesoft.calculator.model.Calculations

class CalculatorViewModel : ViewModel() {

    val s = "MADS"

    val _finalCalculation: MutableLiveData<List<Calculations>>? = null
    var _calculation: MutableLiveData<List<Calculations>>? = null

    fun addValue(calculations: Calculations?) {
        val tempList: MutableList<Calculations> = _calculation?.value as MutableList<Calculations>
        calculations?.let { tempList.add(it) }
        _calculation?.postValue(tempList)
    }

    fun calculate() {
        for (calculation in _calculation?.value!!) {

        }
    }
}