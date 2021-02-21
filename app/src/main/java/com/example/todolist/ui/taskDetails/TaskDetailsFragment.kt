package com.example.todolist.ui.taskDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.example.todolist.R
import com.example.todolist.base.BaseDialogFragment
import com.example.todolist.data.Priority
import com.example.todolist.databinding.FragmentTaskDetailsBinding
import com.example.todolist.ui.TodoListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TaskDetailsFragment : BaseDialogFragment<FragmentTaskDetailsBinding>() {

    private val viewModel: TodoListViewModel by hiltNavGraphViewModels(R.id.main_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.apply {
            taskTitleTxt.text = viewModel.selectedTask.task
            taskDescriptionTxt.text = viewModel.selectedTask.taskDescription
            priorityTxt.text = viewModel.selectedTask.priority.name
            priorityTxt.backgroundTintList = ContextCompat.getColorStateList(
                requireContext(),
                getPriorityColor(viewModel.selectedTask.priority)
            )
        }
    }

    private fun getPriorityColor(priority: Priority) =
        when (priority) {
            Priority.HIGH -> R.color.high_priority
            Priority.MEDIUM -> R.color.medium_priority
            Priority.LOW -> R.color.low_priority
        }

    override fun bindView(inflater: LayoutInflater): View {
        _binding = FragmentTaskDetailsBinding.inflate(inflater)
        return binding.root
    }
}