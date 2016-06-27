package com.example.humberto.audioapplication;

/**
 * Created by Humberto on 23/06/2016.
 */
import android.test.ActivityInstrumentationTestCase2;

import com.robotium.solo.Solo;

public class ExampleTest extends ActivityInstrumentationTestCase2<AudioActivity> {

    private Solo solo;

    public ExampleTest(){
        super(AudioActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    public void test_AudioActivity() throws  Exception {
        solo.unlockScreen();

    }

    public void testEquals() {
        assertEquals(1, 1);
    }

    public void testBoolean() throws Exception {
        assertTrue(true);
    }
}
