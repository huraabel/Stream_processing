package tema5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamUtility {
	
	public static List<MonitoredData> getActivities(String fileName) {
		List<MonitoredData> list = new ArrayList<>();
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			stream.forEach(x -> {
				MonitoredData md = new MonitoredData();
				java.util.Date d = new java.util.Date();
				d.setYear(removeFirstZeroAndParseToInteger(x, 0, 4));
				d.setMonth(removeFirstZeroAndParseToInteger(x, 5, 7)-1);
				d.setDate(removeFirstZeroAndParseToInteger(x, 8, 10));
				d.setHours(removeFirstZeroAndParseToInteger(x, 11, 13));
				d.setMinutes(removeFirstZeroAndParseToInteger(x, 14, 16));
				d.setSeconds(removeFirstZeroAndParseToInteger(x, 17, 19));
				md.setStartTime(d);

				java.util.Date f = new java.util.Date();
				f.setYear(removeFirstZeroAndParseToInteger(x, 21, 25));
				f.setMonth(removeFirstZeroAndParseToInteger(x, 26, 28)-1);
				f.setDate(removeFirstZeroAndParseToInteger(x, 29, 31));
				f.setHours(removeFirstZeroAndParseToInteger(x, 32, 34));
				f.setMinutes(removeFirstZeroAndParseToInteger(x, 35, 37));
				f.setSeconds(removeFirstZeroAndParseToInteger(x, 38, 40));
				md.setEndTime(f);

				String act = activityBuilder(x.substring(42, 44));
				md.setActivityLabel(act);
				list.add(md);
			});

		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public static Integer removeFirstZeroAndParseToInteger(String x, int i, int j) {
		String formated = x.substring(i, j);
		if (formated.startsWith("0")) {
			formated = formated.replaceFirst("0", "");
		}
		return Integer.parseInt(formated);
	}
	
	public static String activityBuilder(String s) {

		if (s.equals("Sl")) return "Sleeping";
		if (s.equals("To")) return "Toileting";
		if (s.equals("Sh")) return "Showering";
		if (s.equals("Br")) return "Breakfast";
		if (s.equals("Gr")) return "Grooming";
		if (s.equals("Sp")) return "Spare_Time/TV";
		if (s.equals("Le")) return "Leaving";
		if (s.equals("Lu")) return "Lunch";
		if (s.equals("Sn")) return "Snack";
		if (s.equals("Di")) return "Dinner";
		return "";
	}
	
	
}
