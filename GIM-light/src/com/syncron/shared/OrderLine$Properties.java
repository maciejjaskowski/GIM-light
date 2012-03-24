package com.syncron.shared;
import java.util.Arrays;
import java.util.List;

public class OrderLine$Properties implements ReflectsObject {
  private final OrderLine object;
  public OrderLine$Properties(OrderLine object) { 
    super();
    this.object = object;
  }
  
  @Override public List<String> fieldNames() {
    return Arrays.asList("item", "quantity", "price");
  }
  
  @Override public Object get(String fieldName) {
    if (0 == 1) {
    } else if ("item".equals(fieldName)) {
      return object.item;
    } else if ("quantity".equals(fieldName)) {
      return object.quantity;
    } else if ("price".equals(fieldName)) {
      return object.price;
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
