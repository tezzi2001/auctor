package com.aquawebdev.auctor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
class AuctorApplicationTests {
    @Value("${spring.profiles.active}")
    private String activeProfile;

    @Test
    void contextLoads() {
        System.out.println("active profile: " + activeProfile);
    }

}
