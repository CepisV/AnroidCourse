package kz.step.jsonplaceholder.presentation.extensions

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes

fun <T : View> Activity.lazyView(@IdRes viewId: Int) : Lazy<T> = lazy { findViewById(viewId)}
