package com.rawsanj.adminlte.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rawsanj.adminlte.web.entity.AccountMerch;
import com.rawsanj.adminlte.web.entity.Customers;
import com.rawsanj.adminlte.web.entity.User;
import com.rawsanj.adminlte.web.service.CallAPi;
import com.rawsanj.adminlte.web.service.ConnectDB;

/**
 * Created by Sanjay on 8/15/2016.
 */

@Controller
public class DashboardController {
	

    @RequestMapping("/")
    public String index() {
        return "dashboard/index";
    }
    @RequestMapping("/user")
    public String user() {
    	
        return "dashboard/user";
    }
    @RequestMapping("/checkSale")
    public String checkSale( HttpSession session, Model model, HttpServletRequest request) {
    	String daySeach=request.getParameter("daySearch");
    	if(daySeach!=null && daySeach!="")
    	{
    		ConnectDB db=new ConnectDB();
    		List<AccountMerch> lst=db.getSaleByDay(daySeach);
    		model.addAttribute("lst", lst);
            return "dashboard/checkSale";
    	}
    	
		model.addAttribute("lst", null);
        return "dashboard/checkSale";
    }
    
    
    @RequestMapping("/allAcc")
    public String allAcc( HttpSession session, Model model, HttpServletRequest request) {
    	
    		ConnectDB db=new ConnectDB();
    		List<AccountMerch> lst=db.getAllAcc();
    		model.addAttribute("lst", lst);
            return "dashboard/allAcc";
    	
    }
    
    @RequestMapping("/checkSaleById")
    @ResponseBody
    public String checkSaleById( HttpSession session, Model model, HttpServletRequest request) {
    	try {
    		String id=request.getParameter("id");
    		ConnectDB db=new ConnectDB();
    		AccountMerch merch=db.getByID(Integer.parseInt(id));
    		Gson gson = new GsonBuilder().setPrettyPrinting().create();
    		String req=gson.toJson( merch);
    		CallAPi callApi=new CallAPi();
    		String rep =callApi.callAPIPost("http://34.71.202.141:8080/checksalemerchtest", req);
    		//model.addAttribute("lst", lst);
            return rep;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return"ok";
    	    
    	
    }
    
    @RequestMapping("/saveCheckSale")
    @ResponseBody
    public String saveCheckSale( HttpSession session, Model model, HttpServletRequest request) {
    	try {
    		String id=request.getParameter("id");
    		ConnectDB db=new ConnectDB();
    		AccountMerch merch=db.getByID(Integer.parseInt(id));
    		Gson gson = new GsonBuilder().setPrettyPrinting().create();
    		String req=gson.toJson( merch);
    		CallAPi callApi=new CallAPi();
    		String rep =callApi.callAPIPost("http://34.71.202.141:8080/checksalemerchtest", req);
    		//model.addAttribute("lst", lst);
            return rep;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return"ok";
    	    
    	
    }




    // Added to test 500 page
    @RequestMapping(path = "/tigger-error", produces = MediaType.APPLICATION_JSON_VALUE)
    public void error500() throws Exception {
        throw new Exception();
    }

}
