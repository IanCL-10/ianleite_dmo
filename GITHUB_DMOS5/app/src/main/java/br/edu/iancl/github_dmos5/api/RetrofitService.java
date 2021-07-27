package br.edu.iancl.github_dmos5.api;

import java.util.List;

import br.edu.iancl.github_dmos5.model.Repositorio;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitService {

    @GET("users/{username}/repos")
    Call<List<Repositorio>> getDados(@Path("username") String username);
}
