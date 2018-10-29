package com.example.todo;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TodoActivity extends AppCompatActivity {

    private String[] mTodos;
    private int mTodoIndex = 0;
    public static final String TAG = "TodoActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, " *** Just to say the PC is in onCreate!");
        // call the super class onCreate to complete the creation of activity like
        // the view hierarchy
        super.onCreate(savedInstanceState);

        // check for saved state due to changes such as rotation or back button
        // and restore any saved state such as the TODO_INDEX
        if (savedInstanceState != null){
            mTodoIndex = savedInstanceState.getInt(TODO_INDEX, 0);
        }

        // set the user interface layout for this Activity
        // the layout file is defined in the project res/layout/activity_todo.xml file
        setContentView(R.layout.activity_todo);

        // initialize member TextView so we can manipulate it later
        final TextView TodoTextView;
        TodoTextView = (TextView) findViewById(R.id.textViewTodo);

        // read the todo array from res/values/strings.xml
        Resources res = getResources();
        mTodos = res.getStringArray(R.array.todo);
        // display the first task from mTodo array in the TodoTextView
        TodoTextView.setText(mTodos[mTodoIndex]);

        Button buttonNext;
        buttonNext = (Button) findViewById(R.id.buttonNext);

        // OnClick listener for the  Next button
        buttonNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mTodoIndex = (mTodoIndex + 1) % mTodos.length;
                TodoTextView.setText(mTodos[mTodoIndex]);
            }
        });
    }

// In case of state change, due to rotating the phone
// store the mTodoIndex to display the same mTodos element post state change
// N.B. small amounts of data, typically IDs can be stored as key, value pairs in a Bundle
// the alternative is to abstract view data to a ViewModel which can be in scope in all
// Activity states and more suitable for larger amounts of data

    private static final String TODO_INDEX = "todoIndex";

    // override to write the value of mTodoIndex into the Bundle with TODO_INDEX as its key
    /*
    onSaveInstanceState(Bundle) is called before stop()
    it saves all view data as bundles (key, value pairs) in the savedInstanceState bundle object
    additionally, we add the mTodoIndex to the bundle object

    onCreate method reconstructs the view from the savedInstanceState
    and we assign the mTodoIndex from the savedInstanceState to mCurrentIndex
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(TODO_INDEX, mTodoIndex);
    }

}