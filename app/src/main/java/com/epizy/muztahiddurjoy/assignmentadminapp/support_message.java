package com.epizy.muztahiddurjoy.assignmentadminapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.epizy.muztahiddurjoy.assignmentadminapp.Datasets.SupportDataset;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link support_message#newInstance} factory method to
 * create an instance of this fragment.
 */
public class support_message extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public support_message() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment support_message.
     */
    // TODO: Rename and change types and number of parameters
    public static support_message newInstance(String param1, String param2) {
        support_message fragment = new support_message();
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
    private RecyclerView recyclerView;
    private ArrayList<SupportDataset> arrayList;
    private FirebaseRecyclerOptions<SupportDataset> options;
    private FirebaseRecyclerAdapter<SupportDataset,ReportViewHolder> adapter;
    private DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_support_message, container, false);
        recyclerView = root.findViewById(R.id.recycler_report);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setReverseLayout(true);
        recyclerView.setLayoutManager(manager);
        arrayList = new ArrayList<SupportDataset>();
        reference = FirebaseDatabase.getInstance().getReference().child("reports");
        reference.keepSynced(true);
        options = new FirebaseRecyclerOptions.Builder<SupportDataset>().setQuery(reference,SupportDataset.class).build();
        adapter = new FirebaseRecyclerAdapter<SupportDataset, ReportViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull ReportViewHolder holder, int position, @NonNull @NotNull SupportDataset model) {
                holder.email.setText(model.getEmail());
                holder.description.setText(model.getDescription());
                holder.copybtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("label", model.getEmail());
                        clipboard.setPrimaryClip(clip);
                        Toast.makeText(getActivity(), "Email copied!", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @NonNull
            @NotNull
            @Override
            public ReportViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                return new ReportViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.support_row,parent,false));
            }
        };
        recyclerView.setAdapter(adapter);
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}