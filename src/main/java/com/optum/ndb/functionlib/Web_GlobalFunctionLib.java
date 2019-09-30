package com.optum.ndb.functionlib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.lft.report.ReportException;
import com.hp.lft.report.Reporter;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.stdwin.ComboBox;
import com.hp.lft.sdk.stdwin.ComboBoxDescription;
import com.hp.lft.sdk.stdwin.Dialog;
import com.hp.lft.sdk.stdwin.DialogDescription;
import com.hp.lft.sdk.stdwin.UiObject;
import com.hp.lft.sdk.stdwin.UiObjectDescription;
import com.hp.lft.sdk.web.Browser;
import com.hp.lft.sdk.web.BrowserDescription;
import com.hp.lft.sdk.web.BrowserFactory;
import com.hp.lft.sdk.web.BrowserType;
import com.hp.lft.sdk.web.Button;
import com.hp.lft.sdk.web.ButtonDescription;
import com.hp.lft.sdk.web.CheckBox;
import com.hp.lft.sdk.web.CheckBoxDescription;
import com.hp.lft.sdk.web.EditField;
import com.hp.lft.sdk.web.EditFieldDescription;
import com.hp.lft.sdk.web.FileField;
import com.hp.lft.sdk.web.FileFieldDescription;
import com.hp.lft.sdk.web.Frame;
import com.hp.lft.sdk.web.FrameDescription;
import com.hp.lft.sdk.web.Image;
import com.hp.lft.sdk.web.ImageDescription;
import com.hp.lft.sdk.web.Link;
import com.hp.lft.sdk.web.LinkDescription;
import com.hp.lft.sdk.web.ListBox;
import com.hp.lft.sdk.web.ListBoxDescription;
import com.hp.lft.sdk.web.Page;
import com.hp.lft.sdk.web.PageDescription;
import com.hp.lft.sdk.web.RadioGroup;
import com.hp.lft.sdk.web.RadioGroupDescription;
import com.hp.lft.sdk.web.WebElement;
import com.hp.lft.sdk.web.WebElementDescription;
import com.optum.ndb.utils.PropertyReader;

public class Web_GlobalFunctionLib extends CommonFunctions {
	
	public static Browser browser;

	protected static int shortTimeout = 20; // in seconds
	protected static int longTimeout = 45;

	public  Logger log = LoggerFactory.getLogger(this.getClass());
	public static Connection conn;


	/**
	 * Method used to launch browser based on value given in properties file
	 * @throws Exception
	 */
	public static void launchBrowser() throws Exception
	{
		PropertyReader reader= new PropertyReader();
		
		String browserType = reader.readProperty("browser");
		System.out.println(browserType);

		if(browserType.equals("InternetExplorer")){
			browser=BrowserFactory.launch(BrowserType.INTERNET_EXPLORER);
			browser.sync();

		}

		else if(browserType.equals("Firefox")){

			browser=BrowserFactory.launch(BrowserType.FIREFOX);        

		}
		else if(browserType.equals("Chrome")){
			browser=BrowserFactory.launch(BrowserType.CHROME);        
		}
		//browser.sync();
		//browser.fullScreen();
	}

	/**
	 * Method used to navigate to URL based on value given in Properties file
	 * @throws GeneralLeanFtException
	 */
	public static void navigateToURL() throws GeneralLeanFtException, InterruptedException{

		PropertyReader reader=new PropertyReader();

		String appURL = reader.readProperty("ApplicationURL");
		System.out.println(appURL);

		browser.navigate(appURL);

		Thread.sleep(5000);
		//Wait for the browser to complete the navigation.
		browser.sync();
	}

	/**
	 * Method used to establish connection to DB
	 */
	public void funEstablishDBConn() throws SQLException, ClassNotFoundException
	{
		PropertyReader reader=new PropertyReader();

		Class.forName(reader.readProperty("DB2Driver"));
		conn = DriverManager.getConnection(reader.readProperty("DB2URL"), reader.readProperty("DBUID"),
				reader.readProperty("DBPWD"));

	}

	//DB Function
	public long funGetColsCount(String strQuery) throws SQLException
	{
		long numberOfColumns = 0;

		PreparedStatement statement = conn.prepareStatement(strQuery);
		ResultSet rs = statement.executeQuery();
		ResultSetMetaData rsMetaData = rs.getMetaData();
		numberOfColumns = rsMetaData.getColumnCount();

		rs.close();
		statement.close();
		conn.close();

		return numberOfColumns;
	}

	public static void getNumberofRowsFromTable() throws GeneralLeanFtException
	{
		String text1 = browser.describe(WebElement.class,new WebElementDescription.Builder().xpath("(.//*[@id='ctl00_ContentBlock_Main_gvCarriers']/tbody//a/../..)[2]").build()).getInnerText();
		//Map<String, String> text = browser.describe(WebElement.class,new WebElementDescription.Builder().xpath("(.//*[@id='ctl00_ContentBlock_Main_gvCarriers']/tbody//a/../..)[2]").build()).findChildren(arg0, arg1);
		String text = browser.describe(WebElement.class,new WebElementDescription.Builder().xpath("(.//*[@id='ctl00_ContentBlock_Main_gvCarriers']/tbody//a/../..)[2]").build()).getTagName();

		System.out.println("Number of rows"+text1);


		//System.out.println(text);
	}

