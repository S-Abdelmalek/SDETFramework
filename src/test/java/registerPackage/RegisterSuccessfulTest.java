package registerPackage;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.AccountPage;
import pageObjects.RegisterPage;
import resources.Base;
import resources.TestCasesData;

public class RegisterSuccessfulTest extends Base{
	
	WebDriver driver;
	
	@BeforeClass
	void init () throws IOException
	{
		driver = initializeDriver();
		driver.get(prop.getProperty("registerUrl"));
		driver.manage().window().maximize();
	}
	
	@Test (dataProvider = "getData")
	void validateRegisterSuccess (String fname , String lname , String mobile , String email, String password , String passconfirm )
	{
		RegisterPage rp = new RegisterPage(driver);
		AccountPage ap = new AccountPage(driver);
		rp.getFirstName().clear();
		rp.enterFirstName(fname);
		rp.getLastName().clear();
		rp.enterLastName(lname);
		rp.getMobileNumber().clear();
		rp.enterMobileNumber(mobile);
		rp.getEmail().clear();
		rp.enterEmail(email);
		rp.getPassword().clear();
		rp.enterPassword(password);
		rp.getPasswordConfirm().clear();
		rp.enterPasswordConfirm(passconfirm);
		rp.clickSignUpBtn();
		rp.clickSignUpBtn();
		Assert.assertEquals(driver.getCurrentUrl(),prop.getProperty("accountUrl"));
		Assert.assertTrue((ap.getPageTitle().contains(fname))&&(ap.getPageTitle().contains(lname)));
		Assert.assertTrue(ap.getAccountBtnTxt().contains(fname));
		
	}
	
	@Test (dataProvider = "getData")
	void validateEmailUnique (String fname , String lname , String mobile , String email, String password , String passconfirm)
	{
		RegisterPage rp = new RegisterPage(driver);
		rp.getFirstName().clear();
		rp.enterFirstName(fname);
		rp.getLastName().clear();
		rp.enterLastName(lname);
		rp.getMobileNumber().clear();
		rp.enterMobileNumber(mobile);
		rp.getEmail().clear();
		rp.enterEmail(email);
		rp.getPassword().clear();
		rp.enterPassword(password);
		rp.getPasswordConfirm().clear();
		rp.enterPasswordConfirm(passconfirm);
		rp.clickSignUpBtn();
		boolean emailAlreadyExistBoxFound = (rp.getEntryAlertBox().isDisplayed())&&(rp.getEntryAlertBox().isEnabled());
		Assert.assertTrue(emailAlreadyExistBoxFound);
		Assert.assertEquals(rp.getEntryAlertBoxText(), "Email Already Exists.");
	}
	
	@DataProvider
	Object[][] getData () throws IOException
	{
		Object[][] dataObj = new Object[1][6];
		TestCasesData data = new TestCasesData();
		ArrayList <String> testData = new ArrayList <String> ();
		for (int i=0 ; i<1 ; i++)
		{
			testData = data.getDataFromExcelSheet("validateRegisterSuccess");
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
