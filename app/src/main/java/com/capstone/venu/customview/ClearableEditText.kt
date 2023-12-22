package com.capstone.venu.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.capstone.venu.R

class ClearableEditText : AppCompatEditText {

    private var clearIcon: Drawable? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        clearIcon = ContextCompat.getDrawable(context, R.drawable.ic_close)
        updateIconVisibility()

        setOnTouchListener { _, event ->
            if (clearIcon != null && event.action == MotionEvent.ACTION_UP) {
                val iconStart = width - totalPaddingEnd + paddingLeft
                if (event.rawX >= iconStart) {
                    setText("")
                    return@setOnTouchListener true
                }
            }
            false
        }

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                updateIconVisibility()
            }

            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }
        })
    }

    private fun updateIconVisibility() {
        val isVisible = text?.isNotEmpty() == true
        setCompoundDrawablesRelativeWithIntrinsicBounds(
            null,
            null,
            if (isVisible) clearIcon else null,
            null
        )
    }
}
