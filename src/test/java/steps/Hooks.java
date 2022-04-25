package steps;

import base.BaseUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks extends BaseUtil{
    private final BaseUtil baseUtil;

    public Hooks(BaseUtil baseUtil) {
        this.baseUtil = baseUtil;
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        baseUtil.driver = new ChromeDriver();
        baseUtil.driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        baseUtil.driver.quit();
    }
}
