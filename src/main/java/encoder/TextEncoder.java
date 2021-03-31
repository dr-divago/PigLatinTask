package encoder;

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TextEncoder {

    private static final String MAINTAIN_SPACE = "((?<=\\s)|(?=\\s))";
    private static final Pattern SPLIT_MAINTAIN_SPACE = Pattern.compile(MAINTAIN_SPACE);

    public String encodeText(final String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }

        final String[] words = SPLIT_MAINTAIN_SPACE.split(text);

        return Stream.of(words)
                .map(this::encode)
                .collect(Collectors.joining());
    }

    private String encode(String word) {
        WordEncoder wordEncoder = new WordEncoder(); //one encoder.WordEncoder for every word to parallelization
        return wordEncoder.encode(word);
    }
}
