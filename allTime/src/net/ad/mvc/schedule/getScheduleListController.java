package net.ad.mvc.schedule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class getScheduleListController {
   public static ArrayList<String> collegeCBNU = new ArrayList<>();
   public static ArrayList<String> collegeSNU = new ArrayList<>();
   public static ArrayList<String> collegeKHU = new ArrayList<>();

   public static void getlist() {
	   new Thread(new Runnable() {

	         @Override
	         public void run() {
	            try {
	               collegeCBNU = useSellenium("quake37", "7712go");
	            } catch (InterruptedException e) {
	               // TODO Auto-generated catch block
	               e.printStackTrace();
	            }
	            // TODO Auto-generated method stub

	         }
	      }).start();
	      new Thread(new Runnable() {

	         @Override
	         public void run() {
	            try {
	               collegeSNU = useSellenium("quake337", "7712go");
	            } catch (InterruptedException e) {
	               // TODO Auto-generated catch block
	               e.printStackTrace();
	            }
	            // TODO Auto-generated method stub

	         }
	      }).start();
	      new Thread(new Runnable() {

	         @Override
	         public void run() {
	            try {
	               collegeKHU = useSellenium("hyj2u94", "qwer1234");
	            } catch (InterruptedException e) {
	               // TODO Auto-generated catch block
	               e.printStackTrace();
	            }
	            // TODO Auto-generated method stub

	         }
	      }).start();


   }

   private static ArrayList<String> useSellenium(String login_id, String login_pwd) throws InterruptedException {

      System.setProperty("webdriver.chrome.driver", "C:\\Users\\kgitbank\\chromedriver_win32\\chromedriver.exe");
      WebDriver driver = new ChromeDriver();
      driver.get("https://everytime.kr/login");
      // driver.manage().window().fullscreen();
      WebElement id = driver.findElement(By.name("userid"));
      WebElement pwd = driver.findElement(By.name("password"));
      id.sendKeys(login_id);
      pwd.sendKeys(login_pwd);
      WebElement login = driver.findElement(By.xpath("//*[@id=\"container\"]/form/p[3]"));
      login.submit();

      driver.findElement(By.xpath("//*[@id=\"menu\"]/li[2]")).click();
      driver.findElement(By.xpath("//*[@id=\"semesters\"]")).click();
      driver.findElement(By.xpath("//*[@id=\"semesters\"]/option[6]")).click();
      driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li[1]")).click();
      (new WebDriverWait(driver, 40)).until(
            ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"sheet\"]/ul/li[3]"))));
      driver.findElement(By.xpath("//*[@id=\"sheet\"]/ul/li[3]")).click();

      if (login_id.equals("quake337")) {
         driver.findElement(By.xpath("//*[@id=\"subjects\"]/div[1]/a[7]")).click();
         driver.findElement(By.xpath("//*[@id=\"subjectGradeFilter\"]/div/label[5]/input")).click();
         driver.findElement(By.xpath("//*[@id=\"subjectGradeFilter\"]/input[3]")).click();
         WebDriverWait wait = new WebDriverWait(driver, 30);
         synchronized (wait) {
            wait.wait(3000);
         }
      }

      JavascriptExecutor js = (JavascriptExecutor) driver;
      for (int i = 0; i < 80; i++) {
         js.executeScript("arguments[0].scrollIntoView(true);",
               driver.findElement(By.xpath("//*[@id=\"subjects\"]/div[2]/table/tfoot")));
         WebDriverWait wait = new WebDriverWait(driver, 30);
         synchronized (wait) {
            wait.wait(3000);
         }
      }

      System.out.println("--------------------------------------------");
      WebElement html1 = driver.findElement(By.className("list"));
      WebElement body1 = html1.findElement(By.tagName("tbody"));
      Map<Integer, ArrayList<String>> map = new TreeMap<>();
      List<WebElement> list1 = body1.findElements(By.tagName("tr"));
      for (int i = 0; i < list1.size(); i++) {
         List<WebElement> sche = list1.get(i).findElements(By.tagName("td"));
         ArrayList<String> temp = new ArrayList<>();
         for (Iterator<WebElement> it = sche.iterator(); it.hasNext();) {
            temp.add(it.next().getText());
         }
         map.put(i, temp);
      }
      ArrayList<String> list = new ArrayList<>();
      for (int i = 0; i < map.size(); i++) {
         ArrayList<String> temp = map.get(i);
         String tm = "";
         for (String j : temp) {
            tm += j + "#";

         }
         String input = null;
         if (login_id.equals("quake37")) {
            input = tm.split("#")[0] + "#" + tm.split("#")[1] + "#" + tm.split("#")[2] + "#" + tm.split("#")[3]
                  + "#" + tm.split("#")[4] + "," + tm.split("#")[5] + "#" + tm.split("#")[9] + "학년";
         } else if (login_id.equals("quake337")) {
            input = tm.split("#")[1] + "#" + tm.split("#")[5] + "#" + tm.split("#")[6] + "#" + tm.split("#")[10]
                  + "#" + tm.split("#")[9] + "#" + tm.split("#")[3];
         } else if (login_id.equals("hyj2u94")) {
            input = tm.split("#")[7] + "#" + tm.split("#")[3] + "#" + tm.split("#")[5] + "#" + tm.split("#")[4]
                  + "#" + tm.split("#")[6] + "#" + tm.split("#")[1] + "학년";
         }
         list.add(input);
      }
      driver.quit();
      return list;
   }

}