package com.ntou.cs.monkeyblood.service;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.ntou.cs.monkeyblood.entity.BloodPressure;
import com.ntou.cs.monkeyblood.repo.MonkeyBloodRepo;
import com.ntou.cs.monkeyblood.exception.*;
import com.ntou.cs.monkeyblood.entity.BloodPressureRequest;
@Service
public class MonkeyBloodService {

	@Autowired
	private MonkeyBloodRepo repository;
	
	public MonkeyBloodService(MonkeyBloodRepo repository) {
		this.repository = repository;
	}
	
	public List<BloodPressure> getBloodPressure(String githubID) {
		return repository.findByGithubID(githubID);
	}
	
	public BloodPressure createBloodPressure(BloodPressureRequest request) {
		System.out.println(request.getGithubID());
		System.out.println(request.getGithubName());
		System.out.println(request.getSystolic());
		System.out.println(request.getDiastolic());
		System.out.println(request.getPulse());
		System.out.println(request.getDate());
		
		BloodPressure bloodPressure = new BloodPressure();
		bloodPressure.setGithubID(request.getGithubID());
		bloodPressure.setGithubName(request.getGithubName());
		bloodPressure.setSystolic(request.getSystolic());
		bloodPressure.setDiastolic(request.getDiastolic());
		bloodPressure.setPulse(request.getPulse());
		bloodPressure.setDate(request.getDate());
		return repository.insert(bloodPressure);
	}
	public BloodPressure updateBloodPressure(BloodPressureRequest request) {
		System.out.println(request.getGithubID());
		System.out.println(request.getGithubName());
		System.out.println(request.getSystolic());
		System.out.println(request.getDiastolic());
		System.out.println(request.getPulse());
		System.out.println(request.getDate());
		
		BloodPressure old = new BloodPressure();
		old = repository.findByGithubIDDate(request.getGithubID(), request.getDate()).findFirst().orElse(null);
		if(old == null)
			System.out.println("null");
		old.setGithubID(request.getGithubID());
		old.setGithubName(request.getGithubName());
		old.setSystolic(request.getSystolic());
		old.setDiastolic(request.getDiastolic());
		old.setPulse(request.getPulse());
		old.setDate(request.getDate());
		
		return repository.save(old);
	}
	public void deleteBloodPressure(BloodPressureRequest request) {
		System.out.println(request.getGithubID());
		System.out.println(request.getGithubName());
		System.out.println(request.getSystolic());
		System.out.println(request.getDiastolic());
		System.out.println(request.getPulse());
		System.out.println(request.getDate());
		
		BloodPressure bye = new BloodPressure();
		bye = repository.findByGithubIDDate(request.getGithubID(), request.getDate()).findFirst().orElse(null);
		if(bye == null)
			System.out.println("null");
		else
			repository.delete(bye);
	}
	public void deleteBloodPressure2(String githubID, String date) {
		System.out.println(githubID);
		System.out.println(date);
		
		BloodPressure bye = new BloodPressure();
		bye = repository.findByGithubIDDate(githubID, date).findFirst().orElse(null);
		if(bye == null)
			System.out.println("null");
		else
			repository.delete(bye);
	}
	

}
