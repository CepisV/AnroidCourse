package kz.step.jsonplaceholder.presentation.albums

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import kz.step.jsonplaceholder.R
import kz.step.jsonplaceholder.domain.models.Album

class AlbumViewHolder(view: View): ViewHolder(view) {
    val ivAlbum: ImageView = view.findViewById(R.id.iv_album)
    val tvAlbumName: TextView = view.findViewById(R.id.tv_album_name)
    val tvUsername: TextView = view.findViewById(R.id.tv_username)

    fun bind(album: Album) {
        tvAlbumName.text = album.title
        tvUsername.text = album.username

        Glide.with(ivAlbum)
            .load(album.previewPhoto)
            .into(ivAlbum)
    }
}