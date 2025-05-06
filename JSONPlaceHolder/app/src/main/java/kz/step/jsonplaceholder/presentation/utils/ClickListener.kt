package kz.step.jsonplaceholder.presentation.utils

fun interface ClickListener<T : Any> {
    fun onClick(item: T)
}