package com.rawsanj.adminlte.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rawsanj.adminlte.web.entity.AccountMerch;
import com.rawsanj.adminlte.web.service.ConnectDB;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//add
		ConnectDB db=new ConnectDB();
		//List<AccountMerch> lst=db.getSaleByDay();
		//System.out.println(lst.size());
		
		/*AccountMerch merch=new AccountMerch();
		merch.setDay(new Date());
		merch.setEmail("Long.tn3101@gmail.com");
		merch.setIp("192.168");
		merch.setMoney(100d);
		merch.setName("ACC 100 ");
		merch.setSale(10);
		db.insert(merch);*/
	}

}
