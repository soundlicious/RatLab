package com.vlab.experiment.ratlabmvvm.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vlab.experiment.ratlabmvvm.BuildConfig
import com.vlab.experiment.ratlabmvvm.data.network.TypicodeService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val remoteDataSourceModule = module {
    factory { createOkHttpClient() }
    single { createWebService<TypicodeService>(get(), BuildConfig.API_ROOT) }
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
//        .addInterceptor {
//            var request = it.request()
//            val builder = request.newBuilder()
//
//            val formBody = FormBody.Builder()
//                .add("email", "Jurassic@Park.com")
//                .add("tel", "90301171XX")
//                .build()
//
//            var postBodyString = bodyToString(request.body())
//            postBodyString += (if (postBodyString.length > 0) "&" else "") + bodyToString(formBody)
//            request = builder
//                .post(
//                    RequestBody.create(
//                        MediaType.parse("application/json;charset=UTF-8"),
//                        postBodyString
//                    )
//                )
//                .build()
//            it.proceed(request)
//        }
        .addInterceptor(httpLoggingInterceptor).build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(url)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
    return retrofit.create(T::class.java)
}

//fun bodyToString(request: RequestBody?): String {
//    return try {
//        val copy = request;
//        val buffer : Buffer = Buffer()
//        copy?.writeTo(buffer)
//        buffer.readUtf8()
//    } catch (e: IOException) {
//        "did not work";
//    }
//}