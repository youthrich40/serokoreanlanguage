package koreanlearning.hangul.serokorean.bottomNavigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;
import com.hangul.serokorean.R;

import koreanlearning.hangul.serokorean.login.LoginActivity;
import koreanlearning.hangul.serokorean.search.Search;

public class More extends Fragment{

    private ImageView imageView;
    private RelativeLayout loginView;
    private ImageView more_userPhoto;
    private TextView more_username;


    public More() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_more, container, false);
        more_username = view.findViewById(R.id.more_username);
        more_userPhoto = view.findViewById(R.id.more_userimage);

        imageView = view.findViewById(R.id.moresearch);
        imageView.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Search.class);
                startActivity(intent);
//                startActivityForResult(intent, 10001);
            }
        });

        loginView = view.findViewById(R.id.login);
        loginView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Toast.makeText(getContext(), "login button pressed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivityForResult(intent, 10001);
//                startActivity(intent);
//                User user = User.getUser();
//
//                if(user.getAuthtoken() == null){
//                    Toast.makeText(getContext(), "login button pressed", Toast.LENGTH_SHORT).show();
//                    loginFragment = LoginFragment.newInstance();
//                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                    fragmentTransaction.replace(R.id.BeginnerOneContainer, loginFragment).addToBackStack("login").commit();
//                }
//                else{
//                    switchToProfileFragment();
//                }
            }
        });

        // if user signed in already, then change name and profile picture
        if(Profile.getCurrentProfile() != null){
            more_username.setText(Profile.getCurrentProfile().getName());
            imageView.setVisibility(View.GONE);
            ProfilePictureView profilePictureView = view.findViewById(R.id.facebook_profile_picture);
            profilePictureView.setProfileId(Profile.getCurrentProfile().getId());
            profilePictureView.setVisibility(View.VISIBLE);
        }

        return view;
    }
}
