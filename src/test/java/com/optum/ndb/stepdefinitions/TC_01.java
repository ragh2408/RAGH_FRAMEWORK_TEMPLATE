package com.optum.ndb.stepdefinitions;

import cucumber.api.java.en.Given;
import junit.framework.Assert;

public class TC_01 {

	@Given("^Launch the browser$")
	public void launch_the_browser() throws Throwable {
	 
		System.out.println("1. Launch the Browser");
	}
	

@Given("^Enter th URL Google\\.Com$")
public void enter_th_URL_Google_Com() throws Throwable {
	System.out.println("2. Enter the URL");
}

@Given("^Enter the usename$")
public void enter_the_usename() throws Throwable {
	System.out.println("3. Enter the useName");
}

@Given("^Enter the Password$")
public void enter_the_Password() throws Throwable {
	System.out.println("4. Enter the Password");
	Assert.assertEquals("a", "bb");
	
	
}

@Given("^Click on the Signon Botton$")
public void click_on_the_Signon_Botton() throws Throwable {

	System.out.println("5. Click on the sign on button");
	
}


	
	
	
}
