package bd.edu.rifat.foodDonationApp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
    public FirebaseAuth mAuth;
    Button signUpButton;
    EditText signUpEmailTextInput;
    EditText signUpPasswordTextInput;
    CheckBox agreementCheckBox;
    TextView errorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        mAuth = FirebaseAuth.getInstance();

        signUpEmailTextInput = findViewById(R.id.editTextTextEmailAddress);
        signUpPasswordTextInput = findViewById(R.id.editTextTextPassword);
        signUpButton = findViewById(R.id.signup_btn);
        agreementCheckBox = findViewById(R.id.checkBox);



        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (signUpEmailTextInput.getText().toString().contentEquals("")) {


                    errorView.setText("Email cannot be empty");


                } else if (signUpPasswordTextInput.getText().toString().contentEquals("")) {


                    errorView.setText("Password cannot be empty");


                } else if (!agreementCheckBox.isChecked()) {

                    showTermsAndConditionsDialog();
                   // errorView.setText("Please agree to terms and Condition");


                } else {


                    mAuth.createUserWithEmailAndPassword(signUpEmailTextInput.getText().toString(), signUpPasswordTextInput.getText().toString()).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                                try {
                                    if (user != null)
                                        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Log.d(TAG, "Email sent.");

                                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                                            SignupActivity.this);

                                                    // set title
                                                    alertDialogBuilder.setTitle("Please Verify Your EmailID");

                                                    // set dialog message
                                                    alertDialogBuilder
                                                            .setMessage("A verification Email Is Sent To Your Registered EmailID, please click on the link and Sign in again!")
                                                            .setCancelable(false)
                                                            .setPositiveButton("Sign In", new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dialog, int id) {

                                                                    Intent signInIntent = new Intent(SignupActivity.this, SignInActivity.class);
                                                                    startActivity(signInIntent);
                                                                }
                                                            });

                                                    // create alert dialog
                                                    AlertDialog alertDialog = alertDialogBuilder.create();

                                                    // show it
                                                    alertDialog.show();


                                                }
                                            }
                                        });


                                } catch(Exception e){
                                    errorView.setText(e.getMessage());
                                }
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(SignupActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                                if (task.getException() != null) {
                                    errorView.setText(task.getException().getMessage());
                                }

                            }

                        }
                    });

                }

            }
        });


    }

    public void signinn(View view) {
        Intent in= new Intent(SignupActivity.this,SignInActivity.class);
        startActivity(in);
    }
    private void showTermsAndConditionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.terms, null);
        builder.setView(dialogView)
                .setTitle("Terms and Conditions")
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}