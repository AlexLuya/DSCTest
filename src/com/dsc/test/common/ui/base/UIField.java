/**
 * Copyright (c) (2010-2013),Deep Sky Century and/or its affiliates.All rights
 * reserved.
 * DSC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 **/
package com.dsc.test.common.ui.base;

import static com.dsc.util.Log.info;
import static com.dsc.util.Util.mustNotNull;
import static com.dsc.util.Util.nullIfEmpty;
import static com.dsc.util.Util.wrap;
import static java.lang.String.format;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.dsc.test.app.App;
import com.dsc.test.common.Context;
import com.dsc.util.Util;

import io.appium.java_client.MobileElement;

/**
 * The Class UIField.
 *
 * @param <T>
 *            the generic type
 */
// LP BLOG exception or boolean+log
public class UIField<T extends WebElement>
{

	/** The Constant VALUE. */
	protected static final String			VALUE							= "value";

	/** The Constant CLASS. */
	private static final String				CLASS							= "class";

	/** The Constant DEFAULT_DURATION_MILLISECONDS. */
	private static final int				DEFAULT_DURATION_MILLISECONDS	= 500;

	/** The Constant ERROR_CSS. */
	private static final String				ERROR_CSS						= "error";

	/** The Constant ID. */
	private static final String				ID								= "id";

	/** The Constant SRC. */
	// String PATTERN="pattern";
	private static final String				SRC								= "src";

	/** The Constant TITLE. */
	private static final String				TITLE							= "title";

	/** The Constant WARN_CSS. */
	private static final String				WARN_CSS						= "warning";

	/** The annotated id. */
	protected String						annotatedId;

	/** The wrapee. */
	protected T								wrapee;

	/** The context. */
	private Context<?, ? extends WebDriver>	context;

	/**
	 * Instantiates a new UI field.
	 *
	 * @param context
	 *            the context
	 * @param id
	 *            the id
	 */
	@SuppressWarnings("unchecked") // NP remove this check
	public UIField(Context<?, ? extends WebDriver> context, String id)
	{
		this(context, (T) context.findElemById(id));
	}

	/**
	 * Instantiates a new UI field.
	 *
	 * @param context
	 *            the context
	 * @param wrapee
	 *            the wrapee
	 */
	public UIField(Context<?, ? extends WebDriver> context, T wrapee)
	{
		this.context = context;
		this.wrapee = wrapee;
	}

	/**
	 * Click if under web context,tap if under app/mobile context
	 */
	public void click()
	{
		if (context instanceof App<?, ?>)
		{
			((App<?, ?>) context).tap(wrapee);
		} else
		{
			wrapee.click();
		}
	}

	/**
	 * Contains css.
	 *
	 * @param css
	 *            the css
	 * @return true, if successful
	 */
	public boolean containsCss(String css)
	{
		info("checks whether %s's css:'%s' contains '%s' in #containsCss()", id(), css(), css);
		return css().contains(css);
	}

	/**
	 * Context.
	 *
	 * @return the context
	 */
	public Context<?, ?> context()
	{
		return context;
	}

	/**
	 * Double click.
	 */
	public void doubleClick()
	{
		context.actions().doubleClick(wrapee).perform();
	}

	/**
	 * Element.
	 *
	 * @return the web element
	 */
	public WebElement element()
	{
		return wrapee;
	}

	/**
	 * Ensure available.
	 */
	public void ensureAvailable()
	{
		try
		{
			waitUntil(ExpectedConditions.visibilityOf(wrapee));
		} catch (Exception e)
		{
			throw new RuntimeException(msgOfNotPresentedInPage(), e.getCause());
		}
	}

	/**
	 * Ensure hidden.
	 */
	public void ensureHidden()
	{
		context.ensureNotPresented(getAnnotatedId());
	}

	/**
	 * Ensure inner html is.
	 *
	 * @param html
	 *            the html
	 */
	public void ensureInnerHtmlIs(String html)
	{
		if (!html.equals(html()))
		{
			throw new IllegalStateException(wrap(format("%s's inner html isn't '%s' but '%s'", id(), html, html())));
		}
	}

