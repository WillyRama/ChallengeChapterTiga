package com.willyramad.challengechaptertiga

import android.graphics.drawable.Icon
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.willyramad.challengechaptertiga.databinding.FragmentAbjadBinding
import kotlinx.android.synthetic.main.fragment_abjad.*

class AbjadFragment : Fragment() {
    private var _binding : FragmentAbjadBinding? = null

    private val binding get() =_binding
    private lateinit var recyclerView: RecyclerView
    private var LinearLayoutManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentAbjadBinding.inflate(inflater, container, false)
        val view = binding?.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding!!.recyclerView
        PilihLayout()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)

        val layoutbutton = menu.findItem(R.id.action_switch_layout)
        icon(layoutbutton)
    }
    private fun PilihLayout(){
        if (LinearLayoutManager){
            recyclerView.layoutManager = LinearLayoutManager(context)
        }else{
            recyclerView.layoutManager =GridLayoutManager(context, 5)
        }
        recyclerView.adapter = AbjadAdapter()
    }
    private fun icon(menuItem: MenuItem?) {
        if (menuItem == null)
            return

        menuItem.icon =
            if (LinearLayoutManager)
                ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_grid)
            else ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_linear_layout)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_switch_layout ->{
                LinearLayoutManager = !LinearLayoutManager
                PilihLayout()
                icon(item)
                return true
            }
            else ->super.onOptionsItemSelected(item)
        }
    }
}