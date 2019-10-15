package koreanlearning.hangul.serokorean.beginnerone.quiz.common;

import android.os.CountDownTimer;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import koreanlearning.hangul.serokorean.beginnerone.quiz.Model.Category;
import koreanlearning.hangul.serokorean.beginnerone.quiz.Model.CurrentQuestion;
import koreanlearning.hangul.serokorean.beginnerone.quiz.Model.Question;
import koreanlearning.hangul.serokorean.beginnerone.quiz.QuestionFragment;

public class QuizCommon {
    public static Category selectedCategory = new Category();
    public static final int TOTAL_TIME = 20*60*1000; // 20mins
    public static List<Question> questionList = new ArrayList<>();
    public static List<CurrentQuestion> answerSheetList = new ArrayList<>();
    public static CountDownTimer countDownTimer;
    public static int right_answer_count = 0;
    public static int wrong_answer_counnt = 0;
    public static ArrayList<QuestionFragment> fragmentsList = new ArrayList<>();
    public static TreeSet<String> selected_values = new TreeSet<>();

    public enum ANSWER_TYPE{
        NO_ANSWER,
        WRONG_ANSWER,
        RIGHT_ANSWER
    }
}