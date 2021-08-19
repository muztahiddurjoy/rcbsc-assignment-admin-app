package com.epizy.muztahiddurjoy.assignmentadminapp.ui.home;

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

import com.epizy.muztahiddurjoy.assignmentadminapp.Datasets.AssignmentDataset;
import com.epizy.muztahiddurjoy.assignmentadminapp.Datasets.NotificationDatasetAlpha;
import com.epizy.muztahiddurjoy.assignmentadminapp.Datasets.NotificationDatasetBeta;
import com.epizy.muztahiddurjoy.assignmentadminapp.NotificationSendingApi.NotificationPlaceHolder;
import com.epizy.muztahiddurjoy.assignmentadminapp.R;
import com.epizy.muztahiddurjoy.assignmentadminapp.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private TextInputEditText title, subject, cleass, submission_date;
    private Button submit_button;
    private DatabaseReference reference;
    private AssignmentDataset assignmentDataset;
    private NotificationDatasetAlpha alpha;
    private NotificationDatasetBeta beta;
    private NotificationPlaceHolder placeHolder;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        title = root.findViewById(R.id.assignment_title);
        subject = root.findViewById(R.id.assignment_sub);
        cleass = root.findViewById(R.id.assignment_class);
        submission_date = root.findViewById(R.id.assignment_date);
        submit_button = root.findViewById(R.id.assignment_upload);
        reference = FirebaseDatabase.getInstance().getReference().child("assignments");
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleT = title.getText().toString();
                String subjectT = subject.getText().toString();
                String classT = cleass.getText().toString();
                String submitionT = submission_date.getText().toString();
                if (titleT!=null||subjectT!=null||classT!=null||submitionT!=null){
                    String date = new Date().toLocaleString();
                    assignmentDataset = new AssignmentDataset(titleT,submitionT,date,classT,subjectT);
                    reference.push().setValue(assignmentDataset).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<Void> task) {
                            if (task.isSuccessful()){
                                sendNoticeNotification();
                                title.setText("");
                                subject.setText("");
                                cleass.setText("");
                                submission_date.setText("");
                                Toast.makeText(getActivity(), "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(getActivity(), "Please Fill all of the fields", Toast.LENGTH_SHORT).show();
                }
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
        alpha = new NotificationDatasetAlpha("New Assignment", "New assignment has been uploaded for class"+cleass.getText().toString());
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