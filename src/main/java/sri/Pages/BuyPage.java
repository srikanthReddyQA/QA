package sri.Pages;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import sri.ObjectRep.Buy_Obj;
import sri.lib.Util;

public class BuyPage extends Util {
	boolean status;

	public void Buy() throws IOException, InterruptedException {

		Thread.sleep(6000);
		status = click(Buy_Obj.Buy);
		//Logevents(status, "clicked on Buy", "Un able to click on Buy");
		


//driver.findElement(By.xpath("//*[@id='enable_scroll']/div[1]/nav/div/ul/li[2]/a")).click();



		

	
	}
	

	public void Buy_Search() throws IOException {

		status = settext(Buy_Obj.search, getdata("search"));
		Logevents(status, "click on search icon", "Un able to click on search icon");

	}

	public void Buy_Search_icon() throws IOException {

		status = click(Buy_Obj.search_icon);
		Logevents(status, "enter value", "Un able to enter value");

	}

	public void image() {

		List<WebElement> element = driver.findElements(By.xpath("//div[@class='card-content']/div[1]/div[1]"));
		int xx = element.size();
		System.out.println(element.size());
		Iterator<WebElement> list=element.iterator();
		
		for(int i=0;i<56;i++) {
			WebElement cc =list.next();
			String price =cc.getText().trim();
			String ccc =price.substring(0, 4);
			System.out.println(price);
			System.out.println(ccc);
			
			
			 
				// d++; 
			  }
			   
			  
		}
		
		
		
		
		
		
		
	
	

}
