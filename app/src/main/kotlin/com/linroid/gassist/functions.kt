package com.linroid.gassist

import android.support.annotation.AttrRes
import android.util.TypedValue

/**
 * @author linroid <linroid@gmail.com>
 * @since 20/07/2017
 */

/**
 * 根据属性ID获取资源 ID
 * @param attr 属性ID
 */
fun attr(@AttrRes attr: Int): Int {
    val theme = App.get().theme;
    val typedValue = TypedValue()
    theme.resolveAttribute(attr, typedValue, true)
    return typedValue.resourceId
}