	/**
	 * Ensure title is.
	 *
	 * @param title
	 *            the title
	 */
	public void ensureTitleIs(String title)
	{
		if (!title.equals(title()))
		{
			throw new IllegalStateException(wrap(format("%s's title isn't '%s' but '%s'", id(), title, title())));
		}
	}

	/**
	 * Gets the annotated id.
	 *
	 * @return the annotatedId
	 */
	public String getAnnotatedId()
	{
		return annotatedId;
	}

	/**
	 * Gets the wrapped.
	 *
	 * @return the wrapped
	 */
	public WebElement getWrapped()
	{
		return wrapee;
	}

	/**
	 * Height.
	 *
	 * @return the int
	 */
	public int height()
	{
		return wrapee.getSize().getHeight();
	}

	/**
	 * Id.
	 *
	 * @return the string
	 */
	public String id()
	{
		return id(wrapee);
	}

	/**
	 * Id.
	 *
	 * @param elem
	 *            the elem
	 * @return the string
	 */
	public String id(WebElement elem)
	{
		return elem.getAttribute(ID);
	}

	/**
	 * Inner text.
	 *
	 * @return the string
	 */
	public String innerText()
	{
		return stringAttr("innerText");
	}

	/**
	 * Checks if is hidden.
	 *
	 * @return true, if is hidden
	 */
	public boolean isHidden()
	{
		info("calls #containsCss(HIDE) in #isHidden()");
		return containsCss(Consts.HIDE);
	}

	/**
	 * Checks if is showing error.
	 *
	 * @return true, if is showing error
	 */
	public boolean isShowingError()
	{
		info("calls #containsCss(ERROR_CSS) in #isShowingError()");
		return containsCss(ERROR_CSS);
	}

	/**
	 * Checks if is showing warning.
	 *
	 * @return true, if is showing warning
	 */
	public boolean isShowingWarning()
	{
		info("calls #containsCss(WARN_CSS) in #isShowingWarning()");
		return containsCss(WARN_CSS);
	}

	/**
	 * Checks if is visible.
	 *
	 * @return true, if is visible
	 */
	public boolean isVisible()
	{
		return wrapee.isDisplayed();
	}

	/**
	 * Left.
	 *
	 * @return the int
	 */
	public int left()
	{
		return wrapee.getLocation().getX();
	}

	/**
	 * Mouse out.
	 */
	public void mouseOut()
	{
		moveOffset(-1, -1);
	}

	/**
	 * Mouse over.
	 */
	public void mouseOver()
	{
		moveOffset(0, 0);
	}

	/**
	 * Not has css.
	 *
	 * @param css
	 *            the css
	 * @return true, if successful
	 */
	public boolean notHasCss(String css)
	{
		info("calls !#containsCss() in #notContainsCss()");
		return !containsCss(css);
	}

	/**
	 * Presented.
	 *
	 * @return true, if successful
	 */
	public boolean presented()
	{
		try
		{
			ensureAvailable();
		} catch (Exception e)
		{
			return false;
		}

		return true;
	}

	/**
	 * Sets the annotated id.
	 *
	 * @param annotatedId
	 *            the annotatedId to set
	 */
	public void setAnnotatedId(String annotatedId)
	{
		this.annotatedId = annotatedId;
	}

	/**
	 * Text is.
	 *
	 * @param text
	 *            the text
	 * @return true, if successful
	 */
	public boolean textIs(String text)
	{
		info("check whether %s's actual text: '%s' equals to expected text: '%s'", id(), text(), text);
		return text().equals(text);
	}

	/**
	 * Title.
	 *
	 * @return the string
	 */
	public String title()
	{
		return stringAttr(TITLE);
	}

	/**
	 * Top.
	 *
	 * @return the int
	 */
	public int top()
	{
		return wrapee.getLocation().getY();
	}

	/**
	 * Wait for seconds.
	 *
	 * @param seconds
	 *            the seconds
	 */
	public void waitForSeconds(int seconds)
	{
		Util.sleep(seconds, annotatedId);
	}

