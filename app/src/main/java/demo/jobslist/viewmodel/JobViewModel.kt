package demo.jobslist.viewmodel

import demo.jobslist.model.JobItem
import androidx.lifecycle.ViewModel

class JobViewModel : ViewModel() {

    var currentJobItem: JobItem? = null

    val jobItems by lazy {
        JobItem.JOB_ITEMS
    }

}
