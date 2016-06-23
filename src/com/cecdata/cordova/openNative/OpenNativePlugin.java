package com.cecdata.cordova.openNative;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OpenNativePlugin extends CordovaPlugin {

  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    if (action.equals("openActivity")) {
      JSONObject json = args.optJSONObject(0);
      String className = json.optString("className");
      String type = json.optString("isForResult");
      String params = json.optString("params");
      try {
        openActivity(className, type,params);
        callbackContext.success("ok!!!");
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else if(action.equals("openPDF")) {
      JSONObject json = args.optJSONObject(0);
      String url = json.optString("pdfUrl");
      Uri path = Uri.parse(url);
      Intent intent = new Intent(Intent.ACTION_VIEW);
      intent.setDataAndType(path, "application/pdf");
      intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


      try {
        this.cordova.getActivity().startActivity(intent);
        callbackContext.success("open pdf ok!!!");
      } catch (ActivityNotFoundException e) {
        callbackContext.error("can't open pdf:"+e);
      }
    }
    return true;
  }

  private void openActivity(String className, String type,String params) throws Exception {

    Intent intent = new Intent(this.cordova.getActivity().getApplicationContext(), Class.forName(className));
    intent.putExtra("params", params);
    if ("1".equals(type)) {
      this.cordova.getActivity().startActivityForResult(intent, 0);
    } else {
      this.cordova.getActivity().startActivity(intent);
    }
    this.cordova.getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
  }

}
