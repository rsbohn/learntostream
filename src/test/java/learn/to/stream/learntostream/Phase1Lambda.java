package learn.to.stream.learntostream;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Function References
 * Anonymous Functions (lambda)
 * Optional<T>
 * Filter
 * Map
 * Collect
 * Collectors.toList()
 *
 */
public class Phase1Lambda {
  private final int FIX_ME = -1;
  // change any references to FIX_ME to an actual value (except for this declaration)

  // these are predicates (functions that return a boolean)
  private boolean isEven(int x) {
    return x % 2 == 0;
  }
  private boolean isOdd(int x) {
    return !isEven(x);
  }
  private boolean isShortString(String s) {
    return s.length() < 8;
  }

  @Test
  public void stream00_evenNumbers() {
    int[] numbers = IntStream.rangeClosed(0,10).toArray();
    // two ways to say the same thing
    assertThat(numbers.length, is(11));
    assertThat(Arrays.stream(numbers).count(), is(11L));

    // filter(func) takes a function of one parameter that returns a boolean
    // it keeps items that satisfy the function (where the function returns true)
    // it drops items that don't satisfy the function
    assertThat(isEven(0), is(true));
    assertThat(isEven(1), is(false));
    // this::isEven is a function reference
    int[] evens = Arrays.stream(numbers).filter(this::isEven).toArray();
    assertThat(evens.length, is(6));
    assertThat(evens[0], is(0));
    assertThat(evens[5], is(10));
  }

  @Test
  public void stream01_oddNumbers() {
    int[] numbers = IntStream.rangeClosed(0,10).toArray();
    // an anonymous function (or lambda)
    // is a list of parameters
    // ->
    // and a block of code
    // that returns a value

    // use the isOdd predicate: (by a function reference)
    int[] odd_by_predicate = Arrays.stream(numbers).filter(this::isOdd).toArray();

    // anonymous 'isOdd' function
    int[] odd_by_anon = Arrays.stream(numbers)
        .filter((x) -> {return !isEven(x);})
        .toArray();

    assertThat(odd_by_predicate.length, is(odd_by_anon.length));
    assertThat(Arrays.equals(odd_by_predicate, odd_by_anon), is(true));
  }

  @Test
  public void stream02_sugar() {
    int[] evens;
    int[] odds;
    //You can simplify the expression of an anonymous function:
    // if you only have one argument you don't need parentheses:
    evens = IntStream.range(0,10)
        .filter(x -> {return isEven(x);})
        .toArray();

    // if your function is a single expression
    // you don't need the curly braces {}
    // the result of the expression is returned
    // so you drop the keyword 'return' also
    evens = IntStream.range(0,10)
        .filter(x -> isEven(x))
        .toArray();
    odds = IntStream.range(0,10)
      .filter(x -> !isEven(x))
      .toArray();
  }

  @Test
  public void stream03_shortStrings() {
    List<String> words = Arrays.asList("taco","taco","taco","banana","stroopwaffel");
    // function reference
    assertThat(words.stream()
        .filter(this::isShortString)
        .count(),
        is(4L));

    // anonymous function
    assertThat(words.stream()
        .filter(word -> !isShortString(word))
        .toArray()[0],
        is("stroopwaffel"));
  }

  @Test
  public void stream04_identity_map() {
    // Map applies a function to each item in a stream.
    // The identity function returns its parameter unchanged.
    int[] ones = IntStream.of(1,1,1)
        .map(x -> x)
        .toArray();
    assertThat(ones.length, is(3));

    // change all the ones to twos
    int[] twos = Arrays.stream(ones)
        .map(x -> x+1)
        .toArray();
    assertThat(Arrays.equals(twos, new int[]{2, 2, 2}), is(true));

  }

  @Test
  public void stream05_collectors() {
    // We can collect the stream output in a list using the Collectors class.
    // Use boxed() to convert the ints to Integers
    List<Integer> numbers = IntStream.range(0,10)
        .boxed()
        .collect(Collectors.toList());
    assertThat(numbers.get(9), is(9));

    // Here we convert ints to Strings using mapToObj.
    // Collectors.toList() handles the changing class.
    List<String> strings = IntStream.range(0,10)
        .mapToObj(x -> {
          if (isEven(x)) { return "even"; }
          return "odd";
        })
        .collect(Collectors.toList());
    assertThat(strings.get(1), is("odd"));
    assertThat(strings.get(6), is("even"));
  }

  private enum Color {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
  }

  @Test
  public void stream06_collecting_colors() {
    // Another example of using collect()
    // Here we convert from enum Colors to String
    List<String> strings = Arrays.stream(Color.values())
        .map(Enum::toString)
        .collect(Collectors.toList());
    assertThat(strings.get(0), is("RED"));
  }

  List<Color> lights = Arrays.asList(
      Color.RED, Color.RED, Color.BLUE, Color.GREEN,
      Color.BLUE, Color.GREEN, Color.YELLOW, Color.VIOLET,
      Color.ORANGE, Color.INDIGO, Color.RED, Color.GREEN);

  // Exercises
  @Test
  public void x00() {
    //Count the blue lights
    //Change the filter predicate
    assertThat(lights.stream()
      .filter(x -> true)
        .count(), is(2L));
  }

  @Test
  public void x01() {
    //Replace the green lights with yellow
    assertThat(lights.stream()
        .map(x -> x)
        .filter(Color.YELLOW::equals)
        .count(), is(3L));
  }

  @Test
  public void x02() {
    // Change n to make the test pass
    // Hint: there are three solutions
    long n = 0L;
    List<Color> someLights = lights.stream()
        .skip(n)
        .collect(Collectors.toList());
    assertThat(someLights.get(0), is(Color.GREEN));

  }

  @Test
  public void x03() {
    // Make all the lights green
    List<Color> greenLights = lights.stream()
        // FIX_ME
        .collect(Collectors.toList());
    assertThat(greenLights, everyItem(is(Color.GREEN)));
  }


}
