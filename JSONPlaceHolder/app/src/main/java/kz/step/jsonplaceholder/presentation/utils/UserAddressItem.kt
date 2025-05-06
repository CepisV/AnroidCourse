package kz.step.jsonplaceholder.presentation.utils

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.cardview.widget.CardView
import kz.step.jsonplaceholder.R


class UserAddressItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    var tvStreet: TextView
    var tvSuite: TextView
    var tvCity: TextView
    var tvZipcode: TextView
    var btnMap : TextView

    init {
        val view = inflate(context, R.layout.item_user_address, this)

        tvStreet = view.findViewById(R.id.tv_street)
        tvSuite = view.findViewById(R.id.tv_suite)
        tvCity = view.findViewById(R.id.tv_city)
        tvZipcode = view.findViewById(R.id.tv_zipcode)
        btnMap = view.findViewById(R.id.btn_show_map)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.UserInfoItem,
            0, 0
        ).apply {
            try {
                tvStreet.text = this.getString(R.styleable.UserAddressItem_street)
                tvSuite.text = this.getString(R.styleable.UserAddressItem_suite)
                tvCity.text = this.getString(R.styleable.UserAddressItem_city)
                tvZipcode.text = this.getString(R.styleable.UserAddressItem_zipcode)

            } finally {
                recycle()
            }
        }
    }

}