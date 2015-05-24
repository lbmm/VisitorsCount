import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * <H2>CountMuseumEntries</H2>
 * <p>
 * Reads the entering and leaving time of customers from an input file. Find
 * the time range(s) when there were the maximum number of visitors in the
 * museum and how many of them there were.
 *  As input takes a log file with time series entries of the following format.
 * HH:MM,HH:MM
 * Output the time range(s) and number
 * of visitors found to standard output in the following format: 
 * <start of time range>-<end of time range>;<number of visitors>.
 * </p>
 * 
 * 
 *
 * @author Federica Moscato
 * @version 0.1 (05/2015)
 */

public class CountMuseumEntries {

	
	
	 /**
      * Build a list of Visitor parsing a log file.
      * Prints to console if records are not well formatted 
      * @param path of the file to read
      * @return List of Visitor
      * @see Visitor
      */

	public List<Visitor> loadCSV(String cvsFile) {

		BufferedReader br = null;
		String line = "";
		String delimiter = ",";
		List<Visitor> visitorsInformation = new ArrayList<Visitor>();
		String timeType = "";

		try {

			br = new BufferedReader(new FileReader(cvsFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] values = line.split(delimiter);

				// sanity check
				if (values.length != 2) {
					System.out.println("Malformed record:" + line);
				}

				for (int i = 0; i < values.length; i++) {
					if (i % 2 == 0) {
						timeType = "in";

					} else {
						timeType = "out";
					}

					Visitor visitor = new Visitor();
					visitor.setVisitor_time(values[i]);
					visitor.setTime_type(timeType);
					visitorsInformation.add(visitor);

				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("Malformed record:" + line);
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
        
		return visitorsInformation;
	}
	
	/**
	   * CountMuseumEntries main method .
	   * @param path of the log files.
	   * @return <start of time range>-<end of time range>;<number of visitors>.
	   */
	
	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("Error - please provide a file");
			
		} else {
			String filename = args[0];
			CountMuseumEntries cme = new CountMuseumEntries();
			List<Visitor> visitorInformation = cme.loadCSV(filename);
			MuseumEntries me = new MuseumEntries();
			me.countMuseumEntries(visitorInformation);
			System.out.println( Visitor.getDateFormat().format(me.getMaxEnterTime()) + "-" +
			           Visitor.getDateFormat().format(me.getMaxExitTime()) + ":" +  me.getMaxVisitors());
		}
	}

}