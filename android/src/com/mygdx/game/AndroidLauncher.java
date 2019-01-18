package com.mygdx.game;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		String macAddress = "";
		WifiManager wifiManager = (WifiManager) getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = null;

		if(wifiManager != null)
			wifiInfo = wifiManager.getConnectionInfo();
		if(wifiInfo != null)
			macAddress = wifiInfo.getBSSID();
		macAddress = macAddress.replace(":", "");
		long mac = Long.parseLong(macAddress, 16);


		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useImmersiveMode = true;
		initialize(new Game(), config);
	}
}
