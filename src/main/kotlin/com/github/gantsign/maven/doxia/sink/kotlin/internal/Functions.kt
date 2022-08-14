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
@file:Suppress("ktlint:filename")

package com.github.gantsign.maven.doxia.sink.kotlin.internal

import org.apache.maven.doxia.sink.SinkEventAttributes
import org.apache.maven.doxia.sink.impl.SinkEventAttributeSet

internal fun attributesOf(vararg pairs: Pair<String, Any?>): SinkEventAttributes {
    val attributes = SinkEventAttributeSet()
    for ((name, value) in pairs) {
        if (value != null && (value !is String || value.isNotEmpty())) {
            attributes.addAttribute(name, value)
        }
    }
    return attributes
}
