package com.example.myfirstapp;

import android.support.test.runner.AndroidJUnit4;

import android.support.

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Trying to test device level events with respect to our activities.
 * -Activity interrupted by another another app
 * -System destroys / recreates our Acvitivy
 * -User paces Activity into multi-window or (Picture-In-Picture) mode
 */

@RunWith(AndroidJUnit4.class)
public class ActivitiesTests {
    @Test
    public void testEvent() {
        //val scenario = launchActivity<AllNotesActivity>();
    }
}
