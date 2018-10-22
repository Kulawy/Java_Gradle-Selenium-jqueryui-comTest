import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CalendarSeleniumTests extends BaseSeleniumTests {

    String datePickerId;
    String calendarElemId;
    WebElement datePickerElem;

    @BeforeMethod
    public void setUpParametrized() {
        chromeDriver.get("https://jqueryui.com/datepicker/#other-months");
        chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        chromeDriver.switchTo().frame(chromeDriver.findElement(By.tagName("iframe")));
        datePickerId = "datepicker";
        calendarElemId = "//div[@id='slider']";
        datePickerElem = chromeDriver.findElement(By.id(datePickerId));
    }

    @Test
    public void calendarTodayTest() {
        datePickerElem.click();
        //String month = chromeDriver.findElement(By.className("ui-datepicker-month")).getText();
        //String year = chromeDriver.findElement(By.className("ui-datepicker-year")).getText();
        //String day = chromeDriver.findElement(By.className("ui-datepicker-today")).getText();
        //System.out.println(day + " " + month + " " + year);
        chromeDriver.findElement(By.className("ui-datepicker-today")).click();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String todayString = today.format(formatter);
        //System.out.println(todayString);
        Assert.assertEquals(datePickerElem.getAttribute("value"), todayString);

    }

    @DataProvider(name = "dateValues")
    public Object[][] createDataForSlider() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        //LocalDate parsedDate = LocalDate.parse(text, formatter);
        return new Object[][]{
                {LocalDate.parse("01.02.2019", formatter)},
                {LocalDate.parse("27.12.2018", formatter)},
                {LocalDate.parse("06.07.2018", formatter)},
                {LocalDate.parse("20.11.2017", formatter)}
        };
    }

    @Test(dataProvider = "dateValues")
    public void calendarTest(LocalDate dateToSet) {

        String monthToSet = dateToSet.getMonth().toString();
        String yearToSet = "" + dateToSet.getYear();
        String dayToSet = "" + dateToSet.getDayOfMonth();
        datePickerElem.click();

        if (dateToSet.getYear() > Integer.parseInt(chromeDriver.findElement(By.className("ui-datepicker-year")).getText())) {
            while(Integer.parseInt(chromeDriver.findElement(By.className("ui-datepicker-year")).getText()) != dateToSet.getYear()){
                yearChangerHigher();
                //System.out.println(""+Integer.parseInt(chromeDriver.findElement(By.className("ui-datepicker-year")).getText()));
            }

        } else if (dateToSet.getYear() < Integer.parseInt(chromeDriver.findElement(By.className("ui-datepicker-year")).getText())) {
            while(Integer.parseInt(chromeDriver.findElement(By.className("ui-datepicker-year")).getText()) != dateToSet.getYear()){
                yearChangerLower();
            }

        }

        int iMonthActual = parseMonth(chromeDriver.findElement(By.className("ui-datepicker-month")).getText());
        System.out.println(""+iMonthActual);
        if (dateToSet.getMonthValue() > iMonthActual) {
            while(parseMonth(chromeDriver.findElement(By.className("ui-datepicker-month")).getText()) != dateToSet.getMonthValue()){
                monthChangerHigher();
            }

        } else if (dateToSet.getMonthValue() < iMonthActual) {
            while(parseMonth(chromeDriver.findElement(By.className("ui-datepicker-month")).getText()) != dateToSet.getMonthValue()){
                monthChangerLower();
            }
        }

        List<WebElement> dates = chromeDriver.findElements(By.cssSelector("td[data-handler=\"selectDay\"]"));
        int totalNodesCount = dates.size();
        //System.out.println(""+totalNodes);

        for (int i=0; i<totalNodesCount; i++){
            int pickedDay = Integer.parseInt(dates.get(i).getText());
            if ( dateToSet.getDayOfMonth() == pickedDay){
                dates.get(i).click();
                break;
            }
        }

        String pickedDate = datePickerElem.getAttribute("value");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String dateToSetString = dateToSet.format(formatter);
        //System.out.println(pickedDate);
        //System.out.println(dateToSetString);

        Assert.assertEquals(dateToSetString, pickedDate);

    }

    private int parseMonth(String nameOfMonth) {
        switch (nameOfMonth.toLowerCase()) {
            case "january":
                return 1;
            case "february":
                return 2;
            case "march":
                return 3;
            case "april":
                return 4;
            case "may":
                return 5;
            case "june":
                return 6;
            case "july":
                return 7;
            case "august":
                return 8;
            case "september":
                return 9;
            case "october":
                return 10;
            case "november":
                return 11;
            case "december":
                return 12;
            default:
                return 0;
        }
    }

    private void monthChangerLower() {
        WebElement previousButton = chromeDriver.findElement(By.cssSelector("a[title='Prev']"));
        previousButton.click();
    }

    private void yearChangerLower() {
        WebElement previousButton = chromeDriver.findElement(By.cssSelector("a[title='Prev']"));
        previousButton.click();
    }

    private void monthChangerHigher() {
        WebElement nextButton = chromeDriver.findElement(By.cssSelector("a[title='Next']"));
        nextButton.click();
    }

    private void yearChangerHigher() {
        WebElement nextButton = chromeDriver.findElement(By.cssSelector("a[title='Next']"));
        nextButton.click();
    }

    //1.02.2019
    //27.12.2018
    // today
    //06.07.2018
    // 20.11.2017

}
