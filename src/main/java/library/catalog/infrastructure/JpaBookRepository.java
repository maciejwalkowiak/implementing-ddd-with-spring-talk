package library.catalog.infrastructure;

import library.catalog.domain.Book;
import library.catalog.domain.BookRepository;
import org.springframework.stereotype.Component;

@Component
public class JpaBookRepository implements BookRepository {
    private final BookEntityRepository bookEntityRepository;

    public JpaBookRepository(BookEntityRepository bookEntityRepository) {
        this.bookEntityRepository = bookEntityRepository;
    }

    @Override
    public Book save(Book book) {
        BookEntity entity = new BookEntity(book.getId().id(), book.getTitle(), book.getIsbn().value());
        bookEntityRepository.save(entity);
        return book;
    }
}
