
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Lotto {
    
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","chromedriver地址");
        WebDriver driver = new ChromeDriver();
        driver.get("任意B站动态页面的url");

        Set<String> ids = new HashSet<String>();
        try{
            TimeUnit.SECONDS.sleep(10);


            while (true){
                List<WebElement> eles = driver.findElements(By.xpath("//*[@class=\"item-user\"]/a[@href]"));
//                抓取转发+评论的用户主页，并用Set数据结构去重（多次转发并不能增加中奖几率哦）
                for (WebElement ele : eles){
                    ids.add(ele.getAttribute("href"));
                }
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("window.scrollBy(0,5000)");
                try{
                    WebElement ele = driver.findElement(By.className("nomore"));
                    break;
                }catch (Exception e){
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        Random random = new Random();  // 从去重结果中随机抽取用户
        List<String> list = new ArrayList<>(ids);
        System.out.println("恭喜B站用户："+list.get(random.nextInt(ids.size())));
    }
}
