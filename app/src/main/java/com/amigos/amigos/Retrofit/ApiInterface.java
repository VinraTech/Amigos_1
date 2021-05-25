package com.amigos.amigos.Retrofit;


import com.amigos.amigos.ModelClass.ChangePasswordModelClass;
import com.amigos.amigos.ModelClass.DriverDetailsMOdelClass;
import com.amigos.amigos.ModelClass.DriverLoginModelClass;
import com.amigos.amigos.ModelClass.DriverOnDutyDetails;
import com.amigos.amigos.ModelClass.DriverOnDutyModelClass;
import com.amigos.amigos.ModelClass.LoginModalClass;
import com.amigos.amigos.ModelClass.RegistrationModalClass;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;

public interface ApiInterface {

    @Multipart
    @POST("api/register/")
    Call<RegistrationModalClass> consumerRegistration(
            @Part("consumerEmail") RequestBody consumerEmail,
            @Part("consumerMobile") RequestBody consumerMobile,
            @Part("consumerName") RequestBody consumerName,
            @Part("password") RequestBody password,
            @Part("login_type") RequestBody userType,
            @Part("ipAddress") RequestBody ipAddress,
            @Part("fcmToken") RequestBody fcmToken,
            @Part("longitude") RequestBody longitude,
            @Part("lattitude") RequestBody latitude,
            @Part("deviceID") RequestBody deviceId,
            @Part("appVersion") RequestBody appVersion,
            @Part("consumerAddress") RequestBody consumerAddress);

    @Multipart
    @POST("api/login/")
    Call<LoginModalClass> consumerLogin(@Part("consumerMobile") RequestBody consumerMobile,
                                        @Part("password") RequestBody password,
                                        @Part("login_type") RequestBody fcmToken);

    @Multipart
    @POST("api/driver-login/")
    Call<DriverLoginModelClass> driverLogin(@Part("driverMobile") RequestBody driverMobile,
                                            @Part("password") RequestBody password,
                                            @Part("login_type") RequestBody login_type);

    @Multipart
    @POST("api/change-password/")
    Call<ChangePasswordModelClass> changePassword(@Part("customer_id") RequestBody driverMobile,
                                                  @Part("old_password") RequestBody password,
                                                  @Part("new_password") RequestBody login_type);

    @Multipart
    @POST("api/driver-verification/")
    Call<DriverLoginModelClass> driverVerification(
            @Part("driver") RequestBody driver,
            @Part("shower") RequestBody shower,
            @Part("charger") RequestBody charger,
            @Part("powerbank") RequestBody powerbank,
            @Part("uniform") RequestBody uniform,
            @Part("bagPack") RequestBody bagPack,
            @Part("swipingMachine") RequestBody swipingMachine,
            @Part("idCard") RequestBody idCard,
            @Part("bodyTemperature") RequestBody bodyTemperature,
            @Part MultipartBody.Part filePart);

    @Multipart
    @POST("api/driver-verification/")
    Call<DriverLoginModelClass> driverVerificationWithoutImage(
            @Part("driver") RequestBody driver,
            @Part("shower") RequestBody shower,
            @Part("charger") RequestBody charger,
            @Part("powerbank") RequestBody powerbank,
            @Part("uniform") RequestBody uniform,
            @Part("bagPack") RequestBody bagPack,
            @Part("swipingMachine") RequestBody swipingMachine,
            @Part("idCard") RequestBody idCard,
            @Part("bodyTemperature") RequestBody bodyTemperature,
            @Part("driverimageUrl") RequestBody image);

    @Multipart
    @POST("api/register/")
    Call<RegistrationModalClass> consumerRequest(
            @Part("consumerEmail") RequestBody consumerEmail,
            @Part("consumerMobile") RequestBody consumerMobile,
            @Part("consumerName") RequestBody consumerName,
            @Part("consumerCarNumber") RequestBody consumerCarNumber,
            @Part("consumerCarModelNumber") RequestBody consumerCarModelNumber,
            @Part("serviceTYpe") RequestBody serviceTYpe,
            @Part("consumerAddress") RequestBody consumerAddress);

    @Multipart
    @POST("api/driver-onoff/")
    Call<DriverOnDutyModelClass> driverOf(
            @Part("driver_id") RequestBody driver_id,
            @Part("driver_name") RequestBody driver_name,
            @Part("driver_off") RequestBody driver_off,
            @Part("driverof_time") RequestBody driverof_time);


    @GET("api/driver-detail/")
    Call<DriverDetailsMOdelClass> getDriverDetails(@QueryMap Map<String, String> pram);

    @GET("api/driver-onoff/")
    Call<DriverOnDutyDetails> driverOnDutyDetails(@QueryMap Map<String, String> pram);

    // GET API..........................................................................................................................................................

   /* @Headers({"APPKEY: Py9YJXgBecbbqxjRVaHarcSnJyuzhxGqJTkY6xKZRfrdXFy72HPXvFRvfEjy"})
    @GET("api/intro/screen")
    Call<IntroScreen> getIntroScreen();
    @Headers({"APPKEY: Py9YJXgBecbbqxjRVaHarcSnJyuzhxGqJTkY6xKZRfrdXFy72HPXvFRvfEjy"})
    @GET("api/user/profile/")
    Call<Profile> getProfile(@QueryMap Map<String, String> pram);*/

}
