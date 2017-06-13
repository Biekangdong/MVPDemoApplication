package com.example.administrator.module.aes;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by MeiNiu-Scholar on 2016/12/6 0006.
 */

public class AesResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;//gson对象
    private final TypeAdapter<T> adapter;

    /**
     * 构造器
     */
    public AesResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    /**
     * 转换
     *
     * @param responseBody
     * @return
     * @throws IOException
     */
    @Override
    public T convert(ResponseBody responseBody) throws IOException {
        String result = responseBody.string();
        result = Aes.decrypt(result);
        if (result != null) {
            result = result.replaceAll("\0", "");
        }
        Log.e("解密后的JSON串", result);
        ResponseBody responseBodyNew = ResponseBody.create(responseBody.contentType(), result);
        JsonReader jsonReader = gson.newJsonReader(responseBodyNew.charStream());
        try {
            return adapter.read(jsonReader);
        } finally {
            jsonReader.close();
            responseBodyNew.close();
            responseBody.close();
        }
    }
}
