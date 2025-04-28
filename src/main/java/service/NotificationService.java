package service;

import model.Notification;
import repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    private Long id;
    private String message;

    public void Notification(Long id, String message) {
        this.id = id;
        this.message = message;
    }
    // Метод для надсилання повідомлення на основі типу
    public void sendNotification(Long userId, String messageType) {
        String message = generateMessage(messageType);
        Notification notification = new Notification(userId, message);
        notificationRepository.save(notification);
    }

    // Метод для створення повідомлення залежно від типу
    private String generateMessage(String messageType) {
        String message;
        switch (messageType) {
            case "duty_assigned":
                message = "Вам призначено чергування на завтра!";
                break;
            case "duty_updated":
                message = "Ваше чергування було оновлено.";
                break;
            case "reminder":
                message = "Нагадуємо, що завтра ваше чергування.";
                break;
            default:
                message = "Невідоме повідомлення.";
                break;
        }
        return message;
    }
}
