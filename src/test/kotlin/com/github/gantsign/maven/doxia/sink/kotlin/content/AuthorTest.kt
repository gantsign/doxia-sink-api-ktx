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
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import org.apache.maven.doxia.sink.Sink
import org.apache.maven.doxia.sink.SinkEventAttributes
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AuthorTest {

    @Test
    fun `no args`() {
        val sink: Sink = mock()

        val authorContainer = object : AuthorContainer {
            override val sink: Sink = sink
        }

        authorContainer.author {
            +"body1"
        }

        argumentCaptor<SinkEventAttributes>().apply {
            verify(sink).author(capture())
            assertThat(firstValue[SinkEventAttributes.EMAIL]).isNull()
        }
        verify(sink).text("body1")
        verify(sink).author_()
        verifyNoMoreInteractions(sink)
    }

    @Test
    fun `with args`() {
        val sink: Sink = mock()

        val authorContainer = object : AuthorContainer {
            override val sink: Sink = sink
        }

        authorContainer.author("email1") {
            +"body1"
        }

        argumentCaptor<SinkEventAttributes>().apply {
            verify(sink).author(capture())

            assertThat(firstValue[SinkEventAttributes.EMAIL]).isEqualTo("email1")
        }
        verify(sink).text("body1")
        verify(sink).author_()
        verifyNoMoreInteractions(sink)
    }
}
