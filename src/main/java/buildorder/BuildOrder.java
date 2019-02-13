package buildorder;

import java.util.List;

public class BuildOrder {
  private List<Event> eventList;
  private String buildOrderName;
  public BuildOrder(String buildOrderName, List<Event> eventList){
   this.buildOrderName = buildOrderName;
   this.eventList = eventList;
  }

  public String getBuildOrderName() {
    return buildOrderName;
  }

  public List<Event> getEventList() {
    return eventList;
  }
}
