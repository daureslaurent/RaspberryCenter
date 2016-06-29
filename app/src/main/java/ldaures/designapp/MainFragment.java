package ldaures.designapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import ldaures.designapp.API.model.ApiInter;
import ldaures.designapp.API.model.Raspberry;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainFragment extends Fragment {
    private ApiInter apiInter;
    /* View components */
    private ListView listView;
    private ProgressBar progressBar;

    private ArrayList<Raspberry> _dataRaspberry;
    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragView = inflater.inflate(R.layout.fragment_main, container, false);
        _dataRaspberry = new ArrayList<>();


        progressBar = (ProgressBar) fragView.findViewById(R.id.FragMainProgressBar);
        listView = (ListView) fragView.findViewById(R.id.mainListView);

        listView.setAdapter(new RaspberryAdapter(getContext(), _dataRaspberry));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInter.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiInter = retrofit.create(ApiInter.class);
        Call<ArrayList<Raspberry>> call = apiInter.getRaspberrys();

        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);

        call.enqueue(new Callback<ArrayList<Raspberry>>() {
            @Override
            public void onResponse(Call<ArrayList<Raspberry>> call, Response<ArrayList<Raspberry>> response) {
                if (response.code() == 404) {
                    Log.e("RapsberryCallApi", "Code 404");
                    return;
                }
                ArrayList<Raspberry> raspList = response.body();
                _dataRaspberry.clear();
                _dataRaspberry.addAll(raspList);
                listView.invalidate();

                progressBar.setVisibility(View.GONE);
                listView.setVisibility(View.VISIBLE);
            }
            @Override
            public void onFailure(Call<ArrayList<Raspberry>> call, Throwable t) {
                Log.e("OnFailure", t.toString());
            }
        });
        return fragView;
    }

}
