/*-
 * #%L
 * PropertiesFramework :: Core
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

package com.github.leanframeworks.propertiesframework.base.transform.number;

import com.github.leanframeworks.propertiesframework.api.transform.Transformer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @see NumberGreaterThanTransformer
 */
public class NumberGreaterThanTransformerTest {

    @Test
    public void testDouble0() {
        Transformer<Number, Boolean> rule = new NumberGreaterThanTransformer(0.0);

        assertEquals(Boolean.FALSE, rule.transform(0.0));
        assertEquals(Boolean.TRUE, rule.transform(65.453));
        assertEquals(Boolean.FALSE, rule.transform(-1.12));
        assertEquals(Boolean.TRUE, rule.transform(Double.NaN)); // Default behavior of Double
        assertEquals(Boolean.FALSE, rule.transform(null));
    }

    @Test
    public void testDoubleNaN() {
        Transformer<Number, Boolean> rule = new NumberGreaterThanTransformer(Double.NaN);

        assertEquals(Boolean.FALSE, rule.transform(0.0)); // Default behavior of Double
        assertEquals(Boolean.FALSE, rule.transform(65.453)); // Default behavior of Double
        assertEquals(Boolean.FALSE, rule.transform(-1.12)); // Default behavior of Double
        assertEquals(Boolean.FALSE, rule.transform(Double.NaN)); // Default behavior of Double
        assertEquals(Boolean.FALSE, rule.transform(null));
    }

    @Test
    public void testDoubleNull() {
        Transformer<Number, Boolean> rule = new NumberGreaterThanTransformer(null);

        assertEquals(Boolean.TRUE, rule.transform(0.0));
        assertEquals(Boolean.TRUE, rule.transform(65.453));
        assertEquals(Boolean.TRUE, rule.transform(-1.12));
        assertEquals(Boolean.TRUE, rule.transform(Double.NaN));
        assertEquals(Boolean.FALSE, rule.transform(null));
    }
}
