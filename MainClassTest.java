import org.junit.Assert;
import org.junit.Test;

public class MainClassTest
{
    MainClass MC = new MainClass();

    @Test
    public void testGetLocalNumber ()
    {
        int a = MC.getLocalNumber();
        Assert.assertTrue("LocalNumber less or equal than 45", a > 45);
    }
}
