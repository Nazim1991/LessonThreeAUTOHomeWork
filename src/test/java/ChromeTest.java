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
        WebDriverManager.chromedriver().setup();

        driver = WebDriverManager.chromedriver()
                .capabilities(new ChromeOptions()
                        .addArguments("--no-sandbox")
                        .addArguments("--disable-dev-shm-usage")
                        //.addArguments("--headless")
                        .addArguments("--user-data-dir=/tmp/chrome-profile"))
                .create();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    void shouldSubmitRequest() {
        driver.get("http://localhost:9999");
        List<WebElement> inputs = driver.findElements(By.cssSelector("input"));
        inputs.get(0).sendKeys("Василий");
        inputs.get(1).sendKeys("+79022090443");
        driver.findElement(By.cssSelector("label")).click();
        driver.findElement(By.cssSelector("button")).click();
        WebElement result = driver.findElement(By.className("alert-success"));
        assertTrue(result.isDisplayed());
        assertEquals("Ваша заявка успешно отправлена!", result.getText().trim());
    }


}