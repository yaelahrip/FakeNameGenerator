package com.rexaflux.fakename;

/**
 * Created by bean on 06/01/18.
 */

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;




/**
 * Created by bean on 23/12/17.
 */

public class request extends AsyncTask<Void, Void, String> {


    private String myResponse;

    @RequiresApi(api = Build.VERSION_CODES.O)

    public String curl (String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            // Do something with the response.
        } catch (IOException e) {
            e.printStackTrace();
        }


        myResponse = response.body().string();
        return myResponse;
    }

    private String buildHmacSignature(String value, String secret) {
        String result;
        try {
            Mac hmacSHA512 = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(),
                    "HmacSHA512");
            hmacSHA512.init(secretKeySpec);

            byte[] digest = hmacSHA512.doFinal(value.getBytes());
            BigInteger hash = new BigInteger(1, digest);
            result = hash.toString(16);
            if ((result.length() % 2) != 0) {
                result = "0" + result;
            }
        } catch (IllegalStateException | InvalidKeyException | NoSuchAlgorithmException ex) {
            throw new RuntimeException("Problemas calculando HMAC", ex);
        }
        return result;
    }


    @Override
    protected String doInBackground(Void... voids) {
        return null;
    }
}
