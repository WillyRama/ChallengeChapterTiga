package com.willyramad.challengechaptertiga

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.UserDictionary
import android.view.*
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView

class KataAdapter(private val letterId: String, context: Context):RecyclerView.Adapter<KataAdapter.KataViewHolder>(){
        private val listKata : List<String>

        init {
            val Kata = context.resources.getStringArray(R.array.words).toList()
            listKata = Kata
                .filter { it.startsWith(letterId, ignoreCase = true) }
                .shuffled()
                .take(4)
                .sorted()
        }
    class KataViewHolder (val view: View): RecyclerView.ViewHolder(view){
        val button =view.findViewById<Button>(R.id.btnitem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KataAdapter.KataViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_abjad, parent, false)

        // Setup custom accessibility delegate to set the text read
        layout.accessibilityDelegate = Accessibility

        return KataViewHolder(layout)
    }

    override fun onBindViewHolder(holder: KataAdapter.KataViewHolder, position: Int) {
        val item = listKata[position]
        val context = holder.view.context

        holder.button.text = item

        holder.button.setOnClickListener {
            val link: Uri = Uri.parse("${KataFragment.pencarian}${item}")
            val intent = Intent(Intent.ACTION_VIEW, link)
            context.startActivities(arrayOf(intent))
        }
    }
    companion object Accessibility : View.AccessibilityDelegate(){
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onInitializeAccessibilityNodeInfo(host: View?, info: AccessibilityNodeInfo?) {
            onInitializeAccessibilityNodeInfo(host, info)

            val customString = host?.context?.getString(R.string.look_up_word)
            val customClick = AccessibilityNodeInfo.AccessibilityAction(
                AccessibilityNodeInfo.ACTION_CLICK,
                customString
            )
            info?.addAction(customClick)
        }
    }

    override fun getItemCount(): Int {
        return listKata.size
    }
}