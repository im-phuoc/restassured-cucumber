package utils;

import com.github.javafaker.Faker;
import payload.UserRequest;

public class FakerUtil {
    private static final Faker faker = new Faker();

    public static String generateUsername() {
        String username;
        do {
            username = faker.name().username();
        } while (username.length() < 2 || username.length() > 20);
        return username;
    }

    public static String generateEmail() {
        return faker.internet().emailAddress();
    }

    public static String generatePassword() {
        return faker.internet().password(6, 20);
    }

    public static UserRequest generateRegisterRequest() {
        return new UserRequest(generateUsername(),generatePassword(), generateEmail());
    }
}
