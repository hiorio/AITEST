import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

public class AutoTransfer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 계좌 정보 입력
        System.out.print("계좌번호를 입력하세요: ");
        String accountNumber = scanner.nextLine();

        // 금액 입력
        System.out.print("송금 금액을 입력하세요: ");
        int amount = scanner.nextInt();

        // 자동이체 시작 날짜 입력 (일자)
        System.out.print("자동이체 시작 일자를 입력하세요 (dd): ");
        int startDay = scanner.nextInt();

        // 자동이체 실행
        while (true) {
            // 매월 입력받은 일자에 자동이체 실행
            LocalDate transferDate = LocalDate.now().plusMonths(1).withDayOfMonth(startDay);

            // 이전 영업일 계산
            while (isWeekend(transferDate) || isHoliday(transferDate)) {
                transferDate = transferDate.minusDays(1);
            }

            // 송금 처리
            System.out.println("[INFO] " + amount + "원이 " + accountNumber + " 계좌로 송금되었습니다.");
            System.out.println("[INFO] 송금 날짜: " + transferDate);

            // 다음 달 동일 일자까지 기다림
            try {
                long businessDays = countBusinessDays(LocalDate.now(), transferDate);
                long millis = businessDays * 24 * 60 * 60 * 1000; // Convert business days to milliseconds

                Thread.sleep(millis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Break out of the infinite loop after the transfer is completed
            break;
        }
    }

    // 주말 확인 메서드
    private static boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    // 공휴일 확인 메서드
    private static boolean isHoliday(LocalDate date) {
        // 공휴일 목록 (수정 필요)
        LocalDate[] holidays = {
                LocalDate.of(2024, 1, 1),   // New Year's Day
                LocalDate.of(2024, 2, 5),   // Seollal (Lunar New Year's Day)
                LocalDate.of(2024, 2, 6),   // Seollal Holiday
                LocalDate.of(2024, 2, 7),   // Seollal Holidayㅇ
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

        for (LocalDate holiday : holidays) {
            if (date.equals(holiday)) {
                return true;
            }
        }
        return false;
    }

    // 비즈니스 데이 수 계산 메서드
    private static long countBusinessDays(LocalDate startDate, LocalDate endDate) {
        long businessDays = 0;
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            if (!isWeekend(currentDate) && !isHoliday(currentDate)) {
                businessDays++;
            }
            currentDate = currentDate.plusDays(1);
        }

        return businessDays;
    }
}