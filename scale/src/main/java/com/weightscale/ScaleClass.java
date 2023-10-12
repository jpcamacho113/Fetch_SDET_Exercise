package com.weightscale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ScaleClass {
    
    private static WebDriver driver = new ChromeDriver();
    private static String[] group1 = {"0", "1", "2"};
    private static String[] group2 = {"3", "4", "5"};
    private static String[] group3 = {"6", "7", "8"};

    static private String scaleLogic() throws InterruptedException {

        initialInput();
        clickWeigh();

        final String comparisonResult = getComparisonResult();
        String finalAnswer = "";

        /*  The button for the symbol and the button for resetting the grid have the same IDs and class names,
        since this is lower in the hierarchy, I got both elements with the IDs and chose the second since it 
        will always be the reset button*/
        driver.findElements(By.id("reset")).get(1).click();
        Thread.sleep(2000);

        if(comparisonResult.contains("<")) {

            finalAnswer = numberComparison(group1);
        } 
        else if (comparisonResult.contains(">")) {

            finalAnswer = numberComparison(group2);
        } 
        else {
            
            finalAnswer = numberComparison(group3);
        }

        return finalAnswer;
    }

    public static void main(String[] args) throws InterruptedException {
        driver.manage().window().fullscreen();
        driver.get("http://sdetchallenge.fetch.com/");
        
        // scalelogic returns the answer and then the correct button is clicked
        driver.findElement(By.id("coin_" + scaleLogic())).click();

        System.out.println(driver.switchTo().alert().getText());

        driver.switchTo().alert().accept();

        // The only ordered list on the page is the one in the game-info class so that is why I used this xpath
        System.out.println(driver.findElement(By.xpath("//ol")).getText());

        // Safely close the webdriver instance
        driver.quit();
    }

    // Helper Functions

    // Gets the '<', '>', or '=' string from the button in the middle of the grids
    static private String getComparisonResult() {
        return driver.findElement(By.id("reset")).getText();
    }
    
    // Inserts numbers into the grid, just need to know the ID of the boxes
    static private void numberInput(String elementID, String value) {
        driver.findElement(By.id(elementID)).sendKeys(value);
    }

    static private void clickWeigh() throws InterruptedException {
        driver.findElement(By.id("weigh")).click();

        // I noticed the game info takes time to load so I added a delay in the thread.
        Thread.sleep(2000);
    }

    static private void initialInput() {

        // Inputs the first group on the left and second on the right
        numberInput("left_0", group1[0]);
        numberInput("left_1", group1[1]);
        numberInput("left_2", group1[2]);
        numberInput("right_0", group2[0]);
        numberInput("right_1", group2[1]);
        numberInput("right_2", group2[2]);
    }

    // This is similar to the logic inside of the scaleLogic function, this is just altered to
    // take any group as a parameter and just compare individual numbers instead of the whole group
    static private String numberComparison(String[] group) throws InterruptedException {
        
        numberInput("left_0", group[0]);
        numberInput("right_0", group[1]);
        clickWeigh();

        Thread.sleep(2000);

        String comparisonResult = getComparisonResult();

        if(comparisonResult.contains("<")) {
            return group[0];
        } else if (comparisonResult.contains(">")) {
            return group[1];
        } else {
            return group[2];
        }
    }
}