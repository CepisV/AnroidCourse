package kz.step.jsonplaceholder.presentation.albums

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.step.jsonplaceholder.R
import kz.step.jsonplaceholder.domain.models.Album
import kz.step.jsonplaceholder.presentation.utils.SpaceItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumsFragment : Fragment() {

    val viewModel: AlbumsViewModel by viewModel()

    lateinit var rvAlbums: RecyclerView
    lateinit var adapter: AlbumsAdapter
    lateinit var progressBar: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel
        return inflater.inflate(R.layout.fragment_albums, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initAdapter()
        setupRecyclerView()
        initObservers()
    }

    private fun initViews(view: View) {
        rvAlbums = view.findViewById(R.id.rv_albums)
        progressBar = view.findViewById(R.id.progress_bar)
    }

    private fun initAdapter() {
        adapter = AlbumsAdapter(
            layoutInflater = layoutInflater,
            onAlbumClick = this::onAlbumClick,
            onUserClick = this::onUserClick
        )
    }

    private fun onAlbumClick(albumId: Int) {
        Toast.makeText(context, "Clicked album ID: $albumId", Toast.LENGTH_SHORT).show()
    }

    private fun onUserClick(userId: Int) {
        Toast.makeText(context, "Clicked user ID: $userId", Toast.LENGTH_SHORT).show()
    }

    private fun setupRecyclerView() {
        rvAlbums.layoutManager = LinearLayoutManager(context)
        rvAlbums.adapter = adapter

        val spaceItemDecoration = SpaceItemDecoration(verticalSpaceInDp = 8, horizontalSpaceInDp = 16)
        rvAlbums.addItemDecoration(spaceItemDecoration)
    }

    private fun initObservers() {
        viewModel.albums.observe(viewLifecycleOwner) { onAlbumsUpdated(it) }
        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_LONG).show()
            }
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun onAlbumsUpdated(albums: List<Album>) {
        adapter.submitList(albums)
    }
}
