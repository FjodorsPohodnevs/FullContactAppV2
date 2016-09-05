package com.fjodors.fullcontactappv2.api;

import com.fjodors.fullcontactappv2.R;

import java.net.HttpURLConnection;
import java.net.UnknownHostException;

/**
 * Created by Fjodors on 05.09.2016.
 */
public class ErrorManager {
    public static final int INVALID_DOMAIN = 422;

    public static int getErrorMessage(Throwable error) {
        if (error.getMessage() != null
                && String.valueOf(HttpURLConnection.HTTP_ACCEPTED)
                .equals(error.getMessage())) {
            return R.string.error_202;
        } else if (error instanceof UnknownHostException) {
            return R.string.error_network;
        }
        return R.string.error_generic;
    }

    public static int getErrorMessage(int httpStatus) {
        switch (httpStatus) {
            case HttpURLConnection.HTTP_NOT_FOUND:
                return R.string.error_404;
            case INVALID_DOMAIN:
                return R.string.error_422;
            case HttpURLConnection.HTTP_UNAVAILABLE:
                return R.string.error_503;
            default:
                return R.string.error_generic;
        }
    }
}
