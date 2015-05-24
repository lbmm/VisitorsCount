import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class MuseumEntriesTest {

	String dataset1 = "12:57,13:06\n" + "15:26,15:49\n" + "12:07,12:24\n"
			+ "17:11,17:28\n" + "10:28,10:44\n" + "11:24,11:45\n"
			+ "09:52,10:16\n" + "15:15,15:40\n" + "11:16,11:41\n"
			+ "13:49,14:14\n" + "12:25,12:44\n" + "10:21,10:38\n"
			+ "13:20,13:42\n" + "15:41,15:55\n" + "09:47,10:12\n"
			+ "08:14,08:25\n" + "11:09,11:31\n" + "16:40,16:56\n"
			+ "12:13,12:18\n" + "15:41,16:09";

	public List<Visitor> buildDataset(String dataset) {

		String delimiter = ",";
		List<Visitor> visitorsInformation = new ArrayList<Visitor>();
		String timeType = "";

		String[] lines = dataset.split("\n");

		try {

			for (int i = 0; i < lines.length; i++) {

				// use comma as separator
				String[] values = lines[i].split(delimiter);

				for (int j = 0; j < values.length; j++) {
					if (j % 2 == 0) {
						timeType = "in";

					} else {
						timeType = "out";
					}

					Visitor visitor = new Visitor();
					visitor.setVisitor_time(values[j]);
					visitor.setTime_type(timeType);
					visitorsInformation.add(visitor);

				}
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return visitorsInformation;
	}

	@Test
	public void test() {

		MuseumEntries me = new MuseumEntries();
		List<Visitor> visitorsInformation = buildDataset(dataset1);
		me.countMuseumEntries(visitorsInformation);
		assertEquals("15:41",
				Visitor.getDateFormat().format(me.getMaxEnterTime()));
		assertEquals("15:49",
				Visitor.getDateFormat().format(me.getMaxExitTime()));
		assertEquals(3, me.getMaxVisitors());
	}

}
