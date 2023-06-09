package com.example.workflow.utils;

import camundajar.impl.com.google.gson.Gson;
import camundajar.impl.com.google.gson.JsonElement;
import com.example.workflow.exceptions.HttpRequestException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Objects;

public class MessageServiceRestHandler {
    private static final OkHttpClient client;
    private static final Gson gson;

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceRestHandler.class);

    static {
        client = new OkHttpClient().newBuilder().build();
        gson = new Gson();
    }

    private MessageServiceRestHandler() {
    }

    public static OkHttpClient getClient() {
        return client;
    }

    public static JsonElement execute(Request request) throws IOException, HttpRequestException {
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                JsonElement jsonElement = gson.fromJson(Objects.requireNonNull(response.body()).charStream(), JsonElement.class);
                return jsonElement;
            } else {
                logger.error("Response failed with status code " + response.code() + " and error message: " + response.message());
                return null;
            }
        } catch (Exception e) {
            throw new HttpRequestException(e.getMessage());
        }
    }
}

