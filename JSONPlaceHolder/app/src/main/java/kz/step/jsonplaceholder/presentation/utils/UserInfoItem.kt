package kz.step.jsonplaceholder.presentation.utils

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.cardview.widget.CardView
import kz.step.jsonplaceholder.R

class UserInfoItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    var tvEmail : TextView
    var tvName : TextView
    var tvPhone : TextView
    var tvWebsite : TextView

    init {
        val view = inflate(context, R.layout.item_user_info, this)

        tvEmail = view.findViewById(R.id.tv_email)
        tvName = view.findViewById(R.id.tv_name)
        tvPhone = view.findViewById(R.id.tv_phone)
        tvWebsite = view.findViewById(R.id.tv_website)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.UserInfoItem,
            0, 0
        ).apply {
            try {
                tvEmail.text = this.getString(R.styleable.UserInfoItem_email)
                tvName.text = this.getString(R.styleable.UserInfoItem_name)
                tvPhone.text = this.getString(R.styleable.UserInfoItem_phone)
                tvWebsite.text = this.getString(R.styleable.UserInfoItem_website)
            } finally {
                recycle()
            }
        }
    }


}