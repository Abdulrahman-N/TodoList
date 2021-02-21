package com.example.todolist.ui.addTask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.example.todolist.R
import com.example.todolist.base.BaseBottomSheetFragment
import com.example.todolist.data.Priority
import com.example.todolist.data.Todo
import com.example.todolist.databinding.FragmentAddTaskBinding
import com.example.todolist.ui.TodoListViewModel
import com.example.todolist.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskFragment : BaseBottomSheetFragment<FragmentAddTaskBinding>() {

    private val viewModel: TodoListViewModel by hiltNavGraphViewModels(R.id.main_graph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addTask()
    }

    private fun addTask(){
        binding.addBtn.setOnClickListener {
            if (binding.taskTitleTxt.text!!.isNotEmpty()) {
                val todo = Todo(
                    task = binding.taskTitleTxt.text.toString(),
                    taskDescription = binding.taskDescriptionTxt.text.toString(),
                    priority = mapPriority(binding.chipGroup.checkedChipId)
                )
                viewModel.insertTask(todo)
                dismiss()
            } else {
                toast("A task must at least have a title.")
            }
        }
    }

    private fun mapPriority(id: Int) = when(id){
            R.id.chip_high -> Priority.HIGH
            R.id.chip_medium -> Priority.MEDIUM
            else -> Priority.LOW
    }

    override fun bindView(inflater: LayoutInflater): View {
        _binding = FragmentAddTaskBinding.inflate(inflater)
        return binding.root
    }

}