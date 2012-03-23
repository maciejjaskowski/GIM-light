package com.syncron.shared;

import java.util.List;

public interface ReflectsObject {

	public List<String> fieldNames();

	public Object get(String fieldName);

	public List<String> actions();

	public void action(String actionName);

}