package ppkwu.weeiaCalendar;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarController {

    @RequestMapping(path = "/getEvents")
    public String reverseString(@RequestParam(value = "year") int year,
            @RequestParam(value = "month") int month){

        return null;

    }

}
