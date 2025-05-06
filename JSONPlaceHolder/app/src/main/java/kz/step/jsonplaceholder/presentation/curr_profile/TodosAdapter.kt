package kz.step.jsonplaceholder.presentation.curr_profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kz.step.jsonplaceholder.R
import kz.step.jsonplaceholder.domain.models.ToDo

class TodosAdapter(private val layoutInflater: LayoutInflater)
    : androidx.recyclerview.widget.ListAdapter<ToDo,TodoViewholder>(DIFF_CALLBACK()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewholder {
        val view = layoutInflater.inflate(R.layout.item_todo, parent, false)

        return TodoViewholder(view)
    }

    override fun onBindViewHolder(holder: TodoViewholder, position: Int) {
        val todo = getItem(position)
        holder.bind(todo)
    }
}

class TodoViewholder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    private val tvTitle : TextView = itemView.findViewById(R.id.tv_title)
    private val isDoneCheckbox : CheckBox = itemView.findViewById(R.id.cb_is_done)

    fun bind(todo : ToDo) {
        tvTitle.text = todo.title
        isDoneCheckbox.isChecked = todo.completed
    }
}

private class DIFF_CALLBACK : DiffUtil.ItemCallback<ToDo>() {
    override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
        return oldItem == newItem
    }
}