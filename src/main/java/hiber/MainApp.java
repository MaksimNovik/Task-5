package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User gleb = new User("Gleb", "Tsiarentsyev", "Tsiarentsyev@mail.ru");
        User maksim = new User("Maksim", "Maksimov", "Maks@mail.ru");
        User petya = new User("Petya", "Petrov", "Petr@mail.ru");
        User nikita = new User("Nikita", "Smirnov", "Nikita@mail.ru");

        Car geely = new Car("Gelly", 50);
        Car bmw = new Car("BMW", 540);
        Car lada = new Car("Lada", 21014);
        Car mercedes = new Car("Mercedes", 220);

        userService.add(gleb.setCar(geely).setUser(gleb));
        userService.add(maksim.setCar(bmw).setUser(maksim));
        userService.add(petya.setCar(lada).setUser(petya));
        userService.add(nikita.setCar(mercedes).setUser(nikita));

        for (User user : userService.listUsers()) {
            System.out.println(user + " " + user.getCar());
        }
//        System.out.println(userService.getUserByCar("BMW", 540));
        context.close();
    }
}
