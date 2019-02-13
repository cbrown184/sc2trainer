package buildorder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BuildOrderFileReader {
  public static BuildOrder readBuildOrderFile(File file) throws FileNotFoundException {
    Scanner sc = new Scanner(file);
    String name = sc.nextLine();
    List<Event> eventList = new ArrayList<>();
    while (sc.hasNextLine()){
      String[] values = sc.nextLine().split(":");
      int minutes = Integer.valueOf(values[0]);
      int seconds = Integer.valueOf(values[1].substring(0,2));
      long time = (minutes * 60) + seconds;
      String message = values[1].substring(2);
      eventList.add(new Event(message, time));
    }
    sc.close();
    return new BuildOrder(name, eventList);
  }
}
