import encoder.WordEncoder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WordEncoderTest {

    private WordEncoder wordEncoder;
    @Before
    public void init() {
        wordEncoder = new WordEncoder();
    }

    @Test
    public void whenConsonantThenAddAyEnd() {
        String str = "Hello";
        String res = wordEncoder.encode(str);
        Assert.assertEquals("Ellohay", res);
    }

    @Test
    public void whenVowelThenAddAyEnd() {
        String str = "applE";
        String res = wordEncoder.encode(str);
        Assert.assertEquals("applEway", res);
    }

    @Test
    public void whenEndWithWayThenNoChange() {
        String str = "AppLeway";
        String res = wordEncoder.encode(str);
        Assert.assertEquals("AppLeway", res);
    }

    @Test
    public void whenEmptyWordThenNoChange() {
        String str = "";
        String res = wordEncoder.encode(str);
        Assert.assertEquals("", res);
    }

    @Test
    public void whenSpaceThenNoChange() {
        String str = " ";
        String res = wordEncoder.encode(str);
        Assert.assertEquals(" ", res);
    }

    @Test
    public void whenNullThenEmpty() {
        String res = wordEncoder.encode(null);
        Assert.assertEquals("", res);
    }

    @Test
    public void whenCapitalizedThenMaintainCapitalization() {
        String str = "Beach";
        String str1 = "McCloud";
        String res = wordEncoder.encode(str);
        String res1 = wordEncoder.encode(str1);
        Assert.assertEquals("Eachbay", res);
        Assert.assertEquals("CcLoudmay", res1);
    }

    @Test
    public void whenHyphensThenEncodeDistinct() {
        String str = "this-thing";
        String res = wordEncoder.encode(str);
        Assert.assertEquals("histay-hingtay", res);
    }

    @Test
    public void whenPuntThenEncodeDistinct() {
        String str = "Can'T.";
        String res = wordEncoder.encode(str);
        Assert.assertEquals("AntCa'y.", res);
    }
}
