
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午5:03:13
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.contacts;

import java.util.List;

import android.content.Context;

import com.codecomb.module.login.Auth;
import com.codecomb.xceptions.AppException;



public interface ContactRepository {

	public List<Contact> getContacts(Context context,String accessToken) throws AppException;
	
	
	
}

