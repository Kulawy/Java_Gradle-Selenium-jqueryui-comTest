package pages;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.base.BasePageTest;
import pages.base.LeftMenuPage;
import pages.base.RightMenuPage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.testng.Assert.*;

public class CalendarPageTest extends BasePageTest {

    CalendarPage calendarPage;

    @BeforeMethod
    public void setUpCalendar() {
        _left = new LeftMenuPage(chromeDriver);
        _left.goToDatePicker();
        _right = new RightMenuPage(chromeDriver);
        _right.goToOtherMonths();
        calendarPage = new CalendarPage(chromeDriver, _left, _right);

    }

    @Test
    public void testToday(){
        calendarPage.calendarTodayStep().verifyTodayPick();
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
    public void calendarTest(LocalDate dateToSet){
        calendarPage.calendarParsing(dateToSet).verifySucces();
    }


}