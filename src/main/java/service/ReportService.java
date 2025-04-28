package service;

import model.Duty;
import model.User;
import repository.DutyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReportService {
    @Autowired
    private DutyRepository dutyRepository;

    @Autowired
    private UserService userService;

    // Метод для генерації звіту про чергування користувачів
    public String generateDutyReport(String reportType) {
        StringBuilder report = new StringBuilder();
        switch (reportType) {
            case "daily":
                report.append(generateDailyReport());
                break;
            case "weekly":
                report.append(generateWeeklyReport());
                break;
            case "monthly":
                report.append(generateMonthlyReport());
                break;
            default:
                report.append("Невідомий тип звіту.");
                break;
        }
        return report.toString();
    }

    // Метод для генерації звіту за день
    private String generateDailyReport() {
        LocalDate date = LocalDate.parse("2025-04-28", DateTimeFormatter.ISO_LOCAL_DATE); // Використовується для генерації звіту за певну дату
        List<Duty> duties = dutyRepository.findByDate(date); // Додано пошук чергувань за конкретною датою
        return formatDutyList(duties); // Форматуємо звіт
    }

    // Метод для генерації звіту за тиждень
    private String generateWeeklyReport() {
        LocalDate date = LocalDate.parse("2025-04-22", DateTimeFormatter.ISO_LOCAL_DATE); // Пошук за певну дату тижня
        List<Duty> duties = dutyRepository.findByDate(date);
        return formatDutyList(duties);
    }

    // Метод для генерації звіту за місяць
    private String generateMonthlyReport() {
        LocalDate date = LocalDate.parse("2025-04-01", DateTimeFormatter.ISO_LOCAL_DATE); // Початок місяця
        List<Duty> duties = dutyRepository.findByDate(date);
        return formatDutyList(duties);
    }

    // Метод для форматування чергувань
    private String formatDutyList(List<Duty> duties) {
        StringBuilder formattedReport = new StringBuilder();
        for (Duty duty : duties) {
            User user = userService.getUserById(duty.getUser().getId());
            formattedReport.append("Користувач: ").append(user.getName()).append(" - ").append(duty.getDate()).append("\n");
        }
        return formattedReport.toString();
    }
}
