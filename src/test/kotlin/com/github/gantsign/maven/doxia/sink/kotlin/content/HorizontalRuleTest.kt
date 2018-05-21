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

class HorizontalRuleTest {

    @Test
    fun `no args`() {
        val sink: Sink = mock()

        val horizontalRuleContainer = object : HorizontalRuleContainer {
            override val sink: Sink = sink
        }

        horizontalRuleContainer.horizontalRule()

        argumentCaptor<SinkEventAttributes>().apply {
            verify(sink).horizontalRule(capture())
            assertThat(firstValue[SinkEventAttributes.ALIGN]).isNull()
            assertThat(firstValue[SinkEventAttributes.NOSHADE]).isNull()
            assertThat(firstValue[SinkEventAttributes.SIZE]).isNull()
            assertThat(firstValue[SinkEventAttributes.WIDTH]).isNull()
            assertThat(firstValue[SinkEventAttributes.ID]).isNull()
            assertThat(firstValue[SinkEventAttributes.CLASS]).isNull()
            assertThat(firstValue[SinkEventAttributes.STYLE]).isNull()
            assertThat(firstValue[SinkEventAttributes.LANG]).isNull()
            assertThat(firstValue[SinkEventAttributes.TITLE]).isNull()
        }
        verifyNoMoreInteractions(sink)
    }

    @Test
    fun `with args`() {
        val sink: Sink = mock()

        val horizontalRuleContainer = object : HorizontalRuleContainer {
            override val sink: Sink = sink
        }

        horizontalRuleContainer.horizontalRule(
            align = "align1",
            noShade = "noShade1",
            size = "size1",
            width = "width1",
            id = "id1",
            cssClass = "class1",
            style = SimpleStyle(FontStyle.BOLD),
            lang = "lang1",
            title = "title1"
        )

        argumentCaptor<SinkEventAttributes>().apply {
            verify(sink).horizontalRule(capture())
            assertThat(firstValue[SinkEventAttributes.ALIGN]).isEqualTo("align1")
            assertThat(firstValue[SinkEventAttributes.NOSHADE]).isEqualTo("noShade1")
            assertThat(firstValue[SinkEventAttributes.SIZE]).isEqualTo("size1")
            assertThat(firstValue[SinkEventAttributes.WIDTH]).isEqualTo("width1")
            assertThat(firstValue[SinkEventAttributes.ID]).isEqualTo("id1")
            assertThat(firstValue[SinkEventAttributes.CLASS]).isEqualTo("class1")
            assertThat(firstValue[SinkEventAttributes.STYLE]).isEqualTo("bold")
            assertThat(firstValue[SinkEventAttributes.LANG]).isEqualTo("lang1")
            assertThat(firstValue[SinkEventAttributes.TITLE]).isEqualTo("title1")
        }
        verifyNoMoreInteractions(sink)
    }
}
