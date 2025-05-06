package kz.step.jsonplaceholder.presentation.curr_profile

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kz.step.jsonplaceholder.R
import kz.step.jsonplaceholder.domain.models.User
import kz.step.jsonplaceholder.presentation.utils.UserAddressItem
import kz.step.jsonplaceholder.presentation.utils.UserCompanyItem
import kz.step.jsonplaceholder.presentation.utils.UserInfoItem
import kz.step.jsonplaceholder.presentation.extensions.openLink
import kz.step.jsonplaceholder.presentation.extensions.showMap
import kz.step.jsonplaceholder.presentation.extensions.startEmail
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

private const val CURR_USER_ID = 1
private const val REQUEST_CALL_PERMISSION = 42

class CurrProfileFragment : Fragment() {

    private val vm: CurrProfileViewModel by viewModel {
        parametersOf(CURR_USER_ID)
    }

    private lateinit var currUser: User

    private lateinit var tvUsername: TextView
    private lateinit var itemUserInfo: UserInfoItem
    private lateinit var itemUserCompany: UserCompanyItem
    private lateinit var itemUserAddress: UserAddressItem
    private lateinit var btnMyToDos: CardView

    private var pendingPhoneNumber: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_curr_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initObservers()
    }

    private fun initViews(view: View) {
        with(view) {
            tvUsername = findViewById(R.id.tv_username)
            itemUserInfo = findViewById(R.id.item_user_info)
            itemUserAddress = findViewById(R.id.item_user_address)
            itemUserCompany = findViewById(R.id.item_user_company)
            btnMyToDos = findViewById(R.id.cv_mytodos)
        }

        itemUserInfo.tvEmail.setOnClickListener {
            context?.startEmail(currUser.email)
        }

        itemUserInfo.tvPhone.setOnClickListener {
            startCall(currUser.phone)
        }

        itemUserInfo.tvWebsite.setOnClickListener {
            context?.openLink(currUser.website)
        }

        btnMyToDos.setOnClickListener {
            findNavController().navigate(
                CurrProfileFragmentDirections.actionCurrProfileFragmentToToDosFragment(
                    CURR_USER_ID
                )
            )
        }

        itemUserAddress.btnMap.setOnClickListener {
            context?.showMap(currUser.address.geo.lat, currUser.address.geo.lng)
        }
    }

    private fun startCall(phone: String) {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            pendingPhoneNumber = phone
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CALL_PHONE),
                REQUEST_CALL_PERMISSION
            )
        } else {
            callPhone(phone)
        }
    }

    private fun callPhone(phone: String) {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phone"))
        startActivity(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CALL_PERMISSION &&
            grantResults.isNotEmpty() &&
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        ) {
            pendingPhoneNumber?.let {
                callPhone(it)
                pendingPhoneNumber = null
            }
        }
    }

    private fun initObservers() {
        vm.currUser.observe(viewLifecycleOwner) { user ->
            currUser = user

            tvUsername.text = user.username

            itemUserInfo.tvEmail.text = user.email
            itemUserInfo.tvName.text = user.name
            itemUserInfo.tvPhone.text = user.phone
            itemUserInfo.tvWebsite.text = user.website

            itemUserCompany.tvCompanyName.text = user.company.name
            itemUserCompany.tvFullName.text = user.company.catchPhrase
            itemUserCompany.tvServices.text = user.company.bs

            itemUserAddress.tvStreet.text = user.address.street
            itemUserAddress.tvSuite.text = user.address.suite
            itemUserAddress.tvCity.text = user.address.city
            itemUserAddress.tvZipcode.text = user.address.zipcode
        }
    }
}
