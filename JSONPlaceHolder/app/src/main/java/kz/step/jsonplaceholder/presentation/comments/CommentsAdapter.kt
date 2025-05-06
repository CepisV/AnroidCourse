package kz.step.jsonplaceholder.presentation.comments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import kz.step.jsonplaceholder.R
import kz.step.jsonplaceholder.domain.models.Comment

class CommentsAdapter(
    private val layoutInflater: LayoutInflater,
    private val onEmailClick: (String) -> Unit,
) : androidx.recyclerview.widget.ListAdapter<Comment, CommentViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = layoutInflater.inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = getItem(position)
        holder.bind(comment)

        holder.tvEmail.setOnClickListener { onEmailClick(comment.email) }
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Comment>() {
    override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
        return oldItem == newItem
    }
}