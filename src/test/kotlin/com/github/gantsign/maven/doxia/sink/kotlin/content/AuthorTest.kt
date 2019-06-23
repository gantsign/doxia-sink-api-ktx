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

class AuthorTest {

    @Test
    fun `no args`() {
        val sink = mockk<Sink>(relaxed = true)

        val authorContainer = object : AuthorContainer {
            override val sink: Sink = sink
        }

        val attributesSlot = slot<SinkEventAttributes>()

        every { sink.author(capture(attributesSlot)) } just Runs

        authorContainer.author {
            +"body1"
        }

        verify { sink.author(any()) }

        attributesSlot.captured.also {
            assertThat(it[SinkEventAttributes.EMAIL]).isNull()
        }

        verify { sink.text("body1") }
        verify { sink.author_() }

        confirmVerified(sink)
    }

    @Test
    fun `with args`() {
        val sink = mockk<Sink>(relaxed = true)

        val authorContainer = object : AuthorContainer {
            override val sink: Sink = sink
        }

        val attributesSlot = slot<SinkEventAttributes>()

        every { sink.author(capture(attributesSlot)) } just Runs

        authorContainer.author("email1") {
            +"body1"
        }

        verify { sink.author(any()) }

        attributesSlot.captured.also {
            assertThat(it[SinkEventAttributes.EMAIL]).isEqualTo("email1")
        }

        verify { sink.text("body1") }
        verify { sink.author_() }

        confirmVerified(sink)
    }
}
