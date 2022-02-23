
/**
 * Write a description of debug1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class debug1 {
    public void findAbc(String input){
           int index = input.indexOf("abc");
           while (true){
               if (index == -1 || index >= input.length() - 3){
                   break;
               }
               String found = input.substring(index+1, index+4);
               System.out.println(found);
               System.out.println(index);
               index = input.indexOf("abc",index+4);
               System.out.println(index);
           }
       }
       public void test() {
        //no code yet
        findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
        //findAbc("abcdabc");
    }
}
