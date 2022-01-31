import org.junit.Assert;
import org.junit.Test;
import sun.font.TrueTypeFont;

public class MainClassTest
{
    MainClass MC = new MainClass();

    @Test
    public void testGetLocalNumber ()
    {
        String a = MC.getLocalString();
        Assert.assertTrue("Local String doesn't contain substring 'Hello' or 'hello'", a.toLowerCase().contains("hello"));
    }
}
