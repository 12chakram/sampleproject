package com.eng.gp.project.ext;

import com.eng.gp.project.util.date.IntArrayType;
import com.eng.gp.project.util.date.IntegerArrayType;



/**
 * This object just sets the serialization style for the various SQL Array types so that HSQLDB will work...
 * when setting to test mode they are serialized as BLOBS, otherwise they use the native SQL ARRAY type.
 */
public class HsqlTestInitializer {

    public static class TestModeState {
        public final boolean doubleArrayTypeTestMode;
        public final boolean intArrayTypeTestMode;
        public final boolean integerArrayTypeTestMode;
        public final boolean specialDoubleArrayTypeTestMode;

        public TestModeState(boolean doubleArrayTypeTestMode, boolean intArrayTypeTestMode, boolean integerArrayTypeTestMode, boolean specialDoubleArrayTypeTestMode){
            this.doubleArrayTypeTestMode = doubleArrayTypeTestMode;
            this.intArrayTypeTestMode = intArrayTypeTestMode;
            this.integerArrayTypeTestMode = integerArrayTypeTestMode;
            this.specialDoubleArrayTypeTestMode = specialDoubleArrayTypeTestMode;
        }
    }

    /**
     * Sets the testMode status of all Types to {code}testMode{code}.
     * @param testMode
     * @return TestModeState representing the original state before states were changed.  This can be used to restore state by calling setState(TestModeState)
     */
    public static TestModeState setState(boolean testMode){
        TestModeState result = new TestModeState(DoubleArrayType.isTestMode(), IntArrayType.isTestMode(), IntegerArrayType.isTestMode(), SpecialDoubleArrayType.isTestMode());
        DoubleArrayType.setIsTestMode( testMode );
        IntArrayType.setIsTestMode( testMode );
        IntegerArrayType.setIsTestMode( testMode );
        SpecialDoubleArrayType.setIsTestMode( testMode );
        return result;
    }

    public static void setState(TestModeState testModeState){
        DoubleArrayType.setIsTestMode(testModeState.doubleArrayTypeTestMode);
        IntArrayType.setIsTestMode( testModeState.intArrayTypeTestMode );
        IntegerArrayType.setIsTestMode( testModeState.integerArrayTypeTestMode );
        SpecialDoubleArrayType.setIsTestMode( testModeState.specialDoubleArrayTypeTestMode );
    }
}
