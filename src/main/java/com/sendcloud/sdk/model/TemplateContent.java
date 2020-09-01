package com.sendcloud.sdk.model;

import org.apache.commons.codec.binary.StringUtils;

import com.sendcloud.sdk.exception.ContentException;

/**
 * 模版发送
 *
 * <pre>
 * 通过已上传的模版发送邮件
 * </pre>
 *
 * @author Administrator
 *
 */
public class TemplateContent implements Content {

	/**
	 * 是否使用模版发送
	 */
	public boolean useTemplate() {
		return true;
	}

	/**
	 * 模版名称
	 */
	private String templateInvokeName;

	/**
	 * 返回模版名称
	 *
	 * @return 模版名称
	 */
	public String getTemplateInvokeName() {
		return templateInvokeName;
	}

	public void setTemplateInvokeName(String templateInvokeName) {
		this.templateInvokeName = templateInvokeName;
	}

	public boolean validate() throws ContentException {
		if (templateInvokeName == null || templateInvokeName.equals(""))
			throw new ContentException("模版为空");
		return true;
	}

}