package com.syncron.shared;
import java.util.Arrays;
import java.util.List;

public class Address$Properties implements ReflectsObject {
  private final Address object;
  public Address$Properties(Address object) { 
    super();
    this.object = object;
  }
  
  @Override public List<String> fieldNames() {
    return Arrays.asList("address");
  }
  
  @Override public Object get(String fieldName) {
    if (0 == 1) {
    } else if ("address".equals(fieldName)) {
      return object.address;
    }
    throw new IllegalArgumentException();
  };
  
  @Override public List<String> actions() {
    return Arrays.asList();
  }
  
  @Override public void action(String actionName) {
    throw new IllegalArgumentException();
  }
}
