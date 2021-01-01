package adrixus.com.utility;

public class AdrixusUtility {

    /**
     * Utility to return number of digits in number
     * @param number
     * @return
     */
    public static int getSize(Long number){
        String num = number + "";
        return num.length();
    }
}
