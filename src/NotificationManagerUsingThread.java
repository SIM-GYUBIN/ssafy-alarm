import java.awt.*;
import java.time.LocalTime;
import javax.swing.JOptionPane;

public class NotificationManagerUsingThread {

    public void sendNotification(LocalTime currentTime) {
        // 원하는 시간을 설정합니다.
        LocalTime targetTime = LocalTime.of(8, 30);

        // 현재 시간과 비교하여 알림을 줍니다.
        if (currentTime.isAfter(targetTime)) {
            // 현재 시간이 8시 30분 이후이면 바로 알림을 줍니다.
            showMessageDialog("이미 부팅 후 8시 30분이 지났습니다.");
        } else if (currentTime.equals(targetTime)) {
            // 정확히 8시 30분일 때 알림을 줍니다.
            showMessageDialog("지금이 8시 30분입니다.");
        } else {
            // 부팅 후 8시 30분이 되면 알림을 줍니다.
            scheduleNotification(targetTime);
        }
    }

    private void scheduleNotification(LocalTime targetTime) {
        // 현재 시각과 타겟 시각을 비교하여 대기 시간을 계산합니다.
        LocalTime currentTime = LocalTime.now();
        long delay = currentTime.until(targetTime, java.time.temporal.ChronoUnit.SECONDS);

        // 대기 후 알림을 줍니다.
        try {
            Thread.sleep(delay * 1000); // 대기 시간 (초 단위)을 밀리초로 변환하여 대기
            showMessageDialog("지금이 8시 30분입니다.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void showMessageDialog(String message) {
        // 간단한 다이얼로그를 이용하여 메시지를 표시합니다.
        JOptionPane.showMessageDialog(null, message);
    }

}
