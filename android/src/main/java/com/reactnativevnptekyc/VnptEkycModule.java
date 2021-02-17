package com.reactnativevnptekyc;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.vnptit.idg.sdk.activity.VnptIdentityActivity;
import com.vnptit.idg.sdk.utils.SDKEnum;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.ACCESS_TOKEN;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.TOKEN_ID;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.TOKEN_KEY;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.DOCUMENT_TYPE;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.SELECT_DOCUMENT;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.VERSION_SDK;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.CAMERA_FOR_PORTRAIT;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.SHOW_RESULT;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.SHOW_SWITCH;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.SHOW_DIALOG_SUPPORT;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.HIDE_TRADE_MARK;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.LANGUAGE;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.LIVENESS_ADVANCED;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.CHECK_MASKED_FACE;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.CHECK_LIVENESS_CARD;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.VALIDATE_POSTCODE;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.GET_POST_CODE;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.CALL_ADD_FACE;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.VERIFY_ID;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.ID_TYPE;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.CHALLENGE_CODE;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.USE_DIFFERENT_SERVER;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.URL_UPLOAD_IMAGE;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.URL_OCR;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.URL_COMPARE;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.URL_LIVENESS;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.URL_CHECK_MASKED_FACE;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.URL_MAPPING_ADDRESS;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.URL_OCR_FRONT;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.URL_VERIFY_FACE;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.URL_ADD_FACE;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.CUSTOMER_INFO;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.HASH_IMAGE_COMPARE;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.ADD_FACE_TITLE;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.UNIT_ADD_FACE;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.CHANGE_THEME;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.LOGO;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.HEIGHT_LOGO;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.WIDTH_LOGO;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.CHANGE_COLOR;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.CHANGE_COLOR_HELP;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.CHANGE_TEXT_COLOR;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.COMPARE_FLOW;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.CALL_ADD_FACE;
import static com.vnptit.idg.sdk.utils.KeyIntentConstants.VERIFY_FACE_FLOW;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.INFO_RESULT;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.COMPARE_RESULT;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.LIVENESS_RESULT;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.LIVENESS_CARD_FRONT_RESULT;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.LIVENESS_CARD_REAR_RESULT;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.MASKED_FACE_RESULT;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.REGISTER_RESULT;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.ENCODE_RESULT;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.HASH_FRONT;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.HASH_REAR;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.HASH_PORTRAIT;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.ORIGIN_LOCATION_RESULT;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.RECENT_LOCATION_RESULT;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.ISSUE_PLACE_RESULT;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.BIRTH_PLACE_RESULT;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.FRONT_IMAGE;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.REAR_IMAGE;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.PORTRAIT_IMAGE;
import static com.vnptit.idg.sdk.utils.KeyResultConstants.NETWORK_PROBLEM;

public class VnptEkycModule extends ReactContextBaseJavaModule implements ActivityEventListener {

    private final ReactApplicationContext reactContext;

    public VnptEkycModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        reactContext.addActivityEventListener(this);
    }

    private Callback mCallback;

    @Override
    public String getName() {
        return "VnptEkyc";
    }

    @ReactMethod
    public void ekyc(Callback callback) {
        mCallback = callback;
        fullStack();
    }
    private void fullStack() {
        Intent intent = new Intent(reactContext.getCurrentActivity(), VnptIdentityActivity.class);
        if (intent != null) {
            intent.putExtra(ACCESS_TOKEN, "");
            intent.putExtra(TOKEN_ID, "");
            intent.putExtra(TOKEN_KEY, "");
            intent.putExtra(DOCUMENT_TYPE, SDKEnum. DocumentTypeEnum. IDENTITY_CARD.getValue());
            intent.putExtra(SELECT_DOCUMENT, false);
            intent.putExtra(VERSION_SDK, SDKEnum.VersionSDKEnum.STANDARD.getValue());
            intent.putExtra(SHOW_RESULT, true);
            intent.putExtra(SHOW_DIALOG_SUPPORT, true);
            intent.putExtra(CAMERA_FOR_PORTRAIT, SDKEnum.CameraTypeEnum.FRONT.getValue());
            intent.putExtra(SHOW_SWITCH, false); 
            intent.putExtra(CALL_ADD_FACE, false);
            intent.putExtra(LIVENESS_ADVANCED, false);
            intent.putExtra(CHECK_MASKED_FACE,false);
            intent.putExtra(CHECK_LIVENESS_CARD,false);
            intent.putExtra(LANGUAGE, SDKEnum.LanguageEnum.VIETNAMESE.getValue());
            intent.putExtra(CHALLENGE_CODE,"");
            reactContext.getCurrentActivity().startActivityForResult(intent, 1);
        }
    }

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String strDataInfo = data.getStringExtra(INFO_RESULT);
                mCallback.invoke(strDataInfo);
            }
        }
    }

    @Override
    public void onNewIntent(Intent intent) {

    }
}
