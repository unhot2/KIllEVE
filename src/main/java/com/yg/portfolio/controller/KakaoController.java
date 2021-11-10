package com.yg.portfolio.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.yg.portfolio.service.RestJsonService;

@Controller
@RequestMapping("/kakao")
public class KakaoController {

	@Autowired
	RestJsonService restJsonService;
	
	@GetMapping(value = "/oauth")
	public String kakaoConnect() {

		StringBuffer url = new StringBuffer();
		url.append("https://kauth.kakao.com/oauth/authorize?");
		url.append("client_id=c7d8445fa4df53e52025f30525d59dc3");
		url.append("&redirect_uri=http://localhost:8099/kakao/callback");
		url.append("&response_type=code");

		return "redirect:" + url.toString();
	}

	@RequestMapping(value = "/callback", produces = "application/json", method = { RequestMethod.GET, RequestMethod.POST })
    public String kakaoLogin(@RequestParam("code") String code, Model model) {

        //access_token이 포함된 JSON String을 받아온다.
        String accessTokenJsonData = restJsonService.getAccessTokenJsonData(code);
        if(accessTokenJsonData=="error") return "error";

        //JSON String -> JSON Object
        JSONObject accessTokenJsonObject = new JSONObject(accessTokenJsonData);

        //access_token 추출
        String accessToken = accessTokenJsonObject.get("access_token").toString();
        
        model.addAttribute("access_token", accessToken);
        System.out.println("돌아온 accessToken 값 : " +accessToken);
        return "redirect:/";
    }
	
	@RequestMapping("/logout")
	public String kakaoLogout() {
		return "redirect:/"; 
	}
}
