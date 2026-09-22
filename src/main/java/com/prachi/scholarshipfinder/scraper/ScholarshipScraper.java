package com.prachi.scholarshipfinder.scraper;

import com.prachi.scholarshipfinder.entity.Scholarship;
import com.prachi.scholarshipfinder.service.ScholarshipService;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

@Component
public class ScholarshipScraper {

    @Autowired
    private ScholarshipService scholarshipService;

    public void scrapeScholarships() {

        WebDriver driver = null;

        int newScholarships = 0;
        int updatedScholarships = 0;
        int unchangedScholarships = 0;
        int skippedScholarships = 0;

        try {

            System.out.println("\n==================================");
            System.out.println("   STARTING SCHOLARSHIP UPDATE");
            System.out.println("==================================");

            WebDriverManager.chromedriver().setup();

            driver = new ChromeDriver();

            driver.manage().window().maximize();

            driver.manage().timeouts()
                    .implicitlyWait(Duration.ofSeconds(10));

            driver.get(
                    "https://scholarships.gov.in/All-Scholarships"
            );

            Thread.sleep(5000);

            List<WebElement> ministries =
                    driver.findElements(
                            By.cssSelector(
                                    "button.accordion-button"
                            )
                    );

            System.out.println(
                    "Total Ministries : "
                            + ministries.size()
            );

            for (int i = 0; i < ministries.size(); i++) {

                try {

                    ministries =
                            driver.findElements(
                                    By.cssSelector(
                                            "button.accordion-button"
                                    )
                            );

                    WebElement ministry =
                            ministries.get(i);

                    ((JavascriptExecutor) driver)
                            .executeScript(
                                    "arguments[0].scrollIntoView({block:'center'});",
                                    ministry
                            );

                    Thread.sleep(1000);

                    ((JavascriptExecutor) driver)
                            .executeScript(
                                    "arguments[0].click();",
                                    ministry
                            );

                    Thread.sleep(2500);

                    String ministryName =
                            ministry.getText().trim();

                    System.out.println("\n==================================");
                    System.out.println(
                            "MINISTRY : "
                                    + ministryName
                    );
                    System.out.println("==================================");

                    WebElement accordionItem =
                            ministry.findElement(
                                    By.xpath(
                                            "./ancestor::div[contains(@class,'accordion-item')]"
                                    )
                            );

                    WebElement body =
                            accordionItem.findElement(
                                    By.cssSelector(
                                            "div.accordion-collapse"
                                    )
                            );

                    List<WebElement> cards =
                            body.findElements(
                                    By.cssSelector(
                                            "div.col-md-10"
                                    )
                            );

                    System.out.println(
                            "Scholarships Found : "
                                    + cards.size()
                    );

                    for (WebElement card : cards) {

                        try {

                            String scholarshipName =
                                    card.findElement(
                                                    By.tagName("h6")
                                            )
                                            .getText()
                                            .trim();

                            if (scholarshipName.isEmpty()) {

                                skippedScholarships++;

                                continue;
                            }

                            /*
                             * --------------------------------------
                             * GET LAST DATE
                             * --------------------------------------
                             */

                            List<WebElement> spans =
                                    card.findElements(
                                            By.tagName("span")
                                    );

                            String lastDate = "";

                            for (WebElement span : spans) {

                                String text =
                                        span.getText().trim();

                                if (text.contains(
                                        "Student Application")) {

                                    lastDate = text;

                                    break;
                                }
                            }

                            /*
                             * --------------------------------------
                             * FIND EXISTING SCHOLARSHIP
                             * --------------------------------------
                             */

                            Scholarship existingScholarship =
                                    scholarshipService
                                            .findExistingScholarship(
                                                    scholarshipName
                                            );

                            /*
                             * ======================================
                             * NEW SCHOLARSHIP
                             * ======================================
                             */

                            if (existingScholarship == null) {

                                Scholarship scholarship =
                                        new Scholarship();

                                scholarship.setScholarshipName(
                                        scholarshipName
                                );

                                scholarship.setCategory(
                                        ministryName
                                );

                                scholarship.setState(
                                        "India"
                                );

                                scholarship.setIncomeLimit(
                                        0
                                );

                                scholarship.setEligibility(
                                        "Refer Official NSP"
                                );

                                scholarship.setAmount(
                                        0
                                );

                                scholarship.setLastDate(
                                        lastDate
                                );

                                scholarship.setWebsite(
                                        "https://scholarships.gov.in"
                                );

                                scholarshipService
                                        .addScholarship(
                                                scholarship
                                        );

                                newScholarships++;

                                System.out.println(
                                        "NEW : "
                                                + scholarshipName
                                );
                            }

                            /*
                             * ======================================
                             * EXISTING SCHOLARSHIP
                             * ======================================
                             */

                            else {

                                boolean changed = false;

                                /*
                                 * Check Category
                                 */

                                if (ministryName != null
                                        && !ministryName.isEmpty()
                                        && !ministryName.equals(
                                        existingScholarship
                                                .getCategory())) {

                                    existingScholarship
                                            .setCategory(
                                                    ministryName
                                            );

                                    changed = true;
                                }

                                /*
                                 * Check Last Date
                                 */

                                if (lastDate != null
                                        && !lastDate.isEmpty()
                                        && !lastDate.equals(
                                        existingScholarship
                                                .getLastDate())) {

                                    existingScholarship
                                            .setLastDate(
                                                    lastDate
                                            );

                                    changed = true;
                                }

                                /*
                                 * Check Website
                                 */

                                String website =
                                        "https://scholarships.gov.in";

                                if (!website.equals(
                                        existingScholarship
                                                .getWebsite())) {

                                    existingScholarship
                                            .setWebsite(
                                                    website
                                            );

                                    changed = true;
                                }

                                /*
                                 * ----------------------------------
                                 * SAVE CHANGES
                                 * ----------------------------------
                                 */

                                if (changed) {

                                    scholarshipService
                                            .updateScholarship(
                                                    existingScholarship
                                            );

                                    updatedScholarships++;

                                    System.out.println(
                                            "UPDATED : "
                                                    + scholarshipName
                                    );

                                } else {

                                    unchangedScholarships++;

                                    System.out.println(
                                            "NO CHANGE : "
                                                    + scholarshipName
                                    );
                                }
                            }

                        } catch (Exception e) {

                            skippedScholarships++;

                            System.out.println(
                                    "Skipped one scholarship."
                            );
                        }
                    }

                    /*
                     * --------------------------------------
                     * CLOSE ACCORDION
                     * --------------------------------------
                     */

                    ((JavascriptExecutor) driver)
                            .executeScript(
                                    "arguments[0].click();",
                                    ministry
                            );

                    Thread.sleep(1000);

                } catch (Exception e) {

                    System.out.println(
                            "Skipped one ministry."
                    );
                }
            }

            /*
             * ==============================================
             * FINAL RESULT
             * ==============================================
             */

            System.out.println("\n==================================");
            System.out.println(
                    "      SCRAPING COMPLETED"
            );
            System.out.println("==================================");

            System.out.println(
                    "New Scholarships Added : "
                            + newScholarships
            );

            System.out.println(
                    "Scholarships Updated : "
                            + updatedScholarships
            );

            System.out.println(
                    "No Changes : "
                            + unchangedScholarships
            );

            System.out.println(
                    "Skipped : "
                            + skippedScholarships
            );

            System.out.println("==================================");

        } catch (Exception e) {

            System.out.println(
                    "\nERROR DURING SCHOLARSHIP UPDATE"
            );

            e.printStackTrace();

        } finally {

            if (driver != null) {

                try {
                    driver.quit();
                } catch (Exception ignored) {
                }
            }
        }
    }
}