package com.example.dagger2sqlite.di;

import com.example.dagger2sqlite.model.User;
import com.example.dagger2sqlite.services.UserService;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public abstract class RetrofitModule {

    /*.addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            Request authenticatedRequest = request.newBuilder().header("Authorization", Credentials.basic(user.getUsername(), user.getUsername())).build();
            return chain.proceed(authenticatedRequest);
        }
    })*/

    //"http://5.189.158.56:8080/ms/api"
    @Provides
    @Singleton
    public static String providesBaseUrl() {
        return "http://5.189.158.56:8080/ms/api";
    }
    @Provides
    @Singleton
    public static Retrofit providesRetrofit(final User user, String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(String.format("%s/%s/", baseUrl, "v1"))
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new Interceptor() {
                            @NotNull
                            @Override
                            public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
                                Request request = chain.request();
                                Request authenticatedRequest = request.newBuilder().header("Authorization", Credentials.basic(user.getUsername(), user.getUsername())).build();
                                return chain.proceed(authenticatedRequest);
                            }
                        })
                        .readTimeout(20, TimeUnit.SECONDS)
                        .connectTimeout(20, TimeUnit.SECONDS)
                        .writeTimeout(20, TimeUnit.SECONDS)
                        .build())
                .build();
    }

    /*@Singleton
    @Binds
    public abstract UserService provideUserServiceImpl();*/
    @Provides
    @Singleton
    public static UserService provideUserService(final Retrofit retrofit) {
        return retrofit.create(UserService.class);
    }
}
