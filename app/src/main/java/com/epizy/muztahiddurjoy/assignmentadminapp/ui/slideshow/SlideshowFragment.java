package com.epizy.muztahiddurjoy.assignmentadminapp.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.epizy.muztahiddurjoy.assignmentadminapp.Datasets.NoticeDataset;
import com.epizy.muztahiddurjoy.assignmentadminapp.Datasets.NotificationDatasetAlpha;
import com.epizy.muztahiddurjoy.assignmentadminapp.Datasets.NotificationDatasetBeta;
import com.epizy.muztahiddurjoy.assignmentadminapp.NotificationSendingApi.NotificationPlaceHolder;
import com.epizy.muztahiddurjoy.assignmentadminapp.R;
import com.epizy.muztahiddurjoy.assignmentadminapp.databinding.FragmentSlideshowBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;
    private TextInputEditText tite, des, cleass;
    private Button sub;
    private NoticeDataset dataset;
    private DatabaseReference reference;
    private NotificationDatasetAlpha alpha;
    private NotificationDatasetBeta beta;
    private NotificationPlaceHolder placeHolder;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel = new ViewModelProvider(this).get(SlideshowViewModel.class);
        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        tite = root.findViewById(R.id.notice_title);
        des = root.findViewById(R.id.notice_description);
        cleass = root.findViewById(R.id.notice_class);
        sub = root.findViewById(R.id.upload_notice_btn);
        reference = FirebaseDatabase.getInstance().getReference().child("notice");
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = tite.getText().toString();
                String desc = des.getText().toString();
                String cleas = cleass.getText().toString();
                dataset = new NoticeDataset(title,desc,cleas,new Date().toLocaleString());
                reference.push().setValue(dataset).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        sendNoticeNotification();
                        tite.setText("");
                        des.setText("");
                        cleass.setText("");
                        Snackbar.make(v,"Notice Uploaded Successfully", BaseTransientBottomBar.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Snackbar.make(v,e.getMessage(),BaseTransientBottomBar.LENGTH_LONG).show();
                    }
                });
            }
        });
        return root;
    }

    private void sendNoticeNotification() {
        Retrofit retrofit = new  Retrofit.Builder()
                .baseUrl("https://fcm.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        placeHolder = retrofit.create(NotificationPlaceHolder.class);
        alpha = new NotificationDatasetAlpha("New Notice", "New notice uploaded for class"+cleass.getText().toString());
        beta = new NotificationDatasetBeta("/topics/student",alpha);
        Call<NotificationDatasetBeta> res = placeHolder.getFcmMessage(beta);
        res.enqueue(new Callback<NotificationDatasetBeta>() {
            @Override
            public void onResponse(Call<NotificationDatasetBeta> call, Response<NotificationDatasetBeta> response) {
            }

            @Override
            public void onFailure(Call<NotificationDatasetBeta> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}