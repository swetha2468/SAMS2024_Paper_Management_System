package backend.webinterface;
import backend.enums.ConferenceDeadLineType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/* Add comments */
public class Deadline
{
    String format = "MM-dd-yy";
    DateTimeFormatter deadline;

    public Deadline()
    {
        deadline = DateTimeFormatter.ofPattern(format, Locale.US);
    }

    public void SetDeadLine(ConferenceDeadLineType cdlType, String day, String month, String year, Integer cdlId)
    {
        /* TODO : DATABASE HOOKUP */
        System.out.println("TODO : DATABASE HOOKUP");
    }

    public String GetDeadLine(ConferenceDeadLineType cdlType, Integer cdlId)
    {
        /* TODO : DATABASE HOOKUP */
        return "TODO : DATABASE HOOKUP";
    }
    public boolean IsDeadlinePassed(String inDeadline)
    {
        LocalDate date = LocalDate.parse(inDeadline);
        String formattedDate = date.format(deadline);


        //System.out.println(formattedDate + (date.isAfter(LocalDate.now()) ? " is before " : " is after ") + LocalDate.now().format(deadline));

        return date.isAfter(LocalDate.now());
    }
    public String DeadlineConvertor(String day, String month, String year)
    {
        return year + "-" + month + "-"  + day;
    }

}
