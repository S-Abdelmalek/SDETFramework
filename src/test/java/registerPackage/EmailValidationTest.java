package registerPackage;

import java.io.IOException;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.RegisterPage;
import resources.Base;
import resources.TestCasesData;

public class EmailValidationTest extends Base {
	
	@BeforeClass
	void init () throws IOException
	{
		driver = initializeDriver();
	}

	@Test (dataProvider = "getData")
	void validateEmailNegative (String fname,String lname,String mob,String email,String pass,String passconf) throws IOException, Exception
	{
		driver.get(prop.getProperty("registerUrl"));
		Assert.assertTrue(driver.getCurrentUrl().contains("register"));
		RegisterPage rp = new RegisterPage(driver);
		rp.getFirstName().clear();
		rp.enterFirstName(fname);
		rp.getLastName().clear();
		rp.enterLastName(lname);
		rp.getMobileNumber().clear();
		rp.enterMobileNumber(mob);
		rp.getEmail().clear();
		rp.enterEmail(email);
		rp.getPassword().clear();
		rp.enterPassword(pass);
		rp.getPasswordConfirm().clear();
		rp.enterPasswordConfirm(passconf);
		rp.clickSignUpBtn();
		boolean emailInvalidBoxFound = (rp.getEntryAlertBox().isDisplayed())&&(rp.getEntryAlertBox().isEnabled());
		Assert.assertTrue(emailInvalidBoxFound);
		Assert.assertEquals(rp.getEntryAlertBoxText(), "The Email field must contain a valid email address.");
	}
	
	@DataProvider
	Object[][] getData () throws IOException
	{
		Object[][] dataObj = new Object[3][6];
		TestCasesData data = new TestCasesData();
		ArrayList <String> testData = new ArrayList <String> ();
		for (int i=0 ; i<3 ; i++)
		{
			testData = data.getDataFromExcelSheet("validateEmailNegative"+String.valueOf(i+1));
			for (int j=0  ; j<6 ;j++)
			{
				dataObj[i][j] = testData.get(j);
			}
		}
		return dataObj;
	}

	@AfterClass
	void end ()
	{
		driver.close();
		driver = null;
	}


}
