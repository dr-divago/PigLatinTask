import encoder.TextEncoder;
import org.junit.Assert;
import org.junit.Test;

public class TextEncoderTest {

    @Test
    public void whenTextThenEncodeEveryDistinctWord() {
        TextEncoder textEncoder = new TextEncoder();
        String str = "Hello world";
        String res = textEncoder.encodeText(str);
        Assert.assertEquals("Ellohay orldway", res);
    }

    @Test
    public void whenDoubleSpaceThenMantainSpaces() {
        TextEncoder textEncoder = new TextEncoder();
        String str = "Hello  world";
        String res = textEncoder.encodeText(str);
        Assert.assertEquals("Ellohay  orldway", res);
    }

    @Test
    public void whenEmptyThenEmpty() {
        TextEncoder textEncoder = new TextEncoder();
        String str = "";
        String res = textEncoder.encodeText(str);
        Assert.assertEquals("", res);
    }

    @Test
    public void whenSpaceThenSpace() {
        TextEncoder textEncoder = new TextEncoder();
        String str = " ";
        String res = textEncoder.encodeText(str);
        Assert.assertEquals(" ", res);
    }

    @Test
    public void whenNullThenEmpty() {
        TextEncoder textEncoder = new TextEncoder();
        String res = textEncoder.encodeText(null);
        Assert.assertEquals("", res);
    }
}