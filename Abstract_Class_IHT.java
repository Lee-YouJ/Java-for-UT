import java.util.Scanner;

public class Abstract_Class_IHT {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("숫자를 입력하세요:");
        int number = scanner.nextInt();

        System.out.println("문자열을 입력하세요:");
        String text = scanner.next();

        if (number > 0) {
            if (number % 2 == 0) {
                System.out.println("입력한 숫자는 양수이며 짝수입니다.");
            } else {
                System.out.println("입력한 숫자는 양수이며 홀수입니다.");
            }
        } else if (number < 0) {
            if (number % 2 == 0) {
                System.out.println("입력한 숫자는 음수이며 짝수입니다.");
            } else {
                System.out.println("입력한 숫자는 음수이며 홀수입니다.");
            }
        } else {
            System.out.println("입력한 숫자는 0입니다.");
        }

        if (text.length() > 5) {
            System.out.println("입력한 문자열은 길이가 5보다 큽니다.");
        } else if (text.length() == 5) {
            System.out.println("입력한 문자열의 길이는 정확히 5입니다.");
        } else {
            System.out.println("입력한 문자열은 길이가 5보다 작습니다.");
        }

        if (number > 0 && text.length() > 5) {
            System.out.println("양수와 길이가 5보다 큰 문자열을 입력했습니다.");
        } else if (number < 0 && text.length() <= 5) {
            System.out.println("음수와 길이가 5 이하인 문자열을 입력했습니다.");
        }

        scanner.close();
    }
}
