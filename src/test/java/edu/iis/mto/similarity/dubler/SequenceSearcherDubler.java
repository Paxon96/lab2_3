package edu.iis.mto.similarity.dubler;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

public class SequenceSearcherDubler implements SequenceSearcher {

    @Override
    public SearchResult search(int key, int[] seq) {
        boolean foundStatus = false;
        int elemPosition = -1;

        SearchResult.Builder search = SearchResult.builder();

        for (int i = 0; i < seq.length; i++) {
            if (seq[i] == key) {
                search.withFound(true);
                search.withPosition(i);
                break;
            }
        }
        return search.build();
    }
}
