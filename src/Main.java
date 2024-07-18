import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.time.LocalTime;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // 프로그램을 시스템 트레이 아이콘으로 실행
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        // 시스템 트레이 아이콘 설정
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }

        final PopupMenu popup = new PopupMenu();
        final TrayIcon trayIcon = new TrayIcon(createImage("icon.png", "tray icon"));
        final SystemTray tray = SystemTray.getSystemTray();

        // 트레이 아이콘에 메뉴 추가
        MenuItem exitItem = new MenuItem("Exit");
        popup.add(exitItem);

        trayIcon.setPopupMenu(popup);

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
            return;
        }

        // 종료 메뉴 클릭 시 동작 설정
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });

        // 프로그램 로직 처리 (예: 시간 체크 및 알림 기능)
        NotificationManagerUsingTimer notificationManager = new NotificationManagerUsingTimer();

        LocalTime currentTime = LocalTime.now();
        notificationManager.sendNotification(currentTime);
//        System.exit(0);
    }

    // 아이콘 이미지 로드 메서드
    protected static Image createImage(String path, String description) {
        URL imageURL = Main.class.getResource(path);
        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }
}
