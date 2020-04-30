package com.axionteq.newsapp.category;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.axionteq.newsapp.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class CategoryTest {

    private static final int last_item = 10;


    @Rule
    public ActivityScenarioRule<CategoryActivity> activityActivityScenarioRule = new ActivityScenarioRule<>( CategoryActivity.class );


    @Test
    public void checking() {
        onView( ViewMatchers.withId( R.id.gv_ac ) )
                .perform();

    }


    @Test
    public void lastItem() {
        onView( withText( last_item ) ).check( doesNotExist() );
    }


    @Test
    public void layoutClick() {

        //this works for only one view(LinearLayout)
        onView( ViewMatchers.withId( R.id.ll_gc ) ).perform( click() );
    }


    @Test
    public void layoutDisplayed() {
        ViewInteraction relativeLayout = onView(
                allOf( withId( R.id.ll_gc ),
                        childAtPosition(
                                allOf( withId( R.id.cv_gc ),
                                        childAtPosition(
                                                withId( R.id.gv_ac ),
                                                0 ) ),
                                0 ),
                        isDisplayed() ) );
        relativeLayout.check( matches( isDisplayed() ) );


        ViewInteraction frameLayout = onView(
                allOf( IsInstanceOf.instanceOf( android.widget.FrameLayout.class ), isDisplayed() ) );
        frameLayout.check( matches( isDisplayed() ) );
    }


    @Test
    public void textCheck() {

        ViewInteraction textView = onView(
                allOf( withId( R.id.tv_gc ), withText( "Lifestyle" ),
                        childAtPosition(
                                allOf( withId( R.id.ll_gc ),
                                        childAtPosition(
                                                withId( R.id.cv_gc ),
                                                0 ) ),
                                1 ),
                        isDisplayed() ) );
        textView.check( matches( withText( "Lifestyle" ) ) );

//        onRow( TEXT_ITEM_30 ).atPosition( 8 ).onChildView( withId( R.id.tv_gc )).check( matches( withText( "Sports" ) ) );
        //this works for only one view(TextView)
//        onView( ViewMatchers.withId( R.id.tv_gc )).check( matches( withText( "News" ) ) );
    }

    private static DataInteraction onRow(String str) {
        return onData( hasEntry( equalTo( CategoryActivity.ROW_TEXT ), is( str ) ) );
    }


    @Test
    public void adapter() {
        onData( instanceOf( CategoryAdapter.class ) )
                .atPosition( 0 )
                .perform( click() );
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
