package kz.step.jsonplaceholder.presentation.albums

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import kz.step.jsonplaceholder.R
import kz.step.jsonplaceholder.domain.models.Album

class AlbumsAdapter(
    private val layoutInflater: LayoutInflater,
    private val onAlbumClick: (albumId: Int) -> Unit,
    private val onUserClick: (userId: Int) -> Unit,
) : androidx.recyclerview.widget.ListAdapter<Album, AlbumViewHolder>(DIFF_CALLBACK()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = layoutInflater.inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = getItem(position)
        holder.bind(album)
        holder.tvAlbumName.setOnClickListener { onAlbumClick(album.id) }
        holder.tvUsername.setOnClickListener { onUserClick(album.userId) }
    }
}

private class DIFF_CALLBACK : ItemCallback<Album>() {
    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }

}