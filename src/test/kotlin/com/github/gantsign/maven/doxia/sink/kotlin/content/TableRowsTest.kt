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
import com.github.gantsign.maven.doxia.sink.kotlin.style.Justify
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

class TableRowsTest {

    @Test
    fun `no args`() {
        val sink: Sink = mockk(relaxed = true)

        val tableRowsContainer = object : TableRowsContainer {
            override val sink: Sink = sink
        }

        val tableRowAttributesSlot = slot<SinkEventAttributes>()
        val tableCellAttributesSlot = slot<SinkEventAttributes>()

        every { sink.tableRow(capture(tableRowAttributesSlot)) } just Runs
        every { sink.tableCell(capture(tableCellAttributesSlot)) } just Runs

        tableRowsContainer.tableRows {
            tableRow {
                tableCell {
                    +"body1"
                }
            }
        }

        verify { sink.tableRows(any(), false) }

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

        confirmVerified(sink)
    }

    @Test
    fun `with args`() {
        val sink: Sink = mockk(relaxed = true)

        val tableRowsContainer = object : TableRowsContainer {
            override val sink: Sink = sink
        }

        val tableRowAttributesSlot = slot<SinkEventAttributes>()
        val tableCellAttributesSlot = slot<SinkEventAttributes>()

        every { sink.tableRow(capture(tableRowAttributesSlot)) } just Runs
        every { sink.tableCell(capture(tableCellAttributesSlot)) } just Runs

        tableRowsContainer.tableRows(false, Justify.JUSTIFY_LEFT) {
            tableRow {
                tableCell {
                    +"body1"
                }
            }
        }

        verify { sink.tableRows(any(), false) }

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

        confirmVerified(sink)
    }
}
