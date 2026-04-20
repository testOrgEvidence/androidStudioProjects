package com.example.tipcalculator

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.core.content.MimeTypeFilter.matches as matches

@RunWith(AndroidJUnit4::class)
class CalculatorTests {
    @get:Rule()
    val activity=ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun calculate_20_percent_tip(){
        onView(withId(R.id.etCost))
            .perform(typeText("50.00"))
            .perform(ViewActions.closeSoftKeyboard())
        onView(withId(R.id.btnCalculate))
            .perform(click())
        onView(withId(R.id.tvTipResult))
            .check(R.id.tvTipResult.equals("$10.00"))

    }


}

private fun ViewInteraction.check(var1: Boolean) {

}
