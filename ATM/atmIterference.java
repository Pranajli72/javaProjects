import java.util.Scanner;
public class atmIterference {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double balance = 0.0;


        System.out.print("Enter initial balance for your account: $");
        balance = scanner.nextDouble();

        int choice;
        do {
            System.out.println("\n=====================");
            System.out.println("      ATM Menu       ");
            System.out.println("=====================");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Select an option (1-4): ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:

                    System.out.printf("Your current balance is: $%.2f\n", balance);
                    break;
                case 2:

                    System.out.print("Enter amount to deposit: $");
                    double depositAmount = scanner.nextDouble();
                    if (depositAmount > 0) {
                        balance += depositAmount;
                        System.out.printf("Successfully deposited: $%.2f\n", depositAmount);
                    } else {
                        System.out.println("Error: Deposit amount must be positive.");
                    }
                    break;
                case 3:

                    System.out.print("Enter amount to withdraw: $");
                    double withdrawAmount = scanner.nextDouble();
                    if (withdrawAmount > 0 && withdrawAmount <= balance) {
                        balance -= withdrawAmount;
                        System.out.printf("Successfully withdrew: $%.2f\n", withdrawAmount);
                    } else if (withdrawAmount > balance) {
                        System.out.println("Error: Insufficient balance for this withdrawal.");
                    } else {
                        System.out.println("Error: Withdrawal amount must be positive.");
                    }
                    break;
                case 4:

                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Error: Invalid option. Please select a valid option (1-4).");
            }
        } while (choice != 4);

        scanner.close();
    }
}
