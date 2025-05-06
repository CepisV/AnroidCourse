package kz.step.jsonplaceholder.presentation.posts.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.step.jsonplaceholder.R
import kz.step.jsonplaceholder.domain.models.Comment
import kz.step.jsonplaceholder.domain.models.Post
import kz.step.jsonplaceholder.domain.models.User
import kz.step.jsonplaceholder.presentation.comments.CommentsAdapter
import kz.step.jsonplaceholder.presentation.utils.SpaceItemDecoration
import kz.step.jsonplaceholder.presentation.extensions.startEmail
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PostDetailsFragment : Fragment() {

    private val args: PostDetailsFragmentArgs by navArgs()

    private val viewModel: PostDetailsViewModel by viewModel {
        parametersOf(args.postId)
    }

    private lateinit var tvAuthor: TextView
    private lateinit var tvTitle: TextView
    private lateinit var tvBody: TextView
    private lateinit var tvShowAllComments: TextView
    private lateinit var rvComments: RecyclerView

    private lateinit var adapter: CommentsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel

        return inflater.inflate(R.layout.fragment_post_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initAdapter()
        setupRecyclerView()
        observeViewModel()
    }

    private fun initViews(view: View) {
        with(view) {
            tvAuthor = findViewById(R.id.tv_author)
            tvTitle = findViewById(R.id.tv_title)
            tvBody = findViewById(R.id.tv_body)
            tvShowAllComments = findViewById(R.id.tv_show_all)
            rvComments = findViewById(R.id.rv_comments)
        }

        tvShowAllComments.setOnClickListener {
            findNavController().navigate(
                PostDetailsFragmentDirections.actionPostDetailsFragmentToCommentsFragment(args.postId)
            )
        }
    }

    private fun initAdapter() {
        adapter = CommentsAdapter(
            layoutInflater = layoutInflater,
            onEmailClick = ::onEmailClick
        )
    }

    private fun onEmailClick(email: String) {
        context?.startEmail(email)
    }

    private fun setupRecyclerView() {
        rvComments.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@PostDetailsFragment.adapter
            addItemDecoration(SpaceItemDecoration(8, 16))
        }
    }

    private fun observeViewModel() {
        viewModel.post.observe(viewLifecycleOwner, ::bindPost)
        viewModel.author.observe(viewLifecycleOwner, ::bindAuthor)
        viewModel.comments.observe(viewLifecycleOwner, ::bindComments)
    }

    private fun bindPost(post: Post) {
        tvTitle.text = post.title
        tvBody.text = post.body
    }

    private fun bindAuthor(user: User) {
        tvAuthor.text = user.name
    }

    private fun bindComments(comments: List<Comment>) {
        adapter.submitList(comments)
    }
}
