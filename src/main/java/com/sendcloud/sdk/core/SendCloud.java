package com.sendcloud.sdk.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.Asserts;
import org.json.JSONArray;

import com.sendcloud.sdk.config.Credential;
import com.sendcloud.sdk.config.Region;
import com.sendcloud.sdk.config.SendcloudConfig;
import com.sendcloud.sdk.exception.SmsException;
import com.sendcloud.sdk.exception.VoiceException;
import com.sendcloud.sdk.model.Attachment;
import com.sendcloud.sdk.model.MailAddressReceiver;
import com.sendcloud.sdk.model.SendCloudMail;
import com.sendcloud.sdk.model.SendCloudSms;
import com.sendcloud.sdk.model.SendCloudVoice;
import com.sendcloud.sdk.model.TemplateContent;
import com.sendcloud.sdk.model.TextContent;
import com.sendcloud.sdk.model.TextContent.ScContentType;
import com.sendcloud.sdk.util.HttpUtil;
import com.sendcloud.sdk.util.Md5Util;
import com.sendcloud.sdk.util.ResponseData;

/**
 *
 * 发送邮件代码示例
 * <p>
 * <blockquote>
 *
 * <pre>
 * SendCloud sc = SendCloudBuilder.build();
 *
 * // 创建邮件body
 * MailBody body = new MailBody();
 * body.setFrom("test@163.com");
 * body.setFromName("张三");
 * body.setReplyTo("service@qq.com");
 * body.setSubject("测试");
 * // 创建文件附件
 * body.addAttachments(new File("D:/test.txt"));
 * // 创建流附件
 * body.addAttachments(new FileInputStream(new File("D:/ff.png")), "ff.png");
 * // 邮箱收件人
 * MailAddressReceiver receiver = new MailAddressReceiver();
 * receiver.setBroadcastSend(true);// 广播发送(收件人会全部显示)
 * receiver.addTo("1234@qq.com");
 *
 * // 地址列表收件人
 * // MailListReceiver receiver=new MailListReceiver();
 * // 添加邮件地址列表
 * // receiver.addMailList("developers@sendcloud.com");
 *
 * // 创建模版邮件内容
 * TemplateContent content = new TemplateContent();
 * content.setTemplateInvokeName("templateInvokeName");
 *
 * // 创建文本邮件内容
 * // TextContent content = new TextContent();
 * // content.setContent_type(ScContentType.html);
 * // content.setText("hello world");
 *
 * // 创建邮件
 * SendCloudMail scmail = new SendCloudMail();
 * scmail.setBody(body);
 * scmail.setContent(content);
 * scmail.setTo(receiver);
 *
 * // 发信
 * ResponseData result = sc.sendMail(scmail);
 * System.out.println(JSONObject.fromObject(result).toString());
 * </pre>
 *
 * </blockquote>
 * <p>
 *
 * 发送短信代码示例
 *
 *
 * <p>
 * <blockquote>
 *
 * <pre>
 * SendCloud sc = SendCloudBuilder.build();
 *
 * SendCloudSms sms = new SendCloudSms();
 * sms.setTemplateId(65825);
 * sms.addPhone("13512345678");
 * sms.addVars("code", "123456");
 *
 * ResponseData result = sc.sendSms(sms);
 *
 * System.out.println(JSONObject.fromObject(result).toString());
 * </pre>
 *
 * </blockquote>
 * <p>
 *
 * 发送语音代码示例
 * <p>
 * <blockquote>
 *
 * <pre>
 * SendCloud sc = SendCloudBuilder.build();
 *
 * SendCloudVoice sms = new SendCloudVoice();
 * sms.setPhone("13312345678");
 * sms.setCode("1234");
 *
 * ResponseData result = sc.sendVoice(sms);
 *
 * System.out.println(JSONObject.fromObject(result).toString());
 * </pre>
 *
 * </blockquote>
 * <p>
 *
 * @author SendCloud
 *
 */
public class SendCloud {

	private String mailAPICN;
	private String templateAPICN;
	private String smsAPICN;
	private String voiceAPICN;
	private String mailAPISGP;
	private String templateAPISGP;
	private String calendarAPICN;
	private String calendarAPISGP;

	public String getMailAPICN() {
		return mailAPICN;
	}

	public void setMailAPICN(String mailAPICN) {
		this.mailAPICN = mailAPICN;
	}

	public String getTemplateAPICN() {
		return templateAPICN;
	}

