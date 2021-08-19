package com.epizy.muztahiddurjoy.assignmentadminapp.ui.gallery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.epizy.muztahiddurjoy.assignmentadminapp.AssignmentCheckViewHolder;
import com.epizy.muztahiddurjoy.assignmentadminapp.Datasets.AssignmentCheckDataset;
import com.epizy.muztahiddurjoy.assignmentadminapp.R;
import com.epizy.muztahiddurjoy.assignmentadminapp.databinding.FragmentGalleryBinding;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;

    private RecyclerView recyclerView;
    private ArrayList<AssignmentCheckDataset> arrayList;
    private FirebaseRecyclerOptions<AssignmentCheckDataset> options;
    private FirebaseRecyclerAdapter<AssignmentCheckDataset, AssignmentCheckViewHolder> adapter;
    private DatabaseReference reference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.recycler_assignments_check);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setReverseLayout(true);
        recyclerView.setLayoutManager(manager);
        arrayList = new ArrayList<AssignmentCheckDataset>();
        reference = FirebaseDatabase.getInstance().getReference().child("files").child("assignfiles");
        options = new FirebaseRecyclerOptions.Builder<AssignmentCheckDataset>().setQuery(reference,AssignmentCheckDataset.class).build();
        adapter = new FirebaseRecyclerAdapter<AssignmentCheckDataset, AssignmentCheckViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull @NotNull AssignmentCheckViewHolder holder, int position, @NonNull @NotNull AssignmentCheckDataset model) {
                holder.title.setText(model.getTitle());
                holder.subject.setText(model.getSubject());
                holder.name.setText(model.getName());
                holder.cleass.setText(model.getCleass());
                holder.roll.setText(model.getRoll());
                holder.section.setText(model.getSection());
                holder.submitted_to.setText(model.getSubmitted_to());
                holder.time.setText("Uploaded on: "+model.getTime());
                holder.download.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(model.getUrl()));
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @NotNull
            @Override
            public AssignmentCheckViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                return new AssignmentCheckViewHolder(LayoutInflater.from(getActivity()).inflate(R.layout.assignment_check_row,parent,false));
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}