package com.juzipi2.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.net.NetworkInterface;

public class DeviceUtils {

    static String getImei(Context context) {
        if (context.checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            try {
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                if (tm != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        return tm.getImei();
                    } else {
                        return tm.getDeviceId();
                    }
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    static String getAndroidId(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
        }
        return null;
    }

    static String getWifiMacAddress() {
        try {
            NetworkInterface ni = NetworkInterface.getByName("wlan0");
            if (ni != null) {
                byte[] bytes = ni.getHardwareAddress();
                if (bytes != null && bytes.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < bytes.length; i++) {
                        if (i != 0) {
                            sb.append(':');
                        }
                        String str = Integer.toHexString(bytes[i] & 0xFF);
                        sb.append(str.length() == 1 ? 0 + str : str);
                    }
                    return sb.toString().toUpperCase();
                }
            }
        } catch (Exception e) {
        }
        return null;
    }
}
