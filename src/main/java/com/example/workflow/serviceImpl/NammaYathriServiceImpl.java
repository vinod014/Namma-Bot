package com.example.workflow.serviceImpl;

import camundajar.impl.com.google.gson.JsonElement;
import com.example.workflow.helpers.NammaYathriApiHelper;
import com.example.workflow.services.NammaYathriService;
import okhttp3.FormBody;
import okhttp3.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NammaYathriServiceImpl implements NammaYathriService {
    // TODO : Replace helpers with SDK Calls
    private final MediaType mediaType = MediaType.parse("application/json");

    @Autowired
    private NammaYathriApiHelper nammaYathriApiHelper;

    @Override
    public JsonElement getStarredPlaces(String userPhone) throws IOException {
        return nammaYathriApiHelper.get(String.format("/starred-places?user=%s", userPhone));
    }

    @Override
    public JsonElement createStarredPlace(String latitude, String longitude, String name, String userPhone) throws IOException {
        FormBody body = new FormBody.Builder()
                .add("latitude", latitude)
                .add("longitude", longitude)
                .add("name", name)
                .add("phone", userPhone)
                .build();

        return nammaYathriApiHelper.post(String.format("/starred-places", userPhone), body);
    }

    @Override
    public JsonElement deleteStarredPlace(String placeId) throws IOException {
        return nammaYathriApiHelper.delete(String.format("/starred-places/%s", placeId), null);
    }

    @Override
    public JsonElement generateEstimate(String sourceLatitude, String sourceLongitude, String destinationLatitude, String destinationLongitude) throws IOException {
        FormBody body = new FormBody.Builder()
                .add("source_lat", sourceLatitude)
                .add("source_long", sourceLongitude)
                .add("dest_lat", destinationLatitude)
                .add("dest_long", destinationLongitude)
                .build();

        return nammaYathriApiHelper.post("/ride-estimate", body);
    }

    @Override
    public JsonElement findNearByRide(String sourceLatitude, String sourceLongitude, String destinationLatitude, String destinationLongitude, String rideSelectionMode) throws IOException {
        FormBody body = new FormBody.Builder()
                .add("source_lat", sourceLatitude)
                .add("source_long", sourceLongitude)
                .add("dest_lat", destinationLatitude)
                .add("dest_long", destinationLongitude)
                .build();

        return nammaYathriApiHelper.post(String.format("/nearby-pickups?choose=%s", rideSelectionMode), body);
    }

    @Override
    public JsonElement bookRide(String driverId, String rideId) throws IOException {
        FormBody body = new FormBody.Builder()
                .add("driver_id", driverId)
                .add("ride_id", rideId)
                .build();

        return nammaYathriApiHelper.post("/book-ride", body);
    }

    @Override
    public JsonElement cancelBooking(String rideId, String cancellationReason) throws IOException {
        FormBody body = new FormBody.Builder()
                .add("ride_id", rideId)
                .add("cancellation_reason", cancellationReason)
                .build();

        return nammaYathriApiHelper.post("/cancel-ride", body);
    }

    @Override
    public JsonElement rateDriver(String rideId, String rating) throws IOException {
        FormBody body = new FormBody.Builder()
                .add("ride_id", rideId)
                .add("rating", rating)
                .build();

        return nammaYathriApiHelper.post("/rate-ride", body);
    }
}
