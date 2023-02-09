

package com.example.android.trackr.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.Checkable
import androidx.annotation.VisibleForTesting
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import com.example.android.trackr.R

class StarButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): AppCompatImageButton(context, attrs, defStyleAttr), Checkable {

    private var _checked = false

    @VisibleForTesting
    var drawableResId = R.drawable.ic_star_border

    override fun setChecked(checked: Boolean) {
        _checked = checked
        if (checked) {
            drawableResId = R.drawable.ic_star
            contentDescription = context.getString(R.string.starred)
        } else {
            drawableResId = R.drawable.ic_star_border
            contentDescription = context.getString(R.string.unstarred)
        }
        background = ContextCompat.getDrawable(context, drawableResId)
    }

    override fun isChecked(): Boolean {
        return _checked
    }

    override fun toggle() {
        _checked = !_checked
    }
}