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

public class AllFieldsMandatoryTest extends Base {
	
	@BeforeClass
	void init () throws IOException
	{
		driver = initializeDriver();
		driver.get(prop.getProperty("registerUrl"));
		driver.manage().window().maximize();
	}
	

	@Test (dataProvider = "getData")
	void validateAllFieldsMandatory (String fname,String lname,String mob,String email,String pass,String passconf) throws IOException
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
		if (fname == "")
			Assert.assertEquals(rp.getFirstName().getAttribute("validationMessage"), "Please fill out this field.");
		else if (lname == "")
			Assert.assertEquals(rp.getLastName().getAttribute("validationMessage"), "Please fill out this field.");
		else if (mob == "")
			Assert.assertEquals(rp.getMobileNumber().getAttribute("validationMessage"), "Please fill out this field.");
		else if (email == "")
			Assert.assertEquals(rp.getEmail().getAttribute("validationMessage"), "Please fill out this field.");
		else if (pass == "")
			Assert.assertEquals(rp.getPassword().getAttribute("validationMessage"), "Please fill out this field.");
		else if (passconf == "")
			Assert.assertEquals(rp.getPasswordConfirm().getAttribute("validationMessage"), "Please fill out this field.");
		else
			Assert.assertEquals(rp.getFirstName().getAttribute("validationMessage"), "Please fill out this field.");
	}
	
	@DataProvider
	Object[][] getData () throws IOException
	{
		Object[][] dataObj = new Object[7][6];
		TestCasesData data = new TestCasesData();
		ArrayList <String> testData = new ArrayList <String> ();
		for (int i=0 ; i<7 ; i++)
		{
			testData = data.getDataFromExcelSheet("validateAllFieldsMandatory"+String.valueOf(i+1));
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
