/*-
 * #%L
 * PropertiesFramework :: Swing Support
 * %%
 * Copyright (C) 2017 LeanFrameworks
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

package com.github.leanframeworks.propertiesframework.swing.property;

import com.github.leanframeworks.propertiesframework.api.property.PropertyChange;
import com.github.leanframeworks.propertiesframework.api.property.PropertyChangeListener;
import com.github.leanframeworks.propertiesframework.api.property.ReadableWritableProperty;
import org.junit.Test;

import javax.swing.Icon;
import javax.swing.JButton;

import static com.github.leanframeworks.propertiesframework.test.TestUtils.matches;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @see JButtonIconProperty
 */
public class JButtonIconPropertyTest {

    private static final Icon ICON1 = new TestIcon();

    private static final Icon ICON2 = new TestIcon();

    @SuppressWarnings("unchecked")
    @Test
    public void testNullFromProperty() {
        JButton button = new JButton(ICON1);
        ReadableWritableProperty<Icon> iconProperty = new JButtonIconProperty(button);
        PropertyChangeListener<Icon> listenerMock = (PropertyChangeListener<Icon>) mock(PropertyChangeListener.class);
        iconProperty.addChangeListener(listenerMock);

        assertEquals(ICON1, iconProperty.getValue());
        iconProperty.setValue(null);
        assertEquals(null, button.getIcon());

        // Check exactly one event fired
        verify(listenerMock).propertyChanged(matches(new PropertyChange<>(iconProperty, ICON1, null)));
        verify(listenerMock).propertyChanged(any());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testNonNullFromProperty() {
        JButton button = new JButton(ICON1);
        ReadableWritableProperty<Icon> iconProperty = new JButtonIconProperty(button);
        PropertyChangeListener<Icon> listenerMock = (PropertyChangeListener<Icon>) mock(PropertyChangeListener.class);
        iconProperty.addChangeListener(listenerMock);

        assertEquals(ICON1, iconProperty.getValue());
        iconProperty.setValue(ICON2);
        assertEquals(ICON2, button.getIcon());

        // Check exactly one event fired
        verify(listenerMock).propertyChanged(matches(new PropertyChange<>(iconProperty, ICON1, ICON2)));
        verify(listenerMock).propertyChanged(any());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testNullFromComponent() {
        JButton button = new JButton(ICON1);
        ReadableWritableProperty<Icon> iconProperty = new JButtonIconProperty(button);
        PropertyChangeListener<Icon> listenerMock = (PropertyChangeListener<Icon>) mock(PropertyChangeListener.class);
        iconProperty.addChangeListener(listenerMock);

        assertEquals(ICON1, iconProperty.getValue());
        button.setIcon(null);
        assertEquals(null, iconProperty.getValue());

        // Check exactly one event fired
        verify(listenerMock).propertyChanged(matches(new PropertyChange<>(iconProperty, ICON1, null)));
        verify(listenerMock).propertyChanged(any());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testNonNullFromComponent() {
        JButton button = new JButton(ICON1);
        ReadableWritableProperty<Icon> iconProperty = new JButtonIconProperty(button);
        PropertyChangeListener<Icon> listenerMock = (PropertyChangeListener<Icon>) mock(PropertyChangeListener.class);
        iconProperty.addChangeListener(listenerMock);

        assertEquals(ICON1, iconProperty.getValue());
        button.setIcon(ICON2);
        assertEquals(ICON2, iconProperty.getValue());

        // Check exactly one event fired
        verify(listenerMock).propertyChanged(matches(new PropertyChange<>(iconProperty, ICON1, ICON2)));
        verify(listenerMock).propertyChanged(any());
    }
}
