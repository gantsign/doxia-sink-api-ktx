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

import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import org.apache.maven.doxia.sink.Sink
import org.junit.jupiter.api.Test

class PageBreakTest {

    @Test
    fun test() {
        val sink = mockk<Sink>(relaxed = true)

        val pageBreakContainer = object : PageBreakContainer {
            override val sink: Sink = sink
        }

        pageBreakContainer.pageBreak()

        verify { sink.pageBreak() }

        confirmVerified(sink)
    }
}