	/**
	 * Width.
	 *
	 * @return the int
	 */
	public int width()
	{
		return wrapee.getSize().getWidth();
	}

	/**
	 * Async text presented.
	 *
	 * @param text
	 *            the text
	 * @return true, if successful
	 */
	protected boolean asyncTextPresented(String text)
	{
		info("checks whether async text: '%s' is presented in #asyncTextPresented()", text);
		// NP tell actual text
		return waitUntil(ExpectedConditions.textToBePresentInElement(wrapee, text));
	}

	/**
	 * Attr.
	 *
	 * @param attrName
	 *            the attr name
	 * @return the string
	 */
	protected String attr(String attrName)
	{
		return wrapee.getAttribute(attrName);
	}

	protected void clear(){
		context.actions().click(wrapee)
		.sendKeys(Keys.END)
		.keyDown(Keys.SHIFT)
		.sendKeys(Keys.HOME)
		.keyUp(Keys.SHIFT)
		.sendKeys(Keys.BACK_SPACE).perform();
	}

	/**
	 * Contains text.
	 *
	 * @param text
	 *            the text
	 * @return true, if successful
	 */
	protected boolean contains(String text)
	{
		info("checks whether %s's text:'%s' contains '%s' in #containsText()", id(), text(), text);
		return text().contains(text);
	}

	/**
	 * Css.
	 *
	 * @return the string
	 */
	protected String css()
	{
		return stringAttr(CLASS);
	}

	/**
	 * Css has changed from.
	 *
	 * @param css
	 *            the css
	 * @return true, if successful
	 */
	protected boolean cssHasChangedFrom(final String css)
	{
		info("chech whether %s's css has changed to '%s' from '%s'", id(), css, css());
		return waitUntil(new ExpectedCondition<Boolean>()
		{
			@Override
			public Boolean apply(WebDriver context)
			{
				if (!css.equals(css()))
				{
					info("the css of %s has changed to '%s' from '%s'", id(), css, css());
					return true;
				}
				info("the css of %s is still '%s'", id(), css);
				return null;
			}

			@Override
			public String toString()
			{
				return wrap(format("css has changed from '%s' to  '%s'", css, css()));
			}
		});
	}

	/**
	 * Drag and drop.
	 *
	 * @param xOffset
	 *            the x offset
	 * @param yOffset
	 *            the y offset
	 */
	protected void dragAndDrop(int xOffset, int yOffset)
	{
		Actions slide = context.actions();
		slide.build();
		// slide.click(wrapee);
		slide.dragAndDropBy(wrapee, xOffset, yOffset);
		slide.perform();
		// slide.release();
	}

	protected boolean endsWith(String text)
	{
		info("checks whether %s's text:'%s' endsWith '%s' in #containsText()", id(), text(), text);
		return text().endsWith(text);
	}

	/**
	 * Ensure attr not null or empty.
	 *
	 * @param attr
	 *            the attr
	 */
	protected void ensureAttrNotNullOrEmpty(String attr)
	{
		Util.mustNotNullOrEmpty(attr, "attribute name");

		if (stringAttr(attr) == null)
		{
			throw new IllegalStateException(wrap(format("%s's %s  mustn't be null", id(), attr)));
		}
	}

	/**
	 * Ensure inner text is.
	 *
	 * @param text
	 *            the text
	 * @return true, if successful
	 */
	protected boolean ensureInnerTextIs(String text)
	{
		if (!text.equals(text()))
		{
			throw new IllegalStateException(wrap(format("%s's inner text isn't '%s' but '%s'", id(), text, text())));
		}

		return true;
	}

	// /**
	// * Ensure text ends with.
	// *
	// * @param expected
	// * the expected
	// * @return true, if successful
	// */
	// protected boolean ensureTextEndsWith(String expected)
	// {
	// if (!text().endsWith(expected))
	// {
	// throw new IllegalStateException(wrap(
	// format("Expect %s's text ends with '%s',but actual text '%s' doesn't end
	// with is", id(), expected, text())));
	// }
	//
	// return true;
	// }

