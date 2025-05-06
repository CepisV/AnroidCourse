package kz.step.jsonplaceholder.presentation.comments

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.step.jsonplaceholder.R
import kz.step.jsonplaceholder.domain.models.Comment

class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    val tvEmail: TextView = itemView.findViewById(R.id.tv_email)
    private val tvComment: TextView = itemView.findViewById(R.id.tv_comment)

    fun bind(comment: Comment) {
        tvTitle.text = comment.name
        tvEmail.text = comment.email
        tvComment.text = comment.body
    }
}