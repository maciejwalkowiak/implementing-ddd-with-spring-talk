package library.catalog.application;

import library.catalog.domain.BarCode;
import library.catalog.domain.BookId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RegisterBookCopyUseCaseTest {
    @Autowired
    RegisterBookCopyUseCase registerBookCopyUseCase;

    @Test
    void foo() {
        registerBookCopyUseCase.execute(new BookId(UUID.randomUUID()), new BarCode("123"));
    }

}