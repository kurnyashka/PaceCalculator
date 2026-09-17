import java.util.Scanner;
import java.text.DecimalFormat;

public class Main {
    private static void printCutoff (int h, int min, int sec, double dist) {
        DecimalFormat df = new DecimalFormat("0.###");

        String timeFormatted = String.format("%d:%02d:%02d", h, min, sec);
        System.out.println(df.format(dist) + "км   " + timeFormatted);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Введите дистанцию для расчета темпа (в км): ");
        double distance = scanner.nextDouble();
        System.out.printf("Желаемое время (чч:мм:сс): ");
        String[] time = scanner.next().split(":");

        int time_sec = 0;
        double pace;
        int pace_min, pace_sec;

        for (int i = 3; i > 0; i--) {
            time_sec += Integer.parseInt(time[3 - i]) * Math.pow(60, (i - 1));
        }

        pace = time_sec / distance;
        pace_min = (int) (pace / 60);
        pace_sec = (int) (pace % 60);

        System.out.println("Ваш целевой темп: " + String.format("%d:%02d", pace_min, pace_sec));
        System.out.println("Раскладка по км: ");

        int i = 1;
        int pace_min_km = 0, pace_sec_km = 0, pace_h_km = 0;

        while (distance > 0) {
            if (distance < 1) {
                pace_h_km = (int) (time_sec / 3600);
                pace_min_km = (int) (time_sec / 60) - (pace_h_km * 60);
                pace_sec_km = (int) (time_sec % 60);
                printCutoff(pace_h_km, pace_min_km, pace_sec_km, (i + distance - 1));
                break;
            }
            pace_h_km = (int) (pace * i / 3600);
            pace_min_km = (int) (pace * i / 60) - (pace_h_km * 60);
            pace_sec_km = (int) (pace * i % 60);
            printCutoff(pace_h_km, pace_min_km, pace_sec_km, i++);
            distance--;
        }
    }
}