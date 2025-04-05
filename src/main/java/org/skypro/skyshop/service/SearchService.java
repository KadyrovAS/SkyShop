package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public List<SearchResult> search(String findLine){
        List<SearchResult>list = new LinkedList<>();
        storageService.getSearchable()
            .stream()
            .filter(x->x.getSearchTerm().contains(findLine))
            .forEach(x->list.add(SearchResult.fromSearchable(x)));

        return list;
    }
}
