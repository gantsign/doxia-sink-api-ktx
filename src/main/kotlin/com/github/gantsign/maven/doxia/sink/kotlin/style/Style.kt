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
package com.github.gantsign.maven.doxia.sink.kotlin.style

import org.apache.maven.doxia.sink.SinkEventAttributes
import org.apache.maven.doxia.sink.impl.SinkEventAttributeSet

sealed class Style {
    abstract val value: Any
}

@Suppress("unused")
class SimpleStyle private constructor(override val value: String) : Style() {
    constructor (value: FontStyle) : this(value.value)
}

@Suppress("unused")
class CssStyle(private val properties: Map<String, String>) : Style() {

    constructor(vararg properties: Pair<String, String>) : this(mapOf(*properties))

    override val value: SinkEventAttributes
        get() {
            val attributes = SinkEventAttributeSet()
            for ((key, value) in properties) {
                attributes.addAttribute(key, value)
            }
            return attributes
        }
}
