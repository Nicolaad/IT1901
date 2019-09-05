package ui;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FxAppControllerTest {
    private FxAppController fxC;
    @Before
    public void init() throws Exception{
        fxC = new FxAppController();
    }

    @Test
    public void getUtgifterText() {
        assertEquals(fxC.getUtgifterText(),"hei");
    }

}