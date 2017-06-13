package com.example.administrator.module.aes;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/**
 * Created by MeiNiu-Scholar on 2016/12/6 0006.
 */
public class AesRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    AesRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        Log.e("requestBody", "在这里：");
        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
        JsonWriter jsonWriter = gson.newJsonWriter(writer);
        adapter.write(jsonWriter, value);
        jsonWriter.close();
//        byte[] bytes = buffer.readByteArray();
        String request = buffer.readString(UTF_8);
        Log.e("requestBody", "jia密前：" + request);
        String after = Aes.encrypt(request);
//        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
        Log.e("requestBody", "jia密后：" + after);
        return RequestBody.create(MEDIA_TYPE, request);
    }
}
