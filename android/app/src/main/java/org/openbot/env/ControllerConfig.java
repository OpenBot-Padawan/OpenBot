package org.openbot.env;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;

public class ControllerConfig {
    private static ControllerConfig _controllerConfig;
    SharedPreferences preferences;

    enum VIDEO_SERVER_TYPE {
        WEBRTC,
        RTSP
    }

    enum CONTROL_MODES {
        SLIDERS,
        TILT
    }

    private boolean isMirrored = true;
    private boolean mute = true;

    private String currentServerType;

    public static ControllerConfig getInstance() {
        if (_controllerConfig == null) {
            synchronized (PhoneController.class) {
                if (_controllerConfig == null) _controllerConfig = new ControllerConfig();
            }
        }

        return _controllerConfig;
    }

    void init(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        currentServerType = get ("video_server", "WEBRTC");
    }

    private void set (String name, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(name, value);
        editor.apply();
    }

    private String get (String name, String defaultValue) {
        try {
            return preferences.getString(name, defaultValue);
        } catch (ClassCastException e) {
            return defaultValue;
        }
    }

    private Boolean getBoolean (String name, Boolean defaultValue) {
        try {
            return preferences.getBoolean(name, defaultValue);
        } catch (ClassCastException e) {
            return defaultValue;
        }
    }

    private void setBoolean (String name, boolean value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(name, value);
        editor.apply();
    }

    // specific settings
    public boolean isMirrored() {
        return getBoolean("MIRRORED", true);
    }

    public void setMirrored(boolean mirrored) {
        setBoolean("MIRRORED", mirrored);
    }

    public boolean isMute() {
        return getBoolean("MUTE", true);
    }

    public void setMute(boolean mute) {
        setBoolean("MUTE", mute);
    }

    public String getVideoServerType() {
        return get ("video_server", "WEBRTC");
    }
    public void setVideoServerType(String type) {
        set ("video_server", type);
    }
}
