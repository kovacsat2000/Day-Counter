import java.util.ArrayList;

public final class Dates {

    public int beginDateYear;
    public int beginDateMonth;
    public int beginDateDay;
    public int endDateYear;
    public int endDateMonth;
    public int endDateDay;
    public int daysBetweenDates;
    public int[] monthDays = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    ArrayList<Integer> involvedYears = new ArrayList<Integer>();

    public void getBeginDate(String beginDate){
        int i = 0;
        int j = 0;
        String dateData;
        while(true){
            if (beginDate.charAt(i) == '.'){
                dateData = beginDate.substring(j, i);
                j = i;
                break;
            }
            i++;
        }
        beginDateDay = Integer.parseInt(dateData);
        i++;
        j++;
        while(true){
            if (beginDate.charAt(i) == '.'){
                dateData = beginDate.substring(j, i);
                break;
            }
            i++;
        }
        beginDateMonth = Integer.parseInt(dateData);
        i++;
        beginDateYear = Integer.parseInt(beginDate.substring(i));
    }

    public void getEndDate(String endDate){
        int i = 0;
        int j = 0;
        String dateData;
        while(true){
            if (endDate.charAt(i) == '.'){
                dateData = endDate.substring(j, i);
                j = i;
                break;
            }
            i++;
        }
        endDateDay = Integer.parseInt(dateData);
        i++;
        j++;
        while(true){
            if (endDate.charAt(i) == '.'){
                dateData =endDate.substring(j, i);
                break;
            }
            i++;
        }
        endDateMonth = Integer.parseInt(dateData);
        i++;
        endDateYear = Integer.parseInt(endDate.substring(i));
    }

    public boolean isTheYearSame(int beginYear, int endYear){
        return beginYear == endYear;
    }

    public boolean isTheMonthSame(int beginMonth, int endMonth){
        return beginMonth == endMonth;
    }

    public boolean isTheDaySame(int beginDay, int endDay){
        return beginDay == endDay;
    }

    private int sameYearsCount() {
        int betweenMonthsDays = 0;
        for (int i = beginDateMonth + 1; i < endDateMonth; i++){
            betweenMonthsDays = betweenMonthsDays + monthDays[i];
        }

        daysBetweenDates =  monthDays[beginDateMonth] + betweenMonthsDays + endDateDay - beginDateDay + 1;

        return daysBetweenDates + betweenLeapYearCounter() + beginLeapYearChecker();
    }

    private int daysTillNewYear(){
        int betweenMonthsDays = 0;
        for (int i = beginDateMonth + 1; i < 12; i++){
            betweenMonthsDays = betweenMonthsDays + monthDays[i];
        }

        daysBetweenDates =  monthDays[beginDateMonth] + betweenMonthsDays + 31 - beginDateDay + 1;

        return daysBetweenDates;
    }

    public int daysFromNewYear(){
        int betweenMonthsDays = 0;
        for (int i = 2; i < endDateMonth; i++){
            betweenMonthsDays = betweenMonthsDays + monthDays[i];
        }

        daysBetweenDates =  monthDays[1] + betweenMonthsDays + endDateDay - 1 + 1;

        return daysBetweenDates;
    }

    private int differentYearsCount() {

        return daysTillNewYear() + daysFromNewYear() + ((endDateYear - beginDateYear - 1) * 365)
                + betweenLeapYearCounter() + beginLeapYearChecker() + endLeapYearChecker();
    }

    private int betweenLeapYearCounter(){
        int leapYears = 0;

        for (int i = beginDateYear; i < endDateYear + 1; i++){
            involvedYears.add(i);
        }

        for (int i = 1; i < involvedYears.size() - 1; i++){
            int year = involvedYears.get(i);
            if(year % 4 == 0)
            {
                if( year % 100 == 0)
                {
                    if ( year % 400 == 0)
                        leapYears++;
                }
                else
                    leapYears++;
            }
        }

        return leapYears;
    }

    public int beginLeapYearChecker(){
        if (isTheYearSame(beginDateYear, endDateYear) && endDateMonth == 2 && endDateDay == 29){
            return 0;
        }

        if (leapYearChecker(beginDateYear)){
            if (beginDateMonth < 3){
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    public int endLeapYearChecker(){
        if (leapYearChecker(endDateYear)){
            if (endDateMonth > 2){
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    private boolean leapYearChecker(int year){
        boolean isLeap = false;

        if(year % 4 == 0)
        {
            if( year % 100 == 0)
            {
                if ( year % 400 == 0)
                    isLeap = true;
                else
                    isLeap = false;
            }
            else
                isLeap = true;
        }
        else {
            isLeap = false;
        }

        return isLeap;
    }

    public int countDays(){
        if (isTheYearSame(beginDateYear, endDateYear)){
            if (isTheMonthSame(beginDateMonth, endDateMonth)){
                if (isTheDaySame(beginDateDay, endDateDay)){
                    return 0;
                } else {
                    return endDateDay - beginDateDay;
                }
            } else {
                return sameYearsCount();
            }
        } else {
            return differentYearsCount();
        }

    }

}