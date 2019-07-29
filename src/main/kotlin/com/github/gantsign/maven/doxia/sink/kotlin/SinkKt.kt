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
package com.github.gantsign.maven.doxia.sink.kotlin

import com.github.gantsign.maven.doxia.sink.kotlin.content.BodyContainer
import com.github.gantsign.maven.doxia.sink.kotlin.content.HeadContainer
import org.apache.maven.doxia.sink.Sink

class SinkKt(override val sink: Sink) : DoxiaContent(), HeadContainer, BodyContainer {

    @Deprecated(
        message = "To be removed",
        replaceWith = ReplaceWith(
            expression = "Sink.invoke()",
            imports = ["org.apache.maven.doxia.sink.Sink"]
        )
    )
    operator fun invoke(init: SinkKt.() -> Unit) = init(this)
}

operator fun Sink.invoke(init: SinkKt.() -> Unit) {
    init(SinkKt(this))
}
