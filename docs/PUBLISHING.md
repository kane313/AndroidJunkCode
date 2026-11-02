# Maven Central 发布与接入

维护仓库：https://github.com/kane313/AndroidJunkCode

- 实现坐标：`io.github.kane313:android-junk-code:2.0.0-team.1`
- 插件 ID：`io.github.kane313.android-junk-code`
- 版本由 `library/gradle.properties` 中的 `VERSION_NAME` 管理。
- 同时发布实现包和插件 marker；POM 包含维护者、上游作者、许可证和 SCM 信息。
- 本说明是发布流程，不表示版本已经在 Central 上线。以 Portal 的 PUBLISHED 状态及远程解析验证为准。

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

首次发布前 demo 无法从 Central 获取此版本，可用 `skipDemo` 只构建插件：

```bash
bash gradlew :library:build :library:publishToMavenLocal -PskipDemo=true
bash gradlew :app:assembleRelease -PPLUGIN_ENABLE=true
```

第一条命令会使用用户目录中的签名配置。仅本地验证且没有密钥时，可额外传入 `-PsignAllPublications=false`，不要用于正式发布。

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

在 `settings.gradle.kts` 的插件仓库中添加 Central：

```kotlin
pluginManagement {
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
}
```

在应用模块使用插件，Android application 插件应先应用：

```kotlin
plugins {
    id("com.android.application")
    id("io.github.kane313.android-junk-code") version "2.0.0-team.1"
}
```

模板 API 的 Kotlin 包名仍为 `cn.hx.plugin.junkcode.template.MethodTemplate`。
本仓库 demo 的版本目录已经指向下游插件，发布前可由 `mavenLocal()` 解析本地验证产物。
最终上线验证应在不启用 `mavenLocal()` 的环境解析，避免把本地成功误认为远程发布成功。
