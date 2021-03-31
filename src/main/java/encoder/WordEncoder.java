package encoder;

import util.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WordEncoder {

    private Function<Character, Function<Character, Character>> capitalize = x -> y -> Character.isUpperCase(x)
                    ? Character.toUpperCase(y)
                    : Character.toLowerCase(y);

    public String encode(String text) {
        if (text == null || text.isEmpty() ) {
            return "";
        }

        if (text.equals(" "))
            return " ";

        final String[] words = text.split("-");

        return Arrays.stream(words)
                .map(this::encodeWord)
                .collect(Collectors.joining("-"));
    }

    public String encodeWord(String word) {

        if (word.endsWith("way"))
            return word;

        String wordWithoutPunctuation = Utils.removePunctuation(word);

        Function<String, String> mapWordFunc = isVowel(wordWithoutPunctuation.charAt(0))
                ? transformWhenVowel()
                : transformWhenConsonant();

        //Capitalize elloHay -> Ellohay
        String capitalizedWord = capitalize( wordWithoutPunctuation, mapWordFunc);
        return Utils.addPunctuation(word, capitalizedWord);
    }


    private Function<String, String> transformWhenVowel() {
        return s -> s.concat("way");
    }

    /**
     * Transform and append Suffix
     * Hello -> elloHay
     * @return
     */
    private Function<String, String> transformWhenConsonant() {
        Function<String, String> transform = this::transform;
        Function<String, String> appendSuffix = this::appendSuffix;

        return transform.andThen(appendSuffix);
    }

    /**
     * From word "abc" -> "bca"
     * @param word
     * @return
     */
    private String transform(final String word) {
        return new StringBuilder(word.substring(1)).append(word.charAt(0)).toString();
    }

    /**
     * Add suffix "ay"
     * @param original
     * @return
     */
    private String appendSuffix(final String original) {
        return new StringBuilder(original).append("ay").toString();
    }

    /**
     * Build a mapper for capitalization.
     * "HeLlo" -> ULULL where U rappresent an action to capitalize the letter and L an action of uncapitalize
     * It uses the concept of currying from functional programming
     * @param originalWord
     * @return
     */
    private List<Function<Character, Character>> buildMapper(final String originalWord) {
        return originalWord
                .chars()
                .mapToObj(ch -> capitalize.apply((char)ch))
                .collect(Collectors.toList());
    }

    /**
     * Capitalize a word according to the capitazation of the original word
     * original word first is transformed with transformFunc
     * originalWord -> apply(transformFunc) -> capitalized
     * @param originalWord
     * @return
     */
    private String capitalize(String originalWord, final Function<String, String> transformFunc) {

        String transformedWord = transformFunc.apply(originalWord);
        List<Function<Character, Character>> mapper = buildMapper(originalWord); //save state of capitalization of original word

        return IntStream
                .range(0, transformedWord.length())
                .mapToObj(i -> i < mapper.size()
                        ? mapper.get(i).apply(transformedWord.charAt(i))
                        : transformedWord.charAt(i))
                .map(Objects::toString)
                .collect(Collectors.joining());
    }



    private boolean isVowel(char c) {
        switch(c) {
            case 'a':
            case 'A':
            case 'e':
            case 'E':
            case 'i':
            case 'I':
            case 'o':
            case 'O':
            case 'u':
            case 'U':
                return true;
            default:
                return false;
        }
    }
}
