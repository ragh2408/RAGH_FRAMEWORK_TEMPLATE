package com.optum.ndb.unittesting;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

//import org.junit.After;
import org.junit.AfterClass;
//import org.junit.Before;
import org.junit.BeforeClass;
//import org.junit.Test;
import org.junit.runner.RunWith;

import reporters.ExtentCucumberFormatter;

import com.hp.lft.sdk.*;
import com.hp.lft.verifications.*;
import com.optum.ndb.unittesting.*;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features    = {"src/test/resources/features/"},
		glue 		= {"com.optum.ndb.stepdefinitions"},
		plugin      = {"reporters.ExtentCucumberFormatter","pretty","json:target/cucumber.json","html:target/cucumber-html"}
//		tags        = {"@UTRPOC","@EDSPOC"}
		)
public class Runnable extends UnitTestClassBase {

	public Runnable() throws Exception {
		ExtentReports extent;
	    ExtentTest test;
		//Change this constructor to private if you supply your own public constructor
	    instance = new Runnable();
		globalSetup(Runnable.class);
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
        ExtentCucumberFormatter.initiateExtentCucumberFormatter();
        
        ExtentCucumberFormatter.loadConfig(new File("extent-config.xml"));

        ExtentCucumberFormatter.addSystemInfo("Browser Name", "Firefox");
        ExtentCucumberFormatter.addSystemInfo("Browser version", "v31.0");
        ExtentCucumberFormatter.addSystemInfo("Selenium version", "v2.53.0");

        Map systemInfo = new HashMap();
        systemInfo.put("Cucumber version", "v1.2.3");
        systemInfo.put("Extent Cucumber Reporter version", "v1.1.0");
        ExtentCucumberFormatter.addSystemInfo(systemInfo);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		//globalTearDown();
	}

//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
//
//	@Test
//	public void test() throws GeneralLeanFtException {
//	}

}
