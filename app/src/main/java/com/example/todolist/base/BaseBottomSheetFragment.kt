package com.example.todolist.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.utils.UiActivity
import com.example.todolist.utils.showSystemUI
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import timber.log.Timber

abstract class BaseBottomSheetFragment<T>: BottomSheetDialogFragment() {
    protected var _binding: T? = null
    protected val binding: T get() = _binding!!
    protected lateinit var ui: UiActivity


    abstract fun bindView(inflater: LayoutInflater): View

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return bindView(inflater)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            ui = context as UiActivity
        }catch (e: ClassCastException) {
            Timber.i("$context must implement UiActivity")
        }
    }

    override fun onStart() {
        super.onStart()
        requireDialog().window!!.findViewById<View>(R.id.container)?.fitsSystemWindows = false
        requireDialog().window?.showSystemUI()
    }
}