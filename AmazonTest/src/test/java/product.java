/**
 * @author - Antoney Mathai
 * @date - 10/27/2022
 */

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.*;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


public class product {
    @Test
    public void testCheckProduct() {
        try {
            String url="https://www.amazon.com/";
            open(url); //open URL
            log("Displaying: " + title()); //log

            $(By.id("nav-hamburger-menu")).click(); //click on hamburger menu
            log("Clicked on Hamburger Menu"); //click on hamburger menu

            //======== xPath of items ============
            String xPathSeeAll = "//*[@id='hmenu-content']/ul[1]//li[contains(.,'see all')][1]";  //see all menu
            String xPathComputer = "//*[@id='hmenu-content']//div[contains(.,'Computers')]"; //computer menu
            String xPathMonitor = "//*[@id='hmenu-content']//a[contains(.,'Monitors')]"; //monitor menu
            String xPathBrand = "//div[contains(@class,'a-container')]//a[text()='LG']";  //LG
            String xPathSort = "//span[contains(text(),'Sort by')]"; //sort by

            String xPathItem2 = "//div[@data-component-type='s-search-result'][2]"; //second item


            click(xPathSeeAll); //click on See All
            click(xPathComputer); //click on computers
            click(xPathMonitor); //click on Monitors
            click(xPathBrand); //click on LG

            click(xPathSort); //click on Sort By
            $("#s-result-sort-select_2").click(); //Click on High to low.  can be used with click method and xpath

            click(xPathItem2); //click on 2nd Item
            log("Selected High to Low"); //log

            url = WebDriverRunner.url(); //get current URL
            switchTo().newWindow(WindowType.TAB); //open new tab
            log("created new tab");  //log

            open(url);  //open URL in new tab
            log("Open URL in new tab");

            String xPathAbtItem = "//h1[contains(.,'About this item')]"; //about this item xpath. some item does not have about this item.
            boolean blnAbtItem = $(By.xpath(xPathAbtItem)).isDisplayed();

            //$(By.xpath(xPathAbtItem)).shouldHave(text("About this item"));
            String strAbtItem ="";

            if (blnAbtItem) { //check to see about this item displayed
                log("Displaying [About this item] section."); //log
                strAbtItem = $("#feature-bullets").getText(); //get about this item section
            } else {
                log("Failed: Unable to find [About this item] section."); //log
            }


            log("Monitor Name: " + $("#productTitle").getText()); //log monitor name
            log(strAbtItem); //log
            String strPrice = $(By.xpath("//*[@id='corePrice_feature_div']//span[@class='a-offscreen']")).getAttribute("textContent");
            log("Price: "+strPrice); //log Price
        }catch(Exception e){
            log(e.getMessage());
        }

    }

    public static void click(String xPath) {
        try{
            $(By.xpath(xPath)).should(exist); //check to see exist
            $(By.xpath(xPath)).shouldBe(visible); //check to see visible
            log("Clicked on: "+$(By.xpath(xPath)).getText()); //log
            $(By.xpath(xPath)).click();  //click on item
        }catch(Exception e){
            log(e.getMessage()); //log
        }
    }

    public static void log(String strLog){
        try{
            System.out.println(strLog); //log monitor name
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
