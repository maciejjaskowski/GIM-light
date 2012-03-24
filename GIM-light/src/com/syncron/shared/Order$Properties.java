package com.syncron.shared;
import java.util.Arrays;
import java.util.List;

public class Order$Properties implements ReflectsObject {
  private final Order object;
  public Order$Properties(Order object) { 
    super();
    this.object = object;
  }
  
  @Override public List<String> fieldNames() {
    return Arrays.asList("number", "date", "buyerAddress", "status", "lines");
  }
  
  @Override public Object get(String fieldName) {
    if (0 == 1) {
    } else if ("number".equals(fieldName)) {
      return object.number;
    } else if ("date".equals(fieldName)) {
      return object.date;
    } else if ("buyerAddress".equals(fieldName)) {
      return object.buyerAddress;
    } else if ("status".equals(fieldName)) {
      return object.status;
    } else if ("lines".equals(fieldName)) {
      return object.lines;
    }
    throw new IllegalArgumentException();
  }
  
  @Override public List<String> actions() {
    return Arrays.asList("confirm");
  }
  
  @Override public void action(String actionName) {
    if ("confirm".equals(actionName)) {
      object.confirm();
      return ;
    }
    throw new IllegalArgumentException();
  }
}
