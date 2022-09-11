package com.willyramad.challengechaptertiga

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.willyramad.challengechaptertiga.databinding.FragmentAbjadBinding
import com.willyramad.challengechaptertiga.databinding.FragmentKataBinding

class KataFragment : Fragment() {
    companion object{
        val LETTER = "letter"
        val pencarian = "https://www.google.com/search?q="
    }
    private var _binding: FragmentKataBinding? = null
    private val binding get() = _binding!!

    private lateinit var letterid:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            letterid = it?.getString(LETTER).toString()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =FragmentKataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = KataAdapter(letterid, requireContext())

        recyclerView.addItemDecoration(
            DividerItemDecoration(context,DividerItemDecoration.VERTICAL)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}