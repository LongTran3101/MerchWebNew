package com.rawsanj.adminlte.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rawsanj.adminlte.web.entity.AccountMerch;
import com.rawsanj.adminlte.web.service.ConnectDB;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//add
		ObjectMapper objectMapper = new ObjectMapper();
		ConnectDB db=new ConnectDB();
		//List<AccountMerch> lst=db.getSaleByDay();
		//System.out.println(lst.size());
		
		AccountMerch merch=new AccountMerch();
		merch.setDay(new Date());
		merch.setDayString("31/10/2021");
		merch.setEmail("Long.tn3101@gmail.com");
		merch.setIp("192.168");
		merch.setMoney(100d);
		merch.setName("ACC 100 ");
		merch.setSale(10);
		merch.setPath("C:\\Users\\haile0879\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String req=gson.toJson( merch);
		String jsonString;
		try {
			jsonString = objectMapper.writeValueAsString(merch);
			System.out.println(jsonString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//db.insert(merch);*/
	}

}
