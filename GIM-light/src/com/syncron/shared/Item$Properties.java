package com.syncron.shared;
import java.util.Arrays;
import java.util.List;

public class Item$Properties implements ReflectsObject {
  private final Item object;
  public Item$Properties(Item object) { 
    super();
    this.object = object;
  }
  
  @Override public List<String> fieldNames() {
    return Arrays.asList("name", "code");
  }
  
  @Override public Object get(String fieldName) {
    if (0 == 1) {
    } else if ("name".equals(fieldName)) {
      return object.name;
    } else if ("code".equals(fieldName)) {
      return object.code;
    }
    throw new IllegalArgumentException();
  }
  
  @Override public List<String> actions() {
    return Arrays.asList();
  }
  
  @Override public void action(String actionName) {
    throw new IllegalArgumentException();
  }
}