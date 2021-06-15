package com.movie.login.vo;


import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.movie.member.vo.MemberVO;

public class JsonParser {
		JSONParser jsonParser = new JSONParser();
		
		public MemberVO changeJson(String string) throws Exception {
			
			HashMap<String, Object> map = new HashMap<>();
			JSONParser jsonParser = new JSONParser();
			MemberVO memberVO = new MemberVO();
			
			JSONObject jsonObject = (JSONObject) jsonParser.parse(string); 
			
			jsonObject = (JSONObject) jsonObject.get("response");
			
			map.put("member_mail", jsonObject.get("id"));
			map.put("member_name", jsonObject.get("name"));
			map.put("member_pass", null);
			map.put("member_tel", null);
			
			memberVO.setMemZip(map.get("member_mail").toString());
			memberVO.setMemName(map.get("member_name").toString());
			memberVO.setMemPass(map.get("member_pass").toString());
			memberVO.setMemHp(map.get("member_tel").toString());
			
			return memberVO;
		}
}
