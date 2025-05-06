package kz.step.jsonplaceholder.presentation.comments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.step.jsonplaceholder.R
import kz.step.jsonplaceholder.domain.models.Comment
import kz.step.jsonplaceholder.presentation.utils.SpaceItemDecoration
import kz.step.jsonplaceholder.presentation.extensions.startEmail
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CommentsFragment : Fragment() {

    lateinit var rvComments: RecyclerView
    lateinit var progressBar: View

    val args: CommentsFragmentArgs by navArgs()
    val viewModel: CommentsViewModel by viewModel {
        parametersOf(args.postId)
    }

    lateinit var adapter: CommentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel
        return inflater.inflate(R.layout.fragment_comments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initAdapter()
        setupRecyclerView()
        initObservers()
    }

    private fun initViews(view: View) {
        with(view) {
            rvComments = findViewById(R.id.rv_comments)
            progressBar = findViewById(R.id.progress_bar)
        }
    }

    private fun initAdapter() {
        adapter = CommentsAdapter(
            layoutInflater = layoutInflater,
            onEmailClick = this::onEmailClick
        )
    }

    private fun onEmailClick(email: String) {
        context?.startEmail(email)
    }

    private fun setupRecyclerView() {
        rvComments.layoutManager = LinearLayoutManager(context)
        rvComments.adapter = adapter

        val spaceItemDecoration = SpaceItemDecoration(verticalSpaceInDp = 8, horizontalSpaceInDp = 16)
        rvComments.addItemDecoration(spaceItemDecoration)
    }

    private fun initObservers() {
        viewModel.comments.observe(viewLifecycleOwner) { onCommentsUpdated(it) }
        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun onCommentsUpdated(comments: List<Comment>) {
        adapter.submitList(comments)
    }
}
