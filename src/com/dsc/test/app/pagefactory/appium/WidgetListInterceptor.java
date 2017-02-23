/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dsc.test.app.pagefactory.appium;

import static io.appium.java_client.pagefactory.utils.WebDriverUnpackUtility.getCurrentContentType;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.TimeOutDuration;
import io.appium.java_client.pagefactory.Widget;
import io.appium.java_client.pagefactory.bys.ContentType;
import io.appium.java_client.pagefactory.interceptors.InterceptorOfAListOfElements;
import io.appium.java_client.pagefactory.locator.CacheableLocator;
import io.appium.java_client.pagefactory.utils.ProxyFactory;

class WidgetListInterceptor extends InterceptorOfAListOfElements {

	private List<WebElement> cachedElements;
	private final List<Widget> cachedWidgets = new ArrayList<>();
	private final Class<? extends Widget> declaredType;
	private final WebDriver driver;
	private final TimeOutDuration duration;
	private final Map<ContentType, Constructor<? extends Widget>> instantiationMap;

	WidgetListInterceptor(CacheableLocator locator, WebDriver driver,
			Map<ContentType, Constructor<? extends Widget>> instantiationMap,
			Class<? extends Widget> declaredType, TimeOutDuration duration) {
		super(locator);
		this.instantiationMap = instantiationMap;
		this.declaredType = declaredType;
		this.duration = duration;
		this.driver = driver;
	}


	@Override protected Object getObject(List<WebElement> elements, Method method, Object[] args)
			throws Throwable {
		if (cachedElements == null || locator != null && !((CacheableLocator) locator)
				.isLookUpCached()) {
			cachedElements = elements;
			cachedWidgets.clear();

			for (WebElement element : cachedElements) {
				ContentType type = getCurrentContentType(element);
				Class<?>[] params =
						new Class<?>[] {instantiationMap.get(type).getParameterTypes()[0]};
						cachedWidgets.add(ProxyFactory
								.getEnhancedProxy(declaredType, params, new Object[] {element},
										new WidgetInterceptor(null, driver, element, instantiationMap, duration)));
			}
		}
		try {
			return method.invoke(cachedWidgets, args);
		} catch (Throwable t) {
			throw ThrowableUtil.extractReadableException(t);
		}
	}
}
