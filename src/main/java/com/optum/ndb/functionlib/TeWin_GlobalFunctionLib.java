package com.optum.ndb.functionlib;

import java.awt.AWTException;
import java.awt.image.RenderedImage;

import com.hp.lft.report.ReportException;
import com.hp.lft.report.Reporter;
import com.hp.lft.report.Status;
import com.hp.lft.sdk.GeneralLeanFtException;
import com.hp.lft.sdk.te.Field;
import com.hp.lft.sdk.te.Keys;
import com.hp.lft.sdk.te.Screen;


public class TeWin_GlobalFunctionLib {
	public void syncApplication(Screen src) throws GeneralLeanFtException {
		src.sync();
	}

	/**
	 * @param fldObject
	 * @param strValue
	 * @throws GeneralLeanFtException
	 * @throws AWTException
	 * @throws InterruptedException
	 * @throws ReportException
	 * @author dmisra1 Description - Enters a string value in the input field
	 */
	public void inputEditField(Field fldObject, String strValue)
			throws GeneralLeanFtException, AWTException, InterruptedException, ReportException {
		fldObject.setText(strValue);
	}

	/**
	 * @param scrObj
	 * @param strValue
	 * @throws GeneralLeanFtException
	 * @throws AWTException
	 * @throws InterruptedException
	 * @throws ReportException
	 */
	public void inputEditScreen(Screen scrObj, String strValue)
			throws GeneralLeanFtException, AWTException, InterruptedException, ReportException {
		scrObj.setText(strValue);
	}

	/**
	 * @param fldObject
	 * @param strValue
	 * @throws GeneralLeanFtException
	 * @throws AWTException
	 * @throws InterruptedException
	 */
	public void inputEditFieldAndEnter(Field fldObject, String strValue)
			throws GeneralLeanFtException, AWTException, InterruptedException {
		fldObject.setText(strValue);
	}



	public void clickEnter(Screen Screen) throws GeneralLeanFtException, AWTException, InterruptedException {
		Screen.sendTEKeys(Keys.ENTER);
		Screen.sync();
	}

	public void clickF3(Screen Screen) throws GeneralLeanFtException, AWTException, InterruptedException {
		Screen.sendTEKeys(Keys.PF3);
	}

	public void clickF6(Screen Screen) throws GeneralLeanFtException, AWTException, InterruptedException {
		Screen.sendTEKeys(Keys.PF6);
	}

	public void clickF5(Screen Screen) throws GeneralLeanFtException, AWTException, InterruptedException {
		Screen.sendTEKeys(Keys.PF5);
	}

	public void clickF11(Screen Screen) throws GeneralLeanFtException, AWTException, InterruptedException {
		Screen.sendTEKeys(Keys.PF11);
	}

	public void clickF2(Screen Screen) throws GeneralLeanFtException, AWTException, InterruptedException {
		Screen.sendTEKeys(Keys.PF2);
	}

	public void clickDOWN(Screen Screen) throws GeneralLeanFtException, AWTException, InterruptedException {
		Screen.sendTEKeys(Keys.DOWN);
	}

	public void inputEditField_DJ(Field fldObject, String strValue) throws GeneralLeanFtException {
		fldObject.setText(strValue);
	}

	public void clickUP(Screen Screen) throws GeneralLeanFtException, AWTException, InterruptedException {
		Screen.sendTEKeys(Keys.UP);
	}

	public void clickF9(Screen Screen) throws GeneralLeanFtException, AWTException, InterruptedException {
		Screen.sendTEKeys(Keys.PF9);
	}
}
