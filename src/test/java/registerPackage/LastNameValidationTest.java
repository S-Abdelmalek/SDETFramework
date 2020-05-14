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

public class LastNameValidationTest extends Base{
	@BeforeClass
	void init () throws IOException
	{
		driver = initializeDriver();
	}

	@Test (dataProvider = "getData")
	void validateLastNameNegative (String fname,String lname,String mob,String email,String pass,String passconf) throws IOException, Exception
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
		Thread.sleep(3000);
		Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("registerUrl"));
	}
	
	@DataProvider
	Object[][] getData () throws IOException
	{
		Object[][] dataObj = new Object[2][6];
		TestCasesData data = new TestCasesData();
		ArrayList <String> testData = new ArrayList <String> ();
		for (int i=0 ; i<2 ; i++)
		{
			testData = data.getDataFromExcelSheet("validateLastNameNegative"+String.valueOf(i+1));
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
