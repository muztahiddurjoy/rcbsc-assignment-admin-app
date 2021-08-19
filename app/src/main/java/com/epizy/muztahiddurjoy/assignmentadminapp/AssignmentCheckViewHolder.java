package com.epizy.muztahiddurjoy.assignmentadminapp;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AssignmentCheckViewHolder extends RecyclerView.ViewHolder {
    public TextView cleass, name,roll,section,subject,submitted_to, time, title;
    public Button download;
    public AssignmentCheckViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        cleass = itemView.findViewById(R.id.assignment_check_class);
        name = itemView.findViewById(R.id.assignment_check_name);
        roll = itemView.findViewById(R.id.assignment_check_roll);
        section = itemView.findViewById(R.id.assignment_check_section);
        subject = itemView.findViewById(R.id.assignment_check_subject);
        submitted_to = itemView.findViewById(R.id.assignment_check_submitted_to);
        time = itemView.findViewById(R.id.assignment_check_time);
        title = itemView.findViewById(R.id.assignment_check_title);
        download = itemView.findViewById(R.id.download_assignment);
    }
}
