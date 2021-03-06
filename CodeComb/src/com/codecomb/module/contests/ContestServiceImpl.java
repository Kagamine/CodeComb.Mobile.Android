
/**
 * @author UFreedom
 * @since 2014 2014-9-19 下午5:01:03
 * @see www.52yummy.com
 * <br>
 * sunfreedom@sina.cn
 * <br>
 * Copyright (C) 2014 UFreedom. All Rights Reserved.
 */

package com.codecomb.module.contests;

import java.util.List;

import android.content.Context;

import com.codecomb.ServerLocator;
import com.codecomb.exceptions.AppException;
import com.codecomb.module.login.Auth;



public class ContestServiceImpl implements ContestService {

	
	
	private ContestRepository contestRepository;
	
	public ContestServiceImpl(){
		
		contestRepository = ServerLocator.getInstance(ContestRepository.class);
		
	}

	@Override
	public List<Contest> getContests(Context contect, String  accessToken)
			throws AppException {

		return contestRepository.getContests(contect, accessToken);
	}

	@Override
	public List<Contest> getContests(Context context, String  accessToken, int page)
			throws AppException {

		return contestRepository.getContests(context, accessToken, page);
	}

	@Override
	public List<Contest> getManagedContests(Context context,String  accessToken, int page)
			throws AppException {

		return contestRepository.getManagedContests(context, accessToken, page);
	}

	@Override
	public List<Contest> getManagedContests(Context context, String  accessToken)
			throws AppException {

		return contestRepository.getManagedContests(context, accessToken);
	}

	@Override
	public void saveManagedContest(Context context, int userID, Contest contest)
			throws AppException {

		contestRepository.saveManagedContest(context, userID, contest);		
	}

	@Override
	public void saveManagedContests(Context context, int userID,
			List<Contest> cts) throws AppException {

		contestRepository.saveManagedContests(context, userID, cts);		
	}

	@Override
	public List<Contest> getManagedContestsFromLocal(Context context,
			int userID, int page) throws AppException {
		// TODO Auto-generated method stub
		return contestRepository.getManagedContestsFromLocal(context, userID, page);
	}





	


}

