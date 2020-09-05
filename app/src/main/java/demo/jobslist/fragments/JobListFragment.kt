package demo.jobslist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import kotlinx.android.synthetic.main.fragment_job_list.*
import demo.jobslist.R
import demo.jobslist.model.JobItem
import demo.jobslist.viewmodel.JobViewModel


class JobListFragment : Fragment() {

    private lateinit var viewModel: JobViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_job_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(JobViewModel::class.java)

        loadingProgress.visibility = View.GONE

        populateRecyclerView(viewModel.jobItems)
    }

    fun populateRecyclerView(items: List<JobItem>) {
        val ctx = context ?: return

        val recyclerView = view?.findViewById<RecyclerView>(R.id.jobsRecyclerView)

        val dividerItemDecoration = DividerItemDecoration(recyclerView!!.context, VERTICAL)
        recyclerView!!.addItemDecoration(dividerItemDecoration)

        recyclerView?.layoutManager = LinearLayoutManager(ctx)

        val videoAdapter = JobListAdapter(ctx, items) {
            view, jobId ->
            val extras = FragmentNavigatorExtras(
                view to "background_${jobId}",
                view.findViewById<TextView>(R.id.jobTitle) to "title_${jobId}",
                view.findViewById<TextView>(R.id.jobDate) to "date_${jobId}"
            )
            val action = JobListFragmentDirections.actionJobListFragmentToJobDetailFragment(jobId)
            NavHostFragment.findNavController(this).navigate(action, extras)
            viewModel.currentJobItem = items[jobId]
        }
        recyclerView?.adapter = videoAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
    }

}

