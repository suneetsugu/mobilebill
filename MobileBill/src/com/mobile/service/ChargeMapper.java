package com.mobile.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChargeMapper 
{
	Properties patterns,callCharges;
	Map<String, Pattern> patternMap;
	public ChargeMapper(Properties patterns, Properties callCharges) 
	{
		this.patterns=patterns;
		this.callCharges=callCharges;
	}
	
	public void formPatternMap()
	{
		patternMap = new HashMap<>();
		for(String callType: patterns.stringPropertyNames())
		{
			patternMap.put(callType, Pattern.compile( patterns.getProperty(callType)) );
		}
	}
	public void processBill(File file)
	{
		Double charge = 0.0;
		ChargeAggregator aggregator = new ChargeAggregator(callCharges);
		Scanner scanner = null;
		try {
			scanner = new Scanner(file);
			while(scanner.hasNextLine())
			{
				charge= calculateCharge(scanner.nextLine());
				aggregator.aggregateCharges(charge);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			scanner.close();
		}
		
	}
	
	public Double calculateCharge(String callDetail) 
	{
		String callType;
		Integer usage;
		Double charges=0.0;
		for(Map.Entry<String, Pattern> pattern: patternMap.entrySet())
		{
			Matcher matcher = pattern.getValue().matcher(callDetail);
			if(matcher.matches())
			{
				callType = pattern.getKey();
				usage = Integer.valueOf(matcher.group(1));
				charges = Double.valueOf(callCharges.getProperty(callType))*usage;
			}
		}
		
		return charges;
	}
}
