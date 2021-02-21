package com.example.todolist.ui.tasks

import android.os.Bundle
import android.view.View
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.base.BaseFragment
import com.example.todolist.databinding.FragmentTasksBinding
import com.example.todolist.ui.TodoListViewModel
import com.example.todolist.utils.navigate
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.insetter.applySystemWindowInsetsToMargin
import dev.chrisbanes.insetter.applySystemWindowInsetsToPadding
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class TasksFragment : BaseFragment<FragmentTasksBinding>(R.layout.fragment_tasks) {

    private val viewModel: TodoListViewModel by hiltNavGraphViewModels(R.id.main_graph)
    private lateinit var tasksAdapter: TasksAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupInsets()
        openAddTask()
        setupRecycler()
        observeTasks()
    }

    private fun openAddTask(){
        binding.addFab.setOnClickListener {
            navigate(R.id.action_tasksFragment_to_addTaskFragment)
        }
    }

    private fun setupRecycler() {
        tasksAdapter = TasksAdapter(taskOnClick = this::openTask, deleteOnClick = this::deleteTask)
        tasksAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.END){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                deleteTask(viewHolder.absoluteAdapterPosition)
            }

        }).attachToRecyclerView(binding.tasksRecycler)
        binding.tasksRecycler.apply {
            this.adapter = tasksAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeTasks() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.tasks.collectLatest {
                tasksAdapter.submitList(it)
            }
        }
    }

    private fun setupInsets() {
        binding.toolbar.applySystemWindowInsetsToPadding(top = true)
        binding.addFab.applySystemWindowInsetsToMargin(bottom = true)
        binding.tasksRecycler.applySystemWindowInsetsToPadding(bottom = true)
    }

    private fun openTask(position: Int) {
        viewModel.selectedTask = tasksAdapter.currentList[position]
        navigate(R.id.action_tasksFragment_to_taskDetailsFragment)
    }

    private fun deleteTask(position: Int) = viewModel.deleteTask(tasksAdapter.currentList[position].id)

    override fun bindView(view: View) {
        _binding = FragmentTasksBinding.bind(view)
    }
}