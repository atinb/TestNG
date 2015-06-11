package firsttestngpackage;


import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class FirstTestNGFile {
	
	private  RemoteWebDriver _webDriver=null;
	 @BeforeClass
	//parameter value will retrieved from testng.xml file's <parameter> tag.
	 @Parameters ({"browser"})
	 public void setup(String browser){//Method will pass value of parameter.
	 
		 DesiredCapabilities capability = new DesiredCapabilities();
         capability.setCapability("browserName", browser);         
         URL server = null;
		try {
			server = new URL("http://atinbvsdesktop:4444/wd/hub");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
         _webDriver = new RemoteWebDriver(server, capability);
         
         _webDriver.manage().window().maximize();
         _webDriver.get("http://bing.com");  
	 } 
	
	 
	 //Both bellow given tests will be executed In both browsers.
	 @Test
	 public void TestNG_SeleniumGridBrowserTest(){   
		 
		 WebElement search = _webDriver.findElement(By.name("q"));
         WebElement go = _webDriver.findElement(By.name("go"));

         search.sendKeys("james bond");
         go.click();

        
             WebElement msWebsite =
                 _webDriver.findElement(By.xpath("//a[@href='http://en.wikipedia.org/wiki/James_Bond']"));
             Assert.assertNotNull(msWebsite);
        
         /*catch () {
             // Save screenshot
             Screenshot screenshot = ((ITakesScreenshot)_webDriver).GetScreenshot();
             screenshot.SaveAsFile("NUnitResult.png", System.Drawing.Imaging.ImageFormat.Png);
         };*/
	 } 
	 
	 @AfterClass 
	 public void closebrowser(){
		 _webDriver.quit();  
	 }
}
