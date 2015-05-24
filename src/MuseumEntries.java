import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * MuseumEntries class
 * 
 * Class that count visitors occurrence in the museum, and
 * select the time interval whith maximum visitors.
 *
 */
public class MuseumEntries {

	/**
	 * number of maximum visitors in a time interval
	 */
	int maxVisitors = 0;
	
	/**
	 * start time of the time interval where the 
	 * number of visitors where max
	 */
	Date maxEnterTime = null;
	
	/**
	 * end time of the time interval where the 
	 * number of visitors where max
	 */
	Date maxExitTime = null;

	public int getMaxVisitors() {
		return maxVisitors;
	}

	public void setMaxVisitors(int maxVisitors) {
		this.maxVisitors = maxVisitors;
	}

	public Date getMaxEnterTime() {
		return maxEnterTime;
	}

	public void setMaxEnterTime(Date maxEnterTime) {
		this.maxEnterTime = maxEnterTime;
	}

	public Date getMaxExitTime() {
		return maxExitTime;
	}

	public void setMaxExitTime(Date maxExitTime) {
		this.maxExitTime = maxExitTime;
	}

	 /**
     * Finds max_visitors, and corresponding interval.
     * Sort all the times entries per type and time. 
     * Iterate over the sorted List and
     * 1- increase counter multipleVisitors if the type of the time_interval is a 
     *    "enterTime" (in). Check if the maxVisitor is gt or eq to the maxVisitor counter.   
     *     If gt : update maxVisitor with current multipleVisitors counter.
     * 2- decrease counter multipleVisitors if  the type of the time_interval is a 
     *    "exitTime" (out)  and update the low bound timeInterval
     * 
     * @param List of Visitor
     */
	public void countMuseumEntries(List<Visitor> entries) {

		int multipleVisitors = 0;
		boolean new_time_interval = true;

		// sort the entries per type (this allow inclusive times)
		Collections.sort(entries, new TimeTypeComparator());
		// sort per time
		Collections.sort(entries, new TimeEntryComparator());

		Iterator<Visitor> itr = entries.iterator();

		while (itr.hasNext()) {
			Visitor v = itr.next();
			if (v.getTime_type() == "in") {
				++multipleVisitors;
				if (multipleVisitors >= this.getMaxVisitors()) {
					if (multipleVisitors > this.getMaxVisitors()) {
						this.setMaxVisitors(multipleVisitors);
					}

					this.setMaxEnterTime(v.getVisitor_time());
					new_time_interval = false;

				}

			}
			if (v.getTime_type() == "out") {
				--multipleVisitors;
				if (!new_time_interval) {
					this.setMaxExitTime(v.getVisitor_time());
				}
				new_time_interval = true;
			}

		}

	}

}
