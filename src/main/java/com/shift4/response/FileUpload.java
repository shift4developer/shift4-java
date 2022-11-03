package com.shift4.response;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shift4.enums.FileUploadPurpose;
import com.shift4.enums.FileUploadType;
import com.shift4.util.Shift4Utils;

import java.util.HashMap;
import java.util.Map;

public class FileUpload {
	private String id;
	private Long created;
	private String purpose;
	private Integer size;
	private String type;
	private String url;

	@JsonIgnore
	private final Map<String, Object> other = new HashMap<>();

	public String getId() {
		return id;
	}

	public Long getCreated() {
		return created;
	}

	public FileUploadPurpose getPurpose() {
		return FileUploadPurpose.fromValue(purpose);
	}

	public String getPurposeAsString() {
		return purpose;
	}

	public Integer getSize() {
		return size;
	}

	public FileUploadType getType() {
		return FileUploadType.fromValue(type);
	}

	public String getTypeAsString() {
		return type;
	}

	public String getUrl() {
		return url;
	}

	public String get(String name) {
		return Shift4Utils.toStringNullSafe(other.get(name));
	}

	@JsonAnySetter
	private void set(String name, Object value) {
		other.put(name, value);
	}
}
