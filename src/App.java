package tema5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

	
	public static void main(String[] args) {
		
		Tasks t = new Tasks();
		t.showData();
		t.task1();
		t.task2();
		t.task3();
		t.task4();
		t.task5();
		t.task6();
	}
	

}
