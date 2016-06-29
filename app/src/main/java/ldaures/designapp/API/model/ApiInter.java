package ldaures.designapp.API.model;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by triba on 29/06/2016.
 */
public interface ApiInter {
    String ENDPOINT = "http://192.168.1.30";

    @GET("/device/{id}")
    Call<Raspberry> getRaspberryById(@Path("id") int id);

    @GET("/devices")
    Call<ArrayList<Raspberry>> getRaspberrys();
}
