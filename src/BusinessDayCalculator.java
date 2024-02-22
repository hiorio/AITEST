import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class BusinessDayCalculator {

    // 휴무일 및 공휴일 설정
    private static final LocalDate[] holidays = {
            LocalDate.of(2024, 1, 1),   // New Year's Day
            LocalDate.of(2024, 2, 5),   // Seollal (Lunar New Year's Day)
            LocalDate.of(2024, 2, 6),   // Seollal Holiday
            LocalDate.of(2024, 2, 7),   // Seollal Holiday
            LocalDate.of(2024, 3, 1),   // Independence Movement Day
            LocalDate.of(2024, 5, 5),   // Children's Day
            LocalDate.of(2024, 6, 6),   // Memorial Day
            LocalDate.of(2024, 8, 15),  // Liberation Day
            LocalDate.of(2024, 9, 12),  // Chuseok (Korean Thanksgiving Day)
            LocalDate.of(2024, 9, 13),  // Chuseok Holiday
            LocalDate.of(2024, 9, 14),  // Chuseok Holiday
            LocalDate.of(2024, 10, 3),  // National Foundation Day
            LocalDate.of(2024, 10, 9),  // Hangeul Day
            LocalDate.of(2024, 12, 25)  // Christmas Day
    };

    // 주말 확인 메서드
    private static boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    // 영업일 수 계산 메서드
    public static long calculateBusinessDays(LocalDate startDate, LocalDate endDate) {
        LocalDate currentDate = startDate;
        long businessDays = 0;

        while (!currentDate.isAfter(endDate)) {
            if (!isWeekend(currentDate) && !isHoliday(currentDate)) {
                businessDays++;
            }
            currentDate = currentDate.plusDays(1);
        }

        return businessDays;
    }

    // 공휴일 확인 메서드
    private static boolean isHoliday(LocalDate date) {
        for (LocalDate holiday : holidays) {
            if (date.equals(holiday)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 첫 번째 날짜 입력 받기
        System.out.print("Enter the first date (yyyy-MM-dd): ");
        String startDateStr = scanner.nextLine();
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);

        // 두 번째 날짜 입력 받기
        System.out.print("Enter the second date (yyyy-MM-dd): ");
        String endDateStr = scanner.nextLine();
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);

        scanner.close();

        long businessDays = calculateBusinessDays(startDate, endDate);
        System.out.println("Start date: " + startDate);
        System.out.println("End date: " + endDate);
        System.out.println("Business days between: " + businessDays);
    }
}