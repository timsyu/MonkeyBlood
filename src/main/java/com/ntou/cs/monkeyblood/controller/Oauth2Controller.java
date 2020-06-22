package com.ntou.cs.monkeyblood.controller;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Splitter;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.ntou.cs.monkeyblood.entity.BloodPressure;
import com.ntou.cs.monkeyblood.entity.BloodPressureRequest;
import com.ntou.cs.monkeyblood.model.GithubUser;
import com.ntou.cs.monkeyblood.service.MonkeyBloodService;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

@RequestMapping("")
@Controller
public class Oauth2Controller {
	
	@Autowired
	private MonkeyBloodService monkeyBloodService;
	
	
	
	private static final String CLIENT_ID = "f3ac334c275afd43ec30";
    private static final String CLIENT_SECRET = "9aa1a58f49afef1893d23480c6f19b831e55a9d4";
    private static final String CALLBACK_URL = "https://powerful-eyrie-44683.herokuapp.com/callback";

    private ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setPropertyNamingStrategy(com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE);

    @RequestMapping("")
    public ModelAndView login() {
    	System.out.println("innnnnn");
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping("/redirectToGithub")
    public String redirectToGithub() {
    	System.out.println("redirect");
        return "redirect:https://github.com/login/oauth/authorize?client_id=" + CLIENT_ID + "&redirect_uri=" + CALLBACK_URL;
    }

    @RequestMapping("/callback")
    public ModelAndView callbackWithoutScribe(@RequestParam String code) throws Exception {
        // 向 Github 取得 accessToken
        System.out.println("in call back");
        HttpPost post = new HttpPost("https://github.com/login/oauth/access_token");

        List<BasicNameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("client_id", CLIENT_ID));
        parameters.add(new BasicNameValuePair("client_secret", CLIENT_SECRET));
        parameters.add(new BasicNameValuePair("code", code));
        post.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));

        // 取得 response 中的 accessToken
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse response = httpClient.execute(post);
        HttpEntity httpEntity = response.getEntity();
        String result = EntityUtils.toString(httpEntity, "utf-8");
        Map<String, String> map = Splitter.on("&").withKeyValueSeparator("=").split(result);
        String accessToken = map.get("access_token");

        // 拿到 accessToken 之後，向 Github user api 取得該 user 的 data
        HttpGet get = new HttpGet("https://api.github.com/user");
        get.addHeader("Authorization", "token " + accessToken);
        HttpResponse getResponse = httpClient.execute(get);
        String json = EntityUtils.toString(getResponse.getEntity(), "utf-8");
        GithubUser githubUser = objectMapper.readValue(json, GithubUser.class);
        System.out.print(json);
        //get 該githubUser 之資料
        
        HttpGet getUser = new HttpGet("https://powerful-eyrie-44683.herokuapp.com/BloodPressure/"+String.valueOf(githubUser.getId()));
        System.out.println("e04");
        HttpResponse getResponse2 = httpClient.execute(getUser);
        
        String json2 = EntityUtils.toString(getResponse2.getEntity());
        System.out.println(json2);
        //JSONObject jsonObj = new JSONObject(json2.toString());
        //ObjectMapper om =new ObjectMapper();
        Gson gson = new Gson();
        String userName = null;
        String userID=null;
        List<BloodPressure> bloodPressure = gson.fromJson(json2, new TypeToken<List<BloodPressure>>() {}.getType());
//        BloodPressure bloodPressure = objectMapper.readValue(json2, BloodPressure.class);
        for(BloodPressure model : bloodPressure) {
        	System.out.println(model.getGithubID());
        	System.out.println(model.getGithubName());
        	userName=model.getGithubName();
        	userID=model.getGithubID();
        }
        

        
        
        // 將 data 展示在前端頁面
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("githubUser", bloodPressure);
        mv.addObject("githubUserName", githubUser.getLogin());
        mv.addObject("githubUserID", String.valueOf(githubUser.getId()));
        return mv;
//        return "redirect:/BloodPressure/"+githubUser.getId();
    }
    
    @GetMapping("/BloodPressure/{githubID}")
    public ResponseEntity<List<BloodPressure>> getBloodPressure(@PathVariable("githubID") String githubID){
    	//BloodPressure bloodPressure = monkeyBloodService.getBloodPressure(githubID);
    	return ResponseEntity.ok().body(monkeyBloodService.getBloodPressure(githubID));
    	
    }
    
    @PostMapping("/BloodPressure/add")
    public ResponseEntity<BloodPressure> createBloodPressure(@ModelAttribute BloodPressureRequest request){
		BloodPressure bloodPressure = monkeyBloodService.createBloodPressure(request);
		System.out.println(request.getGithubID());
		System.out.println(request.getGithubName());
		System.out.println(request.getSystolic());
		System.out.println(request.getDiastolic());
		System.out.println(request.getPulse());
		System.out.println(request.getDate());
		
    	return ResponseEntity.ok().body(bloodPressure);
    	
    }

    @PutMapping("/BloodPressure/update")
    public ResponseEntity<BloodPressure> replaceBloodPressure(@ModelAttribute BloodPressureRequest request){
    	System.out.println(request.getGithubID());
		System.out.println(request.getGithubName());
		System.out.println(request.getSystolic());
		System.out.println(request.getDiastolic());
		System.out.println(request.getPulse());
		System.out.println(request.getDate());
		
		BloodPressure bloodPressure = monkeyBloodService.updateBloodPressure(request);
		return ResponseEntity.ok().body(bloodPressure);
    }
    @DeleteMapping("/BloodPressure/{githubID}/delete")
    public ResponseEntity<BloodPressure> deleteBloodPressure(@ModelAttribute BloodPressureRequest request){
    	System.out.println(request.getGithubID());
		System.out.println(request.getGithubName());
		System.out.println(request.getSystolic());
		System.out.println(request.getDiastolic());
		System.out.println(request.getPulse());
		System.out.println(request.getDate());
		monkeyBloodService.deleteBloodPressure(request);
		return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/BloodPressure/delete")
    public ResponseEntity<BloodPressure> deleteBloodPressure2(String githubID, String date){
    	System.out.println(githubID);
		System.out.println(date);
		monkeyBloodService.deleteBloodPressure2(githubID, date);
		return ResponseEntity.noContent().build();
    }
    
}

