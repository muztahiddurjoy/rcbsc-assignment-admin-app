package com.epizy.muztahiddurjoy.assignmentadminapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.epizy.muztahiddurjoy.assignmentadminapp.Datasets.OnlineClassDataset;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnlineClass#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnlineClass extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OnlineClass() {
        // Required empty public constructor
    }

    public static OnlineClass newInstance(String param1, String param2) {
        OnlineClass fragment = new OnlineClass();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private TextInputEditText title,topic,subject,teacher,classtime, meeting_id, meeting_pass, meeting_link;
    private Button submit;
    private DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_online_class, container, false);
        title = root.findViewById(R.id.class_title);
        topic = root.findViewById(R.id.class_topic);
        subject = root.findViewById(R.id.class_subject);
        teacher = root.findViewById(R.id.class_teacher);
        classtime = root.findViewById(R.id.class_time);
        meeting_id = root.findViewById(R.id.class_meeting_id);
        meeting_pass = root.findViewById(R.id.class_pass);
        meeting_link  = root.findViewById(R.id.class_link);
        submit = root.findViewById(R.id.submit_online_class);
        reference = FirebaseDatabase.getInstance().getReference().child("onlineclass");
        submit.setOnClickListener((v)->{
            if (!title.getText().toString().isEmpty() || !topic.getText().toString().isEmpty() || !subject.getText().toString().isEmpty() || !teacher.getText().toString().isEmpty() || !meeting_link.getText().toString().isEmpty() || !meeting_id.getText().toString().isEmpty() || !meeting_pass.getText().toString().isEmpty() ||  !classtime.getText().toString().isEmpty()) {
                reference.setValue(new OnlineClassDataset(title.getText().toString(), topic.getText().toString(), subject.getText().toString(), teacher.getText().toString(), meeting_link.getText().toString(), meeting_id.getText().toString(), meeting_pass.getText().toString(), classtime.getText().toString())).addOnSuccessListener((s) -> {
                    new SendNotification(getActivity()).sendNotification("New Online Class","New online class has been scheduled by "+teacher.getText().toString());
                    title.setText("");
                    topic.setText("");
                    subject.setText("");
                    teacher.setText("");
                    classtime.setText("");
                    meeting_id.setText("");
                    meeting_pass.setText("");
                    meeting_link.setText("");
                    Snackbar.make(v,"Online class scheduled", BaseTransientBottomBar.LENGTH_SHORT).show();

                });
            }
            else {
                Snackbar.make(v,"please fill the fields", BaseTransientBottomBar.LENGTH_SHORT).show();
            }
        });
        return root;
    }
}