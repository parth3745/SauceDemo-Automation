import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public int countOnes(String s) {
        int count = 0;
        char arr[] = s.toCharArray();
        for(int i = 0; i < s.length(); i++) {
            if(arr[i] == '1') {
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) throws IOException {
        int[] arr = new int[]{10, 15, 20};
        System.out.println(arr.length);
        System.exit(0);
    }
}
