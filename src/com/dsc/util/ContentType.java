/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.util;

import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.ArrayUtils.contains;
import static org.apache.commons.lang3.StringUtils.endsWithIgnoreCase;
import static org.apache.commons.lang3.StringUtils.trim;

import java.nio.charset.Charset;
import java.util.Iterator;

import org.apache.commons.lang3.StringUtils;

import io.restassured.internal.http.ContentTypeExtractor;

/**
 * Refer:https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/MIME_types
 * Refer:io.restassured.http.ContentType
 *
 * @Author alex
 * @CreateTime 08.03.2017 10:39:05
 * @Version 1.0
 * @Since 1.0
 */
public enum ContentType
{
	APPLICATION("application/*"), AUDIO("audio/*"), BINARY("application/octet-stream"),

	BYTE_RANGES("multipart/byteranges"), FORM_DATA("multipart/form-data"), GIF("image/gif"),

	HTML("text/html"), IMAGE("image/*"), JPG("image/jpeg"),

	JSON("application/json", "application/javascript", "text/javascript"), PNG("image/png"),

	TEXT("text/plain"),

	URLENC("application/x-www-form-urlencoded"), VIDEO("video/*"),

	XML("application/xml", "text/xml", "application/xhtml+xml");

	private static final String	PLUS_HTML	= "+html";

	private static final String	PLUS_JSON	= "+json";
	private static final String	PLUS_XML	= "+xml";

	public static ContentType fromContentType(String contentType)
	{
		if (contentType == null)
		{
			return null;
		}
		contentType = ContentTypeExtractor.getContentTypeWithoutCharset(contentType.toLowerCase());
		final ContentType foundContentType;
		if (contains(XML.ctStrings, contentType) || endsWithIgnoreCase(contentType, PLUS_XML))
		{
			foundContentType = XML;
		} else if (contains(JSON.ctStrings, contentType) || endsWithIgnoreCase(contentType, PLUS_JSON))
		{
			foundContentType = JSON;
		} else if (contains(TEXT.ctStrings, contentType))
		{
			foundContentType = TEXT;
		} else if (contains(HTML.ctStrings, contentType) || endsWithIgnoreCase(contentType, PLUS_HTML))
		{
			foundContentType = HTML;
		} else
		{
			foundContentType = null;
		}
		return foundContentType;
	}

	private final String[] ctStrings;

	private ContentType(String... contentTypes)
	{
		this.ctStrings = contentTypes;
	}

	/**
	 * Builds a string to be used as an HTTP <code>Accept</code> header
	 * value, i.e. "application/xml, text/xml"
	 *
	 * @return
	 */
	public String getAcceptHeader()
	{
		Iterator<String> iter = asList(ctStrings).iterator();
		StringBuilder sb = new StringBuilder();
		while (iter.hasNext())
		{
			sb.append(iter.next());
			if (iter.hasNext())
			{
				sb.append(", ");
			}
		}
		return sb.toString();
	}

	public String[] getContentTypeStrings()
	{
		return ctStrings;
	}

	public boolean matches(String contentType)
	{
		String expectedContentType = StringUtils.trimToNull(contentType);
		if (expectedContentType == null)
		{
			return false;
		}

		for (String supportedContentType : getContentTypeStrings())
		{
			if (supportedContentType.equalsIgnoreCase(expectedContentType))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString()
	{
		return ctStrings[0];
	}

	/**
	 * Specify a charset for this content-type
	 *
	 * @param charset
	 *            The charset
	 * @return The content-type with the given charset.
	 */
	public String withCharset(Charset charset)
	{
		if (charset == null)
		{
			throw new IllegalArgumentException("charset cannot be null");
		}
		return withCharset(charset.toString());
	}

	/**
	 * Specify a charset for this content-type
	 *
	 * @param charset
	 *            The charset
	 * @return The content-type with the given charset.
	 */
	public String withCharset(String charset)
	{
		if (StringUtils.isBlank(charset))
		{
			throw new IllegalArgumentException("charset cannot be empty");
		}
		return format("%s; charset=%s", this.toString(), trim(charset));
	}

}
