package demo.jobslist.fragments

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import demo.jobslist.R
import demo.jobslist.databinding.ItemJobBinding
import demo.jobslist.model.JobItem

class JobListAdapter
constructor(
    context: Context,
    private val data: List<JobItem>,
    private val listener: (View, Int) -> Unit
) : RecyclerView.Adapter<JobListAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private val context = context
    private lateinit var binding: ItemJobBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemJobBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        ViewCompat.setTransitionName(binding.jobItemLayout, "background_${position}")
        ViewCompat.setTransitionName(binding.jobTitle, "title_${position}")
        ViewCompat.setTransitionName(binding.jobDate, "date_${position}")

        holder.jobItemLayout.tag = position
        binding.jobItem = data[position]
        binding.jobDate.text = data[position].formattedDate(context.resources)
        if (data[position].isCurrentDay()) {
            val textColor = R.color.colorPrimaryDark
            binding.jobDate.setTextColor(ContextCompat.getColor(context, textColor))
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        internal var jobItemLayout = itemView as ViewGroup

        init {
            jobItemLayout.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            listener.invoke(itemView, itemView.tag as Int)
        }
    }

}
