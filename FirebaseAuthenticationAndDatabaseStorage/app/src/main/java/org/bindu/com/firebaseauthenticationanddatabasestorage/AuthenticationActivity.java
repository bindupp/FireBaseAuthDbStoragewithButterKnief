package org.bindu.com.firebaseauthenticationanddatabasestorage;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthenticationActivity extends BaseActivity {

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etNumber) EditText etNumber;
    @BindView(R.id.etEmail) EditText etEmail;
    @BindView(R.id.etPass) EditText etPassword;
    @BindView(R.id.haveAccount)
    TextView AlreadyAccount;
    @BindView(R.id.fblogin)
    Button fbLogin;
    @BindView(R.id.googleLogin)
    Button googleLogin;
    @BindView(R.id.submit)
    Button submit;

  /*  @BindView(R.id.userbg)
    ImageView userLogo;*/

    private FirebaseAuth auth;



    private String userId;
    private String email;
    private  String password;
    private   String number;
    private String name;
    boolean clickedLogin = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        auth = FirebaseAuth.getInstance();





    }

    @OnClick(R.id.haveAccount)
    void haveAccount(){

        String haveAccountText = AlreadyAccount.getText().toString();

        etName.setVisibility(View.GONE);
        etNumber.setVisibility(View.GONE);


        if (!TextUtils.isEmpty(haveAccountText)&& haveAccountText.equalsIgnoreCase(getString(R.string.haveaccount))){
            etPassword.setVisibility(View.VISIBLE);
            AlreadyAccount.setVisibility(View.VISIBLE);
            AlreadyAccount.setText(R.string.forgotPass);

            submit.setText(R.string.loginTexr);
            fbLogin.setText("Login");
            googleLogin.setText("Login");
        }else{
            etPassword.setVisibility(View.GONE);
            AlreadyAccount.setVisibility(View.GONE);
            submit.setText(R.string.forgot_pss_submit);

        }

    }

    @OnClick(R.id.submit)
    public  void submitFiled(){


        showToast("inside SignUp");

        email = etEmail.getText().toString().trim();
        password = etPassword.getText().toString().trim();

        number = etNumber.getText().toString().trim();
        name = etName.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            showToast ("Enter email address!");
            return;
        }





        showProgress("loading");


        String butonText = submit.getText().toString();

        if(!TextUtils.isEmpty(butonText) && butonText.equalsIgnoreCase(getString(R.string.sign_up))){
            if (TextUtils.isEmpty(password)) {
                showToast ( "Enter password!");
                return;
            }

            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(AuthenticationActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        dismissProgress();
                        showToast(task.getResult().toString());
                    } else {

                        dismissProgress();

                        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");


                        String userId = mDatabase.push().getKey();

                        User user = new User(name,number,email,password);

                        mDatabase.child(userId).setValue(user);


                    }
                }
            });
        }else if(!TextUtils.isEmpty(butonText) && butonText.equalsIgnoreCase(getString(R.string.loginTexr))){
            if (TextUtils.isEmpty(password)) {
                showToast ( "Enter password!");
                return;
            }



            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(AuthenticationActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    dismissProgress();
                    if (!task.isSuccessful()) {
                        // there was an error
                        dismissProgress();
                        Toast.makeText(AuthenticationActivity.this,getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                    } else {

                        finish();
                    }
                }
            });
        }else if(!TextUtils.isEmpty(butonText) && butonText.equalsIgnoreCase(getString(R.string.forgot_pss_submit))){
            auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                showToast(getString(R.string.success_reset_password_inst));
                            } else {
                                showToast(getString(R.string.failed_reset_pass_inst));
                            }

                            dismissProgress();
                        }
                    });
        }



    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }





}
