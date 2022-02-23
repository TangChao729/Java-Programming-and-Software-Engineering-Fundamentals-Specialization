
/**
 * Write a description of test1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class test1 {
    public String reverse(String s){
        String ret = "";
        for(int k=0; k < s.length(); k +=2){
          ret = s.charAt(k) + ret;
        }
        return ret;
    }
    
    public void testString() {
        String ret = reverse("pit");
        System.out.println(ret);
        String ret2 = reverse("computer");
        System.out.println(ret2);
    }
}
