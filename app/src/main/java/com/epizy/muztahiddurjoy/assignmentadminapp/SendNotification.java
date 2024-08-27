package com.epizy.muztahiddurjoy.assignmentadminapp;

import android.content.Context;
import android.widget.Toast;

import com.epizy.muztahiddurjoy.assignmentadminapp.Datasets.NotificationDatasetAlpha;
import com.epizy.muztahiddurjoy.assignmentadminapp.Datasets.NotificationDatasetBeta;
import com.epizy.muztahiddurjoy.assignmentadminapp.NotificationSendingApi.NotificationPlaceHolder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SendNotification {
    private NotificationDatasetAlpha alpha;
    private NotificationDatasetBeta beta;
    private NotificationPlaceHolder placeHolder;
     Context context;

    public SendNotification(Context context) {
        this.context = context;
    }

    public void sendNotification(String title, String body){
        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl("https://fcm.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        placeHolder = retrofit.create(NotificationPlaceHolder.class);
        alpha = new NotificationDatasetAlpha(title, body);
        beta = new NotificationDatasetBeta("/topics/student",alpha);
        Call<NotificationDatasetBeta> res = placeHolder.getFcmMessage(beta);
        res.enqueue(new Callback<NotificationDatasetBeta>() {
            @Override
            public void onResponse(Call<NotificationDatasetBeta> call, Response<NotificationDatasetBeta> response) {

            }

            @Override
            public void onFailure(Call<NotificationDatasetBeta> call, Throwable t) {
                Toast.makeText(context, t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }
}
