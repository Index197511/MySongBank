package me.index197511.mysongbank.common_feature

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment

fun Fragment.closeKeyboard() {
    (this.activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
        view?.windowToken,
        InputMethodManager.HIDE_NOT_ALWAYS
    )
}