package koreanlearning.hangul.serokorean.view.main.bottomNavigation;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.hangul.serokorean.R;

import koreanlearning.hangul.serokorean.view.main.webview.ChapterWebviewActivity;
import koreanlearning.hangul.serokorean.utility.ChapterUtil;

public class HomeFragment extends Fragment {

    private final int CHAPTERS = 30;
    private Dialog chapterLockDialog;
    private ImageView closeUnlockButton;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // set intro chapter's chapter and the number of pages
        CardView intro = view.findViewById(R.id.intro);
        intro.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                try {
                    Intent intent = new Intent(getActivity(), ChapterWebviewActivity.class);
                    intent.putExtra("chapter", "chapter 0");
                    intent.putExtra("pages", ChapterUtil.getChapterIntroNumOfPage());
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Typeface customFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/helvetica.ttf");
        changeFont(view, customFont);

        GridLayout mainGrid = view.findViewById(R.id.mainGrid);
        setChaptersGrid(mainGrid);

        // Inflate the layout for this fragment
        return view;
    }

    // set up chapter cardview grid. When it's clicked it sends the arguments of chapter number and the number of pages to ChapterWebviewActivity
    private void setChaptersGrid(GridLayout mainGrid){

        for (int i=0; i<mainGrid.getChildCount(); i++){
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            final String chapter = Integer.toString(i+1);

            int chapterIndex = i;

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // TODO: need to change this logic later when database is set up
                    if(chapterIndex > 6){
                        chapterLockDialog = new Dialog(getContext());
                        chapterLockDialog.setContentView(R.layout.chapter_lock_popup);
                        closeUnlockButton = chapterLockDialog.findViewById(R.id.close_unlock_popup);

                        closeUnlockButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                chapterLockDialog.dismiss();
                            }
                        });

                        chapterLockDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        chapterLockDialog.show();
                    }
                    else{
                        Intent intent = new Intent(getActivity(), ChapterWebviewActivity.class);
                        int numberOfPages = ChapterUtil.detectTheNumberOfPages(chapter);
                        intent.putExtra("chapter", "chapter " + chapter);
                        intent.putExtra("pages", numberOfPages);
                        startActivity(intent);
                    }
                }
            });
        }
    }

    private void changeFont(View view, Typeface customFont) {
        TextView tv;
        tv = view.findViewById(R.id.introduction);
        tv.setTypeface(customFont);

        for(int i=1; i<=CHAPTERS; ++i){
            try {
                int id = R.id.class.getField("ch" + i).getInt(0);
                tv = view.findViewById(id);
                tv.setTypeface(customFont);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }
}
