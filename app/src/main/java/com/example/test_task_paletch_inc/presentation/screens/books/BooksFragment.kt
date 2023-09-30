package com.example.test_task_paletch_inc.presentation.screens.books

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.test_task_paletch_inc.R
import com.example.test_task_paletch_inc.databinding.FragmentBooksBinding
import com.example.test_task_paletch_inc.databinding.FragmentCategoriesBinding
import com.example.test_task_paletch_inc.data.network.NYTimesApiStatus
import com.example.test_task_paletch_inc.presentation.screens.books.recycler.BooksAdapter

class BooksFragment : Fragment() {

    private var _binding: FragmentBooksBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedCategory: String

    companion object {
        var CATEGORIES = "categories"
    }

    private lateinit var viewModel: BooksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            selectedCategory = it.getString(CATEGORIES).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBooksBinding.inflate(inflater)
        binding.lifecycleOwner = this
        setUpActionBar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[BooksViewModel::class.java]
        binding.viewModel = viewModel
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

    private fun setUpAdapter(){
        binding.recyclerView2.adapter = BooksAdapter(viewModel.booksData) {
            val action =
                BooksFragmentDirections.actionBooksFragmentToWebFragment(viewModel.getLinkById(it))
            view?.findNavController()?.navigate(action)
        }
    }

    private fun setUpObserver(){
        viewModel.booksData.observe(viewLifecycleOwner, Observer {
            binding.recyclerView2.adapter?.notifyDataSetChanged()
            if (it.isEmpty())
                binding.textView.visibility = View.VISIBLE
        })
        viewModel.status.observe(viewLifecycleOwner, Observer {
            if (it == NYTimesApiStatus.DONE) {
                viewModel.getBooksList(selectedCategory)
            }
        })
    }

}
