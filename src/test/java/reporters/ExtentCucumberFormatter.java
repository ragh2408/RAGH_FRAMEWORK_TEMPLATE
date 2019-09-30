package reporters;

import com.hp.lft.sdk.Desktop;
import com.hp.lft.sdk.RegExpProperty;
import com.hp.lft.sdk.te.Keys;
import com.hp.lft.sdk.te.Screen;
import com.hp.lft.sdk.te.ScreenDescription;
import com.hp.lft.sdk.te.Window;
import com.hp.lft.sdk.te.WindowDescription;
import com.relevantcodes.extentreports.*;
import cucumber.runtime.CucumberException;
import cucumber.runtime.io.URLOutputStream;
import gherkin.formatter.Formatter;
import gherkin.formatter.Reporter;
import gherkin.formatter.model.*;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.imageio.ImageIO;

/**
 * Cucumber custom format listener which generates ExtentsReport html file
 */
public class ExtentCucumberFormatter implements Reporter, Formatter {
	

    private static ExtentReports extent;
    private ExtentTest featureTest;
    private ExtentTest scenarioTest;
    private LinkedList<Step> testSteps = new LinkedList<Step>();
    private static File htmlReportDir;
    private static Map systemInfo;
    private boolean scenarioOutlineTest;
    
    static Date date = new Date();
	static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss");  
	
    public static String ReportPath = "output" + File.separator + "Run_" + formatter.format(date);

    private static final Map<String, String> MIME_TYPES_EXTENSIONS = new HashMap() {
        {
            this.put("image/bmp", "bmp");
            this.put("image/gif", "gif");
            this.put("image/jpeg", "jpg");
            this.put("image/png", "png");
            this.put("image/svg+xml", "svg");
            this.put("video/ogg", "ogg");
        }
    };

    public ExtentCucumberFormatter(File filePath) {
        if (!filePath.getPath().equals("")) {
            String reportPath = filePath.getPath();
            this.htmlReportDir = new File(reportPath);
            this.extent = new ExtentReports(reportPath);
        } else {
            String reportDir = "output/Run_" + System.currentTimeMillis();
            this.htmlReportDir = new File(reportDir);
            this.extent = new ExtentReports(reportDir + "/report.html");
        }
    }

    public ExtentCucumberFormatter() {
    }

    public static void initiateExtentCucumberFormatter(File filePath, Boolean replaceExisting,
                                                       DisplayOrder displayOrder, NetworkMode networkMode,
                                                       Locale locale) {
        htmlReportDir = filePath;
        extent = new ExtentReports(filePath.getAbsolutePath(), replaceExisting, displayOrder, networkMode, locale);
    }

    public static void initiateExtentCucumberFormatter(File filePath, Boolean replaceExisting,
                                                       DisplayOrder displayOrder, NetworkMode networkMode) {
        initiateExtentCucumberFormatter(filePath, replaceExisting, displayOrder, networkMode, null);
    }

    public static void initiateExtentCucumberFormatter(File filePath, Boolean replaceExisting,
                                                       DisplayOrder displayOrder, Locale locale) {
        initiateExtentCucumberFormatter(filePath, replaceExisting, displayOrder, null, locale);
    }

    public static void initiateExtentCucumberFormatter(File filePath, Boolean replaceExisting,
                                                       DisplayOrder displayOrder) {
        initiateExtentCucumberFormatter(filePath, replaceExisting, displayOrder, null, null);
    }

    public static void initiateExtentCucumberFormatter(File filePath, Boolean replaceExisting, NetworkMode networkMode,
                                                       Locale locale) {
        initiateExtentCucumberFormatter(filePath, replaceExisting, null, networkMode, locale);
    }

    public static void initiateExtentCucumberFormatter(File filePath, Boolean replaceExisting,
                                                       NetworkMode networkMode) {
        initiateExtentCucumberFormatter(filePath, replaceExisting, null, networkMode, null);
    }

    public static void initiateExtentCucumberFormatter(File filePath, NetworkMode networkMode) {
        initiateExtentCucumberFormatter(filePath, null, null, networkMode, null);
    }

    public static void initiateExtentCucumberFormatter(File filePath, Boolean replaceExisting, Locale locale) {
        initiateExtentCucumberFormatter(filePath, replaceExisting, null, null, locale);
    }

