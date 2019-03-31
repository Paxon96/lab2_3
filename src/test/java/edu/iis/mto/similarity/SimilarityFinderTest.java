package edu.iis.mto.similarity;

import edu.iis.mto.similarity.dubler.SequenceSearcherDubler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class SimilarityFinderTest {

    private SimilarityFinder finder;

    @Before
    public void init(){
        finder = new SimilarityFinder(new SequenceSearcherDubler());
    }

    @Test
    public void jackardSimilarityWithEmptySequencesTest() {
        int[] firstSequence = {};
        int[] secondSequence = {};

        Assert.assertThat(1.0 ,is(equalTo(finder.calculateJackardSimilarity(firstSequence,secondSequence))) );
    }

    @Test
    public void jackardSimilarityWithEmptySequenceTest() {
        int[] firstSequence = {1,2,3,4,5};
        int[] secondSequence = {};
        double expectedIntersecton = calculateIntersection(firstSequence.length, secondSequence.length, 0);
        Assert.assertThat(expectedIntersecton,is(equalTo(finder.calculateJackardSimilarity(firstSequence,secondSequence))) );
    }

    @Test
    public void jackardSimilaritySequencesDifferentSizesWithCommonElementTest() {
        int[] firstSequence = {1,2,3,4,5,11};
        int[] secondSequence = {1,9,8};

        double expectedIntersecton = calculateIntersection(firstSequence.length, secondSequence.length, 1);

        Assert.assertThat(expectedIntersecton,is(equalTo(finder.calculateJackardSimilarity(firstSequence,secondSequence))) );
    }

    @Test
    public void jackardSimilaritySequencesDifferentSizesWithoutCommonElementTest() {
        int[] firstSequence = {1,2,3,4,14,5,11};
        int[] secondSequence = {124,9,8};

        double expectedIntersecton = calculateIntersection(firstSequence.length, secondSequence.length, 0);
        Assert.assertThat(expectedIntersecton,is(equalTo(finder.calculateJackardSimilarity(firstSequence,secondSequence))) );
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


    private double calculateIntersection(int firstSeqLength, int secondSeqLength, int numberOfTheSameValues){
        return (double) numberOfTheSameValues / ((double) firstSeqLength + secondSeqLength - numberOfTheSameValues);
    }
}
