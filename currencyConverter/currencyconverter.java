import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class currencyconverter {
  
    private static final Map<String, Double> exchangeRates = new HashMap<>();
    static {
        exchangeRates.put("USD", 1.0);  
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("INR", 74.0);
        exchangeRates.put("JPY", 110.0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Available currencies: " + exchangeRates.keySet());

        String baseCurrency = getCurrencyInput(scanner, "Enter base currency (e.g., USD): ");

        String targetCurrency = getCurrencyInput(scanner, "Enter target currency (e.g., EUR): ");

        System.out.print("Enter amount to convert: ");
        double amount = scanner.nextDouble();

        double convertedAmount = convertCurrency(amount, baseCurrency, targetCurrency);

        if (convertedAmount != -1) {
            System.out.printf("%.2f %s = %.2f %s%n", amount, baseCurrency, convertedAmount, targetCurrency);
        } else {
            System.out.println("Currency conversion failed. Please check the currency codes.");
        }

        scanner.close();
    }

    private static String getCurrencyInput(Scanner scanner, String prompt) {
        String currency;
        while (true) {
            System.out.print(prompt);
            currency = scanner.nextLine().toUpperCase();
            if (exchangeRates.containsKey(currency)) {
                return currency;
            } else {
                System.out.println("Invalid currency code. Please try again.");
            }
        }
    }

    private static double convertCurrency(double amount, String baseCurrency, String targetCurrency) {
        if (exchangeRates.containsKey(baseCurrency) && exchangeRates.containsKey(targetCurrency)) {
            double baseRate = exchangeRates.get(baseCurrency);
            double targetRate = exchangeRates.get(targetCurrency);
            return amount * (targetRate / baseRate);
        }
        return -1; 
    }
}