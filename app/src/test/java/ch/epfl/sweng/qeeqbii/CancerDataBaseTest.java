package ch.epfl.sweng.qeeqbii;


import org.junit.BeforeClass;
import org.junit.Test;

public class CancerDataBaseTest {

    CancerDataBase cancer_db_tester;

    @BeforeClass
    public static void testSetup() {
        cancer_db_tester = new CancerDataBase();
    }
}















//@RunWith(AndroidJUnit4.class)

/* It is important to note here that we tested the behaviour of an activity. This is not an easy
 * since an activity is "refreshed" constantly by the handler loop of the application. It was neces-
 * sary to implement the android.os folder that contains code that is making sur that once the
 * background talks (here uploading a .csv file, has been done and is now available for testing.
 * Then we had to extend the class with ActivityInstrumentationTestCase2<CancerDataShowActivity>,
 * create an instance of the class that we want to test (CancerDataShowActivity) and to call its
 * contructor in the constructure of this class. Furthermore, the setUp method is also necessery
 * for this tsting manner. It is important to understand that this is now a basic JUnit test
 * trsting a class that run in backgroud but a class that is making the link between back end and
 * front end.
 */


/*

public class AReadCSVClassShould extends ActivityInstrumentationTestCase2<CancerDataShowActivity>
{
    private CancerDataShowActivity cancerData;

    public AReadCSVClassShould() {
        super(CancerDataShowActivity.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        cancerData = getActivity();
    }

    @Test
    public void TestReadInAFile() {
        List<String> expected = Arrays.asList("Formaldehyde","1","Phenobarbital", "2B", "Mitomycin C","2B");
        cancerData.setmIsTest(true);
        cancerData.readCSVFile();
        List<String> data = cancerData.getImportedDataString();
        assertEquals(expected, data);
    }
}


*/