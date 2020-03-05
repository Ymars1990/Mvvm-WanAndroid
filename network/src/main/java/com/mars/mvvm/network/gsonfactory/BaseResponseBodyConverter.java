package com.mars.mvvm.network.gsonfactory;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.mars.mvvm.common_utils.LogManger;
import com.mars.mvvm.common_utils.MyStringUtils;
import com.mars.mvvm.common_utils.constant.Constant;
import com.mars.mvvm.network.base.BaseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author Mars
 * 时间：2019/08/23
 * 描述：请求返回基类转化工厂
 */

public class BaseResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private Gson gson;
    private TypeAdapter<T> adapter;

    BaseResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String jsonString = value.string();
        LogManger.Companion.logE("value", MyStringUtils.Companion.stringIsNotNull(jsonString) ? jsonString : "is null");
        try {
            JSONObject object = new JSONObject(jsonString);
            int status = object.getInt(Constant.code);
            if (status != 0) {
                if (status == -1001) {
                    LogManger.Companion.logE("当前状态", "当前状态未登录");
                }
                String msg = object.getString(Constant.msg);
                if (TextUtils.isEmpty(msg)) {
                    msg = String.format("[%s]", status);
                }
                //异常处理
                throw new BaseException(msg, status);
            } else {
                String data = object.getString(Constant.data);
                if (MyStringUtils.Companion.stringIsNotNull(data)) {
                    LogManger.Companion.logE("Data", data);
                    return adapter.fromJson(data);
                } else {
                    LogManger.Companion.logE("Data", "data is null");
                    return adapter.fromJson("{}");
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
            //数据解析异常
            LogManger.Companion.logE(this.getClass().getSimpleName(), e.getMessage());
            throw new BaseException(BaseException.PARSE_ERROR_MSG, BaseException.PARSE_ERROR);
        } finally {
            value.close();
        }
    }
}
