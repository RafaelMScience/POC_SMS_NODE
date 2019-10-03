package com.example.loginfirebase.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginfirebase.CadastroActivity;
import com.example.loginfirebase.R;
import com.example.loginfirebase.Users;

import java.util.ArrayList;

public class MensagemAdapter extends RecyclerView.Adapter<MensagemAdapter.ViewHolder> {

    private int resource;
    private ArrayList<Users> mensagemList;

    public MensagemAdapter(ArrayList<Users> mensagemList, int resource){
        this.mensagemList = mensagemList;
        this.resource = resource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int index) {
        Users user = mensagemList.get(index);
        viewHolder.textViewMensagem.setText(user.getLogin());


    }

    @Override
    public int getItemCount() {
        return mensagemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewMensagem;

        public View view;

        public ViewHolder(View view){
            super(view);

            this.view = view;
            this.textViewMensagem = (TextView) view.findViewById(R.id.textViewMensagem);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                }
            });
        }
    }
}
