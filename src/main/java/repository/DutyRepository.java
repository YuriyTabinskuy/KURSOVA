package repository;

import model.Duty;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface DutyRepository extends JpaRepository<Duty, Long> {
    List<Duty> findByUserId(Long userId);
    List<Duty> findByDate(LocalDate date);
    List<Duty> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
