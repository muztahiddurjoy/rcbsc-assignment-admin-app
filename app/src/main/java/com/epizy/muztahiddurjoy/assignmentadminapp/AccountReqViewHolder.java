package com.epizy.muztahiddurjoy.assignmentadminapp;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AccountReqViewHolder extends RecyclerView.ViewHolder {
    public TextView nameUser, roll, cleass, idUser, phone, section;
    public Button approve, reject;

    public AccountReqViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        nameUser = itemView.findViewById(R.id.name_account_request_user);
        roll = itemView.findViewById(R.id.roll_account_request_user);
        cleass = itemView.findViewById(R.id.cleass_account_request_user);
        idUser = itemView.findViewById(R.id.id_account_request_user);
        phone = itemView.findViewById(R.id.phone_account_request_user);
        section = itemView.findViewById(R.id.section_account_request_user);

        approve = itemView.findViewById(R.id.account_request_apporve);
        reject = itemView.findViewById(R.id.reject_account_request);
    }
}
