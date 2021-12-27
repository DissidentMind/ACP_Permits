package utils.custom;
import java.io.File;
import java.net.*;

public class Verifier_Utility {
    public static URL verifyUrl(String url) {
        // Only allow HTTP URLs.
        if (!url.toLowerCase().startsWith("http://") && !url.toLowerCase().startsWith("https://"))
            return null;
        // Verify format of URL.
        URL verifiedUrl = null;
        try {
            verifiedUrl = new URL(url);
        } catch (Exception e) {
            return null;
        }
        // Make sure URL specifies a file.
        if (verifiedUrl.getFile().length() < 2)
            return null;
        return verifiedUrl;
    }

    public static boolean intranetVerifyUrl(String url) {
        System.out.println("Value: "+url);
        System.out.println(""+url.toLowerCase());
        System.out.println("UrlDOne: "+url.toLowerCase().startsWith("\\"));
        if (!url.toLowerCase().startsWith("\\"))
            return false;
        if (new File(url).length() < 2)
            return false;
        return true;
    }
}
