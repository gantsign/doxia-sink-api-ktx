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

import com.github.gantsign.maven.doxia.sink.kotlin.internal.attributesOf
import com.github.gantsign.maven.doxia.sink.kotlin.style.Decoration
import com.github.gantsign.maven.doxia.sink.kotlin.style.Style
import com.github.gantsign.maven.doxia.sink.kotlin.style.VAlign
import org.apache.maven.doxia.sink.Sink
import org.apache.maven.doxia.sink.SinkEventAttributes

interface TextContainer {
    val sink: Sink

    @JvmDefault
    fun text(
        vAlign: VAlign? = null,
        decoration: Decoration? = null,
        id: String = "",
        cssClass: String = "",
        style: Style? = null,
        lang: String = "",
        title: String = "",
        supplier: () -> String
    ) {
        sink.text(
            supplier(),
            attributesOf(
                SinkEventAttributes.VALIGN to vAlign?.value,
                SinkEventAttributes.DECORATION to decoration?.value,
                SinkEventAttributes.ID to id,
                SinkEventAttributes.CLASS to cssClass,
                SinkEventAttributes.STYLE to style?.value,
                SinkEventAttributes.LANG to lang,
                SinkEventAttributes.TITLE to title
            )
        )
    }

    @JvmDefault
    operator fun String.unaryPlus() {
        sink.text(this)
    }
}
