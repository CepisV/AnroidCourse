package kz.step.jsonplaceholder.presentation.users

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kz.step.jsonplaceholder.R
import kz.step.jsonplaceholder.domain.models.User

class UsersAdapter(
    private val layoutInflater: LayoutInflater,
    private val onUserClick: (userId: Int) -> Unit,
    private val onEmailClick: (email : String) -> Unit
) : androidx.recyclerview.widget.ListAdapter<User, UserViewHolder>(DIFF_CALLBACK())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = layoutInflater.inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
        holder.tvUserName.setOnClickListener { onUserClick(user.id) }
        holder.tvEmail.setOnClickListener { onEmailClick(user.email) }
    }

}

class UserViewHolder(itemView : View) : ViewHolder(itemView) {
    val tvUserName : TextView = itemView.findViewById(R.id.tv_username)
    private val tvFullName : TextView = itemView.findViewById(R.id.tv_name)
    val tvEmail : TextView = itemView.findViewById(R.id.tv_email)

    fun bind(user : User) {
        tvUserName.text = user.username
        tvFullName.text = user.name
        tvEmail.text = user.email
    }
}
private class DIFF_CALLBACK : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}