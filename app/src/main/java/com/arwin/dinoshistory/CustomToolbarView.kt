package com.arwin.dinoshistory

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout
import com.arwin.dinoshistory.databinding.CustomToolbarViewBinding

class CustomToolbarView : ConstraintLayout {

    private lateinit var binding: CustomToolbarViewBinding

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attributeSet: AttributeSet?) {
        binding = CustomToolbarViewBinding.bind(
            LayoutInflater.from(context).inflate(R.layout.custom_toolbar_view, this, true)
        )

    }

    fun toolbar(): Toolbar {
        return binding.toolbar
    }

    fun setTitle(title: String) {
        binding.tvTitle.text = title
    }

    fun setEndIcon(icon: Drawable?, onClick: () -> Unit?) = with(binding) {
        ivEndIcon.setImageDrawable(icon)
        ivEndIcon.visibility = VISIBLE
        ivEndIcon.setOnClickListener {
            onClick.invoke()
        }
    }
}