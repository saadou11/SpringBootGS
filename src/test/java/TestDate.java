import controllers.HelloController;
import junit.framework.TestCase;


public class TestDate extends TestCase {
		
	public void testDate(){
		assertEquals("2018/01/16", HelloController.getToday());
	}
}
