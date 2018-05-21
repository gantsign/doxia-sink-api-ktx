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
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import org.apache.maven.doxia.sink.Sink
import org.apache.maven.doxia.sink.SinkEventAttributes
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TableHeaderCellTest {

    @Test
    fun `no args`() {
        val sink: Sink = mock()

        val tableHeaderCellContainer = object : TableHeaderCellContainer {
            override val sink: Sink = sink
        }

        tableHeaderCellContainer.tableHeaderCell {
            +"body1"
        }

        argumentCaptor<SinkEventAttributes>().apply {
            verify(sink).tableHeaderCell(capture())
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
        verify(sink).tableHeaderCell_()
        verifyNoMoreInteractions(sink)
    }

    @Test
    fun `with args`() {
        val sink: Sink = mock()

        val tableHeaderCellContainer = object : TableHeaderCellContainer {
            override val sink: Sink = sink
        }

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

        argumentCaptor<SinkEventAttributes>().apply {
            verify(sink).tableHeaderCell(capture())
            assertThat(firstValue[SinkEventAttributes.ABBRV]).isEqualTo("abbvr1")
            assertThat(firstValue[SinkEventAttributes.ALIGN]).isEqualTo("align1")
            assertThat(firstValue[SinkEventAttributes.AXIS]).isEqualTo("axis1")
            assertThat(firstValue[SinkEventAttributes.BGCOLOR]).isEqualTo("bgColor1")
            assertThat(firstValue[SinkEventAttributes.COLSPAN]).isEqualTo(1)
            assertThat(firstValue[SinkEventAttributes.HEADERS]).isEqualTo("headers1")
            assertThat(firstValue[SinkEventAttributes.HEIGHT]).isEqualTo("height1")
            assertThat(firstValue[SinkEventAttributes.NOWRAP]).isEqualTo("noWrap1")
            assertThat(firstValue[SinkEventAttributes.ROWSPAN]).isEqualTo(2)
            assertThat(firstValue[SinkEventAttributes.SCOPE]).isEqualTo("scope1")
            assertThat(firstValue[SinkEventAttributes.VALIGN]).isEqualTo("vAlign1")
            assertThat(firstValue[SinkEventAttributes.WIDTH]).isEqualTo("width1")
            assertThat(firstValue[SinkEventAttributes.ID]).isEqualTo("id1")
            assertThat(firstValue[SinkEventAttributes.CLASS]).isEqualTo("class1")
            assertThat(firstValue[SinkEventAttributes.STYLE]).isEqualTo("bold")
            assertThat(firstValue[SinkEventAttributes.LANG]).isEqualTo("lang1")
            assertThat(firstValue[SinkEventAttributes.TITLE]).isEqualTo("title1")
        }
        verify(sink).text("body1")
        verify(sink).tableHeaderCell_()
        verifyNoMoreInteractions(sink)
    }
}
