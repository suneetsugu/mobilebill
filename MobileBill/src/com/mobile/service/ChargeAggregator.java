package com.mobile.service;

import java.util.List;
import java.util.Properties;

import com.mobile.constants.ChargeTypes;

public class ChargeAggregator 
{
	Double maxCharge,totalCharge;
	public ChargeAggregator(Properties callCharges) 
	{
		Double baseCharge=Double.valueOf(callCharges.getProperty(ChargeTypes.BASE_CHARGE));
		maxCharge=Double.valueOf(callCharges.getProperty(ChargeTypes.MAX_CHARGE));
		totalCharge=baseCharge;
	}
	public void aggregateCharges(Double charge)
	{		
		totalCharge=totalCharge+charge;
		
		if(totalCharge>maxCharge)
		{
			System.out.println("Rs."+totalCharge + " -- Exceeded limit by Rs." + (totalCharge-maxCharge));
		}
		else
		{
			System.out.println("Rs."+totalCharge+" Within Limit");
		}
	}
	
}
