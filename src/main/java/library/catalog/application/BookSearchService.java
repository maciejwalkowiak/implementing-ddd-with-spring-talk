package library.catalog.application;

import library.catalog.domain.Isbn;

public interface BookSearchService {
    BookInformation search(Isbn isbn);
}
