package com.java.tutorial.project.util;

import cn.hutool.json.JSONObject;
import com.google.i18n.phonenumbers.PhoneNumberToCarrierMapper;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;

import java.util.Locale;

/**
 * @author meta
 */
public class PhoneToRegionUtil {

    /**
     * 手机号基本工具类
     */
    private final static PhoneNumberUtil PHONE_NUMBER_UTIL = PhoneNumberUtil.getInstance();

    /**
     * 运营商
     */
    private final static PhoneNumberToCarrierMapper CARRIER_MAPPER = PhoneNumberToCarrierMapper.getInstance();

    /**
     *
     */
    private final static PhoneNumberOfflineGeocoder GEO_CODER = PhoneNumberOfflineGeocoder.getInstance();

    /**
     * 验证当前手机号是否有效
     * @param phone 手机号
     * @return 校验结果
     */
    public static boolean isValidNumber(String phone){
        return PHONE_NUMBER_UTIL.isValidNumber(getPhoneNumber(phone));
    }

    /**
     * 获取手机号运营商
     * @param phone 手机号
     * @return 运营商
     */
    public static String getPhoneCarrier(String phone){
        return isValidNumber(phone) ?  CARRIER_MAPPER.getNameForNumber(getPhoneNumber(phone), Locale.CHINA) : "";
    }

    /**
     * 获取手机号归属地
     * @param phone 手机号
     * @return 归属地
     */
    public static String getRegionInfoByPhone(String phone){
        return isValidNumber(phone) ? GEO_CODER.getDescriptionForNumber(getPhoneNumber(phone), Locale.CHINESE) : "";
    }

    /**
     * 生成PhoneNumber
     * @param phone 手机号
     * @return PhoneNumber
     */
    private static Phonenumber.PhoneNumber getPhoneNumber(String phone){
        Phonenumber.PhoneNumber phoneNumber = new Phonenumber.PhoneNumber();
        phoneNumber.setCountryCode(86);
        phoneNumber.setNationalNumber(Long.parseLong(phone));
        return phoneNumber;
    }

    /**
     * 获取手机号的归属信息：运营商，归属地
     * @param phone 手机号
     * @return 归属信息
     */
    public static JSONObject getPhoneAffiliationInfo(String phone){
        JSONObject affiliation = new JSONObject();
        affiliation.set("phone", phone);
        affiliation.set("carrier", getPhoneCarrier(phone));
        affiliation.set("region", getRegionInfoByPhone(phone));
        return affiliation;
    }
}

