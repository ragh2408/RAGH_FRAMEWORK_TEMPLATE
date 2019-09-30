package com.optum.ndb.functionlib;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import com.hp.lft.report.ReportException;
import com.hp.lft.report.Reporter;
import com.hp.lft.report.Status;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.Keys;
import com.hp.lft.sdk.stdwin.Dialog;
import com.hp.lft.sdk.stdwin.UiObject;
import com.hp.lft.sdk.winforms.Button;
import com.hp.lft.sdk.winforms.ButtonDescription;
import com.hp.lft.sdk.winforms.ComboBox;
import com.hp.lft.sdk.winforms.EditField;
import com.hp.lft.sdk.winforms.Label;
import com.hp.lft.sdk.winforms.ListView;
import com.hp.lft.sdk.winforms.RadioButton;
import com.hp.lft.sdk.winforms.TabControl;
import com.optum.ndb.utils.PropertyReader;

//import reporters.ExtentCucumberFormatter;

public class Win_GlobalFunctionLib extends CommonFunctions {
	
	PropertyReader reader=new PropertyReader();
	
	String appType  = reader.readProperty("APPLICATION_TYPE");
	
	public static Process appProc;
	
	public void LaunchApplication(String strAppExePath) {
		try {
			if(appType.equalsIgnoreCase("mainframe")) {
				File file = new File(reader.readProperty("APPLICATION_PATH"));
				java.awt.Desktop.getDesktop().open(file);
				Thread.sleep(5000);
			}
			else if(appType.equalsIgnoreCase("windows"))
			{
				appProc = new ProcessBuilder(reader.readProperty("APPLICATION_PATH")).start();
				Thread.sleep(6000);
			}
			//ExtentCucumberFormatter.setTestRunnerOutput("User does page validation for  " + "PASS");
			Reporter.reportEvent("Launch Application", "Application Launched Successfully", Status.Passed);
			//extent reporting command
			// JUnit Assesrtion
		} catch (Exception e) {
			e.printStackTrace();
			//Reporter.reportEvent("Launch Application", "Application Launched Successfully", Status.Failed);
			//extent reporting command
			// JUnit Assesrtion
		}
	}
	
	public void closeApplication()
	{
		appProc.destroy();
	}
	
	public void inputEditField(EditField winObject, String strValue) throws GeneralLeanFtException
	{
		winObject.setText(strValue);
	}
	
	public void buttonClick(com.hp.lft.sdk.stdwin.Button button) throws GeneralLeanFtException
	{
		button.click();
	}
	
	public void buttonClick(Button button) throws GeneralLeanFtException
	{
		button.click();
	}
	
	public void checkRadioButton(RadioButton winObject) throws GeneralLeanFtException
	{
		winObject.set();
	}
	
	public void selectTabControl(TabControl winObject, int selIndx) throws GeneralLeanFtException
	{
		winObject.select(selIndx);
	}
	
	public void selectTabControl(TabControl winObject, String selStr) throws GeneralLeanFtException
	{
		winObject.select(selStr);
	}
	
	public void selectComboBox(ComboBox winObject, int selIndx) throws GeneralLeanFtException
	{
		winObject.select(selIndx);
	}
	
	public void selectComboBox(ComboBox winObject, String selStr) throws GeneralLeanFtException
	{
		winObject.select(selStr);
	}
	
	public void selectListView(com.hp.lft.sdk.stdwin.ListView listView, int selIndx) throws GeneralLeanFtException
	{
		listView.select(selIndx);
	}
	
	public void selectListView(ListView winObject, int selIndx) throws GeneralLeanFtException, ReportException
	{
		winObject.select(selIndx);
	}
	
	public void selectListView(ListView winObject, String selStr) throws GeneralLeanFtException
	{
		winObject.select(selStr);
	}
	
	public String getLabelText(Label winObject) throws GeneralLeanFtException
	{
		return winObject.getText();
	}
	
	public void acceptDialogBox(Dialog winObject) throws GeneralLeanFtException
	{
		winObject.describe(Button.class, new ButtonDescription.Builder().text("&Yes").nativeClass("Button").build()).click();
	}
	
	public void dismissDialogBox(Dialog winObject) throws GeneralLeanFtException
	{
		winObject.describe(Button.class, new ButtonDescription.Builder().text("&No").nativeClass("Button").build()).click();
	}
}
