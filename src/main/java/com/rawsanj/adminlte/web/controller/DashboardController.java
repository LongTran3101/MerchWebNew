package com.rawsanj.adminlte.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rawsanj.adminlte.web.entity.SaleMerch;
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
    	System.out.println("abc");
        return "/dashboard/index";
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
    		List<SaleMerch> lst=db.getSaleByDay(daySeach);
    		model.addAttribute("lst", lst);
            return "dashboard/checkSale";
    	}
    	
		model.addAttribute("lst", null);
        return "dashboard/checkSale";
    }
    
    
    @RequestMapping("/allAcc")
    public String allAcc( HttpSession session, Model model, HttpServletRequest request) {
    	
    		ConnectDB db=new ConnectDB();
    		List<SaleMerch> lst=db.getAllAcc();
    		model.addAttribute("lst", lst);
            return "dashboard/allAcc";
    	
    }
    
    @RequestMapping("/checkSaleById")
    @ResponseBody
    public String checkSaleById( HttpSession session, Model model, HttpServletRequest request) {
    	try {
    		String id=request.getParameter("id");
    		
    		ConnectDB db=new ConnectDB();
    		SaleMerch merch=db.getByID(Integer.parseInt(id));
    		ObjectMapper objectMapper = new ObjectMapper();
    		String req = objectMapper.writeValueAsString(merch);
    		CallAPi callApi=new CallAPi();
    		String rep =callApi.callAPIPost("http://"+merch.getIp()+":8080/checksalemerchtest", req);
    		//model.addAttribute("lst", lst);
    		if(rep!=null && rep.equalsIgnoreCase("00"))
			{
				return "00";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return"01";
    	    
    	
    }
    @RequestMapping("/deleteAccountMerch")
    @ResponseBody
    public String deleteAccountMerch( HttpSession session, Model model, HttpServletRequest request) {
    	try {
    		String id=request.getParameter("id");
    		ConnectDB db=new ConnectDB();
			
			 boolean check=db.deleteAccountMerch(Integer.parseInt(id));
			if(check)
			{
				return "00";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return"01";
    	    
    	
    }
    
    
    
    @RequestMapping("/saveAccountMerch")
    @ResponseBody
    public String saveAccountMerch(@ModelAttribute AccountMerch acc, HttpSession session, Model model, HttpServletRequest request) {
    	try {
			ConnectDB db=new ConnectDB();
			
			 boolean check=db.insertAccountMerch(acc);
			if(check)
			{
				return "00";
			}
    		//model.addAttribute("lst", lst);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return"01";
    	    
    	
    }
    
    @ResponseBody
    @RequestMapping(value = "/saveCheckSale", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private String test( @RequestBody String req,HttpServletRequest request, HttpServletResponse resp) {
    
    	try {
    		ObjectMapper objectMapper = new ObjectMapper();
			SaleMerch mech=objectMapper.readValue(req, SaleMerch.class);
			ConnectDB db=new ConnectDB();
			
			 boolean check=db.insert(mech);
			if(check)
			{
				return "00";
			}
    		//model.addAttribute("lst", lst);
            
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return"01";
    	    
    	
    }




    // Added to test 500 page
    @RequestMapping(path = "/tigger-error", produces = MediaType.APPLICATION_JSON_VALUE)
    public void error500() throws Exception {
        throw new Exception();
    }

}
