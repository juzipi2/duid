package com.juzipi2.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import java.util.UUID;

public class DUID {

    public static String getDUID(Context context) {
        SharedPreferences sp = context.getSharedPreferences(context.getPackageName() + "_duid", Context.MODE_PRIVATE);
        String duid = sp.getString("duid", null);
        if (duid == null) {
            duid = generateDUID(context);
            sp.edit().putString("duid", duid).apply();
        }
        return duid;
    }

    private static String generateDUID(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            String androidId = getAndroidId(context);
            if (androidId != null) {
                return fromString(androidId);
            }
        }
        String imei = DeviceUtils.getImei(context);
        if (imei != null) {
            return fromString(imei);
        }
        String wifiMac = DeviceUtils.getWifiMacAddress();
        if (wifiMac != null) {
            return fromString(wifiMac);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String androidId = getAndroidId(context);
            if (androidId != null) {
                return fromString(androidId);
            }
        }
        return UUID.randomUUID().toString();
    }

    private static String getAndroidId(Context context) {
        String androidId = DeviceUtils.getAndroidId(context);
        if (androidId != null && !"9774d56d682e549c".equals(androidId)) {
            return androidId;
        }
        return null;
    }

    private static String fromString(String name) {
        return UUID.nameUUIDFromBytes(name.getBytes()).toString();
    }
}