	public void setTemplateAPICN(String templateAPICN) {
		this.templateAPICN = templateAPICN;
	}

	public String getSmsAPICN() {
		return smsAPICN;
	}

	public void setSmsAPICN(String smsAPICN) {
		this.smsAPICN = smsAPICN;
	}

	public String getVoiceAPICN() {
		return voiceAPICN;
	}

	public void setVoiceAPICN(String voiceAPICN) {
		this.voiceAPICN = voiceAPICN;
	}

	public String getMailAPISGP() {
		return mailAPISGP;
	}

	public void setMailAPISGP(String mailAPISGP) {
		this.mailAPISGP = mailAPISGP;
	}

	public String getTemplateAPISGP() {
		return templateAPISGP;
	}

	public void setTemplateAPISGP(String templateAPISGP) {
		this.templateAPISGP = templateAPISGP;
	}

	public String getCalendarAPISGP() {
		return calendarAPISGP;
	}

	public void setCalendarAPISGP(String calendarAPISGP) {
		this.calendarAPISGP = calendarAPISGP;
	}

	public String getCalendarAPICN() {
		return calendarAPICN;
	}

	public void setCalendarAPICN(String calendarAPICN) {
		this.calendarAPICN = calendarAPICN;
	}

	/**
	 * 发送邮件，默认Region.CN
	 *
	 * @param mail
	 *            邮件
	 * @return 发送结果
	 */
	public ResponseData sendMail(SendCloudMail mail) throws Throwable {
		Asserts.notNull(mail, "mail");
		Credential credential = new Credential(SendcloudConfig.getApi_user_cn(), SendcloudConfig.getApi_key_cn());
		mail.validate();
		if (mail.getBody().getAttachments() == null || mail.getBody().getAttachments().size() == 0) {
			return post(credential, mail, Region.CN);
		} else {
			return multipartPost(credential, mail, Region.CN);
		}
	}

	/**
	 * 发送邮件
	 *
	 * @param mail
	 *            邮件
	 * @return 发送结果
	 */
	public ResponseData sendMail(SendCloudMail mail, Credential credential, Region region) throws Throwable {
		Asserts.notNull(mail, "mail");
		Asserts.notNull(region, "region");
		Asserts.notNull(credential, "credential");
		Asserts.notBlank(credential.getApiUser(), "api user");
		Asserts.notBlank(credential.getApiKey(), "api key");
		mail.validate();
		if (mail.getBody().getAttachments() == null || mail.getBody().getAttachments().size() == 0) {
			return post(credential, mail, region);
		} else {
			return multipartPost(credential, mail, region);
		}
	}

