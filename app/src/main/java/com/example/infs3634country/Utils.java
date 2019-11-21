package com.example.infs3634country;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.ImageView;

import com.example.infs3634country.R;
import com.pixplicity.sharp.Sharp;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


// Class Source: https://muetsch.io/how-to-load-svg-into-imageview-by-url-in-android.html
public class Utils {
    private static OkHttpClient httpClient;

    public static void fetchSvg(final Context context, String url, final ImageView target) {
        if (httpClient == null) {
            // Use cache for performance and basic offline capability
            httpClient = new OkHttpClient.Builder()
                    .cache(new Cache(context.getCacheDir(), 5 * 1024 * 1014))
                    .build();
        }

        Request request = new Request.Builder().url(url).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                target.setImageDrawable(target.getResources().getDrawable(R.drawable.ic_launcher_background));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream stream = response.body().byteStream();

                try {
                    AssetManager assetManager = context.getAssets();
                    Sharp.loadInputStream(stream).withAssets(assetManager).into(target);
                } catch(IllegalArgumentException a) {
                    target.setImageDrawable(target.getResources().getDrawable(R.mipmap.ic_launcher));
                } catch (StringIndexOutOfBoundsException b) {
                    target.setImageDrawable(target.getResources().getDrawable(R.mipmap.ic_launcher));
                } catch (IllegalStateException c) {
                    target.setImageDrawable(target.getResources().getDrawable(R.mipmap.ic_launcher));
                }
                stream.close();
            }
        });
    }
}