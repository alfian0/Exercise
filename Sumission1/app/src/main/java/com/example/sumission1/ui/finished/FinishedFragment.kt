package com.example.sumission1.ui.finished

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sumission1.EventListAdapter
import com.example.sumission1.R
import com.example.sumission1.data.response.ListEventsItem
import com.example.sumission1.databinding.FragmentFinishedBinding
import com.example.sumission1.databinding.FragmentUpcomingBinding
import com.example.sumission1.ui.detail.DetailActivity
import com.example.sumission1.ui.upcoming.UpcomingViewModel

class FinishedFragment : Fragment() {
    private var binding: FragmentFinishedBinding? = null
    private val viewModel by viewModels<FinishedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFinishedBinding.inflate(inflater, container, false)
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)

        viewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        viewModel.events.observe(viewLifecycleOwner) { events ->
            setEventData(events)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            Toast.makeText(binding?.root?.context, message, Toast.LENGTH_LONG).show()
        }

        return binding?.root
    }

    private fun setEventData(events: List<ListEventsItem>) {
        val adapter = EventListAdapter { event ->
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_EVENT, event)
            startActivity(intent)
        }
        adapter.submitList(events)
        binding?.recyclerView?.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding?.progressBar?.visibility = View.VISIBLE
        } else {
            binding?.progressBar?.visibility = View.GONE
        }
    }
}