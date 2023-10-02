package com.example.test_task_paletch_inc.presentation.screens.books

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
import com.example.test_task_paletch_inc.data.network.NYTimesApiStatus
import com.example.test_task_paletch_inc.databinding.FragmentBooksBinding
import com.example.test_task_paletch_inc.presentation.screens.SharedViewModel
import com.example.test_task_paletch_inc.presentation.screens.books.recycler.BooksAdapter

class BooksFragment : Fragment() {

    private var _binding: FragmentBooksBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedCategory: String

    companion object {
        var CATEGORIES = "categories"
    }

    private val sharedViewModel: SharedViewModel by activityViewModels()

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
        binding.recyclerView2.adapter = BooksAdapter(sharedViewModel.dataBooks) {
            val action =
                BooksFragmentDirections.actionBooksFragmentToWebFragment(
                    sharedViewModel.getLinkById(
                        it
                    )
                )
            view?.findNavController()?.navigate(action)
        }

    }

    private fun setUpObserver() {
        /*
        sharedViewModel.dataBooks.observe(viewLifecycleOwner, Observer {
            binding.recyclerView2.adapter?.notifyDataSetChanged()
            if (it.isEmpty())
                binding.textView.visibility = View.VISIBLE
        })
        */
        sharedViewModel.statusForBook.observe(viewLifecycleOwner, Observer {
            if (it == NYTimesApiStatus.DONE) {
                sharedViewModel.getBooksListByCategory(selectedCategory)
            }
        })
    }

}
