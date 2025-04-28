package controller;

import model.Duty;
import service.DutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/duty")
public class DutyController {
    @Autowired
    private DutyService dutyService;

    @PostMapping("/generate")
    public void generate(@RequestParam int year, @RequestParam int month) {
        dutyService.generateMonthlySchedule(year, month);
    }

    @GetMapping("/user/{userId}")
    public List<Duty> getUserDuties(@PathVariable Long userId) {
        return dutyService.getUserDuties(userId);
    }
}
