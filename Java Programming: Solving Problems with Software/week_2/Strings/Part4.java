import edu.duke.*;
import java.io.File;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public String findURL(String target, String url) {
        String target_web = target.toLowerCase();
        //http://www.informationweek.com/hardware/desktop/turings-universal-machine-voted-top-brit/240151643
        //http://www.youtube.com/watch?v=242Z5BhJAC8
        int temp = url.indexOf(target_web);
        if (temp == -1) {
            return "";
        }
       
        //find first(") from start of url to temp
        int tempStart = url.substring(0, temp).lastIndexOf("\"");
        
        //find second(") from temp to end of url
        int tempEnd = url.lastIndexOf("\"");
        
        String result = url.substring(tempStart, tempEnd+1);
        
        return result;
    }
    
    public void testFindURL () {
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
        for (String s : ur.lines()) {
            s = s.toLowerCase();
            String result = findURL("youtube.com", s);
            if (result != "") {
                System.out.println(result);
            }
        }
    }
}