	public static String currentDate() {

		Date curDate = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyyy");
		System.out.println("Current Date:"+curDate);
		System.out.println(ft.format(curDate));
		String NewCurDate = ft.format(curDate);
		return NewCurDate;
	}

	public static String currentDateFormat1() {

		Date curDate = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("MMddyyyyHHmmss");
		System.out.println("Current Date:"+curDate);
		System.out.println(ft.format(curDate));
		String NewCurDate = ft.format(curDate);
		return NewCurDate;
	}
	
	
	public static void verifyFileSaveDialogIE() throws GeneralLeanFtException, ReportException, InterruptedException
	{
		
		Thread.sleep(5000);
		
		if (browser.describe(UiObject.class, new UiObjectDescription.Builder()
		.text("").windowClassRegExp("DirectUIHWND").windowId(0).nativeClass("DirectUIHWND").index(0).build()).exists())
						Reporter.reportEvent("Verify Save Button", "Open Save Button is displayed");

		
		
	}

	public static void inputText(String strLocType, String strLocValue, String strValue) throws GeneralLeanFtException
	{
		if(strLocType.equalsIgnoreCase("id"))
			browser.describe(EditField.class,new EditFieldDescription.Builder().id(strLocValue).build()).setValue(strValue);
		else if(strLocType.equalsIgnoreCase("xpath"))
			browser.describe(EditField.class,new EditFieldDescription.Builder().xpath(strLocValue).build()).setValue(strValue);
		else if(strLocType.equalsIgnoreCase("name"))
			browser.describe(EditField.class,new EditFieldDescription.Builder().name(strLocValue).build()).setValue(strValue);
		else if(strLocType.equalsIgnoreCase("tagName"))
			//browser.describe(EditField.class,new EditFieldDescription.Builder().tagName(strLocValue).name(strValue).build());
			browser.describe(EditField.class, new EditFieldDescription.Builder()
					.type("text").tagName("INPUT").name(strLocValue).build()).setValue(strValue);;
	}

	/*public static void inputText(String strLocName, String strValue) throws GeneralLeanFtException
	{
		String[] strLocComps= strLocName.split("#");
		String strLocType = strLocComps[0];
		String strLocValue = strLocComps[1];

		if(strLocType.equalsIgnoreCase("id"))
			browser.describe(EditField.class,new EditFieldDescription.Builder().id(strLocValue).build()).setValue(strValue);
		else if(strLocType.equalsIgnoreCase("xpath"))
			browser.describe(EditField.class,new EditFieldDescription.Builder().xpath(strLocValue).build()).setValue(strValue);
		else if(strLocType.equalsIgnoreCase("name"))
			browser.describe(EditField.class,new EditFieldDescription.Builder().name(strLocValue).build()).setValue(strValue);
		else if(strLocType.equalsIgnoreCase("tagName"))
			browser.describe(EditField.class,new EditFieldDescription.Builder().tagName(strLocValue).name(strValue).build());
	}*/
	public static void btnClick(String strLocType, String strLocValue) throws GeneralLeanFtException
	{
		if(strLocType.equalsIgnoreCase("id"))
			browser.describe(Button.class,new ButtonDescription.Builder().id(strLocValue).build()).click();
		else if(strLocType.equalsIgnoreCase("xpath"))
			browser.describe(Button.class,new ButtonDescription.Builder().xpath(strLocValue).build()).click();
		else if(strLocType.equalsIgnoreCase("tagName"))
			browser.describe(Button.class, new ButtonDescription.Builder()
					.buttonType("submit").tagName("INPUT").name(strLocValue).build()).click();
		else if(strLocType.equalsIgnoreCase("tagName_button"))
			browser.describe(Button.class, new ButtonDescription.Builder()
					.buttonType("button").tagName("INPUT").name(strLocValue).build()).click();
	}

	public static void btnimageClick(String strLocType, String strLocValue) throws GeneralLeanFtException
	{

		if(strLocType.equalsIgnoreCase("id"))
			browser.describe(Image.class, new ImageDescription.Builder().id(strLocValue).build()).click();
		else if(strLocType.equalsIgnoreCase("xpath"))
			browser.describe(Image.class, new ImageDescription.Builder().xpath(strLocValue).build()).click();

	}

	public static void linkClick(String strLocType, String strLocValue, String strValue) throws GeneralLeanFtException
	{
		if(strLocType.equalsIgnoreCase("id"))
			browser.describe(Link.class,new LinkDescription.Builder().id(strLocValue).build()).click();
		else if(strLocType.equalsIgnoreCase("xpath"))
			browser.describe(Link.class,new LinkDescription.Builder().xpath(strLocValue).build()).click();
		else if(strLocType.equalsIgnoreCase("name"))
			browser.describe(Link.class,new LinkDescription.Builder().name(strLocValue).build()).click();
		else if(strLocType.equalsIgnoreCase("tagName"))
			browser.describe(Link.class,new LinkDescription.Builder().tagName(strLocValue).innerText(strValue).build()).click();
	}

