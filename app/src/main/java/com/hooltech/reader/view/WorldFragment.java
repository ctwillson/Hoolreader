package com.hooltech.reader.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.hooltech.reader.MainActivity;
import com.hooltech.reader.R;
import com.hooltech.reader.model.BookDetail;
import com.hooltech.reader.view.adapter.worldBean;
import com.hooltech.reader.view.adapter.worldAdapter;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorldFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorldFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView.Adapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<worldBean> mList;
    private EditText mEditText;
    private String TAG = "WorldFragment";

    public WorldFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WorldFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WorldFragment newInstance(String param1, String param2) {
        WorldFragment fragment = new WorldFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_world, container, false);
        Log.d("Reader","inflate");

        mRecyclerView = (RecyclerView)view.findViewById(R.id.worldRecyclerView);
        mEditText = (EditText) view.findViewById(R.id.et_search);

        mRecyclerView.setHasFixedSize(true);
// 控制 recyclerview 显示方式
//        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager = new GridLayoutManager(getContext(),3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mList = new ArrayList<>();
        for (int i = 0;i < 20; i ++){
            mList.add(new worldBean(R.mipmap.ic_launcher_round,"标题" + i,false));
        }
        mAdapter = new worldAdapter(mList);
        mRecyclerView.setAdapter(mAdapter);
        BookDetail bookDetail = new BookDetail();
        bookDetail.refreshBookDetail("53b76d8840f97c4245511144");

//        mEditText.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Log.d(TAG,"mEditText 触摸");
//                return true;
//            }
//        });


//        mSearchView = (SearchView) view.findViewById(R.id.searchView);
//        mSearchView.setIconified(true);
//        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            //输入完成后，点击回车或是完成键
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                if(query.length() >0){
//                    Log.d(TAG,"onQueryTextSubmit" + query);
//                }
//                return false;
//            }
//
//            //查询文本框有变化时事件
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                Log.d(TAG,"onQueryTextSubmit" + newText);
//                return false;
//            }
//        });
//        mSearchView.setOnSearchClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("Reader","setOnSearchClickListener");
////                Toast.makeText(getContext(),"search click",Toast.LENGTH_SHORT).show();
//            }
//        });

//        mSearchView.setOnQueryTextListener(new On);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*操作searchFragment*/
                Log.d(TAG,"mEditText 触摸");
                Intent intent = new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    public List<worldBean> getData(){
        for (int i = 0;i < 20; i ++){
            mList.add(new worldBean(R.mipmap.ic_launcher_round,"标题" + i,false));
        }
        return mList;
    }
}
