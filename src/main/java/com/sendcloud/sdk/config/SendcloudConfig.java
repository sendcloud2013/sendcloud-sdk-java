package com.sendcloud.sdk.config;

import java.io.InputStream;
import java.util.Properties;

public class SendcloudConfig {

    public static final String CHARSET = "utf-8";
    // 普通邮件发送
    public static String send_api_cn = "https://api.sendcloud.net/apiv2/mail/send";
    // 地址列表发送
    public static String send_template_api_cn = "https://api.sendcloud.net/apiv2/mail/sendtemplate";
    // 发送会议日历
    public static String send_calendar_cn = "https://api.sendcloud.net/apiv2/mail/sendcalendar";
    // 短信发送
    public static String send_sms_api_cn = "https://www.sendcloud.net/smsapi/send";
    // 语音发送
    public static String send_voice_api_cn = "https://www.sendcloud.net/smsapi/sendVoice";
    // 邮件user
    public static String api_user_cn = null;
    // 邮件key
    public static String api_key_cn = null;
    // 短信user
    public static String sms_user_cn = null;
    // 短信key
    public static String sms_key_cn = null;

    public static String send_api_sgp = "https://api2.sendcloud.net/api/mail/send";

    public static String send_template_api_sgp = "https://api2.sendcloud.net/api/mail/sendtemplate";

    public static String send_calendar_sgp = "https://api2.sendcloud.net/api/mail/sendcalendar";

    public static String api_user_sgp = null;

    public static String api_key_sgp = null;

    // 最大收件人数
    public static final int MAX_RECEIVERS = 100;
    // 最大地址列表数
    public static final int MAX_MAILLIST = 5;
    // 邮件内容大小
    public static final int MAX_CONTENT_SIZE = 5 * 1024 * 1024;

    public static String getSend_api_cn() {
        return send_api_cn;
    }

    public static void setSend_api_cn(String send_api_cn) {
        SendcloudConfig.send_api_cn = send_api_cn;
    }

    public static String getSend_template_api_cn() {
        return send_template_api_cn;
    }

    public static void setSend_template_api_cn(String send_template_api_cn) {
        SendcloudConfig.send_template_api_cn = send_template_api_cn;
    }

    public static String getSend_sms_api_cn() {
        return send_sms_api_cn;
    }

    public static void setSend_sms_api_cn(String send_sms_api_cn) {
        SendcloudConfig.send_sms_api_cn = send_sms_api_cn;
    }

    public static String getSend_voice_api_cn() {
        return send_voice_api_cn;
    }

    public static void setSend_voice_api_cn(String send_voice_api_cn) {
        SendcloudConfig.send_voice_api_cn = send_voice_api_cn;
    }

    public static String getApi_user_cn() {
        return api_user_cn;
    }

    public static void setApi_user_cn(String api_user_cn) {
        SendcloudConfig.api_user_cn = api_user_cn;
    }

    public static String getApi_key_cn() {
        return api_key_cn;
    }

    public static void setApi_key_cn(String api_key_cn) {
        SendcloudConfig.api_key_cn = api_key_cn;
    }

    public static String getSms_user_cn() {
        return sms_user_cn;
    }

    public static void setSms_user_cn(String sms_user_cn) {
        SendcloudConfig.sms_user_cn = sms_user_cn;
    }

    public static String getSms_key_cn() {
        return sms_key_cn;
    }

    public static void setSms_key_cn(String sms_key_cn) {
        SendcloudConfig.sms_key_cn = sms_key_cn;
    }

    public static String getSend_api_sgp() {
        return send_api_sgp;
    }

    public static void setSend_api_sgp(String send_api_sgp) {
        SendcloudConfig.send_api_sgp = send_api_sgp;
    }

    public static String getSend_template_api_sgp() {
        return send_template_api_sgp;
    }

    public static void setSend_template_api_sgp(String send_template_api_sgp) {
        SendcloudConfig.send_template_api_sgp = send_template_api_sgp;
    }

    public static String getSend_calendar_sgp() {
        return send_calendar_sgp;
    }

    public static void setSend_calendar_sgp(String send_calendar_sgp) {
        SendcloudConfig.send_calendar_sgp = send_calendar_sgp;
    }

    public static String getApi_user_sgp() {
        return api_user_sgp;
    }

    public static void setApi_user_sgp(String api_user_sgp) {
        SendcloudConfig.api_user_sgp = api_user_sgp;
    }

    public static String getApi_key_sgp() {
        return api_key_sgp;
    }

    public static void setApi_key_sgp(String api_key_sgp) {
        SendcloudConfig.api_key_sgp = api_key_sgp;
    }

    public static String getSend_calendar_cn() {
        return send_calendar_cn;
    }

    public static void setSend_calendar_cn(String send_calendar_cn) {
        SendcloudConfig.send_calendar_cn = send_calendar_cn;
    }

    static {
        try {
            InputStream f = SendcloudConfig.class.getClassLoader().getResourceAsStream("sendcloud.properties");
            Properties pros = new Properties();
            pros.load(f);
            send_api_cn = pros.getProperty("send_api_cn");
            send_template_api_cn = pros.getProperty("send_template_api_cn");
            send_calendar_cn = pros.getProperty("send_calendar_cn");
            send_sms_api_cn = pros.getProperty("send_sms_api_cn");
            send_voice_api_cn = pros.getProperty("send_voice_api_cn");
            api_user_cn = pros.getProperty("api_user_cn");
            api_key_cn = pros.getProperty("api_key_cn");
            sms_user_cn = pros.getProperty("sms_user_cn");
            sms_key_cn = pros.getProperty("sms_key_cn");

            send_api_sgp = pros.getProperty("send_api_sgp");
            send_template_api_sgp = pros.getProperty("send_template_api_sgp");
            send_calendar_sgp = pros.getProperty("send_calendar_sgp");
            api_user_sgp = pros.getProperty("api_user_sgp");
            api_key_sgp = pros.getProperty("api_key_sgp");
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}