package com.sendcloud.sdk;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;

import com.sendcloud.sdk.builder.SendCloudBuilder;
import com.sendcloud.sdk.config.Credential;
import com.sendcloud.sdk.config.SendcloudConfig;
import com.sendcloud.sdk.core.SendCloud;
import com.sendcloud.sdk.exception.SmsException;
import com.sendcloud.sdk.exception.VoiceException;
import com.sendcloud.sdk.model.SendCloudSms;
import com.sendcloud.sdk.util.ResponseData;

public class SmsTest {

	public static void main(String[] args)
			throws ClientProtocolException, IOException, SmsException, ParseException, VoiceException {
		SendCloud sc = SendCloudBuilder.build();

		SendCloudSms sms = new SendCloudSms();
		sms.setTemplateId(65825);
		sms.addPhone("13512345678");
		sms.addVars("code", "123456");
		Credential credential = new Credential(SendcloudConfig.getApi_user_cn(), SendcloudConfig.getApi_key_cn());
		ResponseData result = sc.sendSms(sms, credential);

		System.out.println(result.toString());

	}

}