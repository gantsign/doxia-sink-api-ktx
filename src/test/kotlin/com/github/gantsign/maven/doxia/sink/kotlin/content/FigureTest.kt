/*-
 * #%L
 * doxia-sink-api-ktx
 * %%
 * Copyright (C) 2018 GantSign Ltd.
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.github.gantsign.maven.doxia.sink.kotlin.content

import com.github.gantsign.maven.doxia.sink.kotlin.get
import com.github.gantsign.maven.doxia.sink.kotlin.style.FontStyle
import com.github.gantsign.maven.doxia.sink.kotlin.style.SimpleStyle
import io.mockk.Runs
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.apache.maven.doxia.sink.Sink
import org.apache.maven.doxia.sink.SinkEventAttributes
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class FigureTest {

    @Test
    fun `no args`() {
        val sink: Sink = mockk(relaxed = true)

        val figureContainer = object : FigureContainer {
            override val sink: Sink = sink
        }

        val figureAttributesSlot = slot<SinkEventAttributes>()
        val figureCaptionAttributesSlot = slot<SinkEventAttributes>()
        val figureGraphicsAttributesSlot = slot<SinkEventAttributes>()

        every { sink.figure(capture(figureAttributesSlot)) } just Runs
        every { sink.figureCaption(capture(figureCaptionAttributesSlot)) } just Runs
        every {
            sink.figureGraphics(
                "http://example.com",
                capture(figureGraphicsAttributesSlot)
            )
        } just Runs

        figureContainer.figure {
            caption {
                +"caption1"
            }
            figureGraphics("http://example.com")
        }

        verify { sink.figure(any()) }

        figureAttributesSlot.captured.also {
            assertThat(it[SinkEventAttributes.ID]).isNull()
            assertThat(it[SinkEventAttributes.CLASS]).isNull()
            assertThat(it[SinkEventAttributes.STYLE]).isNull()
            assertThat(it[SinkEventAttributes.LANG]).isNull()
            assertThat(it[SinkEventAttributes.TITLE]).isNull()
        }

        verify { sink.figureCaption(any()) }

        figureCaptionAttributesSlot.captured.also {
            assertThat(it[SinkEventAttributes.ID]).isNull()
            assertThat(it[SinkEventAttributes.CLASS]).isNull()
            assertThat(it[SinkEventAttributes.STYLE]).isNull()
            assertThat(it[SinkEventAttributes.LANG]).isNull()
            assertThat(it[SinkEventAttributes.TITLE]).isNull()
        }
        verify { sink.text("caption1") }
        verify { sink.figureCaption_() }

        verify { sink.figureGraphics("http://example.com", any()) }

        figureGraphicsAttributesSlot.captured.also {
            assertThat(it[SinkEventAttributes.ALT]).isNull()
            assertThat(it[SinkEventAttributes.WIDTH]).isNull()
            assertThat(it[SinkEventAttributes.HEIGHT]).isNull()
            assertThat(it[SinkEventAttributes.ALIGN]).isNull()
            assertThat(it[SinkEventAttributes.BORDER]).isNull()
            assertThat(it[SinkEventAttributes.HSPACE]).isNull()
            assertThat(it[SinkEventAttributes.VSPACE]).isNull()
            assertThat(it[SinkEventAttributes.ISMAP]).isNull()
            assertThat(it[SinkEventAttributes.USEMAP]).isNull()
            assertThat(it[SinkEventAttributes.ID]).isNull()
            assertThat(it[SinkEventAttributes.CLASS]).isNull()
            assertThat(it[SinkEventAttributes.STYLE]).isNull()
            assertThat(it[SinkEventAttributes.LANG]).isNull()
            assertThat(it[SinkEventAttributes.TITLE]).isNull()
        }

        verify { sink.figure_() }

        confirmVerified(sink)
    }

    @Test
    fun `with args`() {
        val sink: Sink = mockk(relaxed = true)

        val figureContainer = object : FigureContainer {
            override val sink: Sink = sink
        }

        val figureAttributesSlot = slot<SinkEventAttributes>()
        val figureCaptionAttributesSlot = slot<SinkEventAttributes>()
        val figureGraphicsAttributesSlot = slot<SinkEventAttributes>()

        every { sink.figure(capture(figureAttributesSlot)) } just Runs
        every { sink.figureCaption(capture(figureCaptionAttributesSlot)) } just Runs
        every {
            sink.figureGraphics(
                "http://example.com",
                capture(figureGraphicsAttributesSlot)
            )
        } just Runs

        figureContainer.figure(
            id = "id1",
            cssClass = "class1",
            style = SimpleStyle(FontStyle.BOLD),
            lang = "lang1",
            title = "title1"
        ) {
            caption(
                id = "id2",
                cssClass = "class2",
                style = SimpleStyle(FontStyle.ITALIC),
                lang = "lang2",
                title = "title2"
            ) {
                +"caption1"
            }
            figureGraphics("http://example.com")
        }

        verify { sink.figure(any()) }

        figureAttributesSlot.captured.also {
            assertThat(it[SinkEventAttributes.ID]).isEqualTo("id1")
            assertThat(it[SinkEventAttributes.CLASS]).isEqualTo("class1")
            assertThat(it[SinkEventAttributes.STYLE]).isEqualTo("bold")
            assertThat(it[SinkEventAttributes.LANG]).isEqualTo("lang1")
            assertThat(it[SinkEventAttributes.TITLE]).isEqualTo("title1")
        }

        verify { sink.figureCaption(any()) }

        figureCaptionAttributesSlot.captured.also {
            assertThat(it[SinkEventAttributes.ID]).isEqualTo("id2")
            assertThat(it[SinkEventAttributes.CLASS]).isEqualTo("class2")
            assertThat(it[SinkEventAttributes.STYLE]).isEqualTo("italic")
            assertThat(it[SinkEventAttributes.LANG]).isEqualTo("lang2")
            assertThat(it[SinkEventAttributes.TITLE]).isEqualTo("title2")
        }

        verify { sink.text("caption1") }
        verify { sink.figureCaption_() }

        verify { sink.figureGraphics("http://example.com", any()) }

        figureGraphicsAttributesSlot.captured.also {
            assertThat(it[SinkEventAttributes.ALT]).isNull()
            assertThat(it[SinkEventAttributes.WIDTH]).isNull()
            assertThat(it[SinkEventAttributes.HEIGHT]).isNull()
            assertThat(it[SinkEventAttributes.ALIGN]).isNull()
            assertThat(it[SinkEventAttributes.BORDER]).isNull()
            assertThat(it[SinkEventAttributes.HSPACE]).isNull()
            assertThat(it[SinkEventAttributes.VSPACE]).isNull()
            assertThat(it[SinkEventAttributes.ISMAP]).isNull()
            assertThat(it[SinkEventAttributes.USEMAP]).isNull()
            assertThat(it[SinkEventAttributes.ID]).isNull()
            assertThat(it[SinkEventAttributes.CLASS]).isNull()
            assertThat(it[SinkEventAttributes.STYLE]).isNull()
            assertThat(it[SinkEventAttributes.LANG]).isNull()
            assertThat(it[SinkEventAttributes.TITLE]).isNull()
        }

        verify { sink.figure_() }

        confirmVerified(sink)
    }
}
