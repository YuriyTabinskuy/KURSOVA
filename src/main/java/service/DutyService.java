package service;

import model.Duty;
import model.User;
import model.Notification;
import repository.DutyRepository;
import repository.UserRepository;
import repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class DutyService {
    @Autowired
    private DutyRepository dutyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationService notificationService;

    public void generateMonthlySchedule(int year, int month) {
        List<User> users = userRepository.findAll();
        List<LocalDate> allDays = getAllDaysOfMonth(year, month);

        for (User user : users) {
            int maxDuties = 6;
            int assignedDuties = 0;

            for (LocalDate day : allDays) {
                if (assignedDuties >= maxDuties) break;
                if (user.getBlockedDates() != null && user.getBlockedDates().contains(day.toString())) continue;

                boolean preferred = user.getPreferredDates() != null && user.getPreferredDates().contains(day.toString());
                boolean chance = preferred || new Random().nextInt(100) < 10; // 10% шанс якщо не бажана дата

                if (chance) {
                    Duty duty = new Duty();
                    duty.setUser(user);
                    duty.setDate(day);
                    duty.setStartTime(LocalTime.of(9, 0));
                    duty.setEndTime(LocalTime.of(18, 0));

                    // Використовуємо switch для вибору статусу чергування
                    String status = assignDutyStatus(day);
                    duty.setStatus(status);

                    dutyRepository.save(duty);
                    assignedDuties++;

                    sendDutyNotification(user, status);
                }
            }
        }
    }

    // Метод для вибору статусу чергування
    private String assignDutyStatus(LocalDate day) {
        String status;
        int dayOfWeek = day.getDayOfWeek().getValue(); // Отримуємо день тижня (1 - Понеділок, 7 - Неділя)

        switch (dayOfWeek) {
            case 1: // Понеділок
            case 3: // Середа
            case 5: // П'ятниця
                status = "active"; // В ці дні активне чергування
                break;
            case 2: // Вівторок
                status = "on-call"; // Вівторок - чергування "на зв'язку"
                break;
            case 4: // Четвер
                status = "rest"; // Четвер - день відпочинку
                break;
            default:
                status = "inactive"; // У неділю або вихідні чергування не активне
                break;
        }

        return status;
    }

    private void sendDutyNotification(User user, String status) {
        String message;
        switch (status) {
            case "active":
                message = "Ваше чергування на " + LocalDate.now() + " активне!";
                break;
            case "on-call":
                message = "Ви на чергуванні на зв'язку сьогодні, " + LocalDate.now();
                break;
            case "rest":
                message = "Сьогодні ваш день відпочинку!";
                break;
            default:
                message = "Ваше чергування не активне.";
                break;
        }

        notificationService.sendNotification(user.getId(), message);
    }

    private List<LocalDate> getAllDaysOfMonth(int year, int month) {
        List<LocalDate> days = new ArrayList<>();
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.plusMonths(1).minusDays(1);

        while (!start.isAfter(end)) {
            days.add(start);
            start = start.plusDays(1);
        }
        return days;
    }

    public List<Duty> getUserDuties(Long userId) {
        return dutyRepository.findByUserId(userId);
    }
}
