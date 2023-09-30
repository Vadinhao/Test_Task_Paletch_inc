package com.example.test_task_paletch_inc.presentation.screens.categories

import android.os.Bundle
import android.text.Layout.Directions
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.test_task_paletch_inc.R
import com.example.test_task_paletch_inc.constants.Constants
import com.example.test_task_paletch_inc.databinding.FragmentCategoriesBinding
import com.example.test_task_paletch_inc.presentation.screens.categories.recycler.CategoriesAdapter

class CategoriesFragment : Fragment() {

    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CategoriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(inflater)
        binding.lifecycleOwner = this
        setUpActionBar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]
        binding.viewModel = viewModel
        setUpObserver()
        setUpAdapter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpActionBar(){
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.title = getString(R.string.ny_times)
    }

    private fun setUpAdapter(){
        binding.recyclerView.adapter = CategoriesAdapter(viewModel.categoriesData) {
            val action = CategoriesFragmentDirections.actionCategoriesFragmentToBooksFragment(viewModel.getCategoryNameById(it))
            view?.findNavController()?.navigate(action)
        }
    }

    private fun setUpObserver(){
        viewModel.categoriesData.observe(viewLifecycleOwner, Observer {
            binding.recyclerView.adapter?.notifyDataSetChanged()
        })
    }

}