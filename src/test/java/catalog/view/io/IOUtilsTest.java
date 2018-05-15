package catalog.view.io;

import org.junit.Test;

import static org.junit.Assert.*;

public class IOUtilsTest {

    @Test
    public void checkEnterDateWithRegExp() {
        assertTrue(IOUtils.checkEnterDateWithRegExp("12/12/1992"));
        assertFalse(IOUtils.checkEnterDateWithRegExp(""));
        assertTrue(IOUtils.checkEnterDateWithRegExp("12-12-1999"));
        assertFalse(IOUtils.checkEnterDateWithRegExp("23/23/1999"));
    }

}