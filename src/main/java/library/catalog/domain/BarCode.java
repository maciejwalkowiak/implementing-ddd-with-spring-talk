package library.catalog.domain;

import org.springframework.util.Assert;

public record BarCode(String code) {
    public BarCode {
        Assert.notNull(code, "Bar code must not be null");
    }
}
