package service;

import model.Duty;
import repository.DutyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private DutyRepository dutyRepository;

    // Метод для перегляду всіх чергувань
    public void viewAllDuties() {
        dutyRepository.findAll().forEach(duty -> {
            System.out.println("Користувач: " + duty.getUser().getName() + ", Дата: " + duty.getDate());
        });
    }

    // Метод для видалення чергування
    public void deleteDuty(Long dutyId) {
        dutyRepository.deleteById(dutyId);
    }

    // Метод для редагування чергування
    public void updateDuty(Long dutyId, Duty updatedDuty) {
        Duty duty = dutyRepository.findById(dutyId).orElseThrow(() -> new RuntimeException("Duty not found"));
        duty.setDate(updatedDuty.getDate());
        duty.setStartTime(updatedDuty.getStartTime());
        duty.setEndTime(updatedDuty.getEndTime());
        duty.setStatus(updatedDuty.getStatus());
        dutyRepository.save(duty);
    }
}
