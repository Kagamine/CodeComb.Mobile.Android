
/**
 * @author UFreedom
 * @since 2014 2014-9-20 上午12:18:55
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.profile;

import java.io.File;

import com.codecomb.MyApplication;
import com.codecomb.ServerLocator;
import com.codecomb.SettingsManager;
import com.codecomb.utils.Utils;
import com.codecomb.xceptions.AppException;

import android.content.Context;



public class ProfileManager {
	
	
	private static Context context;
	private String accessToken;
	
	static{
		context = MyApplication.getInstance().getApplicationContext();
	}
	
	
	private ProfileService profileService;
	
	public static ProfileManager getInstance(){
		return SingletonCreator.instance;
	}
	
	
	private static class SingletonCreator{
		private static  ProfileManager instance = new ProfileManager();
		
	}
	
	
	private ProfileManager(){
		profileService = ServerLocator.getInstance(ProfileService.class);
		accessToken = SettingsManager.getInstance().getAccessToken();
	}
	
	
	
	
	public Profile getProfile() throws AppException{
		
		return profileService.getProfile(context, accessToken);
	}
	
	public  File getMottoFile(String fileName) throws Exception{
		
		File imageDir = getMottoDir();
		
		return new File(imageDir,fileName);
		
	}
	
	
	public  File getMottoDir()throws Exception{
		
		
		File moduleDir = getModuleDir();

		File audioDir = new File(moduleDir, "Motto");
		if (!audioDir.exists()) {
			audioDir.mkdirs();
		}

		return audioDir;
		
	}
		
	
	public  File getModuleDir() throws Exception {

		File userDir = Utils.getCurrentUserDir(MyApplication.getInstance()
				.getApplicationContext());

		File moduleDir = new File(userDir, "profile");
		if (!moduleDir.exists()) {
			moduleDir.mkdirs();
		}
		return moduleDir;

	}
	

	

}

