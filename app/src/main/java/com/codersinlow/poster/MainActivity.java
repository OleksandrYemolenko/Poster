package com.codersinlow.poster;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recView;
    private LinearLayoutManager manager;
    private RecyclerAdapter adapter;
    private Intent intent;
    private String title;
    private Context context;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

          requestWindowFeature(Window.FEATURE_NO_TITLE);
          getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                  WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbara);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); */

        title = "Dishes";

        setTitle(title);

        recView = findViewById(R.id.dishRecView);

        manager = new LinearLayoutManager(this);

        recView.setLayoutManager(manager);
        adapter = new RecyclerAdapter();
        recView.setAdapter(adapter);
        adapter.addAll(DishItem.getDishItem());
    }

  /*  public void ChangeActivity(int pos, String title) {
        try {
            intent = new Intent(this, AlgoContentPageActivity.class);
            intent.putExtra("pos", pos);
            intent.putExtra("act", "a");
            intent.putExtra("title", title);
            startActivity(intent);
        } catch(Exception e) {
            Toast.makeText(getApplicationContext(), "Exception", Toast.LENGTH_LONG).show();
        }
    } */

    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
        ArrayList<DishItem> items = new ArrayList<>();

        public void addAll(List<DishItem> items) {
            int pos = getItemCount();
            this.items.addAll(items);
            notifyItemRangeInserted(pos, this.items.size());
        }

        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            context = parent.getContext();
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dishitem, parent, false);
            return new RecyclerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder holder, final int position) {
            holder.bind(items.get(position));
            holder.itemView.setOnClickListener (new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toast.makeText(getApplicationContext(), Integer.toString(position), Toast.LENGTH_SHORT).show();
                    //ChangeActivity(position, items.get(position).getTitle());

                }
            });
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView title, price;
        private ImageView image;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            price = (TextView) itemView.findViewById(R.id.price);
            title.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf"));
            price.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf"));
            image = (ImageView) itemView.findViewById(R.id.imgD);
            //image.setImageResource(R.drawable.no_img); //TODO check it
        }

        public void bind(DishItem recyclerItem) {
            title.setText(recyclerItem.getName());
            price.setText(recyclerItem.getPrice());
            Picasso.with(context)
                    .load(recyclerItem.getURL())
                                         // optional
                    .into(image);
        }
    }
}
