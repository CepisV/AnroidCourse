package kz.step.jsonplaceholder.presentation.utils

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.cardview.widget.CardView
import kz.step.jsonplaceholder.R

class UserCompanyItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    var tvCompanyName : TextView
    var tvFullName : TextView
    var tvServices : TextView

    init {
        val view = inflate(context, R.layout.item_user_company, this)

        tvCompanyName = view.findViewById(R.id.tv_company_name)
        tvFullName = view.findViewById(R.id.tv_full_name)
        tvServices = view.findViewById(R.id.tv_services)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.UserInfoItem,
            0, 0
        ).apply {
            try {
                tvCompanyName.text = this.getString(R.styleable.UserCompanyItem_companyName)
                tvFullName.text = this.getString(R.styleable.UserCompanyItem_fullName)
                tvServices.text = this.getString(R.styleable.UserCompanyItem_services)
            } finally {
                recycle()
            }
        }
    }
}