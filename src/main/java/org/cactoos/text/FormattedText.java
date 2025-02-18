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
package org.cactoos.text;

import java.util.Collection;
import java.util.Formatter;
import java.util.Locale;
import org.cactoos.Text;
import org.cactoos.list.ListOf;

/**
 * Text in Sprinf format.
 *
 * <p>There is no thread-safety guarantee.
 *
 * @since 0.1
 */
public final class FormattedText extends TextEnvelope {

    /**
     * New formatted string with default locale.
     *
     * @param ptn Pattern
     * @param arguments Arguments
     */
    public FormattedText(final String ptn, final Object... arguments) {
        this(ptn, new ListOf<>(arguments));
    }

    /**
     * New formatted string with default locale.
     *
     * @param ptn Pattern
     * @param arguments Arguments
     */
    public FormattedText(final Text ptn, final Object... arguments) {
        this(ptn, new ListOf<>(arguments));
    }

    /**
     * New formatted string with specified locale.
     *
     * @param ptn Pattern
     * @param locale Format locale
     * @param arguments Arguments
     */
    public FormattedText(
        final String ptn,
        final Locale locale,
        final Object... arguments
    ) {
        this(ptn, locale, new ListOf<>(arguments));
    }

    /**
     * New formatted string with specified locale.
     *
     * @param ptn Pattern
     * @param locale Format locale
     * @param arguments Arguments
     */
    public FormattedText(
        final Text ptn,
        final Locale locale,
        final Object... arguments
    ) {
        this(ptn, locale, new ListOf<>(arguments));
    }

    /**
     * New formatted string with default locale.
     *
     * @param ptn Pattern
     * @param arguments Arguments
     */
    public FormattedText(final CharSequence ptn, final Collection<?> arguments) {
        this(ptn, Locale.getDefault(Locale.Category.FORMAT), arguments);
    }

    /**
     * New formatted string with default locale.
     *
     * @param ptn Pattern
     * @param arguments Arguments
     */
    public FormattedText(final Text ptn, final Collection<Object> arguments) {
        this(ptn, Locale.getDefault(Locale.Category.FORMAT), arguments);
    }

    /**
     * New formatted string with specified locale.
     *
     * @param ptn Pattern
     * @param locale Format locale
     * @param arguments Arguments
     */
    public FormattedText(
        final CharSequence ptn,
        final Locale locale,
        final Collection<?> arguments
    ) {
        this(new TextOf(ptn), locale, arguments);
    }

    /**
     * New formatted string with specified locale.
     *
     * @param ptn Pattern
     * @param locale Format locale
     * @param args Arguments
     */
    public FormattedText(
        final Text ptn,
        final Locale locale,
        final Collection<?> args
    ) {
        super(
            new Mapped(
                pattern -> {
                    final StringBuilder out = new StringBuilder(pattern.length());
                    try (Formatter fmt = new Formatter(out, locale)) {
                        fmt.format(pattern, args.toArray());
                    }
                    return out.toString();
                },
                ptn
            )
        );
    }
}
