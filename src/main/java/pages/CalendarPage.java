package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.base.BasePage;
import pages.base.LeftMenuPage;
import pages.base.RightMenuPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class CalendarPage extends BasePage {

    List<WebElement> elements;
    String forAssertionString;
    String dateToSetString;

    public CalendarPage(WebDriver driver, LeftMenuPage left, RightMenuPage right){
        super(driver);
        _left = left;
        _right = right;
        PageFactory.initElements(_driver, this);
        (new WebDriverWait(_driver,10))
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(elementIFrame));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // SOORRY ZA TO ALE STRONA JAKBY SIĘ PRZEŁADOWUJE JAK SIĘ WŁĄCZA
        // NIE WAZNE CZY SIE JAKI BUTTON WLACZY CZY NIE ONA SIE PRZELADOWUJE
        //WIEC TRZEBA POCZEKAC AZ SIE TAK STATYCZNIE ZALADUJE,
        //NAWET PROBOWALEM CZEKAC AZ SIE CALE BODY ZALADUJE ALE TO nic nie dalo
        //clickElement(elementDatePicker);
    }

    @FindBy( tagName = "iframe")
    private WebElement elementIFrame;
    @FindBy( id = "datepicker" )
    private WebElement elementDatePicker;
    @FindBy( className = "ui-datepicker-today" )
    private WebElement elementTodayPicker;
    @FindBy ( className = "ui-datepicker-year")
    private WebElement elementYear;
    @FindBy ( className = "ui-datepicker-month")
    private WebElement elementMonth;
    @FindAll({@FindBy( css = "td[data-handler=\"selectDay\"]" )})
    private List<WebElement> elementDates;
    @FindBy( css = "a[title='Prev']")
    private WebElement previousButton;
    @FindBy( css = "a[title='Next']")
    private WebElement nextButton;
    @FindBy( css = "body")
    private WebElement body;


    public CalendarPage calendarTodayStep(){
        clickElement(elementDatePicker);
        clickElement(elementTodayPicker);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        forAssertionString = today.format(formatter);
        return this;
    }

    public CalendarPage verifyTodayPick(){
        Assert.assertEquals(elementDatePicker.getAttribute("value"), forAssertionString);
        return this;
    }

    public CalendarPage calendarParsing(LocalDate dateToSet){

        dateToSetToString(dateToSet);
        clickElement(elementDatePicker);
        waitForElements( Arrays.asList(elementYear, elementMonth));
        parseYear(dateToSet);
        parseMonth(dateToSet);
        //int iMonthActual = parseMonth(elementMonth.getText());
        parseDay(dateToSet);

        return this;
    }

    private void parseDay(LocalDate dateToSet) {
        int totalNodesCount = elementDates.size();
        waitForElements(elementDates);
        for (int i=0; i<totalNodesCount; i++){
            int pickedDay = Integer.parseInt(elementDates.get(i).getText());
            if ( (dateToSet.getDayOfMonth() == pickedDay)  && ( i+ 1 >= pickedDay  ) ){
                //clickElement(elementDates.get(i));
                elementDates.get(i).click();
                break;
            }
        }
    }

    private void parseMonth(LocalDate dateToSet) {
        int iMonthActual = parseMonth(elementMonth.getText());
        while (iMonthActual != dateToSet.getMonthValue()){
            if (dateToSet.getMonthValue()  > iMonthActual){
                monthChangerHigher();
                iMonthActual = parseMonth(elementMonth.getText());
            }
            else {
                monthChangerLower();
                iMonthActual = parseMonth(elementMonth.getText());
            }
        }
    }

    private void parseYear(LocalDate dateToSet) {
        if (dateToSet.getYear() > Integer.parseInt(elementYear.getText())) {
            while(Integer.parseInt(elementYear.getText()) != dateToSet.getYear()){
                yearChangerHigher();
            }

        } else if (dateToSet.getYear() < Integer.parseInt(elementYear.getText())) {
            while(Integer.parseInt(elementYear.getText()) != dateToSet.getYear()){
                yearChangerLower();
            }
        }
    }

    public CalendarPage verifySucces(){
        String pickedDate = elementDatePicker.getAttribute("value");
        Assert.assertEquals(pickedDate, dateToSetString);
        return this;
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

    //month LocalDate String moth to int

    private void monthChangerLower() {
        previousButton.click();
    }

    private void yearChangerLower() {
        previousButton.click();
    }

    private void monthChangerHigher() {
        nextButton.click();
    }

    private void yearChangerHigher() {
        nextButton.click();
    }

    private void dateToSetToString (LocalDate dateToSet){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        dateToSetString = dateToSet.format(formatter);
    }


}
