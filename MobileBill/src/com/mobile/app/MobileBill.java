package com.mobile.app;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mobile.service.ChargeAggregator;
import com.mobile.service.ChargeMapper;

public class MobileBill 
{
	Properties patterns;
	Properties callCharges;
	
	public static void main(String[] args) 
	{
		MobileBill mobileBill = new MobileBill();
		mobileBill.init();
		mobileBill.run(new File("D:\\eclipse_workspace\\MobileBill\\input.txt"));
	}
	
	public void init()
	{
		patterns = new Properties();
		callCharges = new Properties();
		try 
		{
			patterns.load( ClassLoader.getSystemResourceAsStream("patterns.properties") );
			callCharges.load( ClassLoader.getSystemResourceAsStream("charges.properties") );	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void run(File file) 
	{
		ChargeMapper chargeMapper = new ChargeMapper(patterns, callCharges);
		chargeMapper.formPatternMap();
		chargeMapper.processBill(file);
	}
	
}
