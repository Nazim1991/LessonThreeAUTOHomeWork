import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
                        .addArguments("--headless")
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
        WebElement form = driver.findElement(By.cssSelector("[data-test-id='callback-form']"));
        form.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Василий");
        form.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79270000000");
        form.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        form.findElement(By.cssSelector("[data-test-id='submit']")).click();
        String text = driver.findElement(By.className("alert-success")).getText();
        assertEquals("Ваша заявка успешно отправлена!", text.trim());
    }
}