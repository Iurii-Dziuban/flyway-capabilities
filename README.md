# flyway-capabilities
[![Open Source Love](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badge/)    

[![Build Status](https://travis-ci.org/Iurii-Dziuban/flyway-capabilities.svg?branch=master)](https://travis-ci.org/Iurii-Dziuban/flyway-capabilities)
[![Coverage Status](https://coveralls.io/repos/github/Iurii-Dziuban/flyway-capabilities/badge.svg?branch=master)](https://coveralls.io/github/Iurii-Dziuban/flyway-capabilities?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/57b8ae77fc182700376fe67e/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/57b8ae77fc182700376fe67e)
[![contributions welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat)](https://github.com/Iurii-Dziuban/flyway-capabilities/issues)

A project that shows flyway capabilities **(https://flywaydb.org/)**

Table of contents:
 * [Static Analysis QA Checks](#checks)
 * [Project parts](#project-parts)
 * [Flyway main features](#flyway-main-features-demo)
 * [Flyway test features](#flyway-test-features-demo)
 * [Build configuration](#pomxml)
 * [Logging configuration](#logging)
 * [Flyway default configuration](#flyway-default-configuration)

# Checks

`Jacoco` code coverage, `pmd`, `checkstyle`, `enforcer`, `findbugs`

# Project parts
Maven project that consists of the following parts:
- Java-driven configurations for flyway migration features (`src/main/java/main`)
- Declarative config-driven maven configuration for flyway migration features (`pom.xml`) via `maven flyway plugin` 
- Test extensions for executing tests with flyway migrations (`src/test/java/fly`) 

# Flyway main features demo
Main features examples are under `src/main/java/main` folder. It is simply classes with main methods, that explain the feature and ready to be executed and provide log that shows the results.

# Flyway test features demo
Test features are shown via tests under `src/test/java/fly` folder. Each test shows particular test feature and provides descriptive log.

# Pom.xml
Basic maven flyway plugins capabilities are invocations `mvn flyway:clean` / `mvn flyway:migrate` / `mvn flyway:repair`.
In case support for couple db migrations needed it can be described in the configuration via maven <executions>

**Libraries:** `flyway core`, `spring jdbc` for spring jdbc migrations, `commons-dbcp2` for database connection pool, `h2` file based database for ease of db usage, `log4j` logging (possibility to configure) via `slf4j`.

**Test libraries:** `junit`, `flyway-spring-test` flyway test integration via spring configuration, `flyway-dbunit-spring4-test` db unit support with spring and flyway and `db unit` itself.

# Logging
Flyway logging logic is described in the `org.flywaydb.core.internal.util.logging.LogFactory` class. 
The order of finding appropriate logger is the following:
- Android log
- Slf4j
- Apache-commons
- java.util.log

In the examples `Slf4j` with `log4j` configuration is used.

# Flyway default configuration
Default Flyway configuration can be found in the class `org.flywaydb.core.Flyway` is the following:
- Java and sql migrations should be in the classpath under `db/migrations`
- Flyway schema table name is `schema_version`
- Target version is the latest one
- Placeholders in sql - `${placeholderName}` and in the config create key-value pair with key `flyway.placeholders.<placeholderName>`
- Versioned migrations file names start with `V`
- Repeatable migrations file names start with `R`
- Separator between versions is `__`
