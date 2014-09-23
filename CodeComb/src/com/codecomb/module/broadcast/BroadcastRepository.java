
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午5:16:23
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.broadcast;

import android.content.Context;

import com.codecomb.module.base.Base;
import com.codecomb.module.login.Auth;
import com.codecomb.xceptions.AppException;



public interface BroadcastRepository {

	public Base pushBroadcast(Context context,String content,String accessToken) throws AppException;
	
}

