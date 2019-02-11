import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.util.concurrent.TimeUnit;

public class projectNew {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver\\chromedriver.exe");
        //System.setProperty("webdriver.gecko.driver", "C:\\Selenium\\geckodriver\\geckodriver.exe");
        //System.setProperty("webdriver.ie.driver", "C:\\Selenium\\IEDriverServer\\IEDriverServer.exe");
        WebDriver driver = new ChromeDriver();
        //WebDriver driver = new FirefoxDriver();
        //WebDriver driver = new InternetExplorerDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://blog.csssr.ru/qa-engineer/");
        WebElement element;

        //1. Ширина контентной части должна быть равна 1000px
        element = driver.findElement(By.className("wrap"));
        if (element.getSize().toString().equals("(1000, 1757)")) {
            System.out.println("Test 1 - ok");
        } else System.out.println("Test 1 - failed");

        //2. При преключении столбики с процентами должны заполняются красным паттерном снизу вверх
        driver.findElement(By.linkText("НАХОДИТЬ НЕСОВЕРШЕНСТВА")).click();
        element = driver.findElement(By.className("graph-active"));
        int y = element.getRect().y;
        while (element.getSize().getHeight() < 98) {   //98px - graphs-errors graph-active max-height
            //System.out.println("y - " + element.getRect().y + " Height - " + element.getSize().getHeight());
        }
        if (y > element.getRect().y) {
            System.out.println("Test 2 - ok");
        } else System.out.println("Test 2 - failed");

        //3. ссылка "Софт для быстрого создания скриншотов"
        driver.findElement(By.linkText("НАХОДИТЬ НЕСОВЕРШЕНСТВА")).click();
        if (driver.findElement(By.linkText("Софт для быстрого создания скриншотов")).getAttribute("href").equals("https://monosnap.com"))
        {System.out.println("Test 3 - ok");
        } else System.out.println("Test 3 - failed");

        //4. Section.info не отображает информацию если выбрать любую вкладку дважды
        driver.findElement(By.linkText("ВНИКАТЬ В ДЕТАЛИ ПРОЕКТОВ")).click();
        element = driver.findElement(By.className("info-details"));

        while (!element.getCssValue("opacity").contentEquals("1")) {
            //System.out.println(element.getCssValue("opacity"));
        }
        driver.findElement(By.linkText("ВНИКАТЬ В ДЕТАЛИ ПРОЕКТОВ")).click();
        while (!element.getCssValue("opacity").contentEquals("1")) {
            //System.out.println(element.getCssValue("opacity"));
        }
        if (element.getCssValue("display").equals("block")) {
        System.out.println("Test 4 - ok");
        } else System.out.println("Test 4 - failed");

        //6. Чекбоксы beautiful, attention2, beautiful2 работают только один раз
        driver.findElement(By.linkText("НАХОДИТЬ НЕСОВЕРШЕНСТВА")).click();
        driver.findElement(By.xpath("//label[@for='beautiful']")).click();
        driver.findElement(By.xpath("//label[@for='beautiful']")).click();
        if (driver.findElement(By.id("beautiful")).isSelected()) {
            System.out.print("Test 6: beautiful - ok;");
        } else System.out.print("Test 6: beautiful - failed;");

        driver.findElement(By.linkText("СОПРОВОЖДАТЬ ПРОЕКТЫ")).click();
        driver.findElement(By.xpath("//label[@for='attention2']")).click();
        driver.findElement(By.xpath("//label[@for='attention2']")).click();
        if (driver.findElement(By.id("attention2")).isSelected()) {
            System.out.print(" attention2 - ok;");
        } else System.out.print(" attention2 - failed;");

        driver.findElement(By.linkText("РАБОТАТЬ С ФАЙЛАМИ ПРОЕКТОВ")).click();
        driver.findElement(By.xpath("//label[@for='beautiful2']")).click();
        driver.findElement(By.xpath("//label[@for='beautiful2']")).click();
        if (driver.findElement(By.id("beautiful2")).isSelected()) {
            System.out.println(" beautiful2 - ok;");
        } else System.out.println(" beautiful2 - failed;");

        //7. Длина border-bottom в названии вкладок не соответствует длине текста

        //8. Столбцы диаграммы имеют разную ширину
        element = driver.findElement(By.className("graphs-details"));
        String bar1 = element.getCssValue("width");
        element = driver.findElement(By.className("graphs-errors"));
        String bar2 = element.getCssValue("width");
        element = driver.findElement(By.className("graphs-support"));
        String bar3 = element.getCssValue("width");
        element = driver.findElement(By.className("graphs-files"));
        String bar4 = element.getCssValue("width");
        if (bar1.equals(bar2) && bar1.equals(bar3) && bar1.equals(bar4)) {
        System.out.println("Test 8 - ok");
        } else System.out.println("Test 8 - failed");

        //10. Popup сообщение “Поздравляем, вы только что достигли 100500 уровень в тестировании!”
        driver.findElement(By.linkText("ВНИКАТЬ В ДЕТАЛИ ПРОЕКТОВ")).click();
        driver.findElement(By.linkText("СОПРОВОЖДАТЬ ПРОЕКТЫ")).click();
        driver.findElement(By.linkText("НАХОДИТЬ НЕСОВЕРШЕНСТВА")).click();
        driver.findElement(By.linkText("РАБОТАТЬ С ФАЙЛАМИ ПРОЕКТОВ")).click();
        driver.findElement(By.linkText("ВНИКАТЬ В ДЕТАЛИ ПРОЕКТОВ")).click();
        driver.findElement(By.linkText("НАХОДИТЬ НЕСОВЕРШЕНСТВА")).click();
        element = driver.findElement(By.className("egg"));
        if (element.getText().isEmpty()) {
        System.out.println("Test 10 - ok");
        } else System.out.println("Test 10 - failed");

        //driver.quit();
    }
}
