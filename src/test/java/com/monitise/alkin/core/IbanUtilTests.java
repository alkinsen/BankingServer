package com.monitise.alkin.core;

import static org.junit.Assert.*;

import org.junit.Test;

public class IbanUtilTests {

    //correctIban = GR1601101250000000012300695

    IbanUtil ibanUtil = new IbanUtil();

    @Test
    public void testInvalidLength(){

        assertFalse(IbanUtil.isIbanValid("GR1601101250000000012300695123123123123123"));
        assertFalse(IbanUtil.isIbanValid("GR160110"));
        assertFalse(IbanUtil.isIbanValid(""));
        assertFalse(IbanUtil.isIbanValid(" "));
        assertFalse(IbanUtil.isIbanValid("                                      "));
        assertFalse(IbanUtil.isIbanValid(null));

    }

    @Test
    public void testInvalidCharactersAtStart(){

        assertFalse(IbanUtil.isIbanValid("111601101250000000012300695"));
        assertFalse(IbanUtil.isIbanValid("G11601101250000000012300695"));
        assertFalse(IbanUtil.isIbanValid("g11601101250000000012300695"));
        assertFalse(IbanUtil.isIbanValid("1R1601101250000000012300695"));
        assertFalse(IbanUtil.isIbanValid("1r1601101250000000012300695"));
        assertFalse(IbanUtil.isIbanValid("gr1601101250000000012300695"));

    }

    @Test
    public void testInvalidCharacters(){

        assertFalse(IbanUtil.isIbanValid("GR160110a250000000012300695"));
        assertFalse(IbanUtil.isIbanValid("GR160110125000000S012300695"));
        assertFalse(IbanUtil.isIbanValid("GR160110125000000001230069A"));
        assertFalse(IbanUtil.isIbanValid("GR16011012500.0000012300695"));
        assertFalse(IbanUtil.isIbanValid("GR16011012500^0000012300695"));
        assertFalse(IbanUtil.isIbanValid("GR1601101250 00000012300695"));
        assertFalse(IbanUtil.isIbanValid("GR160110125000000001230069 "));
        assertFalse(IbanUtil.isIbanValid("GR160110125000000001230069 "));
        assertFalse(IbanUtil.isIbanValid(" R1601101250000000012300695"));
    }

    @Test
    public void testIncorrectIban(){

        assertFalse(IbanUtil.isIbanValid("GR1601101250000600012301695"));
        assertFalse(IbanUtil.isIbanValid("GR1601101250000500012301695"));
        assertFalse(IbanUtil.isIbanValid("GR1601101250000400012301695"));
        assertFalse(IbanUtil.isIbanValid("GR1601101250000300012301695"));
        assertFalse(IbanUtil.isIbanValid("GR1601101250000200012301695"));
        assertFalse(IbanUtil.isIbanValid("GR1601101250000100012301695"));
        assertFalse(IbanUtil.isIbanValid("GR1601101250000700012301695"));

    }

    @Test
    public void testCorrectIban(){

        assertTrue(IbanUtil.isIbanValid("GR1601101250000000012300695"));
        assertTrue(IbanUtil.isIbanValid("MR1300020001010000123456753"));

    }
}
