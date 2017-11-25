package learn.to.stream.learntostream;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Counting Streams
 * Summing Streams
 * Distinct, Skip, Limit
 * Streams of Integer, Long, Double
 * Streams from arrays
 */
public class Phase0Streams {
  private final int FIX_ME = -1;

  // Getting Started
  @Test
  public void stream00() {
    // So you want to learn how to stream?
    // You've come to the right place!
    // Instead of looping you can stream your data
    // through a series of functions
    // and collect the results.

    // We'll use the Hamcrest test framework,
    // you may want to have a cheatsheet available.

    // Here's a stream with a single item: zero.
    //// (count() returns a long)
    assertThat(IntStream.of(0).count(), is(1L));
    //// sum() of [0] is zero.
    assertThat(IntStream.of(0).sum(), is(0));
    assertThat(IntStream.of(1).sum(), is(1));
  }

  @Test
  public void stream01() {
    // So what is the point of that?
    // Let's make a some longer streams:
    assertThat(IntStream.of(0,1,2,3).count(), is(4L));

    // Your turn: this assertion fails.
    // EDIT the expected value to make it pass!
    assertThat(IntStream.of(0,1,2,3).sum(), is(FIX_ME));
  }

  @Test
  public void stream02() {
    // Maybe we don't have to give all the values for the stream...
    assertThat(IntStream.range(0, 4).count(), is(4L));
    int n = 2000;
    assertThat(IntStream.range(0, n).count(), is((long)n));

    // you might call this $\sum_{i=0}^n i$
    // EDIT the expected value to make the test pass
    assertThat(IntStream.range(0, n).sum(), is(FIX_ME));
  }

  @Test
  public void stream03() {
    // But I want 1 to 10, not 0 to 9!
    assertThat(IntStream.range(1,10).count(), is(9L));
    // Use rangeClosed() to include both min and max:
    assertThat(IntStream.rangeClosed(1,10).count(), is(10L));
  }

  // Simple Functional Tools
  //// distinct()
  @Test
  public void stream04() {
    // I don't care how many ints are in the stream,
    // I just want to know how many different values are present.
    assertThat(IntStream.of(0,1,1,1,2,2,2).distinct().count(), is(3L));
  }

  //// skip and limit (aka 'take')
  @Test
  public void stream05() {
    // Skip the first three on the stream
    assertThat(IntStream.range(0,10).skip(3L).count(), is(7L));
    assertThat(IntStream.range(0,10).skip(8L).sum(), is(8+9));
    // Take the first four elements
    assertThat(IntStream.range(0,10).limit(4L).count(), is(4L));
    // 0+1+2+3
    assertThat(IntStream.range(0,10).limit(4L).sum(), is(FIX_ME));

  }

  // you do the math...
  @Test
  public void stream06() {
    assertThat(IntStream.of(20,21).sum(), is(FIX_ME));
    assertThat(IntStream.rangeClosed(1,100).sum(), is(FIX_ME));
    assertThat(IntStream.of(5,12,1,7).sum(), is(FIX_ME));
  }

  // Not just for Ints!
  @Test
  public void stream07() {
    // You can stream Longs
    assertThat(LongStream.range(1000L, 1200L).count(), is(200L));
    // You can stream Doubles (but no range() here...)
    assertThat(DoubleStream.of(1/3., 1/3., 1/3.).sum(), is(1.0));
    // you can stream characters in a string
    // ...but note that this returns an IntStream()
    assertThat("bolton".chars().count(), is(6L));

    // And you can stream elements from an array
    String[] words = {"taco","taco","taco","sushi"};
    assertThat(Arrays.stream(words).count(), is(4L));
    assertThat(Arrays.stream(words).distinct().count(), is(FIX_ME));
  }

  // Congratulations, you've completed Phase 0.
  // In Phase 1 we learn about streaming with anonymous functions...
}
