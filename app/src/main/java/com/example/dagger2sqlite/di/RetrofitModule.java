package com.example.dagger2sqlite.di;

import com.example.dagger2sqlite.services.UserService;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
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

    @Provides
    @Singleton
    public static Retrofit providesRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(String.format("%s/%s/", "http://5.189.158.56:8080/ms/api", "v1"))
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new OkHttpClient.Builder()
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
