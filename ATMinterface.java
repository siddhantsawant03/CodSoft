import java.util.Scanner;

public class ATMinterface {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        userAccount user = new userAccount(1000);
        ATM atm = new ATM(user);

        int choice;
        do {
            atm.Menu();
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    float depositAmount = sc.nextFloat();
                    atm.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    float withdrawAmount = sc.nextFloat();
                    atm.withdraw(withdrawAmount);
                    break;
                case 3:
                    atm.Balance();
                    break;
                case 4:
                    System.out.println("Exiting ATM. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }

        } while (choice != 4);

        sc.close();
    }
}

class userAccount{
    private float Balance;

    public userAccount(float initBalance) {
        this.Balance = initBalance;
    }

    public void deposit(float amount) {
        if(amount>0) {
            Balance += amount;
            System.out.println("Deposit done!");
            System.out.println("Updated Balance is: "+ Balance);
        }else {
            System.out.println("Please Enter amount!");
        }
    }

    public float getBalance() {
        return Balance;
    }

    public void withdraw(float amount) {
        if(amount > 0 && amount <=Balance) {
            Balance -= amount;
            System.out.println("Withdrawal done!");
            System.out.println("Updated Balance is: "+ Balance);
        }
    }
}

class ATM{
    private userAccount user;

    public ATM(userAccount acc) {
        this.user = acc;
    }


    public void Menu() {
        System.out.println("Welcome!");
        System.out.println("1. Deposit Money");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Check Balance");
        System.out.println("4. EXIT");
    }


    public void Balance() {
        System.out.println("Current Balance: "+ user.getBalance());
    }

    public void withdraw(float amount) {
        user.withdraw(amount);
    }

    public void deposit(float amount) {
        user.deposit(amount);
    }
}

