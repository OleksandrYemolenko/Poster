package com.codersinlow.poster;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // FirebaseAuth auth = FirebaseAuth.getInstance();

          requestWindowFeature(Window.FEATURE_NO_TITLE);
          getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                  WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
       /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbara);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); */

        title = "Dishes";

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_favorites:
                        Toast.makeText(MainActivity.this, "action_favorites", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        setTitle(title);

        recView = findViewById(R.id.dishRecView);

        /*recView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && bottomNavigationView.isShown()) {
                    bottomNavigationView.setVisibility(View.GONE);
                } else if (dy < 0 ) {
                    bottomNavigationView.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);
            }
        }); */

        manager = new LinearLayoutManager(this);

        recView.setLayoutManager(manager);
        adapter = new RecyclerAdapter();
        recView.setAdapter(adapter);
        adapter.addAll(getDishItem());
    }

    public static List<DishItem> getDishItem() {
        ArrayList<DishItem> dishList = new ArrayList<>();


        // TODO Передать в dishList список блюд с именем (title), URL на картинку (imgURL) и ценой (price)
        dishList.add(new DishItem("Hot Dog", "https://www.micccp.com/wp/wp-content/uploads/2016/02/4111.jpg", "30.00₴", "op"));
        /*dishList.add(new DishItem("Coffee", "https://million-wallpapers.ru/wallpapers/2/56/294040267495523.jpg", "25.00₴"));
        dishList.add(new DishItem("Juice", "http://images.media-allrecipes.com/userphotos/960x960/3758394.jpg", "28.00₴"));
        dishList.add(new DishItem("Pizza", "http://food.studiofact.ru/upload/iblock/706/7062914c3c69c543991a45ca006e456b.jpg", "300.00₴"));
        dishList.add(new DishItem("Cake", "https://31p86334w2bvkz0249eyr0cr-wpengine.netdna-ssl.com/wp-content/uploads/2013/04/triple-chocolate-cake-4-600x900.jpg", "150.00₴"));
*/
        return dishList;
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
            final DishItem dishItem = items.get(position);

            holder.bind(dishItem);

            holder.itemView.setOnClickListener (new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean expanded = dishItem.isExpanded();
                    dishItem.setExpanded(!expanded);
                    notifyItemChanged(position);
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
        private View subItem;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            subItem = itemView.findViewById(R.id.sub_item);
            title = (TextView) itemView.findViewById(R.id.title);
            price = (TextView) itemView.findViewById(R.id.price);
            title.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf"));
            price.setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Thin.ttf"));
            image = (ImageView) itemView.findViewById(R.id.imgD);
            //image.setImageResource(R.drawable.no_img); //
        }

        public void bind(DishItem recyclerItem) {
            boolean expanded = recyclerItem.isExpanded();

            title.setText(recyclerItem.getName());
            price.setText(recyclerItem.getPrice());
            Picasso.with(context)
                    .load(recyclerItem.getURL())
                    // optional
                    .into(image);

            subItem.setVisibility(expanded ? View.VISIBLE : View.GONE);
        }
    }
}
