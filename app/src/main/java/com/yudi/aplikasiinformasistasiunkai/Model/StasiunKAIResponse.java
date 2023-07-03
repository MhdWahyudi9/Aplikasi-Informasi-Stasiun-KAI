package com.yudi.aplikasiinformasistasiunkai.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class StasiunKAIResponse{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<StasiunKAI> data;

	@SerializedName("message")
	private String message;

	public int getCode(){
		return code;
	}

	public List<StasiunKAI> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}
}