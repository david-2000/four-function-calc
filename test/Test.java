import org.junit.Test;
import static org.junit.Assert.assertEquals;

public abstract class TestJunit {
	

	
	@Test
	public void testPrintMessage() {
		assertEquals(message,messageUtil.printMessage());
	}
}
