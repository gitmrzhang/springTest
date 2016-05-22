package com.zhang.module.login;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class MultiValueMapTest {

	public static void main(String[] args) {
		MultiValueMap<String,String> map = new LinkedMultiValueMap<String, String>();
		List<String> strl = new ArrayList<String>();
		strl.add("key1");
		strl.add("key11");
		strl.add("key111");
		map.put("key", strl);
		List<String> str2 = new ArrayList<String>();
		str2.add("key2");
		str2.add("key21");
		str2.add("key211");
		map.put("key", strl);
		map.put("key2", str2);
		List<String> data = map.get("key");
		System.out.println(data);
	}

}