	public static void dropdown(String strLocType, String strLocValue, String strValue) throws GeneralLeanFtException
	{
		if(strLocType.equalsIgnoreCase("tagName"))
			browser.describe(ListBox.class, new ListBoxDescription.Builder()
					.tagName("SELECT").name(strLocValue).build()).select(strValue);
	}

	public static void verifyText(String strLocType, String strLocValue, String strValue) throws GeneralLeanFtException, InterruptedException
	{
		String text = null;
		if(strLocType.equalsIgnoreCase("id"))
			//text = browser.describe(WebElement.class, new WebElementDescription.Builder().tagName(strLocValue).id(strValue).build()).getInnerText();
			text = browser.describe(WebElement.class,new WebElementDescription.Builder().id(strLocValue).build()).getInnerText();
		else if(strLocType.equalsIgnoreCase("xpath"))
			//browser.describe(WebElement.class,new WebElementDescription.Builder().xpath(strLocValue).build());
			text = browser.describe(WebElement.class,new WebElementDescription.Builder().xpath(strLocValue).build()).getInnerText();
		System.out.println(text);
		//browser.wait(longTimeout);
//		assertEquals(text, strValue, "Text is not same");
		if(strValue.equals(text.trim()))
		{
			System.out.println("Text matching");
		}else
		{
			System.out.println("Text is not same");
		}

	}

	public static void fileBrowse(String strLocType, String strLocValue, String strValue) throws GeneralLeanFtException
	{
		if(strLocType.equalsIgnoreCase("tagName"))
			browser.describe(FileField.class,new FileFieldDescription.Builder().tagName(strLocValue).name(strValue).build()).click();
	}

	public static void fileUpload(String strLocValue, String strValue) throws GeneralLeanFtException
	{
		browser.describe(Dialog.class, new DialogDescription.Builder()
				.ownedWindow(true).childWindow(false).text(strLocValue).nativeClass("#32770").build()).describe(ComboBox.class, new ComboBoxDescription.Builder()
						.attachedText("File &name:").nativeClass("ComboBox").build()).sendKeys(strValue);

		browser.describe(Dialog.class, new DialogDescription.Builder()
				.ownedWindow(true).childWindow(false).text(strLocValue).nativeClass("#32770").build()).describe(UiObject.class, new UiObjectDescription.Builder()
						.windowClassRegExp("Button").windowId(1).build()).click();

	}

	public static void switchToFrame(String strLocType, String strValue) throws GeneralLeanFtException
	{
		//browser.describe(Frame.class, new FrameDescription.Builder().name("").build());
		

		boolean flag = false;
		
		
		 Browser aboutTab = BrowserFactory.attach(new BrowserDescription.Builder().title("Billing Services | Report Viewer").build());
		
				
		if(strLocType.equalsIgnoreCase("Link"))
		{
			
			flag=aboutTab.describe(Page.class, new PageDescription.Builder()
			.title("Billing Services | Report Viewer").build()).describe(Frame.class, new FrameDescription.Builder()
			.name("").build()).describe(Link.class, new LinkDescription.Builder()
			.tagName("A").innerText("ABC Apprentice Trust - So Cal").build()).exists();

			System.out.println("Flag "+flag);
//			assertEquals(flag, true);
			if(flag==true)
			{
				
			}else
			{
				
			}
		}

	}

	public static void eltExists(String strLocType, String strValue) throws GeneralLeanFtException
	{
		boolean flag = false;
		if(strLocType.equalsIgnoreCase("tagName"))
		{
			flag = browser.describe(WebElement.class, new WebElementDescription.Builder()
					.tagName("SPAN").innerText(strValue).build()).exists();
		}
		System.out.println("Flag "+flag);
//		assertEquals(flag, true);
		if(flag==true)
		{
			
		}else
		{
			
		}

	}

	public static void validateNewsRoomURL() throws GeneralLeanFtException, InterruptedException, ReportException
	{
		Thread.sleep(15000);
		Browser newsRoom = BrowserFactory.attach(new BrowserDescription.Builder().title("Newsroom").build());
	

		if (newsRoom.describe(Page.class, new PageDescription.Builder()
		.title("Newsroom").build()).describe(Link.class, new LinkDescription.Builder()
		.tagName("A").innerText("Newsroom Home").build()).exists())
			{Reporter.reportEvent("NewsRoom Page", "News Rom Page is displayed successfully");
			
			System.out.println("News Room Test case Passed");
			newsRoom.close();
			}
				
				

	}

	public static void selectRadioButton(String strLocType, String strLocValue) throws GeneralLeanFtException
	{

		browser.describe(RadioGroup.class, new RadioGroupDescription.Builder()
				.tagName("INPUT").name(strLocValue).build()).click();
	}
	
	public static void selectCheckBox(String strLocType, String strLocValue) throws GeneralLeanFtException
	{
		
		browser.describe(CheckBox.class, new CheckBoxDescription.Builder()
			.type("checkbox").tagName("INPUT").name(strLocValue).build()).click();
	}
}
