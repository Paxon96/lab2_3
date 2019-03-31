package edu.iis.mto.similarity.dubler;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

public class SequenceSearcherDubler implements SequenceSearcher {

    private static int callsNumber = 0;

    public static int getCallsNumber() {
        return callsNumber;
    }

    public static void setCallsNumber(int callsNumber) {
        SequenceSearcherDubler.callsNumber = callsNumber;
    }

    @Override
    public SearchResult search(int key, int[] seq) {
        callsNumber ++;
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
