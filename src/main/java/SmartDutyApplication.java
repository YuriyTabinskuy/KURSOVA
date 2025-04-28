import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import service.UserService;

@SpringBootApplication
public class SmartDutyApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(SmartDutyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Додавання користувачів
        userService.addSampleUsers();
    }
}
