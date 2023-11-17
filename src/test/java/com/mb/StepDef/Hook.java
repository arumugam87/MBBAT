package com.mb.StepDef;

import com.mb.Utility.BaseProperty;


import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hook extends BaseProperty {
	
	
public static String scenarioname;
	
	public BaseProperty base;
	
	public Hook(BaseProperty base) {
		
		this.base = base;
	}
	
	@Before
	public void initializetest() {
		
		//System.out.println("Hook initializetest ");
	}
	
	@After
	public void TearDownTest(cucumber.api.Scenario scenario) {
		
		if(scenario.isFailed()) {
			
			scenarioname  =	scenario.getName();	
		//	System.out.println("Hook Scenario name fail: "+scenario.getName());
			
			System.out.println("Hook Scenario fail : "+scenarioname);
			
		}
		else{
			
			scenarioname  =	scenario.getName();	
			//System.out.println("Hook Scenario name pass: "+scenario.getName());
			
			System.out.println("Hook Scenario pass: "+scenarioname);
			
		}
	}

}