    public static void initiateExtentCucumberFormatter(File filePath, Boolean replaceExisting) {
        initiateExtentCucumberFormatter(filePath, replaceExisting, null, null, null);
    }

    public static void initiateExtentCucumberFormatter(File filePath, Locale locale) {
        initiateExtentCucumberFormatter(filePath, null, null, null, locale);
    }

    public static void initiateExtentCucumberFormatter(File filePath) {
        initiateExtentCucumberFormatter(filePath, null, null, null, null);
    }

    public static void initiateExtentCucumberFormatter() {
        String reportFilePath = ReportPath + File.separator + "report.html";
        initiateExtentCucumberFormatter(new File(reportFilePath));
    }

    public static void setTestRunnerOutput(String s) {
        extent.setTestRunnerOutput(s);
    }

    public static void loadConfig(File configFile) {
        extent.loadConfig(configFile);
    }

    public static void addSystemInfo(String param, String value) {
        if (systemInfo == null) {
            systemInfo = new HashMap();
        }
        systemInfo.put(param, value);
    }

    public static void addSystemInfo(Map<String, String> info) {
        if (systemInfo == null) {
            systemInfo = new HashMap();
        }
        systemInfo.putAll(info);
    }

    public void before(Match match, Result result) {

    }

    public void result(Result result) {
        if (!scenarioOutlineTest) {
            Step step = testSteps.poll();
            
			try {
				if ("passed".equals(result.getStatus())) {
					//String screenshotPath = PageHelper.captureIt();
	                scenarioTest.log(LogStatus.PASS, step.getKeyword() + step.getName(), "PASSED");
	                //scenarioTest.log(LogStatus.INFO, "Snapshot below: " + scenarioTest.addScreenCapture(screenshotPath));
	            } else if ("failed".equals(result.getStatus())) {
	            	//String screenshotPath = PageHelper.captureIt();
	            	//String screenshotPath = "Screenshot\\"+step.getName() +".jpg";
	            	//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> PAth ---" + screenshotPath);
	            		
	            	//BaseClass ab =new BaseClass();
	            	
	            	
	            	//String screenshotPath =W_Screen_StepDefinition_access.ScreenSHot_Test(); ///ab.ScreenSHot_Test();
	            	
	            	String screenshotPath = ScreenSHot(result);
	                //System.out.println("Screen shot path---->>>> " + screenshotPath);   
	            	
	            	String error = result.getErrorMessage();
	            	int i = error.indexOf("'");
	            	int j = error.lastIndexOf("'");
	            //	error = error.substring(i+1, j);
	            	scenarioTest.log(LogStatus.FAIL, step.getKeyword() + step.getName(), "FAILED");
	              //  scenarioTest.log(LogStatus.FAIL, step.getKeyword() + step.getName(), error);
	                //scenarioTest.log(LogStatus.INFO, "Snapshot below: " + scenarioTest.addScreenCapture("screenshotPath"));
	            	scenarioTest.log(LogStatus.INFO, "Snapshot below: " + scenarioTest.addScreenCapture(screenshotPath));
	            	
	            	/*Window teWindow = Desktop.describe(Window.class, new WindowDescription.Builder().shortName(new RegExpProperty(".*")).build());
	        		Screen teScreen = teWindow.describe(Screen.class, new ScreenDescription.Builder().label(new RegExpProperty(".*")).build());
	        		teScreen.sendTEKeys(Keys.PF2);
	        		teScreen.sync();*/
	            	
	            } else if ("skipped".equals(result.getStatus())) {
	                scenarioTest.log(LogStatus.SKIP, step.getKeyword() + step.getName(), "SKIPPED");
	               // scenarioTest.log(LogStatus.INFO, "Snapshot below: " + scenarioTest.addScreenCapture(screenshotPath));
	            } else if ("undefined".equals(result.getStatus())) {
	                scenarioTest.log(LogStatus.UNKNOWN, step.getKeyword() + step.getName(), "UNDEFINED");
	                //scenarioTest.log(LogStatus.INFO, "Snapshot below: " + scenarioTest.addScreenCapture(screenshotPath));
	            }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }
    }

    public void after(Match match, Result result) {

    }

    public void match(Match match) {

    }

    public void embedding(String s, byte[] bytes) {
        if (!scenarioOutlineTest) {
            String extension = (String)MIME_TYPES_EXTENSIONS.get(s);
            String fileName = "screenshot-" + System.currentTimeMillis() + "." + extension;
            this.writeBytesAndClose(bytes, this.reportFileOutputStream(fileName));
            scenarioTest.log(LogStatus.INFO, scenarioTest.addScreenCapture(fileName));
        }
    }

    public void write(String s) {
        if (!scenarioOutlineTest)
            scenarioTest.log(LogStatus.INFO, s);
    }

    public void syntaxError(String s, String s1, List<String> list, String s2, Integer integer) {
    }

    public void uri(String s) {
    }

    public void feature(Feature feature) {
        featureTest = extent.startTest("Feature: " + feature.getName());

        for (Tag tag : feature.getTags()) {
            featureTest.assignCategory(tag.getName());
        }
    }

    public void scenarioOutline(ScenarioOutline scenarioOutline) {
        scenarioOutlineTest = true;
    }

    public void examples(Examples examples) {
    }

    public void startOfScenarioLifeCycle(Scenario scenario) {
        //scenarioTest = extent.startTest("Scenario: " + scenario.getName());
    	scenarioTest = extent.startTest(scenario.getName());
        for (Tag tag : scenario.getTags()) {
            scenarioTest.assignCategory(tag.getName());
        }
        scenarioOutlineTest = false;
    }

    public void background(Background background) {
    }

    public void scenario(Scenario scenario) {
    }

    public void step(Step step) {
        if (!scenarioOutlineTest)
            testSteps.add(step);
    }

    public void endOfScenarioLifeCycle(Scenario scenario) {
        if (!scenarioOutlineTest) {
            extent.endTest(scenarioTest);
            featureTest.appendChild(scenarioTest);
        }
    }

    public void done() {
    }

    public void close() {
        extent.addSystemInfo(systemInfo);
        extent.close();
    }

    public void eof() {
        extent.endTest(featureTest);
        extent.flush();
    }

    private OutputStream reportFileOutputStream(String fileName) {
        try {
            return new URLOutputStream(new URL(this.htmlReportDir.toURI().toURL(), fileName));
        } catch (IOException var3) {
            throw new CucumberException(var3);
        }
    }

    private void writeBytesAndClose(byte[] buf, OutputStream out) {
        try {
            out.write(buf);
        } catch (IOException var4) {
            throw new CucumberException("Unable to write to report file item: ", var4);
        }
    }
    
    public String ScreenSHot(Result result)
    {
    	Date date1 = new Date();
    	SimpleDateFormat formatter1 = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss");
    	
    	try {
    		Thread.sleep(120); 
            Robot r = new Robot(); 
            String current_path = System.getProperty("user.dir");
            String path = current_path +"\\Screenshot\\"+ formatter1.format(date1) +".jpg"; 
            System.out.println("Screen shot patho ----" + path);
            // Used to get ScreenSize and capture image 
            Rectangle capture =  
            new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()); 
            BufferedImage Image = r.createScreenCapture(capture); 
            ImageIO.write(Image, "jpg", new File(path)); 
            System.out.println("~~~~~~~~~~~~~~~~~~~~~Screenshot saved~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); 
            return path;
        } 
        catch (Exception ex) { 
            System.out.println(ex); 
        }
		return null; 
    }
  
    
    
    
  /*  public String ScreenSHot_Test(String result)
    {	
    	try {
    		//System.out.println("Test Name-----" + step.getName());
            Thread.sleep(120); 
            Robot r = new Robot(); 
            String current_path = System.getProperty("user.dir");
            String path = current_path +"\\Screenshot\\"+ formatter.format(date) +".jpg"; 
            System.out.println("Screen shot patho ----" + path);
            // Used to get ScreenSize and capture image 
            Rectangle capture =  
            new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()); 
            BufferedImage Image = r.createScreenCapture(capture); 
            ImageIO.write(Image, "jpg", new File(path)); 
            System.out.println("~~~~~~~~~~~~~~~~~~~~~Screenshot saved~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"); 
            return path;
        } 
        catch (Exception ex) { 
            System.out.println(ex); 
        }
		return null; 
    	
    }
		*/
    
    
    
}
    
    


