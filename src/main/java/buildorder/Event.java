package buildorder;

public class Event {

  private String message;
  private long time;

  public Event(String message, long time){
    this.message = message;
    this.time = time * 1000;
  }

  public String getMessage() {
    return message;
  }

  public long getTime() {
    return time;
  }
}
