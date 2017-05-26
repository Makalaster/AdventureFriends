package com.makalaster.adventurefriends;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class PlayerTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void playerTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.twitter_button), withText("Sign in with Twitter"),
                        withParent(withId(R.id.btn_holder)),
                        isDisplayed()));
        appCompatButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3586948);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction button = onView(
                allOf(withId(R.id.tw__allow_btn), withText("Allow")));
        button.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3598686);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.new_campaign_fab), isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.existing_campaign_edit_text), isDisplayed()));
        appCompatEditText.perform(longClick());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton2 = onView(
                allOf(withText("Paste"), withContentDescription("Paste"), isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.existing_campaign_edit_text), isDisplayed()));
        appCompatEditText2.perform(replaceText("-Kl0zwd8dEMOrZ9ldM5r"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button1), withText("GO")));
        appCompatButton3.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3584928);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction editText = onView(
                allOf(withId(R.id.character_name_edit_text), withText("Name"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText.check(matches(withText("Name")));

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.text1), withText("Tank +1 body"),
                        childAtPosition(
                                allOf(withId(R.id.job_spinner),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                1)),
                                0),
                        isDisplayed()));
        textView.check(matches(isDisplayed()));

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.character_name_edit_text), isDisplayed()));
        appCompatEditText3.perform(replaceText("Costco"), closeSoftKeyboard());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.size_spinner), isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(android.R.id.text1), withText("Regular +1 mind"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.job_spinner), isDisplayed()));
        appCompatSpinner2.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(android.R.id.text1), withText("Doctor +1 mind"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.create_character_button), withText("create"), isDisplayed()));
        appCompatButton4.perform(click());

        pressBack();

        ViewInteraction textView2 = onView(
                allOf(withText("STATS"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("STATS")));

        ViewInteraction textView3 = onView(
                allOf(withText("ABILITIES"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        textView3.check(matches(withText("ABILITIES")));

        ViewInteraction textView4 = onView(
                allOf(withText("EQUIPMENT"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        2),
                                0),
                        isDisplayed()));
        textView4.check(matches(withText("EQUIPMENT")));

        ViewInteraction textView5 = onView(
                allOf(withText("INVENTORY"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        3),
                                0),
                        isDisplayed()));
        textView5.check(matches(withText("INVENTORY")));

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.character_name), withText(" Costco"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        textView6.check(matches(withText(" Costco")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.character_size), withText(" Regular"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        textView7.check(matches(withText(" Regular")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.character_job), withText(" Doctor"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                        2),
                                1),
                        isDisplayed()));
        textView8.check(matches(withText(" Doctor")));

        ViewInteraction textView9 = onView(
                allOf(withText("Health:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        textView9.check(matches(withText("Health:")));

        ViewInteraction textView10 = onView(
                allOf(withId(R.id.current_pg), withText(" 11"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                1),
                        isDisplayed()));
        textView10.check(matches(withText(" 11")));

        ViewInteraction textView11 = onView(
                allOf(withId(R.id.max_pg), withText("11 "),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                3),
                        isDisplayed()));
        textView11.check(matches(withText("11 ")));

        ViewInteraction textView12 = onView(
                allOf(withId(R.id.mind_label), withText("Mind:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        3),
                                4),
                        isDisplayed()));
        textView12.check(matches(withText("Mind:")));

        ViewInteraction textView13 = onView(
                allOf(withId(R.id.mind_value), withText(" 2"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        3),
                                5),
                        isDisplayed()));
        textView13.check(matches(withText(" 2")));

        ViewInteraction textView14 = onView(
                allOf(withId(R.id.speed_label), withText("Speed:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        3),
                                2),
                        isDisplayed()));
        textView14.check(matches(withText("Speed:")));

        ViewInteraction textView15 = onView(
                allOf(withId(R.id.speed_value), withText(" 4"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        3),
                                3),
                        isDisplayed()));
        textView15.check(matches(withText(" 4")));

        ViewInteraction textView16 = onView(
                allOf(withId(R.id.ability_name), withText("Scratch"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView16.check(matches(withText("Scratch")));

        ViewInteraction textView17 = onView(
                allOf(withId(R.id.ability_name), withText("Diagnose"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView17.check(matches(withText("Diagnose")));

        ViewInteraction textView18 = onView(
                allOf(withId(R.id.weapon_name_and_description), withText("Grass Bow, Flimsy bow made of grass"),
                        childAtPosition(
                                withParent(withId(R.id.player_view_pager)),
                                11),
                        isDisplayed()));
        textView18.check(matches(withText("Grass Bow, Flimsy bow made of grass")));

        ViewInteraction textView19 = onView(
                allOf(withId(R.id.item_name), withText("Fungus"),
                        childAtPosition(
                                allOf(withId(R.id.inventory_item),
                                        childAtPosition(
                                                withId(R.id.inventory_recycler),
                                                3)),
                                0),
                        isDisplayed()));
        textView19.check(matches(withText("Fungus")));

        ViewInteraction textView20 = onView(
                allOf(withId(R.id.item_name), withText("Adhesive Bandage"),
                        childAtPosition(
                                allOf(withId(R.id.inventory_item),
                                        childAtPosition(
                                                withId(R.id.inventory_recycler),
                                                0)),
                                0),
                        isDisplayed()));
        textView20.check(matches(withText("Adhesive Bandage")));

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.new_note_fab),
                        childAtPosition(
                                withParent(withId(R.id.player_view_pager)),
                                1),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction view = onView(
                allOf(withId(R.id.player_map),
                        childAtPosition(
                                withParent(withId(R.id.player_view_pager)),
                                0),
                        isDisplayed()));
        view.check(matches(isDisplayed()));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
