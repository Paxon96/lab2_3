package edu.iis.mto.similarity;

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

        Assert.assertThat(1.0 ,is(equalTo(finder.calculateJackardSimilarity(firstSequence,secondSequence))) );
    }

}
