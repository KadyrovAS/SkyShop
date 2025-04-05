package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {
    public String getSearchTerm();

    public String getContentType();

    public String getName();

    public UUID getId();

    public default String getStringRepresentation() {
        return getSearchTerm() + " - " + getContentType();
    }
}
