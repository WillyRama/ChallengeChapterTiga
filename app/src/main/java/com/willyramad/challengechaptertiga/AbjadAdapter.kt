package com.willyramad.challengechaptertiga

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.acos

class AbjadAdapter : RecyclerView.Adapter<AbjadAdapter.AbjadViewHolder>(){
    private val list =('A').rangeTo('L').toList()
    class AbjadViewHolder(val view: View):RecyclerView.ViewHolder(view) {
        val button = view.findViewById<Button>(R.id.btnitem)

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbjadViewHolder {
        val layout =LayoutInflater
        .from(parent.context)
            .inflate(R.layout.item_abjad,parent, false)
        layout.accessibilityDelegate = Accessibility
        return AbjadViewHolder(layout)
    }

    override fun onBindViewHolder(holder: AbjadAdapter.AbjadViewHolder, position: Int) {
        val item = list.get(position)
        holder.button.text = item.toString()

        holder.button.setOnClickListener {
            val aksi = AbjadFragmentDirections.actionAbjadFragmentToKataFragment(letter = holder.button.text.toString())
            holder.view.findNavController().navigate(aksi)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    companion object Accessibility : View.AccessibilityDelegate() {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onInitializeAccessibilityNodeInfo(
            host: View?,
            info: AccessibilityNodeInfo?
        ) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            val customString = host?.context?.getString(R.string.look_up_words)
            val customClick =
                AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    customString
                )
            info?.addAction(customClick)
        }
    }

}