package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
	
WebDriver driver;
	

	public RegisterPage (WebDriver driver)
	{
		this.driver = driver;
	}
	
	private By firstNameTxt =By.xpath("//input[@name='firstname']");
	private By lastNameTxt =By.xpath("//input[@name='lastname']");
	private By mobileNumberTxt =By.xpath("//input[@name='phone']");
	private By emailTxt =By.xpath("//input[@name='email']");
	private By passwordTxt =By.xpath("//input[@name='password']");
	private By passwordConfirmTxt =By.xpath("//input[@name='confirmpassword']");
	private By signUpBtn =By.cssSelector("[class='signupbtn btn_full btn btn-success btn-block btn-lg']");
	private By entryAlert = By.xpath("//div[@class='alert alert-danger']");
	

	public WebElement getFirstName ()
	{
	 return driver.findElement(firstNameTxt);
	}
	public void enterFirstName (String fName)
	{
	 driver.findElement(firstNameTxt).sendKeys(fName);
	}
	public WebElement getLastName ()
	{
	 return driver.findElement(lastNameTxt);
	}
	public void enterLastName (String lName)
	{
	 driver.findElement(lastNameTxt).sendKeys(lName);
	}
	public WebElement getMobileNumber ()
	{
	 return  driver.findElement(mobileNumberTxt);
	}
	public void enterMobileNumber (String mobNum)
	{
	 driver.findElement(mobileNumberTxt).sendKeys(mobNum);
	}
	public WebElement getEmail ()
	{
	 return driver.findElement(emailTxt);
	}
	public void enterEmail (String mail)
	{
	 driver.findElement(emailTxt).sendKeys(mail);
	}
	public WebElement getPassword ()
	{
	 return driver.findElement(passwordTxt);
	}
	public void enterPassword (String pass)
	{
	 driver.findElement(passwordTxt).sendKeys(pass);
	}
	public WebElement getPasswordConfirm ()
	{
	 return  driver.findElement(passwordConfirmTxt);
	}
	public void enterPasswordConfirm (String passConf)
	{
	 driver.findElement(passwordConfirmTxt).sendKeys(passConf);
	}
	public WebElement getSignUpBtn ()
	{
	 return driver.findElement(signUpBtn);
	}
	public void clickSignUpBtn ()
	{
	 driver.findElement(signUpBtn).click();
	}
	public WebElement getEntryAlertBox ()
	{
		return driver.findElement(entryAlert);
	}
	public String getEntryAlertBoxText ()
	{
		return driver.findElement(entryAlert).getText();
	}

}
