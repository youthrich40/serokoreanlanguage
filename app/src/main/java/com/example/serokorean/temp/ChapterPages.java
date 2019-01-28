package com.example.serokorean.temp;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.serokorean.R;
import com.example.serokorean.level.BeginnerOne;

import java.util.ArrayList;

public class ChapterPages extends AppCompatActivity implements GestureDetector.OnGestureListener {

    private TextView textView;
    private int numberOfPages = 0;
    private int currentPage = 0;
    private int currentChapterNum = 0;
    private String currentChapter = "";
    private WebView webView;
    private GestureDetector gestureDetector;
    private ArrayList<String> htmlFiles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        MyWebView webview = new MyWebView(this);
//        setContentView(webview);
        setContentView(R.layout.activity_chapter_pages);

        textView = findViewById(R.id.chapterpage);

        Bundle bundle = getIntent().getExtras();

        StringBuilder stringBuilder = new StringBuilder();
        String newString = "";

        if(bundle == null){
            textView.setText("bundle error");
        }
        else{
            numberOfPages = bundle.getInt("pages");
            currentChapter = bundle.getString("chapter");
            stringBuilder.append(bundle.getString("chapter"));
            stringBuilder.append(" number of pages:");
            stringBuilder.append(Integer.toString(bundle.getInt("pages")));
            newString = stringBuilder.toString();
            textView.setText(newString);
        }

        //add all html pages into the array
        for(int i=0; i<numberOfPages; ++i) {
            StringBuilder url = new StringBuilder();
            url.append("file:///android_asset/level 1/");
            url.append(currentChapter);
            url.append("/");
            url.append(Integer.toString(i));
            url.append(".html");
            htmlFiles.add(url.toString());
        }

        webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
//        webView.getSettings().setBuiltInZoomControls(true);
        webView.loadUrl(htmlFiles.get(0));

        gestureDetector = new GestureDetector(this);

        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int SWIPE_THRESHOLD = 100;
        int SWIPE_VELOCITY_THRESHOLD = 100;
        boolean result = false;
        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {

                        if(currentPage > 0){
                            Toast.makeText(ChapterPages.this, "left", Toast.LENGTH_SHORT).show();
                            --currentPage;
                            webView.loadUrl(htmlFiles.get(currentPage));
                        }
//                        else{
//                            currentPage = numberOfPages-1;
//                            webView.loadUrl(htmlFiles.get(currentPage));
//                        }

                    } else {
                        if(currentPage < numberOfPages-1){
                            Toast.makeText(ChapterPages.this, "right", Toast.LENGTH_SHORT).show();
                            ++currentPage;
                            webView.loadUrl(htmlFiles.get(currentPage));
                        }
                        else{
                            ++currentChapterNum;
                            if(currentChapterNum < 30){
                                Intent intent = new Intent(this, ChapterPages.class);

                                switch (Integer.toString(currentChapterNum)){
                                    case "0": intent.putExtra("chapter", "chapter 1"); intent.putExtra("pages", 10); break;
                                    case "1": intent.putExtra("chapter", "chapter 2"); intent.putExtra("pages", 11); break;
                                    case "2": intent.putExtra("chapter", "chapter 3"); intent.putExtra("pages", 12); break;
                                    case "3": intent.putExtra("chapter", "chapter 4"); intent.putExtra("pages", 13); break;
                                    case "4": intent.putExtra("chapter", "chapter 5"); intent.putExtra("pages", 13); break;
                                    case "5": intent.putExtra("chapter", "chapter 6"); intent.putExtra("pages", 13); break;
                                    case "6": intent.putExtra("chapter", "chapter 7"); intent.putExtra("pages", 13); break;
                                    case "7": intent.putExtra("chapter", "chapter 8"); intent.putExtra("pages", 13); break;
                                    case "8": intent.putExtra("chapter", "chapter 9"); intent.putExtra("pages", 13); break;
                                    case "9": intent.putExtra("chapter", "chapter 10"); intent.putExtra("pages", 13); break;
                                    case "10": intent.putExtra("chapter", "chapter 11"); intent.putExtra("pages", 13); break;
                                    case "11": intent.putExtra("chapter", "chapter 12"); intent.putExtra("pages", 13); break;
                                    case "12": intent.putExtra("chapter", "chapter 13"); intent.putExtra("pages", 13); break;
                                    case "13": intent.putExtra("chapter", "chapter 14"); intent.putExtra("pages", 13); break;
                                    case "14": intent.putExtra("chapter", "chapter 15"); intent.putExtra("pages", 13); break;
                                    case "15": intent.putExtra("chapter", "chapter 16"); intent.putExtra("pages", 13); break;
                                    case "16": intent.putExtra("chapter", "chapter 17"); intent.putExtra("pages", 13); break;
                                    case "17": intent.putExtra("chapter", "chapter 18"); intent.putExtra("pages", 13); break;
                                    case "18": intent.putExtra("chapter", "chapter 19"); intent.putExtra("pages", 13); break;
                                    case "19": intent.putExtra("chapter", "chapter 20"); intent.putExtra("pages", 13); break;
                                    case "20": intent.putExtra("chapter", "chapter 21"); intent.putExtra("pages", 13); break;
                                    case "21": intent.putExtra("chapter", "chapter 22"); intent.putExtra("pages", 13); break;
                                    case "22": intent.putExtra("chapter", "chapter 23"); intent.putExtra("pages", 13); break;
                                    case "23": intent.putExtra("chapter", "chapter 24"); intent.putExtra("pages", 13); break;
                                    case "24": intent.putExtra("chapter", "chapter 25"); intent.putExtra("pages", 13); break;
                                    case "25": intent.putExtra("chapter", "chapter 26"); intent.putExtra("pages", 13); break;
                                    case "26": intent.putExtra("chapter", "chapter 27"); intent.putExtra("pages", 13); break;
                                    case "27": intent.putExtra("chapter", "chapter 28"); intent.putExtra("pages", 13); break;
                                    case "28": intent.putExtra("chapter", "chapter 29"); intent.putExtra("pages", 13); break;
                                    case "29": intent.putExtra("chapter", "chapter 30"); intent.putExtra("pages", 13); break;
                                }

                                startActivity(intent);
                            }
//                            currentPage = 0;
//                            webView.loadUrl(htmlFiles.get(currentPage));
                        }

                    }
                    result = true;
                }
            }
//            else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
//                if (diffY > 0) {
//                    onSwipeBottom();
//                } else {
//                    onSwipeTop();
//                }
//                result = true;
//            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }
    @Override
    public boolean onDown(MotionEvent e) { return false; }

    @Override
    public void onShowPress(MotionEvent e) { }

    @Override
    public boolean onSingleTapUp(MotionEvent e) { return false; }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) { return false; }

    @Override
    public void onLongPress(MotionEvent e) { }
}
