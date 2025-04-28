package service;

import model.Duty;
import repository.DutyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class DutyReportService {
    @Autowired
    private DutyRepository dutyRepository;

    // Генерація щоденного звіту
    public String generateDailyReport() {
        LocalDate date = LocalDate.parse("2025-04-28", DateTimeFormatter.ISO_LOCAL_DATE); // Використовується для генерації звіту за певну дату
        List<Duty> duties = dutyRepository.findByDate(date); // Додано пошук чергувань за конкретною датою
        return formatDutyList(duties); // Форматуємо звіт
    }

    // Форматування чергувань
    private String formatDutyList(List<Duty> duties) {
        StringBuilder formattedReport = new StringBuilder();
        for (Duty duty : duties) {
            formattedReport.append("Користувач: ").append(duty.getUser().getName())
                    .append(" - ").append(duty.getDate()).append("\n");
        }
        return formattedReport.toString();
    }
}
