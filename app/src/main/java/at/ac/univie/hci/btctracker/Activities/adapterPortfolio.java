package at.ac.univie.hci.btctracker.Activities;

import android.app.AlertDialog;
import android.content.Context;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


import at.ac.univie.hci.btctracker.Data.Transaction;
import at.ac.univie.hci.btctracker.Manager.Manager;
import at.ac.univie.hci.btctracker.R;

/**
 * Created by Zhanat on 04/05/2018.
 */


public class adapterPortfolio extends RecyclerView.Adapter<adapterPortfolio.TransactionViewHolder> {
    private Context mCtx;
    private ArrayList<Transaction> listTransaction;
    private int TransactionId;
    private Manager manager;
    private int p;


    public adapterPortfolio(Context mCtx, ArrayList<Transaction> listTransaction) {
        this.mCtx = mCtx;
        this.listTransaction = listTransaction;
        this.manager = new Manager(mCtx);

    }

    public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item, null);
        return new TransactionViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final TransactionViewHolder holder, int position) {
        //getting the product of the specified position
        Transaction transaction = listTransaction.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(transaction.getCurrencyId());

        holder.amountTransaction.setText(transaction.getAmountOfTransaction().toString());
        if (transaction.getPriceInBtc() == 0.0 && transaction.getPriceInEur() == 0.0) {
            holder.price.setText("Price:" + " " + transaction.getPriceInUsd().toString() + " " + "$");
        } else if (transaction.getPriceInUsd() == 0.0 && transaction.getPriceInEur() == 0.0) {
            holder.price.setText("Price:" + " " + transaction.getPriceInBtc().toString() + " " + "BTC");
        } else {
            holder.price.setText("Price:" + " " + transaction.getPriceInEur().toString() + " " + "€");
        }
        holder.buy.setText(transaction.getBuy());
        holder.date.setText("Date:" + " " + transaction.getDate());
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(transaction.getImage()));
        holder.mRemoveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mCtx);
                alertDialogBuilder.setMessage("Are you sure,You wanted to make decision");

                int position = holder.getAdapterPosition();
                p = position;
/**
 * Transaction Delete
 */
                try {
                    manager.deleteTransaction(listTransaction.get(p).getTransactionId());
                    listTransaction.remove(position);
                    notifyItemRemoved(position);


                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
                notifyItemRangeChanged(position, listTransaction.size());
                Toast.makeText(mCtx, " Transaction removed : " + p, Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public int getItemCount() {
        return listTransaction.size();
    }


    class TransactionViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, price, amountTransaction, buy, date;
        ImageView imageView;
        ImageButton mRemoveButton;

        public TransactionViewHolder(View itemView) {
            super(itemView);
            mRemoveButton = itemView.findViewById(R.id.id_remove);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            amountTransaction = itemView.findViewById(R.id.amountTransaction);
            price = itemView.findViewById(R.id.price);
            buy = itemView.findViewById(R.id.buy);
            date = itemView.findViewById(R.id.date);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}