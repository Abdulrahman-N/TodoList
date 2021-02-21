package com.example.todolist.base

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.todolist.utils.UiActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import timber.log.Timber

abstract class BaseDialogFragment<T>: DialogFragment() {
    protected var _binding: T? = null
    protected val binding: T get() = _binding!!
    protected lateinit var ui: UiActivity


    private var dialogView: View? = null
    abstract fun bindView(inflater: LayoutInflater): View

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        dialogView = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            ui = context as UiActivity
        }catch (e: ClassCastException) {
            Timber.i("$context must implement UiActivity")
        }
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return MaterialAlertDialogBuilder(requireContext(), theme).apply {
            dialogView = bindView(LayoutInflater.from(requireContext()))
            onViewCreated(dialogView!!, savedInstanceState)
            setView(dialogView)
        }.create()
    }

}