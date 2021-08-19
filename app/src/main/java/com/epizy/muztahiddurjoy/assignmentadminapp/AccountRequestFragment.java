package com.epizy.muztahiddurjoy.assignmentadminapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.epizy.muztahiddurjoy.assignmentadminapp.Datasets.AccoutReqDataset;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountRequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountRequestFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountRequestFragment() {

    }

    public static AccountRequestFragment newInstance(String param1, String param2) {
        AccountRequestFragment fragment = new AccountRequestFragment();
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
    private ArrayList<AccoutReqDataset> arrayList;
    private ArrayList<String> keys;
    private DatabaseReference reference;
    private AccountRequestAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_account_request, container, false);
        recyclerView = root.findViewById(R.id.recycler_account_request);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setReverseLayout(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        arrayList = new ArrayList<AccoutReqDataset>();
        keys = new ArrayList<String>();
        reference = FirebaseDatabase.getInstance().getReference().child("pendingUser");
        adapter = new AccountRequestAdapter(arrayList, getActivity(), keys);
       recyclerView.setAdapter(adapter);
       reference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               arrayList.clear();
               keys.clear();
               for(DataSnapshot ds : snapshot.getChildren()){
                   AccoutReqDataset dataset = ds.getValue(AccoutReqDataset.class);
                   if (!arrayList.contains(dataset)){
                       arrayList.add(dataset);
                       keys.add(ds.getKey());
                   }
               }
               adapter.notifyDataSetChanged();
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
        return root;
    }

}