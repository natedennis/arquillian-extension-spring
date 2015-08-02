/**
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.arquillian.warp.extension.spring.container;

import org.jboss.arquillian.core.spi.LoadableExtension;
import org.jboss.arquillian.test.spi.enricher.resource.ResourceProvider;
import org.jboss.arquillian.warp.extension.spring.container.provider.*;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * <p>Tests {@link SpringWarpRemoteExtension} class.</p>
 *
 * @author <a href="mailto:jmnarloch@gmail.com">Jakub Narloch</a>
 */
public class SpringWarpRemoteExtensionTestCase {

    /**
     * <p>Represents the instance of tested class.</p>
     */
    private SpringWarpRemoteExtension instance;

    /**
     * <p>Sets up the test environment.</p>
     */
    @Before
    public void setUp() {

        instance = new SpringWarpRemoteExtension();
    }

    /**
     * <p>Tests the {@link SpringWarpRemoteExtension#register(LoadableExtension.ExtensionBuilder)} method.</p>
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testRegister() {

        LoadableExtension.ExtensionBuilder mockExtensionBuilder = mock(LoadableExtension.ExtensionBuilder.class);
        when(mockExtensionBuilder.service(any(Class.class), any(Class.class))).thenReturn(mockExtensionBuilder);
        when(mockExtensionBuilder.observer(any(Class.class))).thenReturn(mockExtensionBuilder);

        instance.register(mockExtensionBuilder);

        verify(mockExtensionBuilder).service(ResourceProvider.class, SpringMvcResultProvider.class);
        verify(mockExtensionBuilder).service(ResourceProvider.class, ModelAndViewProvider.class);
        verify(mockExtensionBuilder).service(ResourceProvider.class, ErrorsProvider.class);
        verify(mockExtensionBuilder).service(ResourceProvider.class, ExceptionProvider.class);
        verify(mockExtensionBuilder).service(ResourceProvider.class, HandlerInterceptorsProvider.class);
        verify(mockExtensionBuilder).service(ResourceProvider.class, HandlerProvider.class);
        verifyNoMoreInteractions(mockExtensionBuilder);
    }
}
