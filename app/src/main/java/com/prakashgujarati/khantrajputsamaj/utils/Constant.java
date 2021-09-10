package com.prakashgujarati.khantrajputsamaj.utils;

import android.graphics.Bitmap;
import android.os.Environment;


import com.prakashgujarati.khantrajputsamaj.commans.BaseActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Constant {

    public static final boolean IS_DEBUG_ENABLE = true;
    public static final String SUCCESS_RESPONSE = "200";
    public static final String FAIL_RESPONSE = "0";
    public static final String MAP_URL = "https://bharatmaps.gov.in/map.aspx?dtcode=072";
    public static final String HIDE = "hide";
    public static final String SHOW = "show";

    public static int visibleAccountCount = 1;
    public static final int MEDIA_PICK_REQUEST = 369;
    public static final int MEDIA_UPLOAD_LIMIT = 10;

    public static String SHOW_CLASS_CODE_FORMAT = "###-###-###";


    public static final String BOARD_SCREEN = "BoardScreen";
    public static final String MAIN_SCREEN = "MainScreen";

    public static final String MENU_CLICK = "menu_click";
    public static final String PRESENT_CLICK = "present_click";
    public static final String ABSENT_CLICK = "absent_click";

    public static final String APPROVE = "aprove";
    public static final String REJECT = "reject";
    public static final String EDIT = "edit";
    public static final String DELETE = "delete";
    public static final String SHUFFLE = "shuffle";
    public static final String SHARE = "share";
    public static final String FROM_TIME = "from_time";
    public static final String TO_TIME = "to_time";
    public static final String ADD_LECTURE = "add_lecture";
    public static final String HIDE_SHOW_DAY = "hide_show_day";

    public static final String IMAGE = "Image";
    public static final String OPEN_SELECTED_FILE_DIALOG = "open_dialog";
    public static final String OPEN_PREVIEW = "open_preview";
    public static final String FILE = "File";
    public static final String AUDIO = "Audio";
    public static final String ABSENT = "absent";
    public static final String PRESENT = "present";

    public static final String PRACTICE = "PRACTICE";
    public static final String TEST = "TEST";
    public static final String BOOK = "Book";
    public static final String SHEET = "Sheet";
    public static final String PAPER = "Paper";
    public static final String VIDEO = "video";
    public static final String WHATSAPP_COUNT = "whatsapp_count";
    public static final String SHARE_COUNT = "share_count";
    public static final String SHOW_COUNT = "show_count";
    public static final String SOLUTIONS = "Solutions";
    public static final String SOLUTION = "Solution";
    public static final String MATERIAL = "Material";
    public static final String TEXTBOOK = "Textbook";
    public static final String WORKSHEET = "Worksheet";
    public static final String NOTES = "Note";

    public interface PreferenceConstant {
        String LOGIN_DATA = "LoginData";
        String USER_ID = "userId";
        String MCQ_TYPE = "mcq_type";
        String BOARD = "board";
        String MEDIUM = "medium";
        String SHORT_NAME = "short_name";
        String STANDARD = "standard";
        String TOKEN = "token";
        String IS_LOGIN = "is_login";
        String MOBILE_NO = "mobile_no";
        String COUNTRY_CODE = "country_code";
        String NO_OF_QUESTIONS = "number_of_question";
        String FIREBASE_TOKEN = "firebase_token";
        String IS_DEVICE_TYPE_TV = "isDeviceTypeTv";
    }

    public interface EndPoint {
        String CANDIDATE_LIST = "candidate_list";
        String CANDIDATE_DETAILS = "candidate_details";
        String BUSINESS_LIST = "business_list";
        String BUSINESS_DETAILS = "business_details";
        String NEWS_LIST = "news_list";
        String NEWS_DETAILS = "news_details";
        String LATE_LIST = "late_list";
        String LATE_DETAILS = "late_details";
        String EMPLOYEE_LIST = "employee_list";
        String EMPLOYEE_DETAILS = "employee_details";
        String PLACEMENT_LIST = "recruitment_list";
        String PLACEMENT_DETAILS = "recruitment_details";
        String SPORTS_LIST = "sport_list";
        String SPORTS_DETAILS = "sport_view";
    }

    public interface ApiKey {
        String CANDIDATE_ID = "candidate_id";
        String BUSINESS_ID = "business_id";
        String NEWS_ID = "news_id";
        String LATE_ID = "late_id";
        String EMPLOYEE_ID = "employee_id";
        String PLACEMENT_ID = "recruitment_id";
        String SPORTS_ID = "sport_id";
    }

    //inreface for app type

    public interface BundleExtra {
        String CANDIDATE_ID = "candidate_id";
        String BUSINESS_ID = "business_id";
        String NEWS_ID = "news_id";
        String LATE_ID = "late_id";
        String EMPLOYEE_ID = "employee_id";
        String PLACEMENT_ID = "recruitment_id";
        String SPORTS_ID = "sport_id";
    }


    public interface Delay {
        int SPLASH_INTERVAL = 3000; // in ms
        int DOUBLE_BACK_PRESS_INTERVAL = 2000;// in ms
        int MIN_TIME_BETWEEN_CLICKS = 300; //in ms
        long MIN_TIME_OTP = 60; // in second
    }

    public interface ClickConstant {
        String ANDROID = "android";
        String TEXT_PLAIN = "text/plain";
    }

    public static File createDirectoryAndSaveFile(Bitmap imageToSave) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "IMG_" + timeStamp + ".jpg";

        File direct = BaseActivity.baseActivity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (!direct.exists()) {
            /*File wallpaperDirectory = new File("/sdcard/.opacko/");*/
            direct.mkdirs();
        }

        File file = new File(direct, fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            imageToSave.compress(Bitmap.CompressFormat.JPEG, 100, out);

            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

}