package kz.step.jsonplaceholder.presentation.users

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.step.jsonplaceholder.R
import kz.step.jsonplaceholder.domain.models.User
import kz.step.jsonplaceholder.presentation.utils.SpaceItemDecoration
import kz.step.jsonplaceholder.presentation.extensions.startEmail
import org.koin.androidx.viewmodel.ext.android.viewModel

class UsersFragment : Fragment() {

    val viewModel: UsersFragmentViewmodel by viewModel()

    lateinit var rvUsers: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var adapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initAdapter()
        setupRecyclerView()
        initObservers()
        viewModel.getUsers()
    }

    private fun initViews(view: View) {
        with(view) {
            rvUsers = findViewById(R.id.rv_users)
            progressBar = findViewById(R.id.progress_bar)
        }
    }

    private fun initAdapter() {
        adapter = UsersAdapter(
            layoutInflater = layoutInflater,
            onUserClick = this::onUserClick,
            onEmailClick = this::onEmailClick
        )
    }

    private fun onUserClick(userId: Int) {
        findNavController().navigate(UsersFragmentDirections.actionUsersFragmentToUserProfileFragment(userId = userId))
    }

    private fun onEmailClick(email: String) {
        context?.startEmail(email)
    }

    private fun setupRecyclerView() {
        rvUsers.adapter = adapter
        rvUsers.layoutManager = LinearLayoutManager(context)

        val spaceItemDecoration = SpaceItemDecoration(verticalSpaceInDp = 8, horizontalSpaceInDp = 16)
        rvUsers.addItemDecoration(spaceItemDecoration)
    }

    private fun initObservers() {
        viewModel.usersLiveData.observe(viewLifecycleOwner) { users ->
            onUsersUpdated(users)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            toggleLoading(isLoading)
        }
    }

    private fun onUsersUpdated(users: List<User>) {
        adapter.submitList(users)
    }

    private fun toggleLoading(isLoading: Boolean) {
        if (isLoading) {
            progressBar.visibility = View.VISIBLE
            rvUsers.visibility = View.GONE
        } else {
            progressBar.visibility = View.GONE
            rvUsers.visibility = View.VISIBLE
        }
    }
}
