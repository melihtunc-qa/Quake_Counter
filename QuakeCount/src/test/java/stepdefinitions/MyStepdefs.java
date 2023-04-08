package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MyStepdefs {

    WebDriver driver;
    List<WebElement> quakelist;

    @Before
    public void setup(){

        driver=new ChromeDriver();

    }


    @Given(": go to AFAD web site")
    public void goToAFADWebSite() {

        driver.get("https://deprem.afad.gov.tr/");

    }

    @And(": navigate to the last earthquakes data")
    public void navigateToTheLastEarthquakes() {

        driver.navigate().to("https://deprem.afad.gov.tr/last-earthquakes.html");

    }

    @When("List to dates,deepnesses,locates and magnitudes of the last {int} earthquakes")
    public void listToDatesDeepnessesLocatesAndMagnitudesOfTheLastEarthquakes() throws IOException {

        quakelist = driver.findElements(By.tagName("tr"));

        File file = new File("src/test/java/quakelist.txt");

        FileWriter fw = new FileWriter(file);

        fw.write(quakelist.toString());
        fw.close();


    }

    @And("test that at least one of the earthquakes is greater than {double}")
    public void testThatAtLeastOneOfTheEarthquakesIsGreaterThan(int i , double magnitude) {

        magnitude = 4.0;

        boolean atLeastOneQuakeOverFour = false;
        for (i = 0; i <= 100; i++) {
            WebElement quakeMagnitude = quakelist.get(i).findElements(By.tagName("td")).get(5);

            if (Double.parseDouble(quakeMagnitude.getText()) >= magnitude) {
                atLeastOneQuakeOverFour = true;


                break;
            }
        }


        Assert.assertTrue(atLeastOneQuakeOverFour);

        if (atLeastOneQuakeOverFour) {
            System.out.println(" son 100 deprem içerisinde tehlike arz eden en az bir deprem bulunmaktadır ");
        } else {
            System.out.println(" son 100 deprem içerisinde tehlike arz eden en az bir deprem bulunmamaktadır");
        }
    }
        @After
        public void tearDown () {
            driver.close();
        }

    }
