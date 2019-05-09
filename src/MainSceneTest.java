//import static junit.framework.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//class MainSceneTest {
//	static MainScene mainscene;
//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//		mainscene = new MainScene();
//	}
//
//	@AfterAll
//	static void tearDownAfterClass() throws Exception {
//	}
//
//	@BeforeEach
//	void setUp() throws Exception {
//	}
//
//	@AfterEach
//	void tearDown() throws Exception {
//	}
//
//	@Test
//	void testInputValidate() {
//		String lon = "43.65776767";
//		String lat ="-4.7668";
//		assertEquals(true, mainscene.inputValidate(lon, lat));
//		assertEquals(false, mainscene.inputValidate("", lat));
//		assertEquals(false, mainscene.inputValidate(lon, ""));
//		assertEquals(false, mainscene.inputValidate("sdfs", "Sfesf"));
//		assertEquals(false, mainscene.inputValidate("546.34", lat));
//		assertEquals(false, mainscene.inputValidate(lon, "sdfkjhsf"));
//		assertEquals(false, mainscene.inputValidate(lon, "-108.45"));
//	}
//
//
//	@Test
//	void testIsDouble() {
//		String lon = "53.6565";
//		assertEquals(true, mainscene.isDouble(lon));
//		assertEquals(false, mainscene.isDouble("rgdrgr"));
//		
//	}
//
//}
