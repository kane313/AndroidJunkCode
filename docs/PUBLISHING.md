# Maven Central 发布与接入

维护仓库：https://github.com/kane313/AndroidJunkCode

- 实现坐标：`io.github.kane313:android-junk-code:2.0.0-team.2`
- 插件 ID：`io.github.kane313.android-junk-code`
- 版本由 `library/gradle.properties` 中的 `VERSION_NAME` 管理。
- 同时发布实现包和插件 marker；POM 包含维护者、上游作者、许可证和 SCM 信息。
- `2.0.0-team.1` 已发布：2026-11-03 确认 Portal 状态为 `PUBLISHED`，实现包和 marker POM 返回 HTTP 200，独立工程仅通过 Maven Central 解析并验证 200 个模板。
- `2.0.0-team.2` 已发布（2026-11-03）：部署 `10cffbbe-ee13-429c-a9f1-fe79034e3b99`，修复 Gradle 7.4 / Java 11 兼容性；实现包与 marker 均可从 Central 下载；禁用 `mavenLocal()` 后，Gradle 7.4 / AGP 7.3.0 在 JDK 11、17 下均通过 release 混淆打包。
- 团队使用者请直接阅读 [README 接入说明](../README.md#接入已发布版本)，不需要配置下文的发布凭据。

## 发布凭据

Central Portal 的账号菜单 → View Account → Generate User Token 返回的是独立的发布 username/password，不能用登录邮箱或登录密码替代。
将它们写入用户目录的 `~/.gradle/gradle.properties`，不要写入本仓库：

```properties
mavenCentralUsername=<Token 返回的 username>
mavenCentralPassword=<Token 返回的 password>
signing.keyId=<签名密钥 ID>
signing.password=<私钥口令>
signing.secretKeyRingFile=<加密的私钥 keyring 绝对路径>
```

私钥和凭据文件应仅当前用户可读；妥善备份，勿提交到 Git。公钥需上传到 Central 支持的公钥服务器。
签名文件、源码包、文档包以及 LICENSE、NOTICE、MODIFICATIONS.md 都随产物发布。

## 验证与发布

开发尚未发布的新版本时，先递增 `VERSION_NAME` 并同步版本目录中的 `junkCode`，再用 `skipDemo` 构建插件并发布本地测试产物：

```bash
bash gradlew :library:build :library:publishToMavenLocal -PskipDemo=true
bash gradlew :app:assembleRelease -PPLUGIN_ENABLE=true
```

第一条命令会使用用户目录中的签名配置。仅本地验证且没有密钥时，可额外传入 `-PsignAllPublications=false`，不要用于正式发布。

发布构建仍使用仓库的 Gradle 9.3.1 和 JDK 17，但 `library` 用隔离的 Kotlin 1.6.21 编译器、Gradle 7.4 API 和 Java 11 目标构建。不要直接改回使用发布环境内嵌的 Kotlin/Gradle API，否则旧项目会再次出现元数据解析错误。

发布前按 [兼容性验证工程](../compatibility/gradle74/README.md) 分别在 JDK 11、17 下运行 Gradle 7.4 的 release 构建，再运行当前 demo。检查产物的 JVM 版本为 11，运行时依赖没有重新引入 Kotlin 2.x。

上传、验证并自动发布：

```bash
bash gradlew :library:publishAndReleaseToMavenCentral -PskipDemo=true
```

也可仅上传待发布部署：

```bash
bash gradlew :library:publishToMavenCentral -PskipDemo=true
```

然后在 Portal 查看部署验证结果并发布。Central 版本发布后不可覆盖；更新时递增版本，并同步版本目录中的 `junkCode`。

## 团队项目接入

完整的 Kotlin DSL、Groovy DSL、模板选择、构建命令和旧版迁移步骤统一维护在 [README 接入说明](../README.md#接入已发布版本)。
已发布版本直接从 Maven Central 获取，无需 `mavenLocal()` 或下载仓库源码。
发布后仍需在不启用 `mavenLocal()` 的环境验证，避免把本地成功误认为远程发布成功。
