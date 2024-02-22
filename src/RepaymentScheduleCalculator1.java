import java.util.Scanner;

public class RepaymentScheduleCalculator1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("대출금액을 입력하세요: ");
        double loanAmount = scanner.nextDouble();

        System.out.print("연이율을 입력하세요(%): ");
        double annualInterestRate = scanner.nextDouble() / 100;

        System.out.print("대출기간(년)을 입력하세요: ");
        int loanTermYears = scanner.nextInt();

        calculateRepaymentSchedule(loanAmount, annualInterestRate, loanTermYears);
    }

    public static void calculateRepaymentSchedule(double loanAmount, double annualInterestRate, int loanTermYears) {
        int numberOfPayments = loanTermYears * 12;
        double monthlyInterestRate = annualInterestRate / 12;
        double monthlyPayment = loanAmount * monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));
        double remainingBalance = loanAmount;

        System.out.println("---------------------------------------------------------------------");
        System.out.println("상환회차별 상환원금\t상환이자\t\t잔액");
        System.out.println("---------------------------------------------------------------------");

        for (int i = 1; i <= numberOfPayments; i++) {
            double interestPayment = remainingBalance * monthlyInterestRate;
            double principalPayment = monthlyPayment - interestPayment;
            remainingBalance -= principalPayment;

            System.out.printf("%d회차\t\t\t\t%,.2f원\t\t%,.2f원\t\t%,.2f원\n", i, principalPayment, interestPayment, remainingBalance);
        }

        double totalPayment = monthlyPayment * numberOfPayments;
        System.out.println("---------------------------------------------------------------------");
        System.out.printf("총 상환금액: %,.2f원\n", totalPayment);
        System.out.println("---------------------------------------------------------------------");
    }
}
