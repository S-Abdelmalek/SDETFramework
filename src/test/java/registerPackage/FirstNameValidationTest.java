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

public class FirstNameValidationTest extends Base{
	
	@BeforeClass
	void init () throws IOException
	{
		driver = initializeDriver();
		driver.get(prop.getProperty("registerUrl"));
		driver.manage().window().maximize();
	}
	

	@Test (dataProvider = "getData")
	void validateFirstNameNegative (String fname,String lname,String mob,String email,String pass,String passconf) throws IOException, Exception
	{
		
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
		Object[][] dataObj = new Object[1][6];
		TestCasesData data = new TestCasesData();
		ArrayList <String> testData = new ArrayList <String> ();
		testData = data.getDataFromExcelSheet("validateFirstNameNegative");
			for (int j=0  ; j<6 ;j++)
			{
				dataObj[0][j] = testData.get(j);
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
