package me.hanul.cordova;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import com.unity3d.ads.android.UnityAds;
import com.unity3d.ads.android.IUnityAdsListener;
import org.json.JSONArray;
import org.json.JSONException;

public class CordovaUnityAdsPlugin extends CordovaPlugin implements IUnityAdsListener {
	
	private CallbackContext nowCallbackContext;
	
	@Override
	public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {

		if (action.equals("init")) {
			init(args.getString(0));
		} else if (action.equals("setTestMode")) {
			setTestMode(args.getBoolean(0));
		} else if (action.equals("checkIsCanShow")) {
			callbackContext.success(checkIsCanShow());
		} else if (action.equals("show")) {
			show(callbackContext);
		} else {
			// Returning false results in a "MethodNotFound" error.
			return false;
		}

		return true;
	}
	
	public void init(final String gameId) {
		
		cordova.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				
				UnityAds.init(cordova.getActivity(), gameId, (IUnityAdsListener) CordovaUnityAdsPlugin.this);
			}
		});
	}

	public void setTestMode(final boolean isTestMode) {
		
		cordova.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				
				UnityAds.setTestMode(isTestMode);
			}
		});
	}

	public int checkIsCanShow() {
		return UnityAds.canShow() == true ? 1 : 0;
	}

	public void show(final CallbackContext callbackContext) {
		
		nowCallbackContext = callbackContext;
		
		cordova.getActivity().runOnUiThread(new Runnable() {
			@Override
			public void run() {
				
				if (UnityAds.canShow() == true) {
					UnityAds.setZone("rewardedVideo");
					UnityAds.show();
				}
			}
		});
	}

	@Override
	public void onVideoCompleted(String itemKey, boolean skipped) {
		nowCallbackContext.success(skipped == true ? 1 : 0);
	}

	@Override
	public void onHide() {}

	@Override
	public void onShow() {}

	@Override
	public void onVideoStarted() {}

	@Override
	public void onFetchCompleted() {}

	@Override
	public void onFetchFailed() {}
}