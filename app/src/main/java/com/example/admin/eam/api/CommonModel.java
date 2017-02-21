package com.example.admin.eam.api;

/**
 * Created by Administrator on 2015/10/30.
 */
public class CommonModel {
    static boolean isIsListenerFragment = true;
    static boolean isListener = true;

    public static boolean isListener() {
        return isListener;
    }

    public static void setIsListener(boolean isListener) {
        CommonModel.isListener = isListener;
    }

    public static boolean isIsListenerFragment() {
        return isIsListenerFragment;
    }

    public static void setIsIsListenerFragment(boolean isIsListenerFragment) {
        CommonModel.isIsListenerFragment = isIsListenerFragment;
    }
}
