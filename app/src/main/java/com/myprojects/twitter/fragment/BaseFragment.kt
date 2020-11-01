package com.myprojects.twitter.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.myprojects.twitter.viewModel.BaseViewModel

open class BaseFragment : Fragment() {
    protected lateinit var viewModel: BaseViewModel

    companion object {
        fun newInstance() = BaseFragment()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}