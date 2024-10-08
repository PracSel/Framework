package StepDefinitions;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import com.google.common.util.concurrent.Uninterruptibles;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReminderStepDefinition 
{

	static WebDriver driver;
	@Given("user is on create reminder landing page")
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
		Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
		driver.findElement(By.xpath("/html/body/section/header/input")).sendKeys(string);
		driver.findElement(By.xpath("/html/body/section/header/input")).sendKeys(Keys.ENTER);
	}
	@Then("he should see {string} added to the reminder list")
	public void he_should_see_added_to_the_reminder_list(String string) 
	{
		System.out.println("User should he reminder added");
		Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
		String Remindername =driver.findElement(By.xpath("/html/body/section/main/ul/li/div[1]/label")).getText();
		Assert.assertEquals(Remindername, string, "Reminder Added isn't present in the list");
	}
	@Then("he should see a total of {int} reminders added to the list")
	public void he_should_see_a_total_of_reminders_added_to_the_list(Integer int1)
	{
		System.out.println("Reminder count increased by 1");
		Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
		int Size =driver.findElements(By.xpath("/html/body/section/main/ul/li")).size();
		Assert.assertEquals(Size,int1,"Total Reminder Added is not 1");
		Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
	}
	@Then("he sees the active count to be {string}")
	public void he_sees_the_active_count_to_be(String string) 
	{
		System.out.println("Active reminder count");
		String Size=driver.findElement(By.xpath("*//span[@class='todo-count']/strong")).getText();
		Assert.assertEquals(Size,string,"Total Reminder Added is not 1");
		Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(1));
		System.out.println("Active count 0");
	}
	@When("he strikes out {string}")
	public void he_strikes_out(String string) 
	{
		System.out.println("Striked out the reminder");
		Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
		int Todolist=driver.findElements(By.xpath("//*[@class='todo-list']//li")).size();
		for(int i=1;i<=Todolist;i++)
		{
			String Listname = driver.findElement(By.xpath("//*[@class='todo-list']//li["+i+"]//div[@class='view']//label")).getText();
			System.out.println("Listname: "+Listname);
			if(Listname.equalsIgnoreCase(string))
			{
				driver.findElement(By.xpath("//*[@class='todo-list']//li["+i+"]//div[@class='view']//input")).click();
			}

		}
		Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(3));
		System.out.println("Strikes out done");
		
	}
	@When("he creates following reminders")
	public void he_creates_following_reminders(List<String> dataTable) 
	{
		for (String Name:dataTable)
		{
			driver.findElement(By.xpath("/html/body/section/header/input")).sendKeys(Name);
			driver.findElement(By.xpath("/html/body/section/header/input")).sendKeys(Keys.ENTER);
		}
	}
	@When("user refreshes the reminder landing page")
	public void user_refreshes_the_reminder_landing_page()
	{
		driver.navigate().refresh();
		
	}
}