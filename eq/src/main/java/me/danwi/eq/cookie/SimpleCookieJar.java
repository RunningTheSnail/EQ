package me.danwi.eq.cookie;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;

public final class SimpleCookieJar implements CookieJar {
    private final List<Cookie> allCookies = new ArrayList<>();

    /**
     * 从响应中保存cookie(从响应头中读取set-cookie:"")
     *
     * @param url
     * @param cookies
     */
    @Override
    public synchronized void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        allCookies.addAll(cookies);
    }

    /**
     * 添加到请求头中(设置请求头cookie:"")
     *
     * @param url 匹配的url
     * @return
     */
    @Override
    public synchronized List<Cookie> loadForRequest(HttpUrl url) {
        List<Cookie> result = new ArrayList<>();
        for (Cookie cookie : allCookies) {
            //将域名相同的cookie添加到result
            if (cookie.matches(url)) {
                result.add(cookie);
            }
        }
        return result;
    }
}
