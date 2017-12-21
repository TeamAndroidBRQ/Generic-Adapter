package com.example.csanchez.genericadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.csanchez.genericadapter.Model.AnyModel;
import com.example.csanchez.genericadapter.Model.Menu;
import com.example.csanchez.genericadapter.View.ItemViewHolder;
import com.example.csanchez.genericadapter.View.SectionViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by csanchez on 20/12/2017.
 */

public class ListWithSectionActivity extends AppCompatActivity {
    @BindView(R.id.anymodel_recycle)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anylist_activity);
        ButterKnife.bind(this);

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.addItems(AnyModel.getItensWithSection());
    }

    private GenericAdapter<AnyModel> adapter = new GenericAdapter<AnyModel>(this) {
        @Override
        public RecyclerView.ViewHolder setViewHolder(ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder viewHolder = null;
            if (viewType == SECTION_TYPE) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.item_view_holder, parent, false);
                viewHolder = new ItemViewHolder(getContext(), view);
            } else {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.section_view_holder, parent, false);
                viewHolder = new SectionViewHolder(getContext(), view);
            }
            return viewHolder;
        }

        @Override
        public void onBindData(RecyclerView.ViewHolder holder, AnyModel item) {
            if(holder instanceof ItemViewHolder){
                ItemViewHolder view = (ItemViewHolder)holder;
                view.setName(item.getName());
            } else if (holder instanceof SectionViewHolder){
                SectionViewHolder view = (SectionViewHolder)holder;
                view.setName(item.getName());
            }
        }
    };
}
