package com.axionteq.newsapp.detail;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import com.axionteq.newsapp.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    @Rule
    public ActivityScenarioRule<DetailActivity> mActivityTestRule = new ActivityScenarioRule<>( DetailActivity.class );

    @Test
    public void detailActivityTest() {
        ViewInteraction webView = onView(
                allOf( childAtPosition(
                        childAtPosition(
                                withId( android.R.id.content ),
                                0 ),
                        2 ),
                        isDisplayed() ) );
        webView.check( matches( isDisplayed() ) );

        ViewInteraction webView2 = onView(
                allOf( childAtPosition(
                        childAtPosition(
                                withId( android.R.id.content ),
                                0 ),
                        2 ),
                        isDisplayed() ) );
        webView2.check( matches( isDisplayed() ) );

        ViewInteraction webView3 = onView(
                allOf( childAtPosition(
                        childAtPosition(
                                withId( android.R.id.content ),
                                0 ),
                        2 ),
                        isDisplayed() ) );
        webView3.check( doesNotExist() );

        ViewInteraction view = onView(
                allOf( withId( android.R.id.statusBarBackground ),
                        childAtPosition(
                                IsInstanceOf.<View>instanceOf( android.widget.FrameLayout.class ),
                                1 ),
                        isDisplayed() ) );
        view.check( matches( isDisplayed() ) );

        ViewInteraction view2 = onView(
                allOf( withId( android.R.id.statusBarBackground ),
                        childAtPosition(
                                IsInstanceOf.<View>instanceOf( android.widget.FrameLayout.class ),
                                1 ),
                        isDisplayed() ) );
        view2.check( doesNotExist() );

        ViewInteraction imageView = onView(
                allOf( withId( R.id.iv_tb_ad ),
                        childAtPosition(
                                childAtPosition(
                                        withId( R.id.tb_ad ),
                                        0 ),
                                0 ),
                        isDisplayed() ) );
        imageView.check( matches( isDisplayed() ) );

        ViewInteraction imageView2 = onView(
                allOf( withId( R.id.iv_tb_ad ),
                        childAtPosition(
                                childAtPosition(
                                        withId( R.id.tb_ad ),
                                        0 ),
                                0 ),
                        isDisplayed() ) );
        imageView2.check( doesNotExist() );

        ViewInteraction linearLayout = onView(
                allOf( withId( R.id.tb_ad ),
                        childAtPosition(
                                childAtPosition(
                                        withId( android.R.id.content ),
                                        0 ),
                                0 ),
                        isDisplayed() ) );
        linearLayout.check( matches( isDisplayed() ) );

        ViewInteraction linearLayout2 = onView(
                allOf( withId( R.id.tb_ad ),
                        childAtPosition(
                                childAtPosition(
                                        withId( android.R.id.content ),
                                        0 ),
                                0 ),
                        isDisplayed() ) );
        linearLayout2.check( doesNotExist() );

        ViewInteraction progressBar = onView(
                allOf( withId( R.id.pb ),
                        childAtPosition(
                                childAtPosition(
                                        withId( android.R.id.content ),
                                        0 ),
                                1 ),
                        isDisplayed() ) );
        progressBar.check( matches( isDisplayed() ) );

        ViewInteraction progressBar2 = onView(
                allOf( withId( R.id.pb ),
                        childAtPosition(
                                childAtPosition(
                                        withId( android.R.id.content ),
                                        0 ),
                                1 ),
                        isDisplayed() ) );
        progressBar2.check( doesNotExist() );

        ViewInteraction linearLayout3 = onView(
                allOf( childAtPosition(
                        IsInstanceOf.instanceOf( android.widget.FrameLayout.class ),
                        0 ),
                        isDisplayed() ) );
        linearLayout3.check( matches( isDisplayed() ) );

        ViewInteraction linearLayout4 = onView(
                allOf( childAtPosition(
                        IsInstanceOf.<View>instanceOf( android.widget.FrameLayout.class ),
                        0 ),
                        isDisplayed() ) );
        linearLayout4.check( doesNotExist() );

        ViewInteraction linearLayout5 = onView(
                allOf( withId( R.id.action_bar_root ),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf( android.widget.LinearLayout.class ),
                                        0 ),
                                0 ),
                        isDisplayed() ) );
        linearLayout5.check( matches( isDisplayed() ) );

        ViewInteraction linearLayout6 = onView(
                allOf( withId( R.id.action_bar_root ),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf( android.widget.LinearLayout.class ),
                                        0 ),
                                0 ),
                        isDisplayed() ) );
        linearLayout6.check( doesNotExist() );
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
}
