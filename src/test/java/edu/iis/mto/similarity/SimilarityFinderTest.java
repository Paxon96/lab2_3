package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SimilarityFinderTest {

    private SimilarityFinder finder;

    @Test
    public void jackardSimilarityWithEmptySequencesTest() {
        int[] firstSequence = {};
        int[] secondSequence = {};

        finder = new SimilarityFinder((key, seq) -> SearchResult.builder().build());

        Assert.assertThat(1.0 ,is(equalTo(finder.calculateJackardSimilarity(firstSequence,secondSequence))) );
    }

}
