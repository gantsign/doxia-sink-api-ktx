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
import com.github.gantsign.maven.doxia.sink.kotlin.style.Style
import org.apache.maven.doxia.sink.Sink
import org.apache.maven.doxia.sink.SinkEventAttributes

interface TableCellContainer {
    val sink: Sink

    @JvmDefault
    fun tableCell(
        abbvr: String = "",
        align: String = "",
        axis: String = "",
        bgColor: String = "",
        colSpan: Int? = null,
        headers: String = "",
        height: String = "",
        noWrap: String = "",
        rowSpan: Int? = null,
        scope: String = "",
        vAlign: String = "",
        width: String = "",
        id: String = "",
        cssClass: String = "",
        style: Style? = null,
        lang: String = "",
        title: String = "",
        init: TableCell.() -> Unit
    ) {
        sink.tableCell(
            attributesOf(
                SinkEventAttributes.ABBRV to abbvr,
                SinkEventAttributes.ALIGN to align,
                SinkEventAttributes.AXIS to axis,
                SinkEventAttributes.BGCOLOR to bgColor,
                SinkEventAttributes.COLSPAN to colSpan,
                SinkEventAttributes.HEADERS to headers,
                SinkEventAttributes.HEIGHT to height,
                SinkEventAttributes.NOWRAP to noWrap,
                SinkEventAttributes.ROWSPAN to rowSpan,
                SinkEventAttributes.SCOPE to scope,
                SinkEventAttributes.VALIGN to vAlign,
                SinkEventAttributes.WIDTH to width,
                SinkEventAttributes.ID to id,
                SinkEventAttributes.CLASS to cssClass,
                SinkEventAttributes.STYLE to style?.value,
                SinkEventAttributes.LANG to lang,
                SinkEventAttributes.TITLE to title
            )
        )
        init(TableCell(sink))
        sink.tableCell_()
    }
}
