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

    @Test
    public void jackardSimilarityWithEmptySequenceTest() {
        int[] firstSequence = {1,2,3};
        int[] secondSequence = {};

        finder = new SimilarityFinder((key, seq) -> SearchResult.builder().withFound(false).build());

        Assert.assertThat(0.0,is(equalTo(finder.calculateJackardSimilarity(firstSequence,secondSequence))) );
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

        Assert.assertThat(0.25,is(equalTo(finder.calculateJackardSimilarity(firstSequence,secondSequence))) );
    }


    @Test
    public void jackardSimilaritySequencesDifferentSizesWithoutCommonElementTest() {
        int[] firstSequence = {1,2,3,4};
        int[] secondSequence = {124,9};

        finder = new SimilarityFinder((key, seq) -> SearchResult.builder().build());

        Assert.assertThat(0.0,is(equalTo(finder.calculateJackardSimilarity(firstSequence,secondSequence))) );
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

        Assert.assertThat(0.2,is(equalTo(finder.calculateJackardSimilarity(firstSequence,secondSequence))) );
    }

    @Test
    public void jackardSimilaritySequencesSameSizeWithoutCommonElementTest() {
        int[] firstSequence = {1,2,3};
        int[] secondSequence = {11,22,33};

        finder = new SimilarityFinder((key, seq) -> SearchResult.builder().build());

        Assert.assertThat(0.0,is(equalTo(finder.calculateJackardSimilarity(firstSequence,secondSequence))) );
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

        Assert.assertThat(0.5,is(equalTo(finder.calculateJackardSimilarity(firstSequence,secondSequence))) );
    }
}
