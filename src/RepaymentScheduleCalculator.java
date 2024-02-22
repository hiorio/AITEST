import java.util.Date;
import java.util.Scanner;

public class RepaymentScheduleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 사용자로부터 대출금액, 연이율, 대출기간 입력 받기
        System.out.print("대출금액을 입력하세요: ");
        double loanAmount = getValidDoubleInput(scanner);

        System.out.print("연이율을 입력하세요 (소수점으로): ");
        double annualInterestRate = getValidDoubleInput(scanner);

        System.out.print("대출기간(년)을 입력하세요: ");
        int loanTermInYears = getValidIntInput(scanner);

        calculateAmortizationSchedule(loanAmount, annualInterestRate, loanTermInYears);

        scanner.close();
    }

    public static double getValidDoubleInput(Scanner scanner) {
        double input;
        while (true) {
            if (scanner.hasNextDouble()) {
                input = scanner.nextDouble();
                break;
            } else {
                System.out.println("유효한 숫자를 입력하세요.");
                scanner.next(); // 다음 입력으로 이동
            }
        }
        return input;
    }

    public static int getValidIntInput(Scanner scanner) {
        int input;
        while (true) {
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                break;
            } else {
                System.out.println("유효한 정수를 입력하세요.");
                scanner.next(); // 다음 입력으로 이동
            }
        }
        return input;
    }

    public static void calculateAmortizationSchedule(double loanAmount, double annualInterestRate, int loanTermInYears) {
        double monthlyInterestRate = annualInterestRate / 12;
        int numberOfPayments = loanTermInYears * 12;

        double monthlyPayment = (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));

        double remainingBalance = loanAmount;
        double totalRepayment = 0; // 총 상환금액 초기화
        for (int i = 1; i <= numberOfPayments; i++) {
            double interest = remainingBalance * monthlyInterestRate;
            double principal = monthlyPayment - interest;
            double repayment = principal + interest; // 상환액은 원금과 이자의 합계
            remainingBalance -= principal;
            totalRepayment += repayment; // 총 상환금액 누적
            Date paymentDate = calculatePaymentDate(i);
            System.out.printf("Payment #%d: Date: %s | Principal: %.2f | Interest: %.2f | Balance: %.2f%n", 
            i, paymentDate, principal, interest, remainingBalance);
        }
        System.out.printf("Total repayment: %.2f%n", totalRepayment); // 총 상환금액 출력
    }

    public static Date calculatePaymentDate(int paymentNumber) {
        // Payment date calculation logic can be implemented here
        // For simplicity, returning current date
        return new Date();
    }
}

