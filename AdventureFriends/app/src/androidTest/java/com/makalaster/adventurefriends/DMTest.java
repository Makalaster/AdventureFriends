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
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DMTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void dMTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction button = onView(
                allOf(withId(R.id.twitter_button),
                        childAtPosition(
                                allOf(withId(R.id.btn_holder),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        button.check(matches(isDisplayed()));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.facebook_button),
                        childAtPosition(
                                allOf(withId(R.id.btn_holder),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                1),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

        ViewInteraction button3 = onView(
                allOf(withId(R.id.google_button),
                        childAtPosition(
                                allOf(withId(R.id.btn_holder),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                2),
                        isDisplayed()));
        button3.check(matches(isDisplayed()));

        ViewInteraction button4 = onView(
                allOf(withId(R.id.email_provider),
                        childAtPosition(
                                allOf(withId(R.id.btn_holder),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                3),
                        isDisplayed()));
        button4.check(matches(isDisplayed()));

        ViewInteraction button5 = onView(
                allOf(withId(R.id.email_provider),
                        childAtPosition(
                                allOf(withId(R.id.btn_holder),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                0)),
                                3),
                        isDisplayed()));
        button5.check(matches(isDisplayed()));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.google_button), withText("Sign in with Google"),
                        withParent(withId(R.id.btn_holder)),
                        isDisplayed()));
        appCompatButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3559047);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView = onView(
                allOf(withId(com.google.android.gms.R.id.title), withText("Choose account for Adventure Friends"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.FrameLayout.class),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Choose account for Adventure Friends")));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3558901);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.new_campaign_fab),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lobby_fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        imageButton.check(matches(isDisplayed()));

        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.new_campaign_fab), isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.existing_campaign_edit_text), withText("Enter existing campaign ID"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText.check(matches(withText("Enter existing campaign ID")));

        ViewInteraction radioButton = onView(
                allOf(withId(R.id.create_campaign_radio_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.custom),
                                        0),
                                1),
                        isDisplayed()));
        radioButton.check(matches(isDisplayed()));

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
        appCompatEditText2.perform(replaceText("-Kkki_KchEVaB_3WrD6m"), closeSoftKeyboard());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button1), withText("GO")));
        appCompatButton3.perform(scrollTo(), click());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(android.R.id.button2), withText("Cancel")));
        appCompatButton4.perform(scrollTo(), click());

        ViewInteraction floatingActionButton2 = onView(
                allOf(withId(R.id.new_campaign_fab), isDisplayed()));
        floatingActionButton2.perform(click());

        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.create_campaign_radio_button), withText("Create New Campaign"), isDisplayed()));
        appCompatRadioButton.perform(click());

        ViewInteraction appCompatButton5 = onView(
                allOf(withId(android.R.id.button1), withText("GO")));
        appCompatButton5.perform(scrollTo(), click());

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.new_campaign_name_edit_text), withText("Title"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lobby_fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        editText2.check(matches(withText("Title")));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.new_campaign_description_edit_text), withText("Description"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lobby_fragment_container),
                                        0),
                                3),
                        isDisplayed()));
        editText3.check(matches(withText("Description")));

        ViewInteraction button6 = onView(
                allOf(withId(R.id.start_campaign_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lobby_fragment_container),
                                        0),
                                5),
                        isDisplayed()));
        button6.check(matches(isDisplayed()));

        ViewInteraction button7 = onView(
                allOf(withId(R.id.start_campaign_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.lobby_fragment_container),
                                        0),
                                5),
                        isDisplayed()));
        button7.check(matches(isDisplayed()));

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.new_campaign_name_edit_text), isDisplayed()));
        appCompatEditText3.perform(replaceText("Espresso"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.new_campaign_description_edit_text), isDisplayed()));
        appCompatEditText4.perform(replaceText("Espresso testing"), closeSoftKeyboard());

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.base_game_spinner), isDisplayed()));
        appCompatSpinner.perform(click());

        ViewInteraction appCompatTextView = onView(
                allOf(withId(android.R.id.text1), withText("Goblins? Goblins!"), isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.start_campaign_button), withText("start campaign"), isDisplayed()));
        appCompatButton6.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3420408);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.campaign_title), withText("Espresso"),
                        childAtPosition(
                                allOf(withId(R.id.campaign_details),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                                0)),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("Espresso")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.campaign_description), withText("Espresso testing"),
                        childAtPosition(
                                allOf(withId(R.id.campaign_details),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                                0)),
                                2),
                        isDisplayed()));
        textView3.check(matches(withText("Espresso testing")));

        ViewInteraction textView4 = onView(
                allOf(withId(R.id.campaign_base_game), withText("Goblins? Goblins!"),
                        childAtPosition(
                                allOf(withId(R.id.campaign_details),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class),
                                                0)),
                                3),
                        isDisplayed()));
        textView4.check(matches(withText("Goblins? Goblins!")));

        ViewInteraction imageButton2 = onView(
                allOf(withId(R.id.new_module_fab),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dm_fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        imageButton2.check(matches(isDisplayed()));

        ViewInteraction floatingActionButton3 = onView(
                allOf(withId(R.id.new_module_fab), isDisplayed()));
        floatingActionButton3.perform(click());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.new_module_title), withText("Title"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dm_fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        editText4.check(matches(withText("Title")));

        ViewInteraction editText5 = onView(
                allOf(withId(R.id.new_module_summary), withText("Summary"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dm_fragment_container),
                                        0),
                                3),
                        isDisplayed()));
        editText5.check(matches(withText("Summary")));

        ViewInteraction textView5 = onView(
                allOf(withId(android.R.id.text1), withText("Select a module type…"),
                        childAtPosition(
                                allOf(withId(R.id.new_module_type_spinner),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                4)),
                                0),
                        isDisplayed()));
        textView5.check(matches(withText("Select a module type…")));

        ViewInteraction button8 = onView(
                allOf(withId(R.id.create_new_module_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dm_fragment_container),
                                        0),
                                5),
                        isDisplayed()));
        button8.check(matches(isDisplayed()));

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.new_module_title), isDisplayed()));
        appCompatEditText5.perform(replaceText("Espresso Module"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.new_module_summary), isDisplayed()));
        appCompatEditText6.perform(replaceText("Java"), closeSoftKeyboard());

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.new_module_type_spinner), isDisplayed()));
        appCompatSpinner2.perform(click());

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(android.R.id.text1), withText("Battle"), isDisplayed()));
        appCompatTextView2.perform(click());

        ViewInteraction appCompatButton7 = onView(
                allOf(withId(R.id.create_new_module_button), withText("create module"), isDisplayed()));
        appCompatButton7.perform(click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.module_recycler_view), isDisplayed()));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        pressBack();

        ViewInteraction textView6 = onView(
                allOf(withId(R.id.module_title), withText("Espresso Module"),
                        childAtPosition(
                                withParent(withId(R.id.module_view_pager)),
                                0),
                        isDisplayed()));
        textView6.check(matches(withText("Espresso Module")));

        ViewInteraction textView7 = onView(
                allOf(withId(R.id.module_type), withText("Battle"),
                        childAtPosition(
                                withParent(withId(R.id.module_view_pager)),
                                1),
                        isDisplayed()));
        textView7.check(matches(withText("Battle")));

        ViewInteraction textView8 = onView(
                allOf(withId(R.id.module_summary), withText("Java"),
                        childAtPosition(
                                withParent(withId(R.id.module_view_pager)),
                                2),
                        isDisplayed()));
        textView8.check(matches(withText("Java")));

        ViewInteraction button9 = onView(
                allOf(withId(R.id.launch_module_button),
                        childAtPosition(
                                withParent(withId(R.id.module_view_pager)),
                                3),
                        isDisplayed()));
        button9.check(matches(isDisplayed()));

        ViewInteraction button10 = onView(
                allOf(withId(R.id.complete_module_button),
                        childAtPosition(
                                withParent(withId(R.id.module_view_pager)),
                                4),
                        isDisplayed()));
        button10.check(matches(isDisplayed()));

        ViewInteraction button11 = onView(
                allOf(withId(R.id.complete_module_button),
                        childAtPosition(
                                withParent(withId(R.id.module_view_pager)),
                                4),
                        isDisplayed()));
        button11.check(matches(isDisplayed()));

        ViewInteraction imageButton3 = onView(
                allOf(withId(R.id.new_npc_fab),
                        childAtPosition(
                                withParent(withId(R.id.module_view_pager)),
                                1),
                        isDisplayed()));
        imageButton3.check(matches(isDisplayed()));

        ViewInteraction floatingActionButton4 = onView(
                allOf(withId(R.id.new_npc_fab), isDisplayed()));
        floatingActionButton4.perform(click());

        ViewInteraction editText6 = onView(
                allOf(withId(R.id.npc_name_edit_text), withText("Name"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        editText6.check(matches(withText("Name")));

        ViewInteraction textView9 = onView(
                allOf(withText("Level:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        textView9.check(matches(withText("Level:")));

        ViewInteraction textView10 = onView(
                allOf(withText("Money:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                2),
                        isDisplayed()));
        textView10.check(matches(withText("Money:")));

        ViewInteraction textView11 = onView(
                allOf(withText("Size:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        2),
                                0),
                        isDisplayed()));
        textView11.check(matches(withText("Size:")));

        ViewInteraction textView12 = onView(
                allOf(withText("Job:"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        3),
                                0),
                        isDisplayed()));
        textView12.check(matches(withText("Job:")));

        ViewInteraction textView13 = onView(
                allOf(withId(android.R.id.text1), withText("Big +1 body"),
                        childAtPosition(
                                allOf(withId(R.id.size_spinner),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                1)),
                                0),
                        isDisplayed()));
        textView13.check(matches(isDisplayed()));

        ViewInteraction textView14 = onView(
                allOf(withId(android.R.id.text1), withText("Tank +1 body"),
                        childAtPosition(
                                allOf(withId(R.id.job_spinner),
                                        childAtPosition(
                                                IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                                1)),
                                0),
                        isDisplayed()));
        textView14.check(matches(isDisplayed()));

        ViewInteraction button12 = onView(
                allOf(withId(R.id.create_npc_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dm_fragment_container),
                                        0),
                                4),
                        isDisplayed()));
        button12.check(matches(isDisplayed()));

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.npc_name_edit_text), isDisplayed()));
        appCompatEditText7.perform(replaceText("Coffee"), closeSoftKeyboard());

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.level_edit_text), isDisplayed()));
        appCompatEditText8.perform(replaceText("5"), closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.money_edit_text), isDisplayed()));
        appCompatEditText9.perform(replaceText("100"), closeSoftKeyboard());

        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.create_npc_button), withText("create"), isDisplayed()));
        appCompatButton8.perform(click());

        ViewInteraction recyclerView2 = onView(
                allOf(withId(R.id.npc_recycler_view), isDisplayed()));
        recyclerView2.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView15 = onView(
                allOf(withId(R.id.npc_name), withText("Coffee"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dm_fragment_container),
                                        0),
                                0),
                        isDisplayed()));
        textView15.check(matches(withText("Coffee")));

        ViewInteraction textView16 = onView(
                allOf(withId(R.id.health), withText("25 / 25 Precious Goo"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dm_fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        textView16.check(matches(withText("25 / 25 Precious Goo")));

        ViewInteraction textView17 = onView(
                allOf(withId(R.id.stats), withText("Body: 2 Mind: 0 Essence: 0"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dm_fragment_container),
                                        0),
                                2),
                        isDisplayed()));
        textView17.check(matches(withText("Body: 2 Mind: 0 Essence: 0")));

        ViewInteraction textView18 = onView(
                allOf(withId(R.id.level_and_money), withText("Level: 5 Money: 100 pearls"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dm_fragment_container),
                                        0),
                                3),
                        isDisplayed()));
        textView18.check(matches(withText("Level: 5 Money: 100 pearls")));

        ViewInteraction textView19 = onView(
                allOf(withText("Abilities:"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dm_fragment_container),
                                        0),
                                4),
                        isDisplayed()));
        textView19.check(matches(withText("Abilities:")));

        ViewInteraction textView20 = onView(
                allOf(withText("Inventory:"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dm_fragment_container),
                                        0),
                                5),
                        isDisplayed()));
        textView20.check(matches(withText("Inventory:")));

        ViewInteraction textView21 = onView(
                allOf(withText("Inventory:"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dm_fragment_container),
                                        0),
                                5),
                        isDisplayed()));
        textView21.check(matches(withText("Inventory:")));

        pressBack();

        pressBack();

        ViewInteraction imageButton4 = onView(
                allOf(withId(R.id.new_note_fab),
                        childAtPosition(
                                withParent(withId(R.id.module_view_pager)),
                                1),
                        isDisplayed()));
        imageButton4.check(matches(isDisplayed()));

        ViewInteraction floatingActionButton5 = onView(
                allOf(withId(R.id.new_note_fab), isDisplayed()));
        floatingActionButton5.perform(click());

        ViewInteraction editText7 = onView(
                allOf(withId(R.id.new_note_title), withText("Title"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dm_fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        editText7.check(matches(withText("Title")));

        ViewInteraction editText8 = onView(
                allOf(withId(R.id.new_note_body), withText("Body"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dm_fragment_container),
                                        0),
                                3),
                        isDisplayed()));
        editText8.check(matches(withText("Body")));

        ViewInteraction button13 = onView(
                allOf(withId(R.id.create_new_note_button),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dm_fragment_container),
                                        0),
                                4),
                        isDisplayed()));
        button13.check(matches(isDisplayed()));

        ViewInteraction textView22 = onView(
                allOf(withText("Enter the note body:"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dm_fragment_container),
                                        0),
                                2),
                        isDisplayed()));
        textView22.check(matches(withText("Enter the note body:")));

        ViewInteraction appCompatEditText10 = onView(
                allOf(withId(R.id.new_note_title), isDisplayed()));
        appCompatEditText10.perform(replaceText("Note"), closeSoftKeyboard());

        ViewInteraction appCompatEditText11 = onView(
                allOf(withId(R.id.new_note_body), isDisplayed()));
        appCompatEditText11.perform(replaceText("Good"), closeSoftKeyboard());

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(R.id.create_new_note_button), withText("create"), isDisplayed()));
        appCompatButton9.perform(click());

        ViewInteraction recyclerView3 = onView(
                allOf(withId(R.id.notes_recycler_view), isDisplayed()));
        recyclerView3.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView23 = onView(
                allOf(withId(R.id.current_title_text_view), withText("Note"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dm_fragment_container),
                                        0),
                                0),
                        isDisplayed()));
        textView23.check(matches(withText("Note")));

        ViewInteraction textView24 = onView(
                allOf(withId(R.id.current_body_text_view), withText("Good"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dm_fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        textView24.check(matches(withText("Good")));

        ViewInteraction button14 = onView(
                allOf(withId(R.id.edit_save_button),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        2),
                                0),
                        isDisplayed()));
        button14.check(matches(isDisplayed()));

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(R.id.edit_save_button), withText("edit"), isDisplayed()));
        appCompatButton10.perform(click());

        ViewInteraction editText9 = onView(
                allOf(withId(R.id.current_title_edit_text), withText("Note"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dm_fragment_container),
                                        0),
                                0),
                        isDisplayed()));
        editText9.check(matches(isDisplayed()));

        ViewInteraction editText10 = onView(
                allOf(withId(R.id.current_body_edit_text), withText("Good"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dm_fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        editText10.check(matches(isDisplayed()));

        ViewInteraction button15 = onView(
                allOf(withId(R.id.cancel_button),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        2),
                                1),
                        isDisplayed()));
        button15.check(matches(isDisplayed()));

        ViewInteraction appCompatEditText12 = onView(
                allOf(withId(R.id.current_title_edit_text), withText("Note"), isDisplayed()));
        appCompatEditText12.perform(replaceText("Title"), closeSoftKeyboard());

        ViewInteraction appCompatEditText13 = onView(
                allOf(withId(R.id.current_body_edit_text), withText("Good"), isDisplayed()));
        appCompatEditText13.perform(replaceText("Bad"), closeSoftKeyboard());

        ViewInteraction appCompatButton11 = onView(
                allOf(withId(R.id.cancel_button), withText("Cancel"), isDisplayed()));
        appCompatButton11.perform(click());

        ViewInteraction textView25 = onView(
                allOf(withId(R.id.current_title_text_view), withText("Note"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dm_fragment_container),
                                        0),
                                0),
                        isDisplayed()));
        textView25.check(matches(withText("Note")));

        ViewInteraction textView26 = onView(
                allOf(withId(R.id.current_body_text_view), withText("Good"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dm_fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        textView26.check(matches(withText("Good")));

        ViewInteraction button16 = onView(
                allOf(withId(R.id.edit_save_button),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        2),
                                0),
                        isDisplayed()));
        button16.check(matches(isDisplayed()));

        ViewInteraction appCompatButton12 = onView(
                allOf(withId(R.id.edit_save_button), withText("edit"), isDisplayed()));
        appCompatButton12.perform(click());

        ViewInteraction appCompatEditText14 = onView(
                allOf(withId(R.id.current_title_edit_text), withText("Note"), isDisplayed()));
        appCompatEditText14.perform(click());

        ViewInteraction appCompatEditText15 = onView(
                allOf(withId(R.id.current_title_edit_text), withText("Note"), isDisplayed()));
        appCompatEditText15.perform(replaceText("Not"), closeSoftKeyboard());

        ViewInteraction appCompatEditText16 = onView(
                allOf(withId(R.id.current_body_edit_text), withText("Good"), isDisplayed()));
        appCompatEditText16.perform(replaceText("Bad"), closeSoftKeyboard());

        ViewInteraction appCompatButton13 = onView(
                allOf(withId(R.id.edit_save_button), withText("save"), isDisplayed()));
        appCompatButton13.perform(click());

        ViewInteraction textView27 = onView(
                allOf(withId(R.id.current_title_text_view), withText("Not"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dm_fragment_container),
                                        0),
                                0),
                        isDisplayed()));
        textView27.check(matches(withText("Not")));

        ViewInteraction textView28 = onView(
                allOf(withId(R.id.current_body_text_view), withText("Bad"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.dm_fragment_container),
                                        0),
                                1),
                        isDisplayed()));
        textView28.check(matches(withText("Bad")));

        pressBack();

        pressBack();

        ViewInteraction view = onView(
                allOf(withId(R.id.dm_map),
                        childAtPosition(
                                withParent(withId(R.id.module_view_pager)),
                                0),
                        isDisplayed()));
        view.check(matches(isDisplayed()));

        ViewInteraction imageButton5 = onView(
                allOf(withId(R.id.update_map_fab),
                        childAtPosition(
                                withParent(withId(R.id.module_view_pager)),
                                1),
                        isDisplayed()));
        imageButton5.check(matches(isDisplayed()));

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
