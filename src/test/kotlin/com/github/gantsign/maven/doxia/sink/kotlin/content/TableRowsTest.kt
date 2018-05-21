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
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import org.apache.maven.doxia.sink.Sink
import org.apache.maven.doxia.sink.SinkEventAttributes
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TableRowsTest {

    @Test
    fun test() {
        val sink: Sink = mock()

        val tableRowsContainer = object : TableRowsContainer {
            override val sink: Sink = sink
        }

        tableRowsContainer.tableRows(false, Justify.JUSTIFY_LEFT) {
            tableRow {
                tableCell {
                    +"body1"
                }
            }
        }

        verify(sink).tableRows(intArrayOf(Sink.JUSTIFY_LEFT), false)
        argumentCaptor<SinkEventAttributes>().apply {
            verify(sink).tableRow(capture())
            assertThat(firstValue[SinkEventAttributes.ALIGN]).isNull()
            assertThat(firstValue[SinkEventAttributes.BGCOLOR]).isNull()
            assertThat(firstValue[SinkEventAttributes.VALIGN]).isNull()
            assertThat(firstValue[SinkEventAttributes.ID]).isNull()
            assertThat(firstValue[SinkEventAttributes.CLASS]).isNull()
            assertThat(firstValue[SinkEventAttributes.STYLE]).isNull()
            assertThat(firstValue[SinkEventAttributes.LANG]).isNull()
            assertThat(firstValue[SinkEventAttributes.TITLE]).isNull()
        }
        argumentCaptor<SinkEventAttributes>().apply {
            verify(sink).tableCell(capture())
            assertThat(firstValue[SinkEventAttributes.ABBRV]).isNull()
            assertThat(firstValue[SinkEventAttributes.ALIGN]).isNull()
            assertThat(firstValue[SinkEventAttributes.AXIS]).isNull()
            assertThat(firstValue[SinkEventAttributes.BGCOLOR]).isNull()
            assertThat(firstValue[SinkEventAttributes.COLSPAN]).isNull()
            assertThat(firstValue[SinkEventAttributes.HEADERS]).isNull()
            assertThat(firstValue[SinkEventAttributes.HEIGHT]).isNull()
            assertThat(firstValue[SinkEventAttributes.NOWRAP]).isNull()
            assertThat(firstValue[SinkEventAttributes.ROWSPAN]).isNull()
            assertThat(firstValue[SinkEventAttributes.SCOPE]).isNull()
            assertThat(firstValue[SinkEventAttributes.VALIGN]).isNull()
            assertThat(firstValue[SinkEventAttributes.WIDTH]).isNull()
            assertThat(firstValue[SinkEventAttributes.ID]).isNull()
            assertThat(firstValue[SinkEventAttributes.CLASS]).isNull()
            assertThat(firstValue[SinkEventAttributes.STYLE]).isNull()
            assertThat(firstValue[SinkEventAttributes.LANG]).isNull()
            assertThat(firstValue[SinkEventAttributes.TITLE]).isNull()
        }
        verify(sink).text("body1")
        verify(sink).tableCell_()
        verify(sink).tableRow_()
        verify(sink).tableRows_()
        verifyNoMoreInteractions(sink)
    }
}
