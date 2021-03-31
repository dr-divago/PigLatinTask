package util;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {
    private static final String PUNCTUATION_CHAR = ".?!,;:–—―(){}[]\"'’";
    private static final Set<Character> PUNCTUATIONS = initCharacters(PUNCTUATION_CHAR);

    public static boolean isPunctuation(char ch) {
        return PUNCTUATIONS.contains(ch);
    }

    private static Set<Character> initCharacters(final String characters) {
        return characters
                .chars()
                .mapToObj(i -> (char)i)
                .collect(Collectors.toSet());
    }

    public static String removePunctuation(final String word) {
        return word
                .chars()
                .mapToObj(i -> (char)i)
                .filter(ch -> !isPunctuation(ch))
                .map(Objects::toString)
                .collect(Collectors.joining());
    }

    public static String addPunctuation(final String originalWord, final String wordToTransform) {

        StringBuilder sb = new StringBuilder(wordToTransform);

        final int length = originalWord.length();
        for (int i = 0; i < length; i++) {
            char ch = originalWord.charAt(length - 1 - i);
            if (isPunctuation(ch)) {
                insertFromTheEnd(sb, ch, i);
            }
        }

        return sb.toString();
    }

    private static void insertFromTheEnd(final StringBuilder stringBuilder,
                                         final char ch,
                                         final int position) {
        final int length = stringBuilder.length();
        stringBuilder.insert(length - position, ch);
    }
}
