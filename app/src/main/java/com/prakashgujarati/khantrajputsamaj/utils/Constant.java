package com.prakashgujarati.khantrajputsamaj.utils;

import android.graphics.Bitmap;
import android.os.Environment;


import com.prakashgujarati.khantrajputsamaj.commans.BaseActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLTransactionRollbackException;
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
        String EVENT_LIST = "event_list";
        String EVENT_DETAILS = "event_view";
        String VIDEO_DETAILS = "video_view";
        String VIDEO_LIST = "video_list";
        String PHOTO_LIST = "gallery_list";
        String PHOTO_VIEW = "gallery_view";
        String SPORTS_LIST = "sport_list";
        String SPORTS_DETAILS = "sport_view";
        String BIRTHDAY_LIST = "birthday_list";
        String BIRTHDAY_DETAILS = "birthday_view";
        String DONER_LIST = "doner_list";
        String DONER_DETAILS = "doner_view";
        String CONTACT_LIST = "contact_list";
        String CONTACT_DETAILS = "contact_view";
        String EVENT_CREATE = "event_create";
        String BIRTHDAY_CREATE = "birthday_create";
        String LATE_CREATE = "late_create";
        String PLACEMENT_CREATE = "recruitment_create";
        String EDUCATION_LIST = "education_list";
        String EDUCATION_DETAILS = "education_view";
        String EDUCATION_CREATE = "education_create";
        String BUSNIESS_CREATE = "business_create";
        String EMPLOYEE_CREATE = "employee_create";
        String CANDIDATE_CREATE = "candidate_create";
        String NEWS_CREATE = "news_create";
        String SURNAME_LIST = "surname_list";
        String SURNAME_CREATE = "surname_create";
        String CONTACT_CREATE = "contact_create";
        String SPORTS_CREATE = "sport_create";
        String DONOR_CREATE = "doner_create";
        String PHOTO_CREATE = "gallery_update";
        String VIDEO_CREATE = "gallery_create";
    }

    public interface ApiKey {
        String CANDIDATE_ID = "candidate_id";
        String BUSINESS_ID = "business_id";
        String NEWS_ID = "news_id";
        String LATE_ID = "late_id";
        String EMPLOYEE_ID = "employee_id";
        String PLACEMENT_ID = "recruitment_id";
        String EVENT_ID = "event_id";
        String VIDEO_ID = "video_id";
        String PHOTO_ID = "gallery_id";
        String SPORTS_ID = "sport_id";
        String BIRTHDAY_ID ="birthday_id" ;
        String DONER_ID = "doner_id";
        String CONTACT_ID = "contact_id";
        String REFERENCE = "reference";
        String DETAILS = "detail_report";
        String CATEGORY = "category";
        String TITLE = "title";
        String HEADLINE = "headline";
        String REPORTED_DATETIME = "reported_datetime";
        String FIRST_NAME = "first_name";
        String MIDDLE_NAME = "middle_name";
        String LAST_NAME = "last_name";
        String COMPANY = "company";
        String DESCRIPTION = "description";
        String CONTACT = "contact";
        String EMAIL = "email";
        String ADDRESS = "address";
        String OFFICE = "office";
        String DESIGNATION = "designation";
        String BIRTH_DATE = "birth_date";
        String BIRTH_TIME = "birth_time";
        String BIRTH_PLACE = "birth_place";
        String HEIGHT = "height";
        String WEIGHT = "weight";
        String EDUCATION = "education";
        String OCCUPATION = "occupation";
        String FATHER_NAME = "father_name";
        String MOTHER_NAME = "mother_name";
        String BROTHERS = "brothers";
        String SISTERS = "sisters";
        String FATHER_OCCUPATION = "father_occupation";
        String MOTHER_OCCUPATION = "mother_occupation";
        String FATHER_CONTACT = "father_contact";
        String RESIDENT_ADDRESS = "resident_address";
        String NATIVE_ADDRESS = "native_address";
        String MATERNAL = "maternal";
        String MATERNAL_PLACE = "maternal_place";
        String HOBBIES = "hobbies";
        String EXPECTATIONS = "expectations";
        String REMARK = "remark";
        String EDUCATION_ID = "education_id";
        String NAME ="name";
        String BIRTHDATE = "birthdate";
        String TIME = "time";
        String PLACE = "place";
        String WISHES = "wishes";
        String GUJARATI_SAVANT = "gujarati_savant";
        String LATE_DATE = "late_date";
        String SHRADHHANJALI = "shradhhanjali";
        String NOTIFICATION = "notifications";
        String SKILLS = "skills";
        String EDUCATIONAL_QUALIFICATION = "education_quailification";
        String REFERENCE_URL = "reference_url";
        String GENDER = "gender";
        String QUALIFICATION = "qualification";
        String NOTE = "note";
        String SURNAME ="name";
        String MOBILE = "mobile";
        String TYPE = "type";
        String EVENT_TITLE = "event_title";
        String LOCATION = "location";
        String DATE = "date";
        String PATH = "path";
    }

    //inreface for app type

    public interface BundleExtra {
        String CANDIDATE_ID = "candidate_id";
        String BUSINESS_ID = "business_id";
        String NEWS_ID = "news_id";
        String LATE_ID = "late_id";
        String EMPLOYEE_ID = "employee_id";
        String PLACEMENT_ID = "recruitment_id";
        String EVENT_ID = "event_id";
        String VIDEO_ID = "video_id";
        String PHOTO_ID = "gallery_id";
        String SPORTS_ID = "sport_id";
        String BIRTHDAY_ID ="birthday_id" ;
        String DONER_ID = "doner_id";
        String CONTACT_ID = "contact_id";
        String EDUCATION_ID = "education_id";
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