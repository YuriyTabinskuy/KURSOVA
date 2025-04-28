package service;

import model.User;
import repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Додавання тестових користувачів
    public void addSampleUsers() {
        User user1 = new User();
        user1.setName("Марія Іванова");
        user1.setEmail("maria@example.com");
        user1.setPassword("Пароль123");  // Пароль буде хешований в процесі збереження
        user1.setPreferredDates(List.of("2025-05-01", "2025-05-03"));  // Приклад вибраних дат
        user1.setBlockedDates(List.of("2025-05-02"));

        User user2 = new User();
        user2.setName("Іван Петренко");
        user2.setEmail("ivan@example.com");
        user2.setPassword("Password456");
        user2.setPreferredDates(List.of("2025-05-04", "2025-05-06"));
        user2.setBlockedDates(List.of("2025-05-05"));

        User user3 = new User();
        user3.setName("Олена Коваль");
        user3.setEmail("olena@example.com");
        user3.setPassword("Qwerty789");
        user3.setPreferredDates(List.of("2025-05-07", "2025-05-09"));
        user3.setBlockedDates(List.of("2025-05-08"));

        // Збереження користувачів у базу даних
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }

    // Логіка аутентифікації (метод для входу)
    public String login(User user) {
        String email = user.getEmail();
        String password = user.getPassword();

        // Перевірка, чи існує користувач з таким email
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (!existingUser.isPresent()) {
            return "Користувача з таким email не знайдено!";
        }

        User existingUserObj = existingUser.get();

        // Перевірка пароля
        if (!BCrypt.checkpw(password, existingUserObj.getPassword())) {
            return "Невірний пароль!";
        }

        return "Вхід успішний!";
    }

    // Реєстрація нового користувача
    public String register(User user, String password) {
        // Перевірка, чи вже існує користувач з таким email
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return "Email вже використовується!";
        }

        // Перевірка на валідність пароля
        if (!isValidPassword(password)) {
            return "Пароль не відповідає вимогам!";
        }

        // Хешування пароля (наприклад, використовуючи BCrypt)
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Збереження нового користувача з хешованим паролем
        user.setPassword(hashedPassword);
        userRepository.save(user);

        return "Реєстрація успішна!";
    }

    // Перевірка валідності пароля
    private boolean isValidPassword(String password) {
        // Пароль повинен бути хоча б 8 символів, містити цифри та великі літери
        return password.length() >= 8 &&
                password.matches(".*[0-9].*") &&  // Має містити цифри
                password.matches(".*[A-Z].*");   // Має містити великі літери
    }

    // Метод для додавання нового користувача
    public void addUser(User user) {
        userRepository.save(user);
    }

    // Метод для редагування інформації про користувача
    public void updateUser(Long userId, User updatedUser) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setPreferredDates(updatedUser.getPreferredDates());
        user.setBlockedDates(updatedUser.getBlockedDates());
        userRepository.save(user);
    }

    // Метод для отримання всіх користувачів
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Метод для отримання користувача за ID
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Метод для заборони певних дат для користувача
    public void blockUserDates(Long userId, List<String> blockedDates) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setBlockedDates(blockedDates);
        userRepository.save(user);
    }
}
