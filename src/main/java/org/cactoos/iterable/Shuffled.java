/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2020 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cactoos.iterable;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Shuffled iterable.
 *
 * <p>There is no thread-safety guarantee.</p>
 *
 * @param <T> Element type
 * @since 0.20
 */
public final class Shuffled<T> extends IterableEnvelope<T> {

    /**
     * Ctor.
     * @param src The underlying iterable
     * @since 0.23
     */
    @SafeVarargs
    public Shuffled(final T... src) {
        this(new IterableOf<>(src));
    }

    /**
     * Ctor.
     * @param src The underlying iterable
     */
    public Shuffled(final Iterable<? extends T> src) {
        this(new SecureRandom(), src);
    }

    /**
     * Ctor.
     * @param rnd Randomizer.
     * @param src The underlying iterable
     */
    public Shuffled(final Random rnd, final Iterable<? extends T> src) {
        super(
            new IterableOf<>(
                () -> new org.cactoos.iterator.Shuffled<>(
                    rnd,
                    src.iterator()
                )
            )
        );
    }

}
