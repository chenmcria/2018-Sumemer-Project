package ui;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class ExchangeRate {
    private String baseCurrency;

    public ExchangeRate(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    // calls REST api with this.basecurrency to get exchange rates
    private HttpURLConnection makeApiCall() {
        try {
            URL url = new URL("https://api.exchangeratesapi.io/latest?base=" + this.baseCurrency);//your url i.e fetch data from .
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            return conn;
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
        }
        return null;
    }

    private Map<String, Double> parseResult(String s) {
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonParser().parse(s).getAsJsonObject();
        JsonObject rates = jsonObject.getAsJsonObject("rates");
        Type type = new TypeToken<HashMap<String, Double>>() {}.getType();
        Map<String, Double> map = gson.fromJson(rates, type);
        System.out.println(map);
        return map;
    }

    // calls makeApiCall() and parses results
    public Map<String, Double> getListOfExchangeRates() {
        HttpURLConnection conn = this.makeApiCall();
        try {
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String lineRead = "";
            String output = "";
            while ((lineRead = br.readLine()) != null) {
                output += lineRead;
            }
            conn.disconnect();
            return this.parseResult(output);
        } catch (IOException e) {
            System.out.println("Exception while parsing " + e.getMessage());
        }
        return null;
    }
}
