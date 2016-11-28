package com.rainsho.test;

import java.util.HashMap;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.rainsho.dao.UsersDAO;
import com.rainsho.entity.Relationships;
import com.rainsho.entity.Users;

public class Test {

	private UsersDAO dao;
	private Users user;
	private HashMap<String, Object> jsonResult;

	public UsersDAO getDao() {
		return dao;
	}

	public void setDao(UsersDAO dao) {
		this.dao = dao;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public HashMap<String, Object> getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(HashMap<String, Object> jsonResult) {
		this.jsonResult = jsonResult;
	}

	// test function
	public String execute() {
		user = dao.findById(1);
		Set<Relationships> set = user.getRelationshipsesForHuid();
		System.out.println(set.size());
		return "success";
	}
	
	public String getJson(){  
	    jsonResult = new HashMap<String, Object>();  
	    user = dao.findById(1);
	    jsonResult.put("user1", user); 
	    jsonResult.put("user2", dao.findById(2)); 
	    return "success";  
	}
	
	public static void main(String[] args) {
		String str = "fasdfasd#abc sjad;kfj;as##fjas;j";
		Pattern p = Pattern.compile("#([^#]\\w+)");
		Matcher m = p.matcher(str); 
		 while (m.find()) { 
			 m.start() ;
			 m.end();
			System.out.println( m.group(1));
			   }
	}
}
