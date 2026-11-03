# 来源与修改记录

本版本基于 [qq549631030/AndroidJunkCode](https://github.com/qq549631030/AndroidJunkCode)，
基线提交为 [`cfcd9eed0b8d5a938033a9268a20e58e059b3039`](https://github.com/qq549631030/AndroidJunkCode/commit/cfcd9eed0b8d5a938033a9268a20e58e059b3039)。
上游发布配置标注开发者为 **huangx（qq549631030）**，许可为 **Apache-2.0**。

许可依据是该提交中的 [library/gradle.properties](https://github.com/qq549631030/AndroidJunkCode/blob/cfcd9eed0b8d5a938033a9268a20e58e059b3039/library/gradle.properties)；
2026-11-02 已核对远程文件与本地基线一致。该基线没有独立的 LICENSE 或 NOTICE。
本版本的 LICENSE 补自 [Apache 官方全文](https://www.apache.org/licenses/LICENSE-2.0.txt)，
NOTICE 是下游新增的来源说明，不是原作者提供的文件，也不代表原作者认可下游改动。

## 2026-10-30：方法模板扩展

- `library/src/main/kotlin/cn/hx/plugin/junkcode/ext/JunkCodeConfig.kt`：新增枚举模板列表配置；空列表随机选择全部模板。
- `library/src/main/kotlin/cn/hx/plugin/junkcode/utils/JunkUtil.kt`：普通类和 Activity 的额外方法接入模板选择，保留自定义回调优先级。
- `library/src/main/kotlin/cn/hx/plugin/junkcode/task/GenerateJunkCodeTask.kt`：递归清理生成目录，避免切换模板后残留旧文件。
- `library/src/main/kotlin/cn/hx/plugin/junkcode/template/MethodTemplate.kt`、`MethodTemplateGenerator.kt` 及 `template/method/`：新增 200 项枚举及实现，包含原有 5 项和新增 195 项。
- `library/src/test/`、`library/build.gradle.kts`：新增 JUnit 依赖及模板编译、执行、选择与清理回归测试。
- `README.md`、`AGENTS.md`：补充模板用法和仓库协作说明。

## 2026-11-02：来源与许可保留

- 新增 `LICENSE`、`NOTICE` 和本修改记录，保留上游的作者、仓库和许可元数据。
- 为已修改的上游文件添加下游修改声明；为新增模板和测试源码添加来源说明。
- `library/build.gradle.kts`：将这三份文件打包至 JAR 的 `META-INF/`，包括插件实现包和源码包。
- `README.md`：增加来源、许可及修改记录入口。

## 后续维护

继续保留上游已有声明；修改上游文件时在文件内标明修改，新增变更同步记录在此。
同步上游版本时检查是否新增 LICENSE、NOTICE 或其他归属声明，并保留适用内容。
团队仓库为 `kane313/AndroidJunkCode`；本记录不虚构团队名称或版权归属。

## 2026-11-02：Maven Central 发布配置

- `library/build.gradle.kts`、`library/gradle.properties`：使用下游坐标 `io.github.kane313:android-junk-code:2.0.0-team.1`，插件 ID 为 `io.github.kane313.android-junk-code`；同时发布插件 marker。
- POM 指向下游仓库，列出下游维护者与上游作者；保留 Apache-2.0 信息，启用签名。
- 发布凭据及私钥仅存放于用户目录或环境变量，不纳入仓库。
- `settings.gradle.kts`、`build.gradle.kts`：新增 `skipDemo` 开关并延迟 demo 插件解析，支持首次发布前构建。
- `gradle/libs.versions.toml`：demo 引用下游插件；`docs/PUBLISHING.md`：记录安全凭据配置、发布与接入步骤。

## 2026-11-03：已发布版本接入说明

- `README.md`：提供 Maven Central 接入、Kotlin/Groovy 配置、枚举选择、变体名称与旧插件迁移说明。
- `docs/PUBLISHING.md`：记录远程发布验证结果，区分团队使用步骤与维护者发布流程。

## 2026-11-03：旧版 Gradle 兼容修复（2.0.0-team.2）

- 插件改用隔离的 Kotlin 1.6.21 编译器，目标 Java 11，编译 API 基线为 Gradle 7.4 / AGP 7.3.0；发布工具仍使用仓库现有环境。
- 移除插件实现对 Gradle Kotlin DSL 扩展的依赖，使用 Java 任务注册 API。
- 为生成 Java 和资源提供稳定的任务输出属性，并对 AGP 7.3 使用旧资源注册 API，修复其资源合并漏掉生成布局的问题；保留原有生成目录。
- 增加独立的 Gradle 7.4 / AGP 7.3.0 验证工程，覆盖模板配置、生成及 release 混淆打包；更新版本及接入文档。
