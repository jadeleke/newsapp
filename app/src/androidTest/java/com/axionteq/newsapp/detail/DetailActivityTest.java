package com.axionteq.newsapp.detail;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.axionteq.newsapp.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.web.assertion.WebViewAssertions.webMatches;
import static androidx.test.espresso.web.model.Atoms.getCurrentUrl;
import static androidx.test.espresso.web.sugar.Web.onWebView;
import static androidx.test.espresso.web.webdriver.DriverAtoms.webClick;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    private String getCurrentUrl = "https://web.whatsapp.com";

    private ActivityScenarioRule<DetailActivity> rule = new ActivityScenarioRule<>( DetailActivity.class );

    @Before
    public void TestSetup() {
    }

    @Test
    public void webViewTest() throws ExceptionInInitializerError {1
        ActivityScenario<DetailActivity> activityActivityScenario = rule.getScenario();

        onWebView( ViewMatchers.withId( R.id.wv ) ).forceJavascriptEnabled();

        onWebView()
                .perform( webClick() )
                .check( webMatches( getCurrentUrl(), containsString( "https://web.whatsapp.com" ) ) );


        ViewInteraction webView3 = onView(
                allOf( childAtPosition(
                        childAtPosition(
                                withId( android.R.id.content ),
                                0 ),
                        2 ),
                        isDisplayed() ) );
        webView3.check( doesNotExist() );

        onView( withId( R.id.pb) ).check( matches( isDisplayed() ) );
    }

    @Test
    private static Intent intent() {
        Intent intent = new Intent();
        intent.putExtra( DetailActivity.key_url, DetailActivity.strurl );
        return intent;
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText( "Child at position " + position + " in parent " );
                parentMatcher.describeTo( description );
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches( parent )
                        && view.equals( ((ViewGroup) parent).getChildAt( position ) );
            }
        };
    }

    @Test
    public void testProgressTask() {
        new DetailActivity.ProgressTask().execute();
    }

}
