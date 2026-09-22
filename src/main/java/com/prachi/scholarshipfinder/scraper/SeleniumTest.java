package com.prachi.scholarshipfinder.scraper;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class SeleniumTest {

    public static void main(String[] args) throws Exception {

        scrapeScholarships();

    }

    public static void scrapeScholarships() throws Exception {

        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://scholarships.gov.in/All-Scholarships");

        Thread.sleep(5000);

        List<WebElement> ministries =
                driver.findElements(By.cssSelector("button.accordion-button"));

        System.out.println("Total Ministries = " + ministries.size());

        for (int i = 0; i < ministries.size(); i++) {

            ministries = driver.findElements(By.cssSelector("button.accordion-button"));

            WebElement ministry = ministries.get(i);

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView({block:'center'});", ministry);

            Thread.sleep(1000);

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", ministry);

            Thread.sleep(2500);

            String ministryName = ministry.getText().trim();

            System.out.println("\n====================================");
            System.out.println("MINISTRY : " + ministryName);
            System.out.println("====================================");

            WebElement accordionItem =
                    ministry.findElement(By.xpath("./ancestor::div[contains(@class,'accordion-item')]"));

            WebElement body =
                    accordionItem.findElement(By.cssSelector("div.accordion-collapse"));

            List<WebElement> cards =
                    body.findElements(By.cssSelector("div.col-md-10"));

            System.out.println("Scholarships Found : " + cards.size());

            for (WebElement card : cards) {

                try {

                    String scholarshipName =
                            card.findElement(By.tagName("h6")).getText().trim();

                    if (scholarshipName.isEmpty()) {
                        continue;
                    }

                    System.out.println("\nScholarship : " + scholarshipName);

                    List<WebElement> spans =
                            card.findElements(By.tagName("span"));

                    for (WebElement span : spans) {

                        System.out.println(span.getText());

                    }

                    List<WebElement> links =
                            card.findElements(By.tagName("a"));

                    for (WebElement link : links) {

                        String text = link.getText().trim();
                        String url = link.getAttribute("href");

                        System.out.println(text + " -> " + url);

                    }

                    System.out.println("----------------------------------------");

                }
                catch (Exception e) {

                    System.out.println("Skipped one scholarship card.");

                }

            }

            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", ministry);

            Thread.sleep(1000);

        }

        driver.quit();

    }
}