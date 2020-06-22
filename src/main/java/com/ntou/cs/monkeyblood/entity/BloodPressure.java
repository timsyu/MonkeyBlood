package com.ntou.cs.monkeyblood.entity;
import org.springframework.data.mongodb.core.mapping.Document;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.util.List;
import java.lang.Object;

@Document(collection = "BloodPressure")
public class BloodPressure {
	private String githubID;
	private String githubName;
	private int systolic;
	private int diastolic;
	private int pulse;
	private String date;
	@SerializedName("_id")
	private String id;
	
	public String getGithubID() {
		return githubID;
	}
	public void setGithubID(String githubID) {
		this.githubID = githubID;
	}
	public String getGithubName() {
		return githubName;
	}
	public void setGithubName(String githubName) {
		this.githubName = githubName;
	}
	public int getSystolic() {
		return systolic;
	}
	public void setSystolic(int systolic) {
		this.systolic = systolic;
	}
	public int getDiastolic() {
		return diastolic;
	}
	public void setDiastolic(int diastolic) {
		this.diastolic = diastolic;
	}
	public int getPulse() {
		return pulse;
	}
	public void setPulse(int pulse) {
		this.pulse = pulse;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
