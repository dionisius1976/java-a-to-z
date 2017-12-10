package ru.dionisius;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.dionisius.data.config.sosConfig;
import ru.dionisius.data.models.Ad;
import ru.dionisius.data.models.Car;
import ru.dionisius.data.models.User;
import ru.dionisius.service.IAdService;

import java.util.List;

/**
 * Created by Dionisius on 21.11.2017.
 */
public class start {
    public static void main(String[] args) {
        Ad ad1 = new Ad("Updated!", 2000,
                    new User("11", "11", "V", "V", "+7-1"),
                    new Car("Lada", "Kalina", "manual", 1.5f, 2000));
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(sosConfig.class);
        IAdService service = annotationConfigApplicationContext.getBean(IAdService.class);
        service.addAd(ad1);
        List<Ad> ads = service.getAll();
        System.out.println("All ads:");
        for (Ad ad : ads) {
            System.out.println(ad);
        }
    }
}
