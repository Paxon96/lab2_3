package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;
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

        Assert.assertThat(finder.calculateJackardSimilarity(firstSequence,secondSequence) ,is(equalTo(1.0)) );
    }

    @Test
    public void jackardSimilarityWithEmptySequenceTest() {
        int[] firstSequence = {1,2,3};
        int[] secondSequence = {};

        finder = new SimilarityFinder((key, seq) -> SearchResult.builder().withFound(false).build());

        Assert.assertThat(finder.calculateJackardSimilarity(firstSequence,secondSequence),is(equalTo(0.0)) );
    }

    @Test
    public void jackardSimilaritySequencesDifferentSizesWithCommonElementTest() {
        int[] firstSequence = {1,2,3};
        int[] secondSequence = {1,9};

        finder = new SimilarityFinder((key, seq) -> {
            if(key == seq[0] || key == seq[1])
                return SearchResult.builder().withFound(true).build();
            return SearchResult.builder().build();
        });

        Assert.assertThat(finder.calculateJackardSimilarity(firstSequence,secondSequence),is(equalTo(0.25)) );
    }


    @Test
    public void jackardSimilaritySequencesDifferentSizesWithoutCommonElementTest() {
        int[] firstSequence = {1,2,3,4};
        int[] secondSequence = {124,9};

        finder = new SimilarityFinder((key, seq) -> SearchResult.builder().build());

        Assert.assertThat(finder.calculateJackardSimilarity(firstSequence,secondSequence) ,is(equalTo(0.0)) );
    }

    @Test
    public void jackardSimilaritySequencesSameSizeWithCommonElementTest() {
        int[] firstSequence = {1,2,3};
        int[] secondSequence = {1,18,2};

        finder = new SimilarityFinder((key, seq) -> {
            if(key == seq[0])
                return SearchResult.builder().withFound(true).build();

            return SearchResult.builder().build();
        });

        Assert.assertThat(finder.calculateJackardSimilarity(firstSequence,secondSequence),is(equalTo(0.2)) );
    }

    @Test
    public void jackardSimilaritySequencesSameSizeWithoutCommonElementTest() {
        int[] firstSequence = {1,2,3};
        int[] secondSequence = {11,22,33};

        finder = new SimilarityFinder((key, seq) -> SearchResult.builder().build());

        Assert.assertThat(finder.calculateJackardSimilarity(firstSequence,secondSequence),is(equalTo(0.0)) );
    }

    @Test
    public void jackardSimilaritySequencesDifferentSizesWithPartCommonElementTest() {
        int[] firstSequence = {1,2,3};
        int[] secondSequence = {1,2,3,77,88,99};

        finder = new SimilarityFinder((key, seq) -> {
            if(key == seq[0] || key == seq[1] || key == seq[2])
                return SearchResult.builder().withFound(true).build();

            return SearchResult.builder().build();
        });

        Assert.assertThat(finder.calculateJackardSimilarity(firstSequence,secondSequence),is(equalTo(0.5)) );
    }

    @Test
    public void jackardSimilarityNumberOfCallsForFirsSequenceTest(){
        int[] firstSequence = {1,2,3,4,5,6,7};
        int[] secondSequence = {1,2,3,4,5,6,7,88,99,111,222,333};

        SequenceSearcherCounter sequenceSearcherCounter = new SequenceSearcherCounter();

        finder = new SimilarityFinder(sequenceSearcherCounter);

        finder.calculateJackardSimilarity(firstSequence,secondSequence);

        Assert.assertThat(sequenceSearcherCounter.getCounter(),is(equalTo(firstSequence.length)) );
    }

    private class SequenceSearcherCounter implements SequenceSearcher {

        private int counter = 0;

        @Override
        public SearchResult search(int key, int[] seq) {
            this.counter++;
            return SearchResult.builder().build();
        }

        int getCounter() {
            return counter;
        }
    }
}
