package com.example.todolist.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import com.example.todolist.R
import com.example.todolist.base.BaseAdapter
import com.example.todolist.base.BaseViewHolder
import com.example.todolist.data.Priority
import com.example.todolist.data.Todo
import com.example.todolist.databinding.ItemTaskBinding

class TasksAdapter(
    private val taskOnClick: (Int) -> Unit,
    private val deleteOnClick: (Int) -> Unit
) :
    BaseAdapter<Todo>(object : DiffUtil.ItemCallback<Todo>() {

        override fun areItemsTheSame(oldItem: Todo, newItem: Todo) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo) = oldItem == newItem

    }) {

    override fun createView(viewType: Int, parent: ViewGroup): BaseViewHolder<Todo> {
        return TaskViewHolder.from(parent, taskOnClick, deleteOnClick)
    }
}

class TaskViewHolder(
    private val binding: ItemTaskBinding,
    private val postOnClick: (Int) -> Unit,
    private val deleteOnClick: (Int) -> Unit
) : BaseViewHolder<Todo>(binding) {
    override fun bind(item: Todo) {
        binding.apply {
            taskTitle.text = item.task
            taskDescription.text = item.taskDescription
            priorityView.backgroundTintList = when (item.priority) {
                Priority.HIGH -> ContextCompat.getColorStateList(
                    binding.root.context,
                    R.color.high_priority
                )
                Priority.MEDIUM -> ContextCompat.getColorStateList(
                    binding.root.context,
                    R.color.medium_priority
                )
                Priority.LOW -> ContextCompat.getColorStateList(
                    binding.root.context,
                    R.color.low_priority
                )
            }

            root.setOnClickListener { postOnClick(absoluteAdapterPosition) }
            deleteBtn.setOnClickListener { deleteOnClick(absoluteAdapterPosition) }

        }
    }

    companion object {
        fun from(
            parent: ViewGroup,
            taskOnClick: (Int) -> Unit,
            deleteOnClick: (Int) -> Unit
        ): TaskViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemTaskBinding.inflate(layoutInflater, parent, false)
            return TaskViewHolder(binding, taskOnClick, deleteOnClick)
        }
    }
}