	/**
	 * 普通方式发送
	 *
	 * @param credential
	 * @param mail
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private ResponseData post(Credential credential, SendCloudMail mail, Region region)
			throws ClientProtocolException, IOException {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("apiUser", credential.getApiUser()));
		params.add(new BasicNameValuePair("apiKey", credential.getApiKey()));
		params.add(new BasicNameValuePair("from", mail.getBody().getFrom()));
		params.add(new BasicNameValuePair("fromName", mail.getBody().getFromName()));
		params.add(new BasicNameValuePair("subject", mail.getBody().getSubject()));
		params.add(new BasicNameValuePair("replyTo", mail.getBody().getReplyTo()));
		if (mail.getBody().getLabelId() != null)
			params.add(new BasicNameValuePair("labelId", mail.getBody().getLabelId().toString()));

		/**
		 * 是否使用模版发送
		 */
		if (mail.getContent().useTemplate()) {
			TemplateContent content = (TemplateContent) mail.getContent();
			params.add(new BasicNameValuePair("templateInvokeName", content.getTemplateInvokeName()));
		} else {
			TextContent content = (TextContent) mail.getContent();
			if (content.getContent_type().equals(ScContentType.html)) {
				params.add(new BasicNameValuePair("html", content.getText()));
			} else {
				params.add(new BasicNameValuePair("plain", content.getText()));
			}
		}
		/**
		 * 是否使用地址列表
		 */
		if (mail.getTo() != null) {
			if (mail.getTo().useAddressList()) {
				params.add(new BasicNameValuePair("useAddressList", "true"));
				params.add(new BasicNameValuePair("to", mail.getTo().toString()));
			} else {
				MailAddressReceiver receiver = (MailAddressReceiver) mail.getTo();
				if (!mail.getContent().useTemplate() && receiver.isBroadcastSend()) {
					params.add(new BasicNameValuePair("to", receiver.toString()));
					if (receiver.getCcString() != null)
						params.add(new BasicNameValuePair("cc", receiver.getCcString()));
					if (receiver.getBccString() != null)
						params.add(new BasicNameValuePair("bcc", receiver.getBccString()));
				} else {
					if (mail.getBody().getXsmtpapi() != null && !mail.getBody().getXsmtpapi().containsKey("to")) {
						mail.getBody().addXsmtpapi("to", new JSONArray(receiver.getTo()));
					}
				}
			}
		}
		if (mail.getBody().getHeaders() != null && mail.getBody().getHeaders().size() > 0)
			params.add(new BasicNameValuePair("headers", mail.getBody().getHeadersString()));
		if (mail.getBody().getXsmtpapi() != null && mail.getBody().getXsmtpapi().size() > 0)
			params.add(new BasicNameValuePair("xsmtpapi", mail.getBody().getXsmtpapiString()));
		params.add(new BasicNameValuePair("respEmailId", "true"));
		params.add(new BasicNameValuePair("useNotification", "false"));
		if (mail.getBody().getMailCalendar() != null) {
			params.add(new BasicNameValuePair("title", mail.getBody().getMailCalendar().getTitle()));
			params.add(new BasicNameValuePair("startTime", mail.getBody().getMailCalendar().getStartTime()));
			params.add(new BasicNameValuePair("endTime", mail.getBody().getMailCalendar().getEndTime()));
			params.add(new BasicNameValuePair("organizerName", mail.getBody().getMailCalendar().getOrganizerName()));
			params.add(new BasicNameValuePair("organizerEmail", mail.getBody().getMailCalendar().getOrganizerEmail()));
			params.add(new BasicNameValuePair("location", mail.getBody().getMailCalendar().getLocation()));
			params.add(new BasicNameValuePair("participatorNames",
					mail.getBody().getMailCalendar().getParticipatorNames()));
			if (mail.getBody().getMailCalendar().getDescription() != null)
				params.add(new BasicNameValuePair("description", mail.getBody().getMailCalendar().getDescription()));
			if (mail.getBody().getMailCalendar().getParticipatorEmails() != null)
				params.add(new BasicNameValuePair("participatorEmails",
						mail.getBody().getMailCalendar().getParticipatorEmails()));
			if (mail.getBody().getMailCalendar().getUid() != null)
				params.add(new BasicNameValuePair("uid", mail.getBody().getMailCalendar().getUid()));
			params.add(new BasicNameValuePair("isCancel", String.valueOf(mail.getBody().getMailCalendar().isCancel())));
			params.add(new BasicNameValuePair("isUpdate", String.valueOf(mail.getBody().getMailCalendar().isUpdate())));
		}
		String url = null;
		if (region.equals(Region.CN)) {
			url = mail.getContent().useTemplate() ? templateAPICN
					: mail.getBody().getMailCalendar() == null ? mailAPICN : calendarAPICN;
		} else {
			url = mail.getContent().useTemplate() ? templateAPISGP
					: mail.getBody().getMailCalendar() == null ? mailAPISGP : calendarAPISGP;
		}
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		return HttpUtil.post(httpPost);
	}

	/**
	 * multipart方式发送
	 *
	 * @param credential
	 * @param mail
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	private ResponseData multipartPost(Credential credential, SendCloudMail mail, Region region)
			throws ClientProtocolException, IOException {
		String url = null;
		if (region.equals(Region.CN)) {
			url = mail.getContent().useTemplate() ? templateAPICN
					: mail.getBody().getMailCalendar() == null ? mailAPICN : calendarAPICN;
		} else {
			url = mail.getContent().useTemplate() ? templateAPISGP
					: mail.getBody().getMailCalendar() == null ? mailAPISGP : calendarAPISGP;
		}
		HttpPost httpPost = new HttpPost(url);
		MultipartEntityBuilder entity = MultipartEntityBuilder.create();
		entity.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		entity.setCharset(Charset.forName("UTF-8"));
		ContentType TEXT_PLAIN = ContentType.create("text/plain", Charset.forName("UTF-8"));
		entity.addTextBody("apiUser", credential.getApiUser(), TEXT_PLAIN);
		entity.addTextBody("apiKey", credential.getApiKey(), TEXT_PLAIN);
		entity.addTextBody("from", mail.getBody().getFrom(), TEXT_PLAIN);
		if (mail.getBody().getFromName() != null && !mail.getBody().getFromName().equals(""))
			entity.addTextBody("fromName", mail.getBody().getFromName(), TEXT_PLAIN);
		entity.addTextBody("subject", mail.getBody().getSubject(), TEXT_PLAIN);
		if (mail.getBody().getReplyTo() != null && !mail.getBody().getReplyTo().equals(""))
			entity.addTextBody("replyTo", mail.getBody().getReplyTo(), TEXT_PLAIN);
		if (mail.getBody().getLabelId() != null)
			entity.addTextBody("labelId", mail.getBody().getLabelId().toString(), TEXT_PLAIN);
		/**
		 * 是否使用模版发送
		 */
		if (mail.getContent().useTemplate()) {
			TemplateContent content = (TemplateContent) mail.getContent();
			entity.addTextBody("templateInvokeName", content.getTemplateInvokeName(), TEXT_PLAIN);
		} else {
			TextContent content = (TextContent) mail.getContent();
			if (content.getContent_type().equals(ScContentType.html)) {
				entity.addTextBody("html", content.getText(), TEXT_PLAIN);
			} else {
				entity.addTextBody("plain", content.getText(), TEXT_PLAIN);
			}
		}
		/**
		 * 是否使用地址列表
		 */
		if (mail.getTo() != null) {
			if (mail.getTo().useAddressList()) {
				entity.addTextBody("useAddressList", "true", TEXT_PLAIN);
				entity.addTextBody("to", mail.getTo().toString(), TEXT_PLAIN);
			} else {
				MailAddressReceiver receiver = (MailAddressReceiver) mail.getTo();

				if (!mail.getContent().useTemplate() && receiver.isBroadcastSend()) {
					entity.addTextBody("to", receiver.toString(), TEXT_PLAIN);
					if (receiver.getCcString() != null && !receiver.getCcString().equals(""))
						entity.addTextBody("cc", receiver.getCcString(), TEXT_PLAIN);
					if (receiver.getBccString() != null && !receiver.getBccString().equals(""))
						entity.addTextBody("bcc", receiver.getBccString(), TEXT_PLAIN);
				} else {
					if (mail.getBody().getXsmtpapi() == null || !mail.getBody().getXsmtpapi().containsKey("to")) {
						mail.getBody().addXsmtpapi("to", new JSONArray(receiver.getTo()));
					}
				}
			}
		}
		if (mail.getBody().getHeaders() != null && mail.getBody().getHeaders().size() > 0)
			entity.addTextBody("headers", mail.getBody().getHeadersString(), TEXT_PLAIN);
		if (mail.getBody().getXsmtpapi() != null && mail.getBody().getXsmtpapi().size() > 0)
			entity.addTextBody("xsmtpapi", mail.getBody().getXsmtpapiString(), TEXT_PLAIN);
		entity.addTextBody("respEmailId", "true", TEXT_PLAIN);
		entity.addTextBody("useNotification", "false", TEXT_PLAIN);
		if (mail.getBody().getMailCalendar() != null) {
			entity.addTextBody("title", mail.getBody().getMailCalendar().getTitle(), TEXT_PLAIN);
			entity.addTextBody("startTime", mail.getBody().getMailCalendar().getStartTime(), TEXT_PLAIN);
			entity.addTextBody("endTime", mail.getBody().getMailCalendar().getEndTime(), TEXT_PLAIN);
			entity.addTextBody("organizerName", mail.getBody().getMailCalendar().getOrganizerName(), TEXT_PLAIN);
			entity.addTextBody("organizerEmail", mail.getBody().getMailCalendar().getOrganizerEmail(), TEXT_PLAIN);
			entity.addTextBody("location", mail.getBody().getMailCalendar().getLocation(), TEXT_PLAIN);
			entity.addTextBody("participatorNames", mail.getBody().getMailCalendar().getParticipatorNames(),
					TEXT_PLAIN);
			if (mail.getBody().getMailCalendar().getDescription() != null)
				entity.addTextBody("description", mail.getBody().getMailCalendar().getDescription(), TEXT_PLAIN);
			if (mail.getBody().getMailCalendar().getParticipatorEmails() != null)
				entity.addTextBody("participatorEmails", mail.getBody().getMailCalendar().getParticipatorEmails(),
						TEXT_PLAIN);
			if (mail.getBody().getMailCalendar().getUid() != null)
				entity.addTextBody("uid", mail.getBody().getMailCalendar().getUid(), TEXT_PLAIN);
			entity.addTextBody("isCancel", String.valueOf(mail.getBody().getMailCalendar().isCancel()), TEXT_PLAIN);
			entity.addTextBody("isUpdate", String.valueOf(mail.getBody().getMailCalendar().isUpdate()), TEXT_PLAIN);
		}
		ContentType OCTEC_STREAM = ContentType.create("application/octet-stream", Charset.forName("UTF-8"));
		for (Object o : mail.getBody().getAttachments()) {
			if (o instanceof File) {
				entity.addBinaryBody("attachments", (File) o, OCTEC_STREAM, ((File) o).getName());
			} else if (o instanceof Attachment) {
				entity.addBinaryBody("attachments", ((Attachment) o).getContent(), OCTEC_STREAM,
						((Attachment) o).getName());
			} else {
				entity.addBinaryBody("attachments", (InputStream) o, OCTEC_STREAM, UUID.randomUUID().toString());
			}
		}
		httpPost.setEntity(entity.build());
		return HttpUtil.post(httpPost);
	}

	/**
	 * 发送短信，默认Credential
	 *
	 * @param sms
	 * @return 发送结果
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws SmsException
	 */
	public ResponseData sendSms(SendCloudSms sms) throws ClientProtocolException, IOException, SmsException {
		Credential credential = new Credential(SendcloudConfig.getApi_user_cn(), SendcloudConfig.getApi_key_cn());
		return sendSms(sms, credential);
	}

	/**
	 * 发送短信
	 *
	 * @param sms
	 * @return 发送结果
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws SmsException
	 */
	public ResponseData sendSms(SendCloudSms sms, Credential credential)
			throws ClientProtocolException, IOException, SmsException {
		Asserts.notNull(sms, "sms");
		Asserts.notNull(credential, "credential");
		Asserts.notBlank(credential.getApiUser(), "api user");
		Asserts.notBlank(credential.getApiKey(), "api key");
		sms.validate();
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		treeMap.put("smsUser", credential.getApiUser());
		treeMap.put("msgType", sms.getMsgType().toString());
		treeMap.put("phone", sms.getPhoneString());
		treeMap.put("templateId", sms.getTemplateId().toString());
		treeMap.put("timestamp", String.valueOf((new Date()).getTime()));
		if (sms.getVars() != null && sms.getVars().size() > 0)
			treeMap.put("vars", sms.getVarsString());
		String signature = Md5Util.md5Signature(treeMap, credential.getApiKey());
		treeMap.put("signature", signature);
		Iterator<String> iterator = treeMap.keySet().iterator();
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		while (iterator.hasNext()) {
			String key = iterator.next();
			params.add(new BasicNameValuePair(key, treeMap.get(key)));
		}

		HttpPost httpPost = new HttpPost(smsAPICN);
		httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		return HttpUtil.post(httpPost);
	}

	/**
	 * 发送短信，默认Credential
	 *
	 * @param sms
	 * @return 发送结果
	 * @throws VoiceException
	 * @throws ParseExceptio
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws SmsException
	 */
	public ResponseData sendVoice(SendCloudVoice voice) throws ParseException, IOException, VoiceException {
		Credential credential = new Credential(SendcloudConfig.getApi_user_cn(), SendcloudConfig.getApi_key_cn());
		return sendVoice(voice, credential);
	}

	/**
	 * 发送语音
	 *
	 * @param voice
	 * @return 发送结果
	 * @throws VoiceException
	 * @throws ParseException
	 * @throws IOException
	 */
	public ResponseData sendVoice(SendCloudVoice voice, Credential credential)
			throws VoiceException, ParseException, IOException {
		Asserts.notNull(voice, "voice");
		Asserts.notNull(credential, "credential");
		Asserts.notBlank(credential.getApiUser(), "api user");
		Asserts.notBlank(credential.getApiKey(), "api key");
		voice.validate();
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		treeMap.put("smsUser", credential.getApiUser());
		treeMap.put("phone", voice.getPhone());
		treeMap.put("code", voice.getCode());
		treeMap.put("timestamp", String.valueOf((new Date()).getTime()));
		String signature = Md5Util.md5Signature(treeMap, credential.getApiKey());
		treeMap.put("signature", signature);
		Iterator<String> iterator = treeMap.keySet().iterator();
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		while (iterator.hasNext()) {
			String key = iterator.next();
			params.add(new BasicNameValuePair(key, treeMap.get(key)));
		}

		HttpPost httpPost = new HttpPost(voiceAPICN);
		httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		return HttpUtil.post(httpPost);
	}

}