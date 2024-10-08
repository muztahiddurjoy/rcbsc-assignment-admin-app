package com.epizy.muztahiddurjoy.assignmentadminapp;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReportViewHolder extends RecyclerView.ViewHolder {
    public TextView email, description;
    public Button copybtn, downloadImage;
    public ImageView imageView;
    public ReportViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        email = itemView.findViewById(R.id.email_report);
        description = itemView.findViewById(R.id.description_report);
        copybtn = itemView.findViewById(R.id.copy_email_btn);
        downloadImage = itemView.findViewById(R.id.download_image_btn);
        imageView = itemView.findViewById(R.id.support_image);
    }
}