	/**
	 * Ensure src end with.
	 *
	 * @param src
	 *            the src
	 */
	protected void ensureSrcEndWith(String src)
	{
		if (!srcEndsWith(src))
		{
			throw new IllegalStateException(wrap(format("%s's src isn't end with '%s' but '%s'", id(), src, src())));
		}
	}

	/**
	 * Ensure text is.
	 *
	 * @param text
	 *            the text
	 * @return true, if successful
	 */
	protected boolean ensureTextIs(int text)
	{
		return ensureTextIs(Integer.toString(text));
	}

	/**
	 * Ensure text is.
	 *
	 * @param expected
	 *            the expected
	 * @return true, if successful
	 */
	/*
	 * @deprecated using text()=="expected text" instead if using spockframework
	 * it will print out more intuitive error message like this:
	 *
	 * @see <a
	 * href="http://spockframework.org/spock/docs/1.1-rc-3/spock_primer.html">
	 * http://spockframework.org/spock/docs/1.1-rc-3/spock_primer.html
	 * </a>
	 */
	protected boolean ensureTextIs(String expected)
	{
		if (!expected.equals(text()))
		{
			throw new IllegalStateException(wrap(format("Expect %s's text is '%s',but actual is '%s'", id(), expected, text())));
		}

		return true;
	}

	/**
	 * Ensure text is part of.
	 *
	 * @param whole
	 *            the whole
	 * @return true, if successful
	 */
	protected boolean ensureTextIsPartOf(String whole)
	{
		return whole.contains(text());
	}

	/**
	 * Ensure text starts with.
	 *
	 * @param expected
	 *            the expected
	 * @return true, if successful
	 */
	protected boolean ensureTextStartsWith(String expected)
	{
		if (!text().startsWith(expected))
		{
			throw new IllegalStateException(wrap(format(
					"Expect %s's text starts with '%s',but actual text:'%s' doesn't start with it", id(), expected, text())));
		}

		return true;
	}

	/**
	 * Input.
	 *
	 * @param text
	 *            the text
	 */
	protected void input(String text)
	{
		// due to
		if (wrapee instanceof MobileElement)
		{
			((MobileElement) wrapee).setValue(text);
		} else
		{
			context.actions().click(wrapee)
			.sendKeys(Keys.END)
			.keyDown(Keys.SHIFT)
			.sendKeys(Keys.HOME)
			.keyUp(Keys.SHIFT)
			.sendKeys(Keys.BACK_SPACE)
			.sendKeys(text)
			.perform();
		}
	}

	/**
	 * Checks if is sync error indicated.
	 *
	 * @param text
	 *            the text
	 * @return true, if is sync error indicated
	 */
	protected boolean isSyncErrorIndicated(final String text)
	{
		info("#isSyncErrorIndicated() call #containsText() and #isShowingError()");
		return contains(text) && isShowingError();
	}

	/**
	 * Checks if is sync warning indicated.
	 *
	 * @param text
	 *            the text
	 * @return true, if is sync warning indicated
	 */
	protected boolean isSyncWarningIndicated(final String text)
	{
		info("#isSyncErrorIndicated() call #containsText() and #isShowWarning()");
		return contains(text) && isShowingWarning();
	}

	protected boolean partOf(String text)
	{
		mustNotNull(text, "text");
		info("checks whether %s's text:'%s' is part of '%s' in #containsText()", id(), text(), text);
		return text.contains(text());
	}

	// protected boolean isPresented(String id)
	// {
	// return context.isPresented(id);
	// }

	/**
	 * Removes the attribute.
	 *
	 * @param name
	 *            the name
	 */
	protected void removeAttribute(String name)
	{
		info("remove attribute: '%s'", name);

		context.executeScript("arguments[0].removeAttribute(arguments[1]);", wrapee, name);
	}

	/**
	 * Sets the attribute.
	 *
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 */
	protected void setAttribute(String name, String value)
	{
		info("set attribute: '%s' to: '%s'", name, value);

		context.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", wrapee, name, value);
	}

	/**
	 * Src.
	 *
	 * @return the string
	 */
	protected String src()
	{
		return stringAttr(SRC);
	}

