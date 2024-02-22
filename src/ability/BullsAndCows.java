package ability;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class BullsAndCows {
	// 1. 랜덤으로 4개 숫자 중복없이 생성
	// 2. 10턴 안에 정답 맞추기
	// 3. Bulls => 값과 위치가 모두 맞다
	// 4. cows => 위치는 틀렸지만 값이 맞다.
	public static void main(String[] args) {
		game();

	}

	static void game() {
		Scanner sc = new Scanner(System.in);
		boolean option = true;
		while (option) {
			int[] answer = random();
			//System.out.println(Arrays.toString(answer));
			String ans = Arrays.toString(answer);
			int length = answer.length;

			int[] input = new int[length];
			
			int cnt = 1;

			while (cnt <= 10) {
				int Bulls = 0;
				int Cows = 0;
				System.out.print("숫자를 입력하세요 : ");
				for (int i = 0; i < length; i++) {
					input[i] = sc.nextInt();
					for (int j = 0; j < i; j++) {
						if (input[j] == input[i]) {
							System.out.println("중복된 숫자를 입력하였습니다. 다시 입력하세요");
							i--;
						}
					}
				}

				if (Arrays.equals(answer, input)) {
					System.out.println("정답입니다. 총 입력한 횟수 : " + cnt);
					break;

				} else {
					for (int i = 0; i < length; i++) {
						int num = input[i];
						if (num == answer[i]) {
							Bulls++;
						} else if (IntStream.of(answer).anyMatch(x -> x == num)) {
							Cows++;
						}

					}
					System.out.println("입력한 숫자 : " + Arrays.toString(input) + " 판정 : " + Bulls + "B" + Cows + "C"
							+ " 입력한 횟수: " + cnt);
					cnt++;

				}
				

			}
			System.out.println("정답은 " +   ans + "Game Over");
			System.out.println("0. 게임 종료");
			System.out.println("1. 게임 다시 시작");

			System.out.print("선택하세요 >>");
			if(sc.nextInt() == 0) {
				option = false;
			}
			else option = true;
			
		}
		System.out.println("게임 종료");
		sc.close();
	}

	static int[] random() {
		int[] nums = new int[4];

		for (int i = 0; i < nums.length; i++) {
			nums[i] = (int) (Math.random() * 9 + 1);
			for (int j = 0; j < i; j++) {
				if (nums[i] == nums[j]) {
					i--;
				}
			}
		}

		return nums;
	}
}