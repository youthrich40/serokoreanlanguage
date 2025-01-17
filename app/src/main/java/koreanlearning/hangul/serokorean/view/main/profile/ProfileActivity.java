package koreanlearning.hangul.serokorean.view.main.profile;

import android.content.Intent;
import android.net.Uri;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.hangul.serokorean.R;

import koreanlearning.hangul.serokorean.view.beginnerone.BeginnerOneActivity;

public class ProfileActivity extends AppCompatActivity {

    private GoogleSignInClient googleSignInClient;
    private GoogleSignInAccount googleSignInAccount;

    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button sign_out = findViewById(R.id.sign_out);
        TextView name = findViewById(R.id.name);
        TextView email = findViewById(R.id.email);
        ImageView photo = findViewById(R.id.photo);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if(firebaseUser != null){

            if(firebaseUser.getDisplayName() != null){
                name.setText(firebaseUser.getDisplayName());
            }

            if(firebaseUser.getEmail() != null){
                email.setText(firebaseUser.getEmail());
            }

            if(firebaseUser.getPhotoUrl() != null){
                Uri personPhoto = firebaseUser.getPhotoUrl();
                Glide.with(this).load(personPhoto).into(photo);
            }

            sign_out.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signOut();
                }
            });
        }
        else{
            name.setText("profile error");
            email.setVisibility(View.GONE);
            sign_out.setVisibility(View.GONE);
        }

        // profile view without firebase
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).requestEmail().build();
//        googleSignInClient = GoogleSignIn.getClient(this, gso);
//        googleSignInAccount = GoogleSignIn.getLastSignedInAccount(ProfileActivity.this);

//        if(Profile.getCurrentProfile() != null){
//            Profile facebookProfile = Profile.getCurrentProfile();
//            name.setText(facebookProfile.getFirstName() + " " + facebookProfile.getLastName());
//            photo.setVisibility(View.GONE);
//            email.setVisibility(View.GONE);
//            ProfilePictureView profilePictureView = findViewById(R.id.facebook_profile_activity_image);
//            profilePictureView.setProfileId(Profile.getCurrentProfile().getId());
//            profilePictureView.setVisibility(View.VISIBLE);
//
//            sign_out.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    signOut();
//                }
//            });
//        }
//        else if(googleSignInAccount != null){
//            Uri personPhoto = googleSignInAccount.getPhotoUrl();
//            name.setText(googleSignInAccount.getDisplayName());
//            email.setText(googleSignInAccount.getEmail());
////            userId.setText(googleSignInAccount.getId());
//            Glide.with(this).load(personPhoto).into(photo);
//            sign_out.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    signOut();
//                }
//            });
//        }
//        else{
//            name.setText("cannot find user");
//            email.setVisibility(View.GONE);
////            userId.setVisibility(View.GONE);
//            sign_out.setVisibility(View.GONE);
//        }
    }

    private void signOut(){
        if(firebaseUser != null){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(ProfileActivity.this, BeginnerOneActivity.class);
            intent.putExtra("sign out", 3);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            finish();
        }

        // sign out without firebase
//        // facebook sign out
//        if(Profile.getCurrentProfile() != null){
//            LoginManager.getInstance().logOut();
//
//            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
//            finish();
//        }
//        // google sign out
//        if(googleSignInAccount != null){
//            googleSignInClient.signOut();
//
//            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent);
//            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
//            finish();
//        }
    }
}
