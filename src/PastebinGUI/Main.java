package PastebinGUI;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


public class Main {

    private static final String api_option = "paste";
    private static String api_paste_code = "";
    private static String api_paste_name = "";

    public static void setApi_paste_code(String api_paste_code) {
        Main.api_paste_code = api_paste_code;
    }

    public static void setApi_paste_name(String api_paste_name) {
        Main.api_paste_name = api_paste_name;
    }

    public static void main(String[] args) {
        GUI.Gui();
    }

    public static String postRequest() throws Exception {
        URL url = new URL("https://pastebin.com/api/api_post.php");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("api_dev_key", Config.api_dev_key);
        parameters.put("api_option", api_option);
        parameters.put("api_paste_code", api_paste_code);
        if (!api_paste_name.isEmpty()) {
            parameters.put("api_paste_name", api_paste_name);
        }

        con.setDoOutput(true);
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(getParamsString(parameters));
        out.flush();
        out.close();
        String content = readInputStream(con);
        con.disconnect();
        return content;
    }

    public static String readInputStream(HttpURLConnection con) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String var4;
            try {
                StringBuilder content = new StringBuilder();

                while(true) {
                    String inputLine;
                    if ((inputLine = in.readLine()) == null) {
                        var4 = content.toString();
                        break;
                    }

                    content.append(inputLine);
                }
            } catch (Throwable var6) {
                try {
                    in.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }

                throw var6;
            }

            in.close();
            return var4;
        } catch (Exception var7) {
            var7.printStackTrace();
            return "";
        }
    }

    public static String getParamsString(Map<String, String> params) {
        StringBuilder result = new StringBuilder();
        params.forEach((name, value) -> {
            result.append(URLEncoder.encode(name, StandardCharsets.UTF_8));
            result.append('=');
            result.append(URLEncoder.encode(value, StandardCharsets.UTF_8));
            result.append('&');
        });
        String resultString = result.toString();
        return !resultString.isEmpty() ? resultString.substring(0, resultString.length() - 1) : resultString;
    }
}
