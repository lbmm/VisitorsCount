import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;


/**
 * Visitor class. 
 * A visitor is consists by a visitor time and time_type (in| out)
 * 
 */
public class Visitor {
	
    /**
     * log time entry.
     **/
	Date visitor_time;
    /**
     * The time type could be an enter time or a exit time.
     **/
	String time_type;
    /**
     * Defines the date format of the log file .
     **/
	static SimpleDateFormat tempDate = new SimpleDateFormat("HH:mm");

	
	
	public Date getVisitor_time() {
		return visitor_time;
	}

	public void setVisitor_time(String visitor_time_str) throws ParseException {
		this.visitor_time = tempDate.parse(visitor_time_str);
	}

	public String getTime_type() {
		return time_type;
	}

	public void setTime_type(String time_type) {
		this.time_type = time_type;
	}

	public static SimpleDateFormat getDateFormat() {
		return tempDate;
	}
}

/**
 * Comparator for two visitors: compare the visitor_time
 *
 */
class TimeEntryComparator implements Comparator<Visitor> {
	public int compare(Visitor v1, Visitor v2) {
		return v1.getVisitor_time().compareTo(v2.getVisitor_time());
	}
}

/**
 * Comparator for two visitors: compare the visitor type
 *
 */
class TimeTypeComparator implements Comparator<Visitor> {
	public int compare(Visitor v1, Visitor v2) {
		return v1.getTime_type().compareTo(v2.getTime_type());
	}
}
