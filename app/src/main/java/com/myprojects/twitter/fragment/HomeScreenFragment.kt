package com.myprojects.twitter.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myprojects.twitter.R
import com.myprojects.twitter.adapter.TweetsAdapter
import com.myprojects.twitter.databinding.HomeScreenFragmentBinding
import com.myprojects.twitter.model.Tweet
import com.myprojects.twitter.viewModel.HomeScreenViewModel
import kotlinx.android.synthetic.main.home_screen_fragment.*


class HomeScreenFragment : BaseFragment() {

    private lateinit var tweetList: ArrayList<Tweet>
    private lateinit var list: ArrayList<Tweet>
    lateinit var viewManager: LinearLayoutManager
    lateinit var viewAdapter: RecyclerView.Adapter<*>

    companion object {
        fun newInstance() = HomeScreenFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(HomeScreenViewModel::class.java)

        val binding = DataBindingUtil.inflate<HomeScreenFragmentBinding>(
            inflater,
            R.layout.home_screen_fragment,
            container,
            false
        ).apply {
            viewModel = this@HomeScreenFragment.viewModel as HomeScreenViewModel
            lifecycleOwner = viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
        btn_search_tweets.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                hideKeyboard(activity)
                filterTweet(search_tweets.text.toString())
            }

        })
    }

    fun setObserver() {
        (viewModel as HomeScreenViewModel).getTweets()
            .observe(viewLifecycleOwner, Observer { tweetResponse ->
                println("Debug Main : {${tweetResponse.data}}")
                tweetList = tweetResponse.data
                list = tweetList
                viewManager = LinearLayoutManager(context)
                viewAdapter = TweetsAdapter(context, list)
                rv_tweets.apply {
                    setHasFixedSize(true)
                    layoutManager = viewManager
                    adapter = viewAdapter
                }
            })
    }

    fun filterTweet(text: String) {
        var filterdList: ArrayList<Tweet> = ArrayList()
        val enteredText: String = text.toLowerCase()
        if (tweetList != null) {
            for (tweet: Tweet in tweetList) {
                if (tweet.name.toLowerCase()
                        .contains(enteredText) || tweet.handle.toLowerCase()
                        .contains(enteredText) || tweet.text.toLowerCase()
                        .contains(enteredText)
                ) {
                    filterdList.add(tweet)
                }
            }
        }
        list = filterdList
        if (list.isEmpty()) {
            rv_tweets.visibility = View.GONE
            empty_list.visibility = View.VISIBLE
        } else {
            rv_tweets.visibility = View.VISIBLE
            empty_list.visibility = View.GONE
        }
        (viewAdapter as TweetsAdapter).updateList(list)
        viewAdapter.notifyDataSetChanged()

    }

    fun hideKeyboard(activity: FragmentActivity?) {
        val imm: InputMethodManager =
            activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}