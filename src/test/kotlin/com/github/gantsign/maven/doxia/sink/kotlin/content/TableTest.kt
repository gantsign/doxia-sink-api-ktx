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
import com.github.gantsign.maven.doxia.sink.kotlin.style.Justify
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

class TableTest {

    @Test
    fun `no args`() {
        val sink = mockk<Sink>(relaxed = true)

        val tableContainer = object : TableContainer {
            override val sink: Sink = sink
        }

        val tableAttributesSlot = slot<SinkEventAttributes>()
        val tableRowAttributesSlot = slot<SinkEventAttributes>()
        val tableCellAttributesSlot = slot<SinkEventAttributes>()

        every { sink.table(capture(tableAttributesSlot)) } just Runs
        every { sink.tableRow(capture(tableRowAttributesSlot)) } just Runs
        every { sink.tableCell(capture(tableCellAttributesSlot)) } just Runs

        tableContainer.table {
            tableRows(false, Justify.JUSTIFY_LEFT) {
                tableRow {
                    tableCell {
                        +"body1"
                    }
                }
            }
        }

        verify { sink.table(any()) }

        tableAttributesSlot.captured.also {
            assertThat(it[SinkEventAttributes.ALIGN]).isNull()
            assertThat(it[SinkEventAttributes.BGCOLOR]).isNull()
            assertThat(it[SinkEventAttributes.BORDER]).isNull()
            assertThat(it[SinkEventAttributes.CELLPADDING]).isNull()
            assertThat(it[SinkEventAttributes.CELLSPACING]).isNull()
            assertThat(it[SinkEventAttributes.FRAME]).isNull()
            assertThat(it[SinkEventAttributes.RULES]).isNull()
            assertThat(it[SinkEventAttributes.SUMMARY]).isNull()
            assertThat(it[SinkEventAttributes.WIDTH]).isNull()
            assertThat(it[SinkEventAttributes.ID]).isNull()
            assertThat(it[SinkEventAttributes.CLASS]).isNull()
            assertThat(it[SinkEventAttributes.STYLE]).isNull()
            assertThat(it[SinkEventAttributes.LANG]).isNull()
            assertThat(it[SinkEventAttributes.TITLE]).isNull()
        }

        verify { sink.tableRows(intArrayOf(Sink.JUSTIFY_LEFT), false) }

        verify { sink.tableRow(any()) }

        tableRowAttributesSlot.captured.also {
            assertThat(it[SinkEventAttributes.ALIGN]).isNull()
            assertThat(it[SinkEventAttributes.BGCOLOR]).isNull()
            assertThat(it[SinkEventAttributes.VALIGN]).isNull()
            assertThat(it[SinkEventAttributes.ID]).isNull()
            assertThat(it[SinkEventAttributes.CLASS]).isNull()
            assertThat(it[SinkEventAttributes.STYLE]).isNull()
            assertThat(it[SinkEventAttributes.LANG]).isNull()
            assertThat(it[SinkEventAttributes.TITLE]).isNull()
        }

        verify { sink.tableCell(any<SinkEventAttributes>()) }

        tableCellAttributesSlot.captured.also {
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
        verify { sink.tableCell_() }
        verify { sink.tableRow_() }
        verify { sink.tableRows_() }
        verify { sink.table_() }

        confirmVerified(sink)
    }

    @Test
    fun `with args`() {
        val sink = mockk<Sink>(relaxed = true)

        val tableContainer = object : TableContainer {
            override val sink: Sink = sink
        }

        val tableAttributesSlot = slot<SinkEventAttributes>()
        val tableRowAttributesSlot = slot<SinkEventAttributes>()
        val tableCellAttributesSlot = slot<SinkEventAttributes>()

        every { sink.table(capture(tableAttributesSlot)) } just Runs
        every { sink.tableRow(capture(tableRowAttributesSlot)) } just Runs
        every { sink.tableCell(capture(tableCellAttributesSlot)) } just Runs

        tableContainer.table(
            align = "align1",
            bgColor = "bgColor1",
            border = "border1",
            cellPadding = "cellPadding1",
            cellSpacing = "cellSpacing1",
            frame = "frame1",
            rules = "rules1",
            summary = "summary1",
            width = "width1",
            id = "id1",
            cssClass = "class1",
            style = SimpleStyle(FontStyle.BOLD),
            lang = "lang1",
            title = "title1"
        ) {
            tableRows(false, Justify.JUSTIFY_LEFT) {
                tableRow {
                    tableCell {
                        +"body1"
                    }
                }
            }
        }

        verify { sink.table(any()) }

        tableAttributesSlot.captured.also {
            assertThat(it[SinkEventAttributes.ALIGN]).isEqualTo("align1")
            assertThat(it[SinkEventAttributes.BGCOLOR]).isEqualTo("bgColor1")
            assertThat(it[SinkEventAttributes.BORDER]).isEqualTo("border1")
            assertThat(it[SinkEventAttributes.CELLPADDING]).isEqualTo("cellPadding1")
            assertThat(it[SinkEventAttributes.CELLSPACING]).isEqualTo("cellSpacing1")
            assertThat(it[SinkEventAttributes.FRAME]).isEqualTo("frame1")
            assertThat(it[SinkEventAttributes.RULES]).isEqualTo("rules1")
            assertThat(it[SinkEventAttributes.SUMMARY]).isEqualTo("summary1")
            assertThat(it[SinkEventAttributes.WIDTH]).isEqualTo("width1")
            assertThat(it[SinkEventAttributes.ID]).isEqualTo("id1")
            assertThat(it[SinkEventAttributes.CLASS]).isEqualTo("class1")
            assertThat(it[SinkEventAttributes.STYLE]).isEqualTo("bold")
            assertThat(it[SinkEventAttributes.LANG]).isEqualTo("lang1")
            assertThat(it[SinkEventAttributes.TITLE]).isEqualTo("title1")
        }

        verify { sink.tableRows(intArrayOf(Sink.JUSTIFY_LEFT), false) }

        verify { sink.tableRow(any()) }

        tableRowAttributesSlot.captured.also {
            assertThat(it[SinkEventAttributes.ALIGN]).isNull()
            assertThat(it[SinkEventAttributes.BGCOLOR]).isNull()
            assertThat(it[SinkEventAttributes.VALIGN]).isNull()
            assertThat(it[SinkEventAttributes.ID]).isNull()
            assertThat(it[SinkEventAttributes.CLASS]).isNull()
            assertThat(it[SinkEventAttributes.STYLE]).isNull()
            assertThat(it[SinkEventAttributes.LANG]).isNull()
            assertThat(it[SinkEventAttributes.TITLE]).isNull()
        }

        verify { sink.tableCell(any<SinkEventAttributes>()) }

        tableCellAttributesSlot.captured.also {
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
        verify { sink.tableCell_() }
        verify { sink.tableRow_() }
        verify { sink.tableRows_() }
        verify { sink.table_() }

        confirmVerified(sink)
    }
}
