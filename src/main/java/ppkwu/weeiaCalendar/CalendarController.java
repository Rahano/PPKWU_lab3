package ppkwu.weeiaCalendar;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarController {

    @RequestMapping(path = "/getEvents")
    public String reverseString(@RequestParam(value = "year") int year,
            @RequestParam(value = "month") int month){

        String url = "http://www.weeia.p.lodz.pl/pliki_strony_kontroler/kalendarz.php?rok=" + year + "&miesiac=" + month;

        return null;

    }

}
