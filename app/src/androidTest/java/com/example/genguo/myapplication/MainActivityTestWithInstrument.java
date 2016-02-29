package com.example.genguo.myapplication;

import android.support.test.InstrumentationRegistry;
import android.test.ActivityInstrumentationTestCase2;

import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by genguo on 2/29/16.
 */
public class MainActivityTestWithInstrument extends ActivityInstrumentationTestCase2<MainActivity>{
    public MainActivityTestWithInstrument(){
        super(MainActivity.class);
    }

    private String mValidString;

    //the name must be setUp
    @Before
    public void setUp() throws Exception{
        super.setUp();
        mValidString = "Kingo";
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        getActivity();
    }

    //must start with test
    @Test
    public void testInputText(){
        onView(withId(R.id.editText)).perform(typeText(mValidString),closeSoftKeyboard());
        onView(withId(R.id.editText)).check(matches(withText(mValidString)));
    }
}
