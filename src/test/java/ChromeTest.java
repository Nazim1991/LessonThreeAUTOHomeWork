import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChromeTest {

    WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup() {
        driver = WebDriverManager.chromedriver()
                .capabilities(new ChromeOptions()
                        .addArguments("--no-sandbox")
                        .addArguments("--disable-dev-shm-usage")
                        //.addArguments("--headless")
                        .addArguments("--user-data-dir=/tmp/chrome-profile"))
                .create();
        driver.get("http://localhost:9999");
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    /*@Test
    void shouldSubmitRequest() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Василий");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79022090444");
        driver.findElement(By.cssSelector("label[data-test-id='agreement']")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }
    @Test
    void errorNameRequest() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Vasiliy");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79022090444");
        driver.findElement(By.cssSelector("label[data-test-id='agreement']")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[class = input__sub]")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }
    @Test
    void errorPhoneRequest() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Vasiliy");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+!_(AВ><*");
        driver.findElement(By.cssSelector("label[data-test-id='agreement']")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[class = input__sub]")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }
    @Test
    void errorEmpotyNameRequest() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys(" ");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79022090444");
        driver.findElement(By.cssSelector("label[data-test-id='agreement']")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[class = input__sub]")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }
    @Test
    void errorEmpotyPhoneRequest() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Vasiliy");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys(" ");
        driver.findElement(By.cssSelector("label[data-test-id='agreement']")).click();
        driver.findElement(By.className("button")).click();
        String text = driver.findElement(By.cssSelector("[class = input__sub]")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }*/
    @Test
    void errorNoClickRequest() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Василий");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79022090444");
        driver.findElement(By.className("button")).click();
        boolean isErrorDisplayed = driver.findElements(By.cssSelector("[data-test-id='agreement'] .input_invalid")).size() > 0;
        assertTrue(isErrorDisplayed);
    }

}