package StepDefinitions;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReminderStepDefinition 
{
	
	static WebDriver driver;
	
	@Given("User is on create reminder landing page")
	public void user_is_on_create_reminder_landing_page()
	{
	   System.out.println("User is on reminder landing page");
	   driver =new ChromeDriver();
	   driver.manage().window().maximize();
	   driver.manage().deleteAllCookies();
	   driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	   driver.get("https://todomvc.com/examples/vue/dist/#/");
	   
	}

	@When("he creates a reminder {string}")
	public void he_creates_a_reminder(String string) 
	{
		System.out.println("User creating reminder");
		System.out.println(driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "TodoMVC: Vue");
		driver.findElement(By.xpath("/html/body/section/header/input")).sendKeys(string);
		driver.findElement(By.xpath("/html/body/section/header/input")).sendKeys(Keys.ENTER);
	}

	@Then("he should see {string} added to the reminder list")
	public void he_should_see_added_to_the_reminder_list(String string) 
	{
		System.out.println("User should he reminder added");
		
		String Remindername =driver.findElement(By.xpath("/html/body/section/main/ul/li/div[1]/label")).getText();
		Assert.assertEquals(Remindername, string, "Reminder Added isn't present in the list");
	}

	@And("he should see a total of {int} reminder added to the list")
	public void he_should_see_a_total_of_reminder_added_to_the_list(Integer int1) 
	{
		System.out.println("Reminder count increased by 1");
		
		int Size =driver.findElements(By.xpath("/html/body/section/main/ul/li")).size();
		Assert.assertEquals(Size,1,"Total Reminder Added is not 1");
	}


}
