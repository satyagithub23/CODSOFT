package com.codesoft;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;

public class CurrencyConverter {
    static Scanner sc = new Scanner(System.in);
    public static Map<String, BigDecimal> exchangeRates;
    public static String supportedCodesUrl = "https://v6.exchangerate-api.com/v6/944de4fc09ad81834c1d0202/codes";
    public static String exchangeRatesUrl = "https://v6.exchangerate-api.com/v6/944de4fc09ad81834c1d0202/latest/";
    public static String pairConversionUrl = "https://v6.exchangerate-api.com/v6/944de4fc09ad81834c1d0202/pair/";
    public static String pairConversionWithAmountUrl = "https://v6.exchangerate-api.com/v6/944de4fc09ad81834c1d0202/pair/"; //AMOUNT variable (decimal format xxxx.xxxx)


    public static void main(String[] args) throws IOException, InterruptedException {;
        int choice;
        do {
            System.out.println("What You Want To Do ? Choose an option from below.");
            System.out.println("1. Get Available Currencies");
            System.out.println("2. Get Current Exchange Rates");
            System.out.println("3. Convert Currency");
            System.out.println("4. Convert Currency With Amount");
            System.out.println("5. Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> getAvailableCurrencies();
                case 2 -> getExchangeRates();
                case 3 -> pairConversion();
                case 4 -> pairConversionWithAmount();
                case 5 -> System.exit(1);
                default -> System.out.println("Choose a valid option");
            }
        }while (choice!=5);
    }


    public static HttpResponse<String> fetchData(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpClient client = HttpClient.newBuilder().build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
    public static void getAvailableCurrencies() throws IOException, InterruptedException {
        HttpResponse<String> response = fetchData(supportedCodesUrl);
        int responseCode = response.statusCode();
        JSONObject obj = new JSONObject(response.body());
        if (responseCode == 200){
            JSONArray supportedCurrencyArray = obj.getJSONArray("supported_codes");
            for (Object currencyData:supportedCurrencyArray) {
                JSONArray currencyDetails = (JSONArray) currencyData;
                System.out.println(currencyDetails.getString(0)+" ("+currencyDetails.getString(1)+")");
            }
        } else System.out.println("Something went wrong");
    }

    public static void getExchangeRates() throws IOException, InterruptedException {
        sc.nextLine();
        System.out.println("Enter Currency Code");
        String currencyCode = sc.nextLine();
        HttpResponse<String> response = fetchData(exchangeRatesUrl+currencyCode);
        int statusCode = response.statusCode();

        if (statusCode == 200){
            JSONObject object = new JSONObject(response.body());
            String baseCode = object.getString("base_code");
            JSONObject conversionRates = object.getJSONObject("conversion_rates");
            System.out.println("Base Currency: "+baseCode);
            System.out.println("Exchange Rates: ");
            for (String currencyCodes:conversionRates.keySet()){
                double exchangeRate = conversionRates.getDouble(currencyCodes);
                System.out.println(currencyCodes+" :"+exchangeRate);
            }
        } else System.out.println("Something went wrong");
    }

    public static void pairConversion() throws IOException, InterruptedException {
        sc.nextLine();
        System.out.println("Enter base currency(in UPPER CASE only)");
        String baseCurrency = sc.nextLine();
        System.out.println("Enter target currency(in UPPER CASE only)");
        String targetCurrency = sc.nextLine();
        HttpResponse<String> response = fetchData(pairConversionUrl+"/"+baseCurrency+"/"+targetCurrency);
        int statusCode = response.statusCode();
        if (statusCode == 200) {
            JSONObject conversionObject = new JSONObject(response.body());
            String fetchedBaseCurrency = conversionObject.getString("base_code");
            String fetchedTargetCurrency = conversionObject.getString("target_code");
            double conversionRate = conversionObject.getDouble("conversion_rate");
            System.out.println("Base Currency: " + fetchedBaseCurrency);
            System.out.println("Target Currency: " + fetchedTargetCurrency);
            System.out.println("Conversion Rate: " + conversionRate);
        } else System.out.println("Something went wrong");
    }

    public static void pairConversionWithAmount() throws IOException, InterruptedException {
        sc.nextLine();
        System.out.println("Enter base currency(in UPPER CASE only)");
        String baseCurrency = sc.nextLine();
        System.out.println("Enter target currency(in UPPER CASE only)");
        String targetCurrency = sc.nextLine();
        System.out.println("Enter Amount");
        float amount = sc.nextFloat();

        HttpResponse<String> response = fetchData(pairConversionWithAmountUrl+"/"+baseCurrency+"/"+targetCurrency+"/"+amount);
        int statusCode = response.statusCode();
        if (statusCode == 200){
            JSONObject pairConversionObject = new JSONObject(response.body());
            String fetchedBaseCode = pairConversionObject.getString("base_code");
            String fetchedTargetCode = pairConversionObject.getString("target_code");
            double conversionRate = pairConversionObject.getDouble("conversion_rate");
            double conversionResult = pairConversionObject.getDouble("conversion_result");

            System.out.println("Base Currency: "+fetchedBaseCode);
            System.out.println("Target Currency: "+fetchedTargetCode);
            System.out.println("Exchange Rate: "+conversionRate);
            System.out.println("Converted Amount: "+conversionResult);
        } else System.out.println("Something went wrong");

    }
}
