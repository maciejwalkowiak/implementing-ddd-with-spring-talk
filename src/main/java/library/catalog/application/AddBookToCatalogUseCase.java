package library.catalog.application;

import library.catalog.domain.Book;
import library.catalog.domain.BookRepository;
import library.catalog.domain.Isbn;
import library.catalog.infrastructure.BookSearchService;
import library.catalog.infrastructure.OpenLibraryIsbnSearchResult;
import org.springframework.stereotype.Service;

@Service
public class AddBookToCatalogUseCase {
    private final BookSearchService bookSearchService;
    private final BookRepository bookRepository;

    public AddBookToCatalogUseCase(BookSearchService bookSearchService, BookRepository bookRepository) {
        this.bookSearchService = bookSearchService;
        this.bookRepository = bookRepository;
    }

    public void execute(Isbn isbn) {
        OpenLibraryIsbnSearchResult result = bookSearchService.search(isbn);
        Book book = new Book(result.title(), isbn);
        bookRepository.save(book);
    }
}
