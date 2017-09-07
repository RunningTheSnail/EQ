package me.danwi.eq.api;

import me.danwi.eq.core.ServiceProducers;

/**
 * Created with Android Studio.
 * User: HandSome-T
 * Date: 17/9/6
 * Time: 下午1:46
 */
public class ApiManagerService {

    private static ApiManager apiManager = ServiceProducers.createService(ApiManager.class);
}
