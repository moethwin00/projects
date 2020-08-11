package scm.bulletinboard.system.common;

public class StringUtil {
    public static boolean isEmpty(String str) {
        if(null == str || str.isEmpty() || str == "") {
            return true;
        }
        else {
            return false;
        }
    }
    
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
