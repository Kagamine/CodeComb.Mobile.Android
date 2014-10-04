
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午11:27:28
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.login;

import android.content.Context;

import com.codecomb.MyApplication;
import com.codecomb.ServerLocator;
import com.codecomb.exceptions.AppException;



public class AuthManager {
	
	
	private LoginService loginService;
	private static Context context;
	
	
	static {
		
		context = MyApplication.getInstance().getApplicationContext();
	}
	
	public static AuthManager getInstance(){
		return SingletonCreator.instance;
	}
	
	private static class SingletonCreator{
			
		private static AuthManager instance = new AuthManager();
		
	}
	
	private AuthManager(){
		
		loginService = ServerLocator.getInstance(LoginService.class);
		
	}
	
	
	public  Auth login(LoginParams params) throws AppException  {
		
		try {
					
			return loginService.login(context, params.getUsername(),params.getPassword());
	
		} catch (AppException e) {
			e.printStackTrace();
		
			throw  e;
			
		}
			
	}
	
	
	
	
	
	
	
	

}

