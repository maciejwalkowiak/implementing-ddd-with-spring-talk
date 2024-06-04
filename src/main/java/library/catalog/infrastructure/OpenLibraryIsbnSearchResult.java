package library.catalog.infrastructure;

import java.util.List;

record OpenLibraryIsbnSearchResult(List<String> publishers,
                                          String title,
                                          List<String> isbn_13,
                                          int revisions) {
}
