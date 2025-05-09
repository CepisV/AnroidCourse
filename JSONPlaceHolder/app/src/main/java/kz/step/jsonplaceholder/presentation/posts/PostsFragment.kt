package kz.step.jsonplaceholder.presentation.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.step.jsonplaceholder.R
import kz.step.jsonplaceholder.domain.models.Post
import kz.step.jsonplaceholder.presentation.utils.ClickListener
import kz.step.jsonplaceholder.presentation.utils.SpaceItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class PostsFragment : Fragment() {

    private val vm: PostsViewModel by viewModel()

    lateinit var rvPosts: RecyclerView

    lateinit var adapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)
        initAdapter()
        initRecycler()
        initObservers()
    }

    private fun initViews(view: View) {
        rvPosts = view.findViewById(R.id.rv_posts)
    }

    private fun initAdapter() {
        adapter = PostAdapter(layoutInflater)
        adapter.listener = ClickListener { post ->
            onPostClick(post)
        }
    }

    private fun onPostClick(post: Post) {
        findNavController().navigate(
            PostsFragmentDirections.actionPostsFragmentToPostDetailsFragment(
                post.id
            )
        )
    }

    private fun initRecycler() {
        val currentContext = context ?: return

        rvPosts.adapter = adapter
        rvPosts.layoutManager = LinearLayoutManager(currentContext)

        val spaceItemDecoration =
            SpaceItemDecoration(verticalSpaceInDp = 8, horizontalSpaceInDp = 16)
        rvPosts.addItemDecoration(spaceItemDecoration)
    }

    private fun initObservers() {
        vm.postsLiveData.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

}