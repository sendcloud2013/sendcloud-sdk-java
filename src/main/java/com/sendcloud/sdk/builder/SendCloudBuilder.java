package com.sendcloud.sdk.builder;

import com.sendcloud.sdk.config.SendcloudConfig;
import com.sendcloud.sdk.core.SendCloud;

public class SendCloudBuilder {

	public static SendCloud build() {
		SendCloud sc = new SendCloud();
		sc.setCalendarAPICN(SendcloudConfig.getSend_calendar_cn());
		sc.setCalendarAPISGP(SendcloudConfig.getSend_calendar_sgp());
		sc.setMailAPICN(SendcloudConfig.getSend_api_cn());
		sc.setMailAPISGP(SendcloudConfig.getSend_api_sgp());
		sc.setSmsAPICN(SendcloudConfig.getSend_api_cn());
		sc.setTemplateAPICN(SendcloudConfig.getSend_template_api_cn());
		sc.setTemplateAPISGP(SendcloudConfig.getSend_template_api_sgp());
		sc.setVoiceAPICN(SendcloudConfig.getSend_voice_api_cn());
		return sc;
	}
}