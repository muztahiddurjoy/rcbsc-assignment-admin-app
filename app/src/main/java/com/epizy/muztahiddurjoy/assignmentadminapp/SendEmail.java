package com.epizy.muztahiddurjoy.assignmentadminapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.epizy.muztahiddurjoy.assignmentadminapp.Datasets.EmailDataAlpha;
import com.epizy.muztahiddurjoy.assignmentadminapp.Datasets.TemplateParams;
import com.epizy.muztahiddurjoy.assignmentadminapp.NotificationSendingApi.NotificationPlaceHolder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SendEmail {
    private NotificationPlaceHolder placeHolder;
    private EmailDataAlpha alpha;
    Context context;

    public SendEmail(Context context) {
        this.context = context;
    }

    public void sendEmail(String tempID, TemplateParams params){

      Retrofit retrofit = new Retrofit.Builder()
              .baseUrl("https://api.emailjs.com/api/v1.0/")
              .addConverterFactory(GsonConverterFactory.create())
              .build();
      placeHolder = retrofit.create(NotificationPlaceHolder.class);
      alpha = new EmailDataAlpha("user_fhPsPDZEYMoMUCM7v4nbq","service_ahyfbnv",tempID,params);
      Call<EmailDataAlpha> res = placeHolder.sendEmailRequest(alpha);
      res.enqueue(new Callback<EmailDataAlpha>() {
          @Override
          public void onResponse(Call<EmailDataAlpha> call, Response<EmailDataAlpha> response) {
              Log.d("CODE", String.valueOf(response.code()));
              Log.d("CODE", String.valueOf(response.message()));
          }

          @Override
          public void onFailure(Call<EmailDataAlpha> call, Throwable t) {
              Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
          }
      });

  }

}
