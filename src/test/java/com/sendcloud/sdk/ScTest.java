package com.sendcloud.sdk;

import java.io.File;
import java.io.FileInputStream;

import com.sendcloud.sdk.builder.SendCloudBuilder;
import com.sendcloud.sdk.config.Credential;
import com.sendcloud.sdk.config.Region;
import com.sendcloud.sdk.core.SendCloud;
import com.sendcloud.sdk.model.MailAddressReceiver;
import com.sendcloud.sdk.model.MailBody;
import com.sendcloud.sdk.model.MailCalendar;
import com.sendcloud.sdk.model.SendCloudMail;
import com.sendcloud.sdk.model.TextContent;
import com.sendcloud.sdk.model.TextContent.ScContentType;
import com.sendcloud.sdk.util.ResponseData;

public class ScTest {

	public static void main(String[] args) throws Throwable {
		SendCloud sc = SendCloudBuilder.build();

		// 创建邮件body
		MailBody body = new MailBody();
		body.setFrom("from");
		body.setFromName("fromName");
		body.setReplyTo("rto");
		body.setSubject("subject");
		// 创建文件附件
		body.addAttachments(new File("D:/test.txt"));
		// 创建流附件
		body.addAttachments(new FileInputStream(new File("D:/icon-test.png")), "icon-test.png");
		// 邮箱收件人
		MailAddressReceiver receiver = new MailAddressReceiver();
		receiver.setBroadcastSend(true);// 广播发送(收件人会全部显示)
		receiver.addTo("to");
		receiver.addCc("cc");

		MailCalendar mc = new MailCalendar();
		mc.setCancel(false);
		// 见https://web.sendcloud.net/zh/doc/email_v2/send_email/#_3
		mc.setUpdate(false);
		body.setMailCalendar(mc);

		// 地址列表收件人
		// MailListReceiver receiver=new MailListReceiver();
		// 添加邮件地址列表
		// receiver.addMailList("developers@sendcloud.com");

		// 创建模版邮件内容
		// TemplateContent content = new TemplateContent();
		// content.setTemplateInvokeName("hello world");

		// 创建文本邮件内容
		TextContent content = new TextContent();
		content.setContent_type(ScContentType.html);
		content.setText("hello world");

		// 创建邮件
		SendCloudMail scmail = new SendCloudMail();
		scmail.setBody(body);
		scmail.setContent(content);
		scmail.setTo(receiver);
		Credential credential = new Credential("user", "key");
		// 发信
		ResponseData result = sc.sendMail(scmail, credential, Region.SGP);
		System.out.println(result.toString());

	}

}