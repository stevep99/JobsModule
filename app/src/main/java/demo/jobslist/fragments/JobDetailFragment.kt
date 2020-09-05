package demo.jobslist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import kotlinx.android.synthetic.main.fragment_job_details.*
import demo.jobslist.R
import demo.jobslist.databinding.FragmentJobDetailsBinding
import demo.jobslist.model.JobItem
import demo.jobslist.viewmodel.JobViewModel


class JobDetailFragment : Fragment() {

    private lateinit var viewModel: JobViewModel
    private lateinit var binding: FragmentJobDetailsBinding

    private val args: JobDetailFragmentArgs by navArgs()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(JobViewModel::class.java)

        val item = viewModel.jobItems[args.jobId]

        showItemDetails(item)
    }

    private fun showItemDetails(item: JobItem) {
        binding.jobItem = item

        jobStartTextView.text = resources.getString(R.string.formatted_countdown,
            item.formattedDate(resources), item.formattedCountdown(resources))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        sharedElementEnterTransition = TransitionInflater.from(this.context).inflateTransition(R.transition.change_bounds)
        sharedElementReturnTransition = TransitionInflater.from(this.context).inflateTransition(R.transition.change_bounds)

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_job_details, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setTransitionName(jobBackground, "background_${args.jobId}")
        ViewCompat.setTransitionName(jobTitleTextView, "title_${args.jobId}")
        ViewCompat.setTransitionName(jobStartTextView, "date_${args.jobId}")
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}