package com.example.genguo.myapplication.util;

import android.content.Context;

import com.example.genguo.myapplication.R;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by genguo on 2/28/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ClassUnderTestTest {
    private static final String APP_NAME = "My Application";

    @Mock
    Context mockContext;

    @Test
    public void checkAppName(){
        when(mockContext.getString(R.string.app_name)).thenReturn(APP_NAME);
        ClassUnderTest classUnderTest = new ClassUnderTest(mockContext);
        String result = classUnderTest.getAppName();
        assertThat(result, is(APP_NAME));
    }
}
