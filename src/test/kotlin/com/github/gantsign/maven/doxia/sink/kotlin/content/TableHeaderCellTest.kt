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
import org.junit.jupiter.api.Test

class TableHeaderCellTest {

    @Test
    fun `no args`() {
        val sink = mockk<Sink>(relaxed = true)

        val tableHeaderCellContainer = object : TableHeaderCellContainer {
            override val sink: Sink = sink
        }

        val attributesSlot = slot<SinkEventAttributes>()

        every { sink.tableHeaderCell(capture(attributesSlot)) } just Runs

        tableHeaderCellContainer.tableHeaderCell {
            +"body1"
        }

        verify { sink.tableHeaderCell(any<SinkEventAttributes>()) }

        attributesSlot.captured.also {
            assertThat(it[SinkEventAttributes.ABBRV]).isNull()
            assertThat(it[SinkEventAttributes.ALIGN]).isNull()
            assertThat(it[SinkEventAttributes.AXIS]).isNull()
            assertThat(it[SinkEventAttributes.BGCOLOR]).isNull()
            assertThat(it[SinkEventAttributes.COLSPAN]).isNull()
            assertThat(it[SinkEventAttributes.HEADERS]).isNull()
            assertThat(it[SinkEventAttributes.HEIGHT]).isNull()
            assertThat(it[SinkEventAttributes.NOWRAP]).isNull()
            assertThat(it[SinkEventAttributes.ROWSPAN]).isNull()
            assertThat(it[SinkEventAttributes.SCOPE]).isNull()
            assertThat(it[SinkEventAttributes.VALIGN]).isNull()
            assertThat(it[SinkEventAttributes.WIDTH]).isNull()
            assertThat(it[SinkEventAttributes.ID]).isNull()
            assertThat(it[SinkEventAttributes.CLASS]).isNull()
            assertThat(it[SinkEventAttributes.STYLE]).isNull()
            assertThat(it[SinkEventAttributes.LANG]).isNull()
            assertThat(it[SinkEventAttributes.TITLE]).isNull()
        }

        verify { sink.text("body1") }
        verify { sink.tableHeaderCell_() }

        confirmVerified(sink)
    }

    @Test
    fun `with args`() {
        val sink = mockk<Sink>(relaxed = true)

        val tableHeaderCellContainer = object : TableHeaderCellContainer {
            override val sink: Sink = sink
        }

        val attributesSlot = slot<SinkEventAttributes>()

        every { sink.tableHeaderCell(capture(attributesSlot)) } just Runs

        tableHeaderCellContainer.tableHeaderCell(
            abbvr = "abbvr1",
            align = "align1",
            axis = "axis1",
            bgColor = "bgColor1",
            colSpan = 1,
            headers = "headers1",
            height = "height1",
            noWrap = "noWrap1",
            rowSpan = 2,
            scope = "scope1",
            vAlign = "vAlign1",
            width = "width1",
            id = "id1",
            cssClass = "class1",
            style = SimpleStyle(FontStyle.BOLD),
            lang = "lang1",
            title = "title1"
        ) {
            +"body1"
        }

        verify { sink.tableHeaderCell(any<SinkEventAttributes>()) }

        attributesSlot.captured.also {
            assertThat(it[SinkEventAttributes.ABBRV]).isEqualTo("abbvr1")
            assertThat(it[SinkEventAttributes.ALIGN]).isEqualTo("align1")
            assertThat(it[SinkEventAttributes.AXIS]).isEqualTo("axis1")
            assertThat(it[SinkEventAttributes.BGCOLOR]).isEqualTo("bgColor1")
            assertThat(it[SinkEventAttributes.COLSPAN]).isEqualTo(1)
            assertThat(it[SinkEventAttributes.HEADERS]).isEqualTo("headers1")
            assertThat(it[SinkEventAttributes.HEIGHT]).isEqualTo("height1")
            assertThat(it[SinkEventAttributes.NOWRAP]).isEqualTo("noWrap1")
            assertThat(it[SinkEventAttributes.ROWSPAN]).isEqualTo(2)
            assertThat(it[SinkEventAttributes.SCOPE]).isEqualTo("scope1")
            assertThat(it[SinkEventAttributes.VALIGN]).isEqualTo("vAlign1")
            assertThat(it[SinkEventAttributes.WIDTH]).isEqualTo("width1")
            assertThat(it[SinkEventAttributes.ID]).isEqualTo("id1")
            assertThat(it[SinkEventAttributes.CLASS]).isEqualTo("class1")
            assertThat(it[SinkEventAttributes.STYLE]).isEqualTo("bold")
            assertThat(it[SinkEventAttributes.LANG]).isEqualTo("lang1")
            assertThat(it[SinkEventAttributes.TITLE]).isEqualTo("title1")
        }

        verify { sink.text("body1") }
        verify { sink.tableHeaderCell_() }

        confirmVerified(sink)
    }
}
