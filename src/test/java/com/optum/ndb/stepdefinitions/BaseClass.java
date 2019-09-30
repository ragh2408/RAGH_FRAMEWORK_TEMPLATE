package com.optum.ndb.stepdefinitions;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import com.hp.lft.sdk.Desktop;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.ModifiableSDKConfiguration;
import com.hp.lft.sdk.RegExpProperty;
import com.hp.lft.sdk.SDK;
import com.hp.lft.sdk.te.Field;
import com.hp.lft.sdk.te.FieldDescription;
import com.hp.lft.sdk.te.Keys;
import com.hp.lft.sdk.te.Screen;
import com.hp.lft.sdk.te.ScreenDescription;
import com.hp.lft.sdk.te.Window;
import com.hp.lft.sdk.te.WindowDescription;

import gherkin.formatter.model.Result;

public class BaseClass  {
	
	public static void main(String[] args) throws Exception 
	{
		System.out.println("Started....................");
		Field(3, 1).setText("Ragh");
		System.out.println("-------------" );
	}
	
	public static com.hp.lft.sdk.te.Field Field(int row , int col) throws Exception {
	
	
	ModifiableSDKConfiguration config = new ModifiableSDKConfiguration();
	config.setServerAddress(new URI("ws://localhost:5095"));
	SDK.init(config);
	
	Window teWindow = Desktop.describe(Window.class, new WindowDescription.Builder().shortName(new RegExpProperty(".*")).build());
	Screen teScreen = teWindow.describe(Screen.class, new ScreenDescription.Builder().label(new RegExpProperty(".*")).build());
	Field field = teScreen.describe(Field.class, new FieldDescription.Builder().startPosition(new com.hp.lft.sdk.te.PositionProperty().setRow(row).setColumn(col)).build());
	return field;
	}
	
	public static Screen screen() throws GeneralLeanFtException {
		Window teWindow = Desktop.describe(Window.class, new WindowDescription.Builder().shortName(new RegExpProperty(".*")).build());
		Screen teScreen = teWindow.describe(Screen.class, new ScreenDescription.Builder().label(new RegExpProperty(".*")).build());
		return teScreen;
	}

	public static void TE_Enter() throws GeneralLeanFtException {
		
		screen().sendTEKeys(Keys.ENTER);
		screen().sync();
		
	}
	public static void PF2() throws GeneralLeanFtException
	{
		screen().sendTEKeys(Keys.PF2);
		screen().sync();
	}
	
	public static void PF8() throws GeneralLeanFtException
	{
		screen().sendTEKeys(Keys.PF8);
		screen().sync();
	}
	
	public static void PF3() throws GeneralLeanFtException
	{
		screen().sendTEKeys(Keys.PF3);
		screen().sync();
	}
	
	
	 static Date date = new Date();
     static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss");  
		
	public static String ReportPath = "output" + File.separator + "Run_" + formatter.format(date);
	

}
