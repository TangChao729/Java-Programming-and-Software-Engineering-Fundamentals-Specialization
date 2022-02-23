
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb) {
        int temp = 0;
        int count = 0;
        int salength = stringa.length();
        int sblength = stringb.length();
        
        
        temp = stringb.indexOf(stringa);
        if (temp == -1) {
            return false;
        }
        count = count + 1;
        System.out.println(stringb.substring(temp,sblength));
        temp = stringb.substring(temp+salength,sblength).indexOf(stringa);
        if (temp == -1) {
            return false;
        }
        count = count + 1;
        return true;
    }
    
    public String lastPart(String stringa, String stringb) {
        int temp = 0;
        int count = 0;
        int salength = stringa.length();
        int sblength = stringb.length();
        String lastpart = "";
        
        //find first occurrance
        temp = stringb.indexOf(stringa);
        if (temp == -1) {
            return stringb;
        }
        //if found, find position of the rest part
        lastpart = stringb.substring(temp+salength,sblength);
        
        //System.out.println(stringb.substring(temp,sblength));
       
        return lastpart;        
        
    }
    
    public void testOccurrences() {
        //“by”, “A story by Abby Long”
        String stringa = "by";
        String stringb = "A story by Abby Long";
        boolean result = twoOccurrences(stringa, stringb);
        System.out.println("Occurrened more than once? " + result);
        
        stringa = "";
        stringb = "banana";
        result = twoOccurrences(stringa, stringb);
        System.out.println("Occurrened more than once? " + result);
        
        stringa = "atg";
        stringb = "ctgtatgta";
        result = twoOccurrences(stringa, stringb);
        System.out.println("Occurrened more than once? " + result);
    }
    
    public void testLastPart() {
        //The part of the string after an in banana is ana.
        String stringa = "by";
        String stringb = "A story by Abby Long";
        String result = lastPart(stringa, stringb);
        System.out.println("The part of the string after " + stringa + " in " + stringb + " is " + result);
        
        stringa = "an";
        stringb = "banana";
        result = lastPart(stringa, stringb);
        System.out.println("The part of the string after " + stringa + " in " + stringb + " is " + result);
        
        stringa = "atg";
        stringb = "ctgtatgta";
        result = lastPart(stringa, stringb);
        System.out.println("The part of the string after " + stringa + " in " + stringb + " is " + result);

        stringa = "zoo";
        stringb = "forest";
        result = lastPart(stringa, stringb);
        System.out.println("The part of the string after " + stringa + " in " + stringb + " is " + result);
    
    }    
}
