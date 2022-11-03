package com.shift4.response;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.shift4.enums.EventType;
import com.shift4.util.EventDataDeserializer;
import com.shift4.util.Shift4Utils;

import java.util.HashMap;
import java.util.Map;

public class Event {

	private String id;
	private Long created;

	private String type;
	@JsonDeserialize(using = EventDataDeserializer.class)
	private Object data;
	private String log;

	@JsonIgnore
	private final Map<String, Object> other = new HashMap<>();

	public String getId() {
		return id;
	}

	public Long getCreated() {
		return created;
	}

	public EventType getType() {
		return EventType.fromValue(type);
	}

	public String getTypeAsString() {
		return type;
	}

	public Object getData() {
		return data;
	}

	public String getLog() {
		return log;
	}

	public String get(String name) {
		return Shift4Utils.toStringNullSafe(other.get(name));
	}

	@JsonAnySetter
	private void set(String name, Object value) {
		other.put(name, value);
	}
}
