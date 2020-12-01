package com.example.mytablelayoutfullcode;

//Projects in Series 1:
//1. Build a Simple App in Android Studio with Java
//2. Build a Persistent Storage App in Android Studio
//3. Build a Linear Layout App in Android Studio
//4. Build a Relative Layout App in Android Studio
//5. Build a Table Layout App in Android Studio

//***THIS FILE WILL SHOW ERRORS UNTIL YOU HAVE COMPLETED THE TASKS YOU ARE REQUIRED TO DO.  WHEN YOU HAVE
//SUCCESSFULLY FINISHED THE TASK THE ERRORS SHOULD ALL BE GONE.***

//androidx.appcompat.app.AppCompatActivity and android.os.Bundle are put in by default when basic
//activity selected when new project is created in Android Studio. All of the other imports where
//put in manually later during the making of the project.
//START
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ToggleButton;
//END

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //The strings below are used for persistent storage - remembering a user selections after the user
    //closes the app. Persistent storage is looked at in much more detail in another project, "Build
    //a Persistent Storage App in Android Studio", and we will not be looking at it here. You do not
    //have to do anything with this code.
    //START
    public static final String MYPREFS = "mySharedPreferences";
    private String savedMonthOfBirth;
    private String savedToggleButtonChoice;
    private String savedNumberPicked;
    //END

    //Creating Java equivalent objects for the widgets in our user interface which we created in xml
    //that we want to interact with (change) or give values to in some way.
    //START
    private Spinner sprMonthOfBirth;
    private ImageView imgMonthOfBirth;
    //Create one ToggleButton, two ImageViews, and two TextViews. Call them tbnCatsOrDogs,
    //**START TASK** ~ 4 lines
    private ToggleButton tbnCatsOrDogs;
    private ImageView imgCatsOrDogs;
    private EditText edtNumber;
    private ImageView imgPickANumber;
    //**END TASK**
    //END

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connect our java objects to the appropriate widget in our user interface
        //START
        sprMonthOfBirth = findViewById(R.id.sprSpinner1);
        imgMonthOfBirth = findViewById(R.id.imgMonthOfBirth);
        //Connect tbnCatsOrDogs, imgCatsOrDogs, edtNumber, and imgPickANumber to the appropriate
        //widgets in our user interface.
        //**START TASK** ~ 4 lines
        tbnCatsOrDogs = findViewById(R.id.tbnToggleButton1);
        imgCatsOrDogs = findViewById(R.id.imgCatsOrDogs);
        edtNumber = findViewById(R.id.edtNumber);
        imgPickANumber = findViewById(R.id.imgPickANumber);
        //**END TASK**
        //END

        //The three lines below are inserting an image called questionmark from the res/drawable
        //folder into our three ImageViews. We will be looking at resources in a future project
        //and will not be explaining it here. You do not have to do anything with this code.
        //START
        imgMonthOfBirth.setImageResource(R.drawable.questionmark);
        imgCatsOrDogs.setImageResource(R.drawable.questionmark);
        imgPickANumber.setImageResource(R.drawable.questionmark);
        //END

        //The below two lines are using array from resources (res/values/strings.xml) to fill in the
        //pieces of information that will be inside our Spinner (months of the year). It is planned
        //to have a future project on using resources and we will not be looking at it in detail here.
        //You do not have to do anything with this code.
        //START
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.month_of_birth, R.layout.support_simple_spinner_dropdown_item);
        sprMonthOfBirth.setAdapter(adapter);
        //END

        //For a Spinner you can not use an onClickListener like you would for a Button.  You must
        //use setOnItemSelectedListener where you set the instructions for what to do when a item in
        //the Spinner is selected. There are two parts of an item selected listener, what to do when
        //an item is selected and what to do when no item is selected.
        //START
        sprMonthOfBirth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //This line is checking what item in the Spinner has been selected based on the
                //position (index number) of the item.
                Object month = parent.getItemAtPosition(position);
                //The position number (same as all arrays in Java) starts at index 0. So January
                //would be at position 0 but it is easier for us to picture in our brains if January
                //equals 1, February equals 2 etc. To do this we create an int called pickedMonth and
                //give it the value of the position number plus 1.
                int pickedMonth = position + 1;
                //We take the the value in picked month, change it to a String and give it as the
                //value of savedMonthOfBirth for use in Persistent Storage (remembering the selection
                //when the user closes the app.
                savedMonthOfBirth = String.valueOf(pickedMonth) ;

                //We are using a switch statement to check what value we just saved in savedMonthOfBirth
                //and then we are changing the image used in the ImageView imgMonthOfBirth so that it
                //matches the month that is selected.
                switch (savedMonthOfBirth) {
                    case "1":  imgMonthOfBirth.setImageResource(R.drawable.january);   break;
                    case "2":  imgMonthOfBirth.setImageResource(R.drawable.february);  break;
                    case "3":  imgMonthOfBirth.setImageResource(R.drawable.march);     break;
                    case "4":  imgMonthOfBirth.setImageResource(R.drawable.april);     break;
                    case "5":  imgMonthOfBirth.setImageResource(R.drawable.may);       break;
                    case "6":  imgMonthOfBirth.setImageResource(R.drawable.june);      break;
                    case "7":  imgMonthOfBirth.setImageResource(R.drawable.july);      break;
                    case "8":  imgMonthOfBirth.setImageResource(R.drawable.august);    break;
                    case "9":  imgMonthOfBirth.setImageResource(R.drawable.september); break;
                    case "10": imgMonthOfBirth.setImageResource(R.drawable.october);   break;
                    case "11": imgMonthOfBirth.setImageResource(R.drawable.november);  break;
                    case "12": imgMonthOfBirth.setImageResource(R.drawable.december);  break;
                    default :  imgMonthOfBirth.setImageResource(R.drawable.questionmark);
                }
            }

            //Nothing being selected in our Spinner is not an option as it will be at January by
            //default if the user does nothing.  We are giving the instruction that if the user does
            //not actively (actually physically select a month instead of leaving it on January
            //without pressing anything) select a month then the value in savedMonthOfBirth is set
            //to "" and the image in the ImageView imgMonthOfBirth is set to the file called
            //questionmark in the res/drawable folder.
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                savedMonthOfBirth = "";
                imgMonthOfBirth.setImageResource(R.drawable.questionmark);
            }
        });
        //END

        //The main onClick method is given as the onClickListener for our ToggleButton called
        //tbnCatsOrDogs (the word this inside the brackets is what is tell our program that this on
        //setOnclickListener will use the main onClick method in this MainActivity.java class.
        //The instructions for what to do with the user clicks the ToggleButton are in the onClick method.
        tbnCatsOrDogs.setOnClickListener(this);

        //We do not want to set an onClick listener for our EditText edtNumber because we do not want
        //an action to happen when the user clicks on the input box. What we want is that an action
        //happens when the user puts a new value inside the input box (EditText).  To do this we will
        //use an addTextChangedListener. What we have in our addTextChangedListener is a TextWatcher()
        //which keeps looking at the text inside our input box and then triggers the actions when it
        //notices that the text inside has changed.
        edtNumber.addTextChangedListener(new TextWatcher() {
            //We will not give any instructions (we do not have to for our app) inside beforeTextChanged.
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //This code says if the input box is not empty(!s.equals("") - if s does not equal
                //string with no characters) then take what is inside the input box, change it to
                //a String if necessary (it will not be necessary), change it to all lowercase if
                //necessary, and then save it as the value in savedNumberPicked which we will use in
                //Persistent Storage (remembering the selection after the app is closed.
                if (!s.equals("")) {
                    savedNumberPicked = edtNumber.getText().toString().toLowerCase();
                }
                //We are using a switch statement to check what value we just saved in savedNumberPicked
                //and then we are changing the image used in the ImageView imgPickANumber so that it
                //matches the number that is input if it is "1", or "2" or "3", or "4", or "5" or "6",
                //or "7", or "8", or "9". If what is input but the user does not match one of these
                //nine options then the default is used which puts an image that says do not use in
                //ImageView imgPickANumber.
                    switch (savedNumberPicked) {
                        case "1": imgPickANumber.setImageResource(R.drawable.numberone);   break;
                        case "2": imgPickANumber.setImageResource(R.drawable.numbertwo);   break;
                        case "3": imgPickANumber.setImageResource(R.drawable.numberthree); break;
                        case "4": imgPickANumber.setImageResource(R.drawable.numberfour);  break;
                        case "5": imgPickANumber.setImageResource(R.drawable.numberfive);  break;
                        case "6": imgPickANumber.setImageResource(R.drawable.numbersix);   break;
                        case "7": imgPickANumber.setImageResource(R.drawable.numberseven); break;
                        case "8": imgPickANumber.setImageResource(R.drawable.numbereight); break;
                        case "9": imgPickANumber.setImageResource(R.drawable.numbernine);  break;
                        default : imgPickANumber.setImageResource(R.drawable.donotuse);
                    }
            }
            //We will not give any instructions (we do not have to for our app) inside afterTextChanged
            @Override
            public void afterTextChanged(Editable s) { }
        });

        //We are calling the loadPreferences method for Persistent Storage. Persistent storage is looked at
        //in much more detail in another project "Build a Persistent Storage App in Android Studio"
        //and we will not be looking at it here. You do not have to do anything with this code.
        //START
        loadPreferences();
        //END
    }

    //This is the main onClick method in MainActivity.java and we have told the program that our
    //ToggleButton tbnCatsOrDogs will be using this onClick method for the setOnClickListener.
    @Override
    public void onClick(View v) {
        //If we have toggle button on Cat then the ImageView imgCatsOrDogs is updated to the image
        //of a cat from a file called cat that is in the res/drawable folder.
        if (tbnCatsOrDogs.isChecked()){
            //set the value of savedToggleButtonChoice to cat. Used for persistent storage.
            savedToggleButtonChoice = "cat";
            //set image in ImageView imgCatsOrDogs to cat.
            imgCatsOrDogs.setImageResource(R.drawable.cat);
        }
        //If we have our toggle button on Dog then the ImageView imgCatsOrDogs is updated to the image
        //of a dog from a file called dog that is in the res/drawable folder.
        else{
            //set the value of savedToggleButtonChoice to dog. Used for persistent storage.
            savedToggleButtonChoice = "dog";
            //set image in ImageView imgCatsOrDogs to dog.
            imgCatsOrDogs.setImageResource(R.drawable.dog);
        }
    }

    //You do not have to change any code below here.  The code below here is related to Persistent
    //Storage (Shared Preferences) and Menus. We have looked at persistent storage in the Build a
    //Persistent Storage App in Android Studio project on Coursera. If you would like to know more
    //about this you can go to that course. We have adjusted the values of the Strings beginning
    //with saved (savedMonthOfBirth, savedToggleButtonChoice, and savedNumberPicked to match what
    //the user has input for month of birth, cats or dogs, and pick a number in the last time they
    //closed the app. We are using key value pairs below to remember what each value is and to update
    //the String values based on what is in the key value pairs. In the loadPreferences() we are also
    //setting what is showing on our app for our Spinner, for our ToggleButton and in our EditText
    //(input box for number) to match what we have now put in savedMonthOfBirth, savedCatsOrDogs,
    //and savedNumberPicked.  We also update the three ImageViews with the appropriate images based
    //on what the user selected when they last exited the app.
    public void loadPreferences() {
        // Get the stored preferences
        int mode = Activity.MODE_PRIVATE;
        android.content.SharedPreferences mySharedPreferences = getSharedPreferences(MYPREFS, mode);
        //When you see words in grey like key: and defValue: below they are tags put in by android
        //studio.  These are not typed in by a person.
        //Retrieve the saved values and put them in savedMonthOfBirth, savedToggleButtonChoice, &
        //savedNumberPicked as appropriate.
        savedMonthOfBirth = mySharedPreferences.getString("month", "");
        savedToggleButtonChoice = mySharedPreferences.getString("toggle", "");
        savedNumberPicked = mySharedPreferences.getString("number", "");

        //We are using a switch statement to check what is the value that was saved in savedMonthOfBirth
        //and if what is saved is "2", or "3", or "4", or "5", or "6", or "7", or "8", or "9" then our
        //app will go and select the appropriate month for us in the Spinner sprMonthOfBirth. If what
        //is saved in savedMonthOfBirth does not match one of these then the default of January is
        //selected. Remember that the position index starts at 0 so months "2" February is at position
        //1, month "1" January is at position 0, etc.
        switch (savedMonthOfBirth) {
            case "2":  sprMonthOfBirth.setSelection(1);  break;
            case "3":  sprMonthOfBirth.setSelection(2);  break;
            case "4":  sprMonthOfBirth.setSelection(3);  break;
            case "5":  sprMonthOfBirth.setSelection(4);  break;
            case "6":  sprMonthOfBirth.setSelection(5);  break;
            case "7":  sprMonthOfBirth.setSelection(6);  break;
            case "8":  sprMonthOfBirth.setSelection(7);  break;
            case "9":  sprMonthOfBirth.setSelection(8);  break;
            case "10": sprMonthOfBirth.setSelection(9);  break;
            case "11": sprMonthOfBirth.setSelection(10); break;
            case "12": sprMonthOfBirth.setSelection(11); break;
            default :  sprMonthOfBirth.setSelection(0);  break;
        }

        //If the value for savedToggleButtonChoice from the previous time the user exited the app
        //then the toggle button is set to cat (checked true) and the imgCatsOrDogs has an image of
        //a cat inserted from res/drawable folder.
        //if the value in savedToggleButtonChoice is equal to cat
        if (savedToggleButtonChoice.equals("cat")){
            //set the toggle button to the positive position (cat)
            tbnCatsOrDogs.setChecked(true);
            //get the image called cat from the drawable folder is res and inset it in imgCatsOrDogs
            imgCatsOrDogs.setImageResource(R.drawable.cat);
        }
        //if the value in savedToggleButtonChoice is equal to anything other than cat
        else {
            //set the toggle button to the negative position (dog)
            tbnCatsOrDogs.setChecked(false);
            //get the image called cat from the drawable folder is res and inset it in imgCatsOrDogs
            imgCatsOrDogs.setImageResource((R.drawable.dog));
        }
        //We are using a switch statement to check what is the value that was saved in savedPickedNumber
        //and if what is saved is "1", or "2", or "3", or "4", or "5", or "6", or "7", or "8", or "9"
        //then our app will go and put the appropriate number in the EditText (input box). If what
        //is saved in savedNumberPicked does not match one of these then the default of empty (no number)
        //input box is done.
        switch (savedNumberPicked) {
            case "1": edtNumber.setText("1"); break;
            case "2": edtNumber.setText("2"); break;
            case "3": edtNumber.setText("3"); break;
            case "4": edtNumber.setText("4"); break;
            case "5": edtNumber.setText("5"); break;
            case "6": edtNumber.setText("6"); break;
            case "7": edtNumber.setText("7"); break;
            case "8": edtNumber.setText("8"); break;
            case "9": edtNumber.setText("9"); break;
            default :
        }
    }
    //When the user closes the app we are checking what values are in savedMonthOfBirth,
    //savedToggleButtonChoice, and savedNumberPicked and we are saving that information in key value
    //pairs with the keys of "month", "toggle", and "number".
    protected void savePreferences() {
        // Create or retrieve the shared preference object.
        int mode = Activity.MODE_PRIVATE;
        android.content.SharedPreferences mySharedPreferences = getSharedPreferences(MYPREFS, mode);
        // Retrieve an editor to modify the shared preferences.
        android.content.SharedPreferences.Editor editor = mySharedPreferences.edit();
        // Store data in the shared preferences object as key value pairs.
        editor.putString("month", savedMonthOfBirth);
        editor.putString("toggle", savedToggleButtonChoice);
        editor.putString("number", savedNumberPicked);
        // Commit the changes.
        editor.commit();
    }

    //This method just tells program what to do when the user closes the app (exit the app and call
    //the savePreferences method so that the relevant information is saved.
    @Override
    protected void onStop() {
        super.onStop();
        this.savePreferences();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shared_preferences_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
