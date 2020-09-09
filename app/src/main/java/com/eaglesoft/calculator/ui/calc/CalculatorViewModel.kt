package com.eaglesoft.calculator.ui.calc

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eaglesoft.calculator.model.Calculations

class CalculatorViewModel : ViewModel() {

    val s = "MADS"

    val _finalCalculation: MutableLiveData<List<String>>? = MutableLiveData()
    var _calculation: MutableLiveData<List<Calculations>>? = MutableLiveData()

    fun addValue(calculations: Calculations?) {
        val tempList: MutableList<Calculations> = _calculation?.value as MutableList<Calculations>
        calculations?.let { tempList.add(it) }
        _calculation?.postValue(tempList)
    }

    fun insertHistory(value: String?) {
        val tempList =
            if (_finalCalculation?.value != null) _finalCalculation.value as MutableList<String> else ArrayList()
        value?.let { tempList.add(it) }
        _finalCalculation?.postValue(tempList)
    }
}