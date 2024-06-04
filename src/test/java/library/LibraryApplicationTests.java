package library;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;

@SpringBootTest
class LibraryApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void verifyModules() {
        ApplicationModules.of(LibraryApplication.class).verify();
    }

}
