package com.ntou.cs.monkeyblood.entity;
import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BloodPressureRequest implements Serializable{
	
	@NotEmpty(message = "This User isn't here.")
	private String GithubID;
	
	@NotEmpty(message = "This User isn't have name.")
	private String GithubName;
	
	@NotEmpty(message = "There is no date.")
	private String Date;
	
	@NotNull
	@Min(value = 0, message = "systolic shold be greater than 0.")
	private int Systolic;
	
	@NotNull
	@Min(value = 0, message = "diastolic shold be greater than 0.")
	private int Diastolic;
	
	@NotNull
	@Min(value = 0, message = "pulse shold be greater than 0.")
	private int Pulse;
	
	public String getGithubID() {
		return GithubID;
	}
	
	public void setGithubID(String GithubID) {
		this.GithubID = GithubID;
	}
	
	public String getGithubName() {
		return GithubName;
	}
	
	public void setGithubName(String GithubName) {
		this.GithubName = GithubName;
	}
	
	public String getDate() {
		return Date;
	}
	
	public void setDate(String Date) {
		this.Date = Date;
	}
	
	public int getSystolic() {
		return Systolic;
	}
	
	public void setSystolic(int Systolic) {
		this.Systolic = Systolic;
	}
	
	public int getDiastolic() {
		return Diastolic;
	}
	
	public void setDiastolic(int Diastolic) {
		this.Diastolic = Diastolic;
	}
	
	public int getPulse() {
		return Pulse;
	}
	
	public void setPulse(int Pulse) {
		this.Pulse = Pulse;
	}
	
}
