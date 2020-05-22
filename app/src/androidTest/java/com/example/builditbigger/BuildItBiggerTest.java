package com.example.builditbigger;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.idling.CountingIdlingResource;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class BuildItBiggerTest {

    private CountingIdlingResource idlingResource;

    @Rule
    public final ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource() {
        final BuildItBiggerApplication application =
                ((BuildItBiggerApplication) activityTestRule.getActivity().getApplication());
        idlingResource = application.getAppRepository().getCountingIdlingResource();

        IdlingRegistry.getInstance().register(idlingResource);
    }

    @Test
    public void verify_button_is_enabled_on_main_screen() {
        onView(withId(R.id.tell_joke_button)).check(matches(isEnabled()));
    }

    @Test
    public void perform_click_on_tell_joke_button() {
        onView(withId(R.id.tell_joke_button)).perform(click());
    }

    @Test
    public void verify_joke_text_view_is_displayed_in_new_activity_and_is_not_empty_when_engine_is_running() {
        onView(withId(R.id.tell_joke_button)).perform(click());

        onView(withId(R.id.joke_text)).check(matches(isDisplayed()));

        onView(withId(R.id.joke_text)).check(matches(not(withText(""))));
    }


    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            IdlingRegistry.getInstance().unregister(idlingResource);
        }
    }
}