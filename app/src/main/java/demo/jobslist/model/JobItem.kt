package demo.jobslist.model

import android.content.res.Resources
import demo.jobslist.R
import java.text.SimpleDateFormat
import java.util.*

data class JobItem(
    val title: String,
    val jobDate: Date,
    val details: List<String>,

    val address: String,
    val lat: Double,
    val lng: Double
    ) {

    fun formattedDate(res: Resources): String {
        val now = Calendar.getInstance()
        val cal = Calendar.getInstance()
        cal.time = jobDate
        if (cal.get(Calendar.YEAR) == now.get(Calendar.YEAR)
            && cal.get(Calendar.DAY_OF_YEAR) == now.get(Calendar.DAY_OF_YEAR)) {
            return res.getString(R.string.today) +
                SimpleDateFormat(" @ HH:mm", Locale.ENGLISH).format(jobDate)
        } else if (cal.get(Calendar.YEAR) == now.get(Calendar.YEAR)
            && cal.get(Calendar.DAY_OF_YEAR) == now.get(Calendar.DAY_OF_YEAR) + 1) {
            return res.getString(R.string.tomorrow) +
                SimpleDateFormat(" @ HH:mm", Locale.ENGLISH).format(jobDate)
        } else if (cal.get(Calendar.YEAR) == now.get(Calendar.YEAR)
            && cal.get(Calendar.DAY_OF_YEAR) < now.get(Calendar.DAY_OF_YEAR) + 6) {
            return SimpleDateFormat("EEEE @ HH:mm", Locale.ENGLISH).format(jobDate)
        } else {
            return SimpleDateFormat("EEEE, d MMM yyyy @ HH:mm", Locale.ENGLISH).format(jobDate);
        }
    }

    fun formattedCountdown(res: Resources): String {
        val now = Calendar.getInstance()
        val cal = Calendar.getInstance()
        cal.time = jobDate
        val delta = cal.time.time - now.time.time
        return if (delta in 1 until ONE_HOUR) {
            res.getString(R.string.minutes_time, (delta / ONE_MINUTE));
        } else {
            ""
        }
    }

    fun isCurrentDay(): Boolean {
        val now = Calendar.getInstance()
        val cal = Calendar.getInstance()
        cal.time = jobDate
        return (cal.get(Calendar.YEAR) == now.get(Calendar.YEAR)
            && cal.get(Calendar.DAY_OF_YEAR) == now.get(Calendar.DAY_OF_YEAR))
    }

    fun detailsTextList() : String {
        return details.joinToString("\n");
    }

    companion object {
        val ONE_MINUTE = 60 * 1000L
        val ONE_HOUR = 60 * ONE_MINUTE
        val ONE_DAY = 24 * ONE_HOUR

        val JOB_ITEMS = listOf(
            JobItem(
                "Go Shopping",
                Date(System.currentTimeMillis() + 15 * ONE_MINUTE),
                listOf("Buy bread", "Buy milk"),
                "Cardiff city centre",
                51.4816,
                -3.1791
            ),
            JobItem(
                "Job A",
                Date(System.currentTimeMillis() + ONE_HOUR),
                listOf("Do first thing", "Do second thing", "Do third thing", "Do fourth thing"),
                "29 Acacia Road, Nuttytown",
                51.4816,
                -3.1791
            ),
            JobItem(
                "Job B",
                Date(System.currentTimeMillis() + 3 * ONE_HOUR),
                listOf("Do first thing", "Do second thing"),
                "29 Acacia Road, Nuttytown",
                51.4545,
                -2.5879
            ),
            JobItem(
                "Job C",
                Date(System.currentTimeMillis() + ONE_DAY),
                listOf("Do first thing", "Do second thing", "Do third thing"),
                "29 Acacia Road, Nuttytown",
                51.4816,
                -3.1791
            ),
            JobItem(
                "Job D",
                Date(System.currentTimeMillis() + 2 * ONE_DAY),
                listOf("Do first thing", "Do second thing", "Do third thing"),
                "29 Acacia Road, Nuttytown",
                51.4545,
                -2.5879
            ),
            JobItem(
                "Job E",
                Date(System.currentTimeMillis() + 4 * ONE_DAY),
                listOf("Do first thing", "Do second thing", "Do third thing"),
                "29 Acacia Road, Nuttytown",
                51.4545,
                -2.5879
            ),
            JobItem(
                "Job F",
                Date(System.currentTimeMillis() + 60 * ONE_DAY),
                listOf("Do first thing", "Do second thing", "Do third thing"),
                "29 Acacia Road, Nuttytown",
                51.4545,
                -2.5879
            )
        )
    }

}






