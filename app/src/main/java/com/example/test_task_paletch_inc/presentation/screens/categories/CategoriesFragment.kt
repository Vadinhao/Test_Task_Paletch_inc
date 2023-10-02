package com.example.test_task_paletch_inc.presentation.screens.categories

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.test_task_paletch_inc.R
import com.example.test_task_paletch_inc.databinding.FragmentCategoriesBinding
import com.example.test_task_paletch_inc.presentation.screens.SharedViewModel
import com.example.test_task_paletch_inc.presentation.screens.categories.recycler.CategoriesAdapter

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
        }
        setUpActionBar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
        setUpAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpActionBar() {
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.title = getString(R.string.ny_times)
    }

    private fun setUpAdapter() {
        binding.recyclerView.adapter = CategoriesAdapter(sharedViewModel.dataCategories) {
            val action = CategoriesFragmentDirections.actionCategoriesFragmentToBooksFragment(
                sharedViewModel.getCategoryNameById(it)
            )
            view?.findNavController()?.navigate(action)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpObserver() {
        sharedViewModel.dataCategories.observe(viewLifecycleOwner, Observer {
            binding.recyclerView.adapter?.notifyDataSetChanged()
            binding.noDataTextView.visibility = View.GONE
            if(sharedViewModel.dataCategories.value!!.isEmpty()){
                binding.noDataTextView.visibility = View.VISIBLE
            }
        })
    }

    override fun onPause() {
        super.onPause()
        binding.loadingImageView.visibility = View.GONE
    }
}