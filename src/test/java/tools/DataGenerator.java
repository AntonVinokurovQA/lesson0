package tools;

import com.github.javafaker.Faker;
import pojo.requests.NewUser;

public class DataGenerator {
    private static Faker faker = new Faker();

    public static NewUser newUserRandom() {
        return new NewUser(faker.name().firstName(), faker.job().position());
    }
}