	/**
	 * Src ends with.
	 *
	 * @param src
	 *            the src
	 * @return true, if successful
	 */
	protected boolean srcEndsWith(String src)
	{
		info("check whether %s's actual src: '%s' ends with: '%s'", id(), src(), src);
		return src().endsWith(src);
	}

	protected boolean startsWith(String text)
	{
		info("checks whether %s's text:'%s' startsWith '%s' in #containsText()", id(), text(), text);
		return text().startsWith(text);
	}

	/**
	 * Swipe digonal.
	 *
	 * @param endX
	 *            the end X
	 * @param endY
	 *            the end Y
	 * @param distance
	 *            the distance
	 */
	protected void swipeDigonal(int endX, int endY, int distance)
	{
		throw new RuntimeException("swipeDigonal() NOT ready due to alex is busy");
	}

	/**
	 * Swipe down.
	 *
	 * @param distance
	 *            the distance
	 */
	protected void swipeDown(int distance)
	{
		if (distance < 0)
		{
			throw new RuntimeException("distance must >=0,using swipeUp() if wanting to swipe up");
		}

		int endY = centerYOrAvaiable() + distance;
		//
		if (endY > windowHeight())
		{
			endY = context.height() - 1;
		}

		swipeWithDefaultDuration(centerXOrAvaiable(), endY);
	}

	/**
	 * Swipe left.
	 *
	 * @param distance
	 *            the distance
	 */
	protected void swipeLeft(int distance)
	{
		if (distance < 0)
		{
			throw new RuntimeException("distance must >=0,using swipeRight() if wanting to swipe right");
		}

		int endX = centerXOrAvaiable() - distance;
		//
		if (endX < 1)
		{
			endX = 1;
		}

		swipeWithDefaultDuration(endX, centerYOrAvaiable());
	}

	/**
	 * Swipe right.
	 *
	 * @param distance
	 *            the distance
	 */
	protected void swipeRight(int distance)
	{
		if (distance < 0)
		{
			throw new RuntimeException("distance must >=0,using swipeLeft() if wanting to swipe left");
		}

		int endX = centerXOrAvaiable() + distance;
		//
		if (endX >= windowWidth())
		{
			endX = windowWidth() - 1;
		}

		swipeWithDefaultDuration(endX, centerYOrAvaiable());
	}

	/**
	 * Swipe up.
	 *
	 * @param distance
	 *            the distance
	 */
	protected void swipeUp(int distance)
	{
		if (distance < 0)
		{
			throw new RuntimeException("distance must >=0,using swipeDown() if wanting to swipe down");
		}

		int endY = centerYOrAvaiable() - distance;
		//
		if (endY < 1)
		{
			endY = 1;
		}

		swipeWithDefaultDuration(centerXOrAvaiable(), endY);
	}

	/**
	 * Text.
	 *
	 * @return inner text content or null
	 */
	protected String text()
	{
		// DON'T do it like blew,return null is acceptable
		// if (doGetText() == null)
		// {
		// return waitUntil(textPresented());
		// }

		// wait 3 seconds for text being presented
		// due to it will be retrieved async
		int wait = 1;
		while (doGetText() == null && wait < 3)
		{
			// wait 10 milliseconds each time
			Util.sleep(0.01 * wait++);
		}

		return doGetText();
	}

	/**
	 * Text content.
	 *
	 * @return the string
	 */
	protected String textContent()
	{
		return stringAttr("textContent");
	}

	/**
	 * Text is not.
	 *
	 * @param text
	 *            the text
	 * @return true, if successful
	 */
	protected boolean textIsNot(String text)
	{
		info("#textIsNot() calls !#textIs()");
		return !textIs(text);
	}

	/**
	 * Value.
	 *
	 * @return the string
	 */
	protected String value()
	{
		return stringAttr(VALUE);
	}

	/**
	 * Wait until.
	 *
	 * @param <O>
	 *            the generic type
	 * @param isTrue
	 *            the is true
	 * @return the o
	 */
	protected <O> O waitUntil(ExpectedCondition<O> isTrue)
	{
		return context.waitUntil(isTrue);
	}

