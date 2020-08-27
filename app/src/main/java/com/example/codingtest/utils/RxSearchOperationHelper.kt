package com.example.codingtest.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

object RxSearchOperationHelper {

    fun fromView(editText: AppCompatEditText): Observable<String> {

        val subject = PublishSubject.create<String>()
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                subject.onNext(s.toString())
            }
        })

        return subject
    }

    fun fromView(editText: EditText): Observable<String> {

        val subject = PublishSubject.create<String>()
        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                subject.onNext(s.toString())
            }
        })

        return subject
    }
}
