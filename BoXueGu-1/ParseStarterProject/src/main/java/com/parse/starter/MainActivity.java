/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;


public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

  Boolean signup

  @Override
  public void onClick(View view) {
    if(view.getId() == R.id.changeSignupModeTextView){
      Log.i("AppInfo","Change Signup Mode");
    }

  }

  public void signUp(View view){

    EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);
    EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);

    if (usernameEditText.getText().toString().matches(" ")|| passwordEditText.getText().toString().matches( " ")){
      Toast.makeText(this,"A username and password are required",Toast.LENGTH_SHORT).show();

    }else
    {
      ParseUser user = new ParseUser();
      user.setUsername(usernameEditText.getText().toString());
      user.setPassword(passwordEditText.getText().toString());

      user.signUpInBackground(new SignUpCallback() {
        @Override
        public void done(ParseException e) {
          if( e== null ){
            Log.i("Signup","Successful");
          }else {
            Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
          }
        }
      });
    }
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    TextView changeSignupModeTextView = (TextView) findViewById(R.id.changeSignupModeTextView);

  changeSignupModeTextView.setOnClickListener(this);




//
//    ParseObject score = new ParseObject("Score");
//    score.put("username","rob");
//    score.put("score", 86);
//    score.saveInBackground(new SaveCallback() {
//      @Override
//      public void done(ParseException e) {
//        if (e==null){
//          Log.i("SaveInBackground","Successful");
//        }else {
//          Log.i("SaveInBackground" , "Failed");
//        }
//
//      }
//    });

//    ParseQuery<ParseObject> query =  ParseQuery.getQuery("Score");
//
//    query.getInBackground("M28vv7ioxO", new GetCallback<ParseObject>() {
//      @Override
//      public void done(ParseObject object, ParseException e) {
//        if(e==null && object !=null) {
//          Log.i("ObjectValue", object.getString("username"));
//
//        }
//      }
//    });


    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}