	/**
	 * Center X.
	 *
	 * @return the int
	 */
	private int centerX()
	{
		Point topLeft = wrapee.getLocation();
		Dimension size = wrapee.getSize();

		return topLeft.getX() + size.getWidth() / 2;
	}

	/**
	 * Center X or avaiable.
	 *
	 * @return the int
	 */
	private int centerXOrAvaiable()
	{
		if (centerX() < 0)
		{
			return 0;
		}

		if (centerY() > windowWidth())
		{
			return windowWidth() - 1;
		}

		return centerX();
	}

	/**
	 * Center Y.
	 *
	 * @return the int
	 */
	private int centerY()
	{
		Point topLeft = wrapee.getLocation();
		Dimension size = wrapee.getSize();

		return topLeft.getY() + size.getHeight() / 2;
	}

	/**
	 * Center Y or avaiable.
	 *
	 * @return the int
	 */
	private int centerYOrAvaiable()
	{
		if (centerY() < 0)
		{
			return 0;
		}

		if (centerY() > context.height())
		{
			return context.height() - 1;
		}

		return centerY();
	}

	/**
	 * Do get text.
	 *
	 * @return the string
	 */
	private String doGetText()
	{
		String text = nullIfEmpty(wrapee.getText());

		if (text == null)
		{
			text = innerText();
		}

		if (text == null)
		{
			// for TextArea
			text = textContent();
		}

		if (text == null)
		{
			text = value();
		}

		// info("text is -------------------------%s", id())));
		return text;
	}

	/**
	 * Html.
	 *
	 * @return the string
	 */
	private String html()
	{
		return stringAttr("innerHTML");
	}

	/**
	 * Move offset.
	 *
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 */
	@SuppressWarnings("deprecation")
	private void moveOffset(int x, int y)
	{
		context.mouseMoveTo(wrapee, x, y);
	}

	/**
	 * Msg of not presented in page.
	 *
	 * @return the string
	 */
	private String msgOfNotPresentedInPage()
	{
		return format(
				"no element has id-------------------------'%s'-------------------------in the page,\n"
						+ wrap("Possible reasons:\n") + "① Expected element doesn't existed in the page\n"
						+ "② Element existed,but no id setted\n"
						+ "③ Element existed,but the id isn't the '%s',ensuring @Find(id='actual id of expected element') will fix this problem\n",
						annotatedId, annotatedId);
	}

	/**
	 * String attr.
	 *
	 * @param attr
	 *            the attr
	 * @return the string
	 */
	private String stringAttr(String attr)
	{
		return nullIfEmpty(attr(attr));
	}

	/**
	 * Swipe.
	 *
	 * @param endx
	 *            the endx
	 * @param endy
	 *            the endy
	 * @param duration
	 *            the duration
	 */
	private void swipe(int endx, int endy, int duration)
	{
		context.swipe(centerXOrAvaiable(), centerYOrAvaiable(), endx, endy, duration);
	}

	/**
	 * Swipe with default duration.
	 *
	 * @param endx
	 *            the endx
	 * @param endy
	 *            the endy
	 */
	private void swipeWithDefaultDuration(int endx, int endy)
	{
		swipe(endx, endy, DEFAULT_DURATION_MILLISECONDS);
	}

	/**
	 * Window height.
	 *
	 * @return the int
	 */
	private int windowHeight()
	{
		return context.height();
	}

	/**
	 * Window width.
	 *
	 * @return the int
	 */
	private int windowWidth()
	{
		return context.width();
	}

	// private ExpectedCondition<String> textPresented()
	// {
	// return new ExpectedCondition<String>()
	// {
	// @Override
	// public String apply(WebDriver driver)
	// {
	// info("wait text is presented in-------------------------%s", id());
	//
	// try
	// {
	// return doGetText();
	// } catch (StaleElementReferenceException e)
	// {
	// return null;
	// }
	// }
	//
	// @Override
	// public String toString()
	// {
	// return String.format("text to be present in element %s", id());
	// }
	// };
	// }
}