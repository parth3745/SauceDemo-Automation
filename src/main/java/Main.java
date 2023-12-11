import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<String> s1 = new HashSet<String>();
        s1.add("one");
        s1.add("two");
        s1.add("one");
        Set<String> s2 = new HashSet<String>();
        s2.add("one");
        s2.add("two");
        s2.add("one");
        System.out.println(s1);
        System.exit(0);
    }
}
