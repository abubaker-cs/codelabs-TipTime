package org.abubaker.tiptime

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorTests {

    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_default_tip() {

        // We are first selected the UI Component using the #id and then performing the action,
        // that is to automatically type 50.00 in the EditField
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("50.00"))

        // Then we are programmatically pressing the "Calculate Button" to perform the calculation.
        onView(withId(R.id.calculate_button)).perform(click())

        // No, we are validating if the end result is = 10.00
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("10.00"))))

    }
}