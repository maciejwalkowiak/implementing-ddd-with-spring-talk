package library.catalog.infrastructure;

import library.catalog.domain.Isbn;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class BookSearchService {
    private final RestClient restClient;

    public BookSearchService(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://openlibrary.org/")
                .build();
    }

    public OpenLibraryIsbnSearchResult search(Isbn isbn) {
        return restClient.get().uri("isbn/{isbn}.json", isbn.value())
                .retrieve()
                .body(OpenLibraryIsbnSearchResult.class);
    }
}
