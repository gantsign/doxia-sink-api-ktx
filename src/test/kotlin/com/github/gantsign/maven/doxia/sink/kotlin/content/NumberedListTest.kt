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
import com.github.gantsign.maven.doxia.sink.kotlin.style.NumberingStyle
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

class NumberedListTest {

    @Test
    fun `no args`() {
        val sink = mockk<Sink>(relaxed = true)

        val numberedListContainer = object : NumberedListContainer {
            override val sink: Sink = sink
        }

        val numberedListAttributesSlot = slot<SinkEventAttributes>()
        val numberedListItemAttributesSlot = slot<SinkEventAttributes>()

        every {
            sink.numberedList(Sink.NUMBERING_DECIMAL, capture(numberedListAttributesSlot))
        } just Runs
        every { sink.numberedListItem(capture(numberedListItemAttributesSlot)) } just Runs

        numberedListContainer.numberedList(NumberingStyle.NUMBERING_DECIMAL) {
            listItem {
                +"body1"
            }
        }

        verify { sink.numberedList(Sink.NUMBERING_DECIMAL, any()) }

        numberedListAttributesSlot.captured.also {
            assertThat(it[SinkEventAttributes.ID]).isNull()
            assertThat(it[SinkEventAttributes.CLASS]).isNull()
            assertThat(it[SinkEventAttributes.STYLE]).isNull()
            assertThat(it[SinkEventAttributes.LANG]).isNull()
            assertThat(it[SinkEventAttributes.TITLE]).isNull()
        }

        verify { sink.numberedListItem(any()) }

        numberedListItemAttributesSlot.captured.also {
            assertThat(it[SinkEventAttributes.ID]).isNull()
            assertThat(it[SinkEventAttributes.CLASS]).isNull()
            assertThat(it[SinkEventAttributes.STYLE]).isNull()
            assertThat(it[SinkEventAttributes.LANG]).isNull()
            assertThat(it[SinkEventAttributes.TITLE]).isNull()
        }

        verify { sink.text("body1") }
        verify { sink.numberedListItem_() }
        verify { sink.numberedList_() }

        confirmVerified(sink)
    }

    @Test
    fun `with args`() {
        val sink = mockk<Sink>(relaxed = true)

        val numberedListContainer = object : NumberedListContainer {
            override val sink: Sink = sink
        }

        val numberedListAttributesSlot = slot<SinkEventAttributes>()
        val numberedListItemAttributesSlot = slot<SinkEventAttributes>()

        every {
            sink.numberedList(Sink.NUMBERING_DECIMAL, capture(numberedListAttributesSlot))
        } just Runs
        every { sink.numberedListItem(capture(numberedListItemAttributesSlot)) } just Runs

        numberedListContainer.numberedList(
            numberingStyle = NumberingStyle.NUMBERING_DECIMAL,
            id = "id1",
            cssClass = "class1",
            style = SimpleStyle(FontStyle.BOLD),
            lang = "lang1",
            title = "title1"
        ) {
            listItem {
                +"body1"
            }
        }

        verify { sink.numberedList(Sink.NUMBERING_DECIMAL, any()) }

        numberedListAttributesSlot.captured.also {
            assertThat(it[SinkEventAttributes.ID]).isEqualTo("id1")
            assertThat(it[SinkEventAttributes.CLASS]).isEqualTo("class1")
            assertThat(it[SinkEventAttributes.STYLE]).isEqualTo("bold")
            assertThat(it[SinkEventAttributes.LANG]).isEqualTo("lang1")
            assertThat(it[SinkEventAttributes.TITLE]).isEqualTo("title1")
        }

        verify { sink.numberedListItem(any()) }

        numberedListItemAttributesSlot.captured.also {
            assertThat(it[SinkEventAttributes.ID]).isNull()
            assertThat(it[SinkEventAttributes.CLASS]).isNull()
            assertThat(it[SinkEventAttributes.STYLE]).isNull()
            assertThat(it[SinkEventAttributes.LANG]).isNull()
            assertThat(it[SinkEventAttributes.TITLE]).isNull()
        }

        verify { sink.text("body1") }
        verify { sink.numberedListItem_() }
        verify { sink.numberedList_() }

        confirmVerified(sink)
    }
}
