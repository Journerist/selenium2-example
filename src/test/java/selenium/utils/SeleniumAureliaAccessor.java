package selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumAureliaAccessor {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public SeleniumAureliaAccessor(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void waitUntilAureliaLoaded() {
        ((JavascriptExecutor) driver).executeAsyncScript(
                "var callback = arguments[arguments.length - 1];" +
                        "document.addEventListener('aurelia-composed', function() { callback();});"
        );
    }

    public void waitUntilLoadingDivDisappears() {
        wait.until(
                ExpectedConditions.not(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("splash"))));
    }

}
