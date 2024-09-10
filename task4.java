import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class CurrencyConverter {

    // Method to select currency
    public static String selectCurrency(String prompt) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(prompt);
            String currencyCode = scanner.nextLine().trim().toUpperCase();
            if (isValidCurrency(currencyCode)) {
                return currencyCode;
            } else {
                System.out.println("Invalid currency code. Please try again.");
            }
        }
    }

    // Method to validate currency code
    public static boolean isValidCurrency(String currencyCode) {
        // Implement your own validation logic here, or use a predefined list of valid codes
        // For simplicity, assuming all codes are valid
        return true;
    }

    // Method to fetch exchange rate
    public static double fetchExchangeRate(String baseCurrency, String targetCurrency) throws IOException {
        String urlStr = "https://api.exchangeratesapi.io/latest?base=" + baseCurrency + "&symbols=" + targetCurrency;
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        Scanner scanner = new Scanner(conn.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNextLine()) {
            response.append(scanner.nextLine());
        }
        scanner.close();

        String jsonResponse = response.toString();
        double exchangeRate = parseExchangeRateFromJson(jsonResponse, targetCurrency);

        return exchangeRate;
    }

    // Method to parse exchange rate from JSON response
    public static double parseExchangeRateFromJson(String jsonResponse, String targetCurrency) {
        // Implement parsing logic here
        // For simplicity, assuming direct parsing
        // Example: parse JSON and extract the rate for the targetCurrency
        // In a real scenario, you would use a JSON parsing library like Jackson or Gson
        return 1.0; // Dummy value, replace with actual parsing logic
    }

    // Method to get amount from user
    public static double getAmount() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter the amount to convert: ");
                double amount = Double.parseDouble(scanner.nextLine().trim());
                return amount;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid amount.");
            }
        }
    }

    // Method to convert currency
    public static double convertCurrency(double amount, double exchangeRate) {
        return amount * exchangeRate;
    }

    // Method to display result
    public static void displayResult(double amount, String baseCurrency, double convertedAmount, String targetCurrency) {
        System.out.printf("%.2f %s = %.2f %s\n", amount, baseCurrency, convertedAmount, targetCurrency);
    }

    public static void main(String[] args) {
        // Select base and target currencies
        String baseCurrency = selectCurrency("Select base currency:");
        String targetCurrency = selectCurrency("Select target currency:");

        try {
            // Fetch exchange rate
            double exchangeRate = fetchExchangeRate(baseCurrency, targetCurrency);

            // Get amount to convert
            double amount = getAmount();

            // Convert currency
            double convertedAmount = convertCurrency(amount, exchangeRate);

            // Display result
            displayResult(amount, baseCurrency, convertedAmount, targetCurrency);

        } catch (IOException e) {
            System.out.println("Failed to fetch exchange rate. Please try again later.");
        }
    }
}
