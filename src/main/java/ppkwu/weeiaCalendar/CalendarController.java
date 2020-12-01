package ppkwu.weeiaCalendar;

import ppkwu.weeiaCalendar.CalendarCreator;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class CalendarController {

    @RequestMapping(path = "/getEvents")
    public String GetWEEIACalendar(@RequestParam(value = "year") int year,
            @RequestParam(value = "month") int month, HttpServletResponse response) throws IOException, URISyntaxException {

        String url = "http://www.weeia.p.lodz.pl/pliki_strony_kontroler/kalendarz.php?rok=" + year + "&miesiac=" + String.format("%02d", month);

        Document document = Jsoup.connect(url).get();

        CalendarCreator calendarCreator = new CalendarCreator();
        calendarCreator.createCalendarICS(document, month, response);

        return null;
    }

}
