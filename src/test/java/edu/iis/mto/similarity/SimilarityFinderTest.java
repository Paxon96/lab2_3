package edu.iis.mto.similarity;

import edu.iis.mto.similarity.dubler.SequenceSearcherDubler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SimilarityFinderTest {

    private SimilarityFinder finder;
    private final double SAME_SEQUENCES = 1.0;
    private final double DIFFERENT_SEQUENCES = 0.0;

    @Before
    public void init(){
        finder = new SimilarityFinder(new SequenceSearcherDubler());
    }

    @Test
    public void jackardSimilarityWithEmptySequencesTest() {
        int[] firstSequence = {};
        int[] secondSequence = {};

        Assert.assertThat(SAME_SEQUENCES,is(equalTo(finder.calculateJackardSimilarity(firstSequence,secondSequence))) );
    }

    @Test
    public void jackardSimilarityWithEmptySequenceTest() {
        int[] firstSequence = {1,2,3,4,5};
        int[] secondSequence = {};

        Assert.assertThat(DIFFERENT_SEQUENCES,is(equalTo(finder.calculateJackardSimilarity(firstSequence,secondSequence))) );
    }

    @Test
    public void jackardSimilaritySequencesDifferentSizesWithCommonElementTest() {
    }

    @Test
    public void jackardSimilaritySequencesDifferentSizesWithoutCommonElementTest() {
    }

    @Test
    public void jackardSimilaritySequencesSameSizeWithCommonElementTest() {
    }

    @Test
    public void jackardSimilaritySequencesSameSizeWithoutCommonElementTest() {
    }

    @Test
    public void jackardSimilaritySequencesDifferentSizesWithPartCommonElementTest() {
    }

    @Test
    public void jackardSimilaritySequencesDifferentSizesWithAllCommonElementTest() {
    }


}
