package com.cyclebikeapp.bikesmartdrivesmart;

import android.app.DialogFragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final int DRIVER_TIPS = 0;
    private static final int CYCLIST_TIPS = 1;
    private int tipType = DRIVER_TIPS;
    private int scenarioNumber = 0;
    private int tipNumber = 0;
    private ArrayList<ArrayList<String>> allDriverTips;
    private ArrayList<ArrayList<String>> allCyclistTips;
    private ArrayList<String> scenarioTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUpTipStringArray();
        setContentView(R.layout.activity_main);
        setupSpinners();
        setTipTextButtonsState(tipType, scenarioNumber, tipNumber);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.about_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {

            case R.id.help:
                showHelp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showHelp() {
            DialogFragment newFragment = new InfoDialog();
            newFragment.show(getFragmentManager(), "info");
    }

    /**
     * Set the enabled / disabled state of the tip buttons depending on tip number, tip type and scenario number
     *
     * @param tipNumber      the number of the tip
     * @param tipType        either DRIVER_TIPS or CYCLIST_TIPS
     * @param scenarioNumber the number of the scenario
     */
    private void setTipTextButtonsState(int tipType, int scenarioNumber, int tipNumber) {
        // If there is no tip number less than the current tip number, disable the left button
        // If there is no tip number higher than the current tip number, disable the right button
        Button leftTipButton = (Button) findViewById(R.id.previousTipButton);
        Button rightTipButton = (Button) findViewById(R.id.nextTipButton);
        boolean hasPrevTip = (tipNumber > 0);
        boolean hasNextTip;
        if (tipType == DRIVER_TIPS) {
            hasNextTip = (tipNumber < allDriverTips.get(scenarioNumber).size() - 1);
        } else {
            hasNextTip = (tipNumber < allCyclistTips.get(scenarioNumber).size() - 1);
        }
        leftTipButton.setEnabled(hasPrevTip);
        rightTipButton.setEnabled(hasNextTip);
    }

    /**
     * Read cyclist and motorist tip String arrays from resources, put in ArrayList<ArrayList<String>>
     * Read scenario title Strings and put in ArrayList
     */
    private void setUpTipStringArray() {
        Resources res = getResources();
        allCyclistTips = new ArrayList<>();
        ArrayList<String> myResArrayList0 = new ArrayList<>();
        Collections.addAll(myResArrayList0, res.getStringArray(R.array.scenario0CycleTips));
        allCyclistTips.add(myResArrayList0);
        ArrayList<String> myResArrayList1 = new ArrayList<>();
        Collections.addAll(myResArrayList1, res.getStringArray(R.array.scenario1CycleTips));
        allCyclistTips.add(myResArrayList1);

        ArrayList<String> myResArrayList2 = new ArrayList<>();
        Collections.addAll(myResArrayList2, res.getStringArray(R.array.scenario2CycleTips));
        allCyclistTips.add(myResArrayList2);

        ArrayList<String> myResArrayList3 = new ArrayList<>();
        Collections.addAll(myResArrayList3, res.getStringArray(R.array.scenario3CycleTips));
        allCyclistTips.add(myResArrayList3);

        ArrayList<String> myResArrayList4 = new ArrayList<>();
        Collections.addAll(myResArrayList4, res.getStringArray(R.array.scenario4CycleTips));
        allCyclistTips.add(myResArrayList4);

        ArrayList<String> myResArrayList5 = new ArrayList<>();
        Collections.addAll(myResArrayList5, res.getStringArray(R.array.scenario5CycleTips));
        allCyclistTips.add(myResArrayList5);

        allDriverTips = new ArrayList<>();
        ArrayList<String> myResDArrayList0 = new ArrayList<>();
        Collections.addAll(myResDArrayList0, res.getStringArray(R.array.scenario0DriverTips));
        allDriverTips.add(myResDArrayList0);

        ArrayList<String> myResDArrayList1 = new ArrayList<>();
        Collections.addAll(myResDArrayList1, res.getStringArray(R.array.scenario1DriverTips));
        allDriverTips.add(myResDArrayList1);

        ArrayList<String> myResDArrayList2 = new ArrayList<>();
        Collections.addAll(myResDArrayList2, res.getStringArray(R.array.scenario2DriverTips));
        allDriverTips.add(myResDArrayList2);

        ArrayList<String> myResDArrayList3 = new ArrayList<>();
        Collections.addAll(myResDArrayList3, res.getStringArray(R.array.scenario3DriverTips));
        allDriverTips.add(myResDArrayList3);

        ArrayList<String> myResDArrayList4 = new ArrayList<>();
        Collections.addAll(myResDArrayList4, res.getStringArray(R.array.scenario4DriverTips));
        allDriverTips.add(myResDArrayList4);

        ArrayList<String> myResDArrayList5 = new ArrayList<>();
        Collections.addAll(myResDArrayList5, res.getStringArray(R.array.scenario5DriverTips));
        allDriverTips.add(myResDArrayList5);

        scenarioTitle = new ArrayList<>();
        Collections.addAll(scenarioTitle, res.getStringArray(R.array.scenarioTitles));
    }

    /**
     * Set up the scenario and travel mode spinners
     */
    private void setupSpinners() {
        // First set-up the travel-mode spinner
        Spinner travelModeSpinner = (Spinner) findViewById(R.id.travelModeSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> travelModeAdapter = ArrayAdapter.createFromResource(this,
                R.array.travel_mode_spinner_choices, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        travelModeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        travelModeSpinner.setAdapter(travelModeAdapter);
        travelModeSpinner.setOnItemSelectedListener(this);

        // Now set-up the scenario spinner using the String array of values
        Spinner scenarioSpinner = (Spinner) findViewById(R.id.scenarioSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> scenarioAdapter = ArrayAdapter.createFromResource(this,
                R.array.scenario_spinner_choices, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        scenarioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        scenarioSpinner.setAdapter(scenarioAdapter);
        scenarioSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //depending on parent, set tipType or set scenarioNumber then refresh ImageView and refresh tipText
        if (parent.getId() == R.id.travelModeSpinner) {
            if (position == 0) {
                tipType = DRIVER_TIPS;
            } else {
                tipType = CYCLIST_TIPS;
            }
        } else {
            scenarioNumber = position;
        }
        // When new scenario selected, start showing tip number 0
        // update tip text, tip button state, scenario image and title
        tipNumber = 0;
        showTipText(tipNumber);
        setTipTextButtonsState(tipType, scenarioNumber, tipNumber);
        setScenarioImage(scenarioNumber);
        showScenarioDescription(scenarioNumber);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        tipNumber = 0;
        scenarioNumber = 0;
        setScenarioImage(scenarioNumber);
        showTipText(tipNumber);
        setTipTextButtonsState(tipType, scenarioNumber, tipNumber);
        showScenarioDescription(scenarioNumber);
    }

    /**
     * Update the image in the scenarioImage depending on the scenarioNumber
     *
     * @param scenarioNumber the number of the scenario
     */
    private void setScenarioImage(int scenarioNumber) {
        ImageView scenarioImageView = (ImageView) findViewById(R.id.scenarioImage);
        // scenario number descriptions
        // <item>Empty road</item>
        // <item>Right Cross</item>
        //<item>Left Hook</item>
        //<item>Right Hook</item>
        //<item>Dooring</item>
        //<item>Pull-out</item>
        switch (scenarioNumber) {
            case 0:
                scenarioImageView.setImageResource(R.drawable.road);
                break;
            case 1:
                scenarioImageView.setImageResource(R.drawable.rightcrossimage);
                break;
            case 2:
                scenarioImageView.setImageResource(R.drawable.lefthookimage);
                break;
            case 3:
                scenarioImageView.setImageResource(R.drawable.righthookimage);
                break;
            case 4:
                scenarioImageView.setImageResource(R.drawable.dooringimage);
                break;
            case 5:
                scenarioImageView.setImageResource(R.drawable.pulloutimage);
                break;
            default:

        }
    }

    /**
     * Respond to nextTip button press by incrementing tipNumber, displaying tip text and changing the state of the button
     */
    public void showNextTip(View view) {

        int maxTipNumber;
        if (tipType == CYCLIST_TIPS) {
            maxTipNumber = allCyclistTips.get(scenarioNumber).size() - 1;
        } else {
            maxTipNumber = allDriverTips.get(scenarioNumber).size() - 1;
        }
        if (tipNumber < maxTipNumber) {
            tipNumber++;
        }
        showTipText(tipNumber);
        setTipTextButtonsState(tipType, scenarioNumber, tipNumber);
    }

    /**
     * Respond to previousTip button press by decrementing tipNumber, displaying tip text and changing the state of the button
     */
    public void showPrevTip(View view) {
        if (tipNumber > 0) {
            tipNumber--;
        }
        showTipText(tipNumber);
        setTipTextButtonsState(tipType, scenarioNumber, tipNumber);
    }

    /**
     * Set the text in the tipText TextBox depending on the tipNumber, scenarioNumber and tipType (motorist or cyclist)
     *
     * @param tipNumber the number of the tip
     */
    private void showTipText(int tipNumber) {
        String tipMessage;

        if (tipType == CYCLIST_TIPS) {
            tipMessage = allCyclistTips.get(scenarioNumber).get(tipNumber);
        } else {
            tipMessage = allDriverTips.get(scenarioNumber).get(tipNumber);
        }
        TextView tipTextView = (TextView) findViewById(R.id.tipText);
        tipTextView.setText(tipMessage);
    }

    /**
     * Set the text in the scenario description TextBox depending on the scenarioNumber
     *
     * @param scenarioNumber the number of the scenario
     */
    private void showScenarioDescription(int scenarioNumber) {
        TextView scenarioTextView = (TextView) findViewById(R.id.scenarioDescription);
        scenarioTextView.setText(scenarioTitle.get(scenarioNumber));
    }

}
