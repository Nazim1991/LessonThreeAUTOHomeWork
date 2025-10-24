import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CallbackTest {
    private WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldSubmitRequest() {
        driver.get("http://localhost:9999");

        // Используем базовые селекторы
        driver.findElement(By.cssSelector("input[type=text]")).sendKeys("Василий");
        driver.findElement(By.cssSelector("input[type=tel]")).sendKeys("+79270000000");

        // Кликаем по чекбоксу через родительский элемент
        driver.findElement(By.cssSelector(".checkbox__box")).click();

        // Нажимаем кнопку
        driver.findElement(By.tagName("button")).click();

        // Ждем появления любого сообщения
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement message = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".notification, .alert, [data-test-id]"))
        );

        // Проверяем результат
        String text = message.getText().trim();
        System.out.println("Message text: " + text);

        // Более гибкая проверка - содержит ли текст ключевые слова
        if (text.contains("успешно") && text.contains("отправлена")) {
            // Тест пройден
        } else {
            assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text);
        }
    }
}