package com.syncron.shared;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import com.syncron.shared.HandlesChange;

/** Generated by {@link com.syncron.rebind.SimplePropertyObjectGenerator} */
public class Order$Properties implements ReflectsObject {
  Set<HandlesChange> eventTargets = new HashSet();
  
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
      for(HandlesChange handlesChange : eventTargets) {
        handlesChange.handleChange();
      }
      return ;
    }
    throw new IllegalArgumentException();
  }
  
  @Override public void addEventTarget(HandlesChange handlesChange) {
    eventTargets.add(handlesChange);
  }
}
