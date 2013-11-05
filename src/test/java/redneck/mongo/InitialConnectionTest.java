package redneck.mongo;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InitialConnectionTest {

	InitialConnection conn;

	@Before
	public void setUp() throws Exception {
		conn = new InitialConnection();
	}

	@After
	public void tearDown() throws Exception {
		conn = null;
	}

	@Test
	public void testRunMethodShouldCompleteWithoutException() throws UnknownHostException {
		conn.run();
	}
}
