# Kotlin Extensions for Doxia

[![Build Status](https://travis-ci.com/gantsign/doxia-sink-api-ktx.svg?branch=master)](https://travis-ci.com/gantsign/doxia-sink-api-ktx)
[![codecov](https://codecov.io/gh/gantsign/doxia-sink-api-ktx/branch/master/graph/badge.svg)](https://codecov.io/gh/gantsign/doxia-sink-api-ktx)
[![Known Vulnerabilities](https://snyk.io/test/github/gantsign/doxia-sink-api-ktx/badge.svg)](https://snyk.io/test/github/gantsign/doxia-sink-api-ktx)

Kotlin extensions for the [Apache Doxia](https://maven.apache.org/doxia/) is a
content generation framework. The main use of these extensions are for
generating reports in custom Maven plugins.

**This API is currently unstable, no compatibility guarantees are made between versions.**

## Writing Maven Report Content

This library provides a Kotlin DSL for writing content using Doxia Sink. The
following is an extract from the [ktlint Maven Plugin](https://github.com/gantsign/ktlint-maven-plugin/blob/master/src/main/kotlin/com/github/gantsign/maven/plugin/ktlint/internal/KtlintReportGenerator.kt):


```kotlin
fun generatorReport(results: CheckResults) {
    sink {
        head {
            title {
                +title
            }
        }
        body {
            section(1) {
                title {
                    +title
                }
                paragraph {
                    +"${bundle["report.ktlint.ktlintlink"]} "
                    link("https://github.com/shyiko/ktlint") {
                        +"ktlint"
                    }
                    if (ktlintVersion != null) {
                        +" $ktlintVersion"
                    }
                    +"."
                }
            }

            section(1) {
                title {
                    +bundle["report.ktlint.summary"]
                }
                table {
                    tableRows {
                        tableRow {
                            tableHeaderCell {
                                +bundle["report.ktlint.files"]
                            }
                            tableHeaderCell {
                                +bundle["report.ktlint.errors"]
                            }
                        }
                        tableRow {
                            tableCell {
                                +"${results.fileCount}"
                            }
                            tableCell {
                                +"${results.errors.size}"
                            }
                        }
                    }
                }
            }

            val errorsByFile =
                results.errors.groupBy(FileLintError::file).toSortedMap()

            if (errorsByFile.isNotEmpty()) {

                section(1) {
                    title {
                        +bundle["report.ktlint.files"]
                    }
                    table {
                        tableRows {
                            tableRow {
                                tableHeaderCell {
                                    +bundle["report.ktlint.file"]
                                }
                                tableHeaderCell {
                                    +bundle["report.ktlint.errors"]
                                }
                            }

                            for ((file, errors) in errorsByFile) {
                                tableRow {
                                    tableCell {
                                        link("#${file.replace('/', '.')}") {
                                            +file
                                        }
                                    }
                                    tableCell {
                                        +"${errors.size}"
                                    }
                                }
                            }
                        }
                    }
                }

                section(1) {
                    title {
                        +bundle["report.ktlint.details"]
                    }
                    for ((file, errors) in errorsByFile) {
                        val sortedErrors = errors.sortedWith(
                            Comparator.comparingInt(FileLintError::line)
                                .thenComparingInt(FileLintError::col)
                                .thenComparing(FileLintError::ruleId)
                        )
                        section(2, id = file.replace('/', '.')) {
                            title {
                                +file
                            }
                        }
                        table {
                            tableRows {
                                tableRow {
                                    tableHeaderCell {
                                        +bundle["report.ktlint.detail"]
                                    }
                                    tableHeaderCell {
                                        +bundle["report.ktlint.ruleId"]
                                    }
                                    tableHeaderCell {
                                        +bundle["report.ktlint.line"]
                                    }
                                }
                                for (error in sortedErrors) {
                                    tableRow {
                                        tableCell {
                                            +error.detail
                                        }
                                        tableCell {
                                            +error.ruleId
                                        }
                                        tableCell {
                                            +"${error.line}"
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

```

## Repository

This library isn't currently available from Maven Central, you need to add the
following to your repository manager or to your Maven POM.

```xml
<repositories>
  ...
  <repository>
    <snapshots>
      <enabled>false</enabled>
    </snapshots>
    <id>bintray-gantsign-maven</id>
    <name>bintray</name>
    <url>https://dl.bintray.com/gantsign/maven</url>
  </repository>
  ...
</repositories>
```

## License

This software is licensed under the terms in the file named "LICENSE" in the
root directory of this project. This project has dependencies that are under
different licenses.

## Author Information

John Freeman

GantSign Ltd.
Company No. 06109112 (registered in England)
