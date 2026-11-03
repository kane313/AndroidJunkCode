<!-- Downstream modifications: template documentation added 2026-10-30; attribution added 2026-11-02; Central integration updated 2026-11-03. See MODIFICATIONS.md. -->

# Android垃圾代码生成插件

## 来源与许可

本版本基于 [qq549631030/AndroidJunkCode](https://github.com/qq549631030/AndroidJunkCode)，原发布配置中的开发者为 **huangx（qq549631030）**。
基线提交的发布配置声明 **Apache-2.0**；本版本补充了官方 [LICENSE](LICENSE) 全文，并保留上游作者及许可元数据。
来源核验依据及本地修改详见 [NOTICE](NOTICE) 和 [MODIFICATIONS.md](MODIFICATIONS.md)。这两份说明由下游新增，不代表上游作者认可本版本。
插件实现包和源码包会在 `META-INF/` 中携带许可、来源及修改说明。

## 接入已发布版本

本插件在 Android 构建时生成 Java 类、Activity 和资源，支持 **200 个可选方法模板**。
`2.0.0-team.2` 已发布到 **Maven Central**，修复旧版 Gradle/Kotlin 和 Java 11 兼容问题。
团队使用者无需 Central 账号、Token、签名密钥或本地发布。

- 插件 ID：`io.github.kane313.android-junk-code`
- 版本：`2.0.0-team.2`
- 实现坐标：`io.github.kane313:android-junk-code:2.0.0-team.2`（由插件自动解析，不要添加为 App 的 `implementation` 依赖）
- [Central 产物目录](https://repo.maven.apache.org/maven2/io/github/kane313/android-junk-code/2.0.0-team.2/)

### 1. 配置插件仓库

在项目根目录 `settings.gradle.kts` 的现有 `pluginManagement.repositories` 中加入 `mavenCentral()`：

```kotlin
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
```

仅在普通依赖的 `repositories` 或 `dependencyResolutionManagement` 中添加 Central 不够。
保留项目原有的其他仓库和配置，无需添加 `mavenLocal()`、JitPack 或 GitHub Packages。

### 2. 应用插件并配置生成范围

以下示例放到 Android 应用模块的 `app/build.gradle.kts`。保留项目现有的 Android 插件版本配置及 `android { ... }`；若已应用 `com.android.application`，无需重复添加。

```kotlin
import cn.hx.plugin.junkcode.template.MethodTemplate

plugins {
    id("com.android.application")
    id("io.github.kane313.android-junk-code") version "2.0.0-team.2"
}

androidJunkCode {
    variantConfig {
        create("release") {
            packageBase = "com.example.generated"
            packageCount = 2
            activityCountPerPackage = 2
            otherCountPerPackage = 10
            methodCountPerClass = 20
            resPrefix = "junk_"
            drawableCount = 10
            stringCount = 10

            // 不设置 methodTemplates：每个方法从全部 200 个模板随机选择。
        }
    }
}
```

`release` 是构建变体的完整名称。只配置 `release` 时，debug 构建不会生成；使用 flavor 时应写实际变体名，例如 `prodRelease`。
生成目录为 `app/build/generated/source/junk/release/`，源码、资源、Manifest 和保留规则会自动接入构建。

### 3. 选择模板（可选）

在上例 `create("release") { ... }` 内按需添加以下配置，三种写法选一种：

```kotlin
// 固定使用一种模板
methodTemplates = listOf(MethodTemplate.STRING_REVERSE)

// 或：每个方法从指定集合中随机选择
methodTemplates = listOf(
    MethodTemplate.STRING_REVERSE,
    MethodTemplate.ARRAY_SUM,
    MethodTemplate.MAP_WORD_FREQUENCY
)

// 或：恢复从全部 200 个模板随机选择，与不设置相同
methodTemplates = emptyList()
```

### 4. 构建验证

```bash
bash gradlew :app:assembleRelease
```

检查生成目录和构建结果。业务项目不需要设置 `PLUGIN_ENABLE`，它只是本仓库 demo 自己定义的开关。
仓库 demo 可运行 `bash gradlew :app:assembleRelease -PPLUGIN_ENABLE=true`。

### 兼容性与旧项目升级

`2.0.0-team.2` 使用 Kotlin 1.6.21 编译、Java 11 字节码，并针对 Gradle 7.4 API 构建。插件使用方不需要把自己的 Kotlin 插件改成 1.6.21；项目自身的 Gradle、AGP 与 JDK 仍需相互匹配。

| Gradle | AGP | 运行 JDK | 验证范围 |
| --- | --- | --- | --- |
| 7.4 | 7.3.0 | 11、17 | Kotlin DSL、模板枚举、生成源码/资源/Manifest、release 混淆打包 |
| 9.3.1 | 9.0.0 | 17 | 仓库 demo 的 release 生成与打包 |

旧版本 `2.0.0-team.1` 使用 Kotlin 2.3.10 和 Java 17。在 Gradle 7.4 中，即使切换到 JDK 17，也可能出现 `Protocol message contained an invalid tag (zero).`。请将插件版本升级到 `2.0.0-team.2`；`resolutionStrategy` 映射和清理缓存无法改变旧产物的字节码或 Kotlin 元数据。

AGP 7.3.x 与 Gradle 7.4 是本次验证的旧版组合；[AGP 7.4.x 本身要求 Gradle 7.5](https://developer.android.com/build/releases/agp-7-4-0-release-notes)。其他组合仍需在目标项目验证。维护者可运行 [兼容性验证工程](compatibility/gradle74/README.md)。

### Groovy DSL

使用 `settings.gradle` 时，`pluginManagement { repositories { google(); mavenCentral(); gradlePluginPortal() } }` 同样适用。
应用模块 `app/build.gradle` 中的对应配置如下，Android 插件版本仍沿用现有项目配置：

```groovy
import cn.hx.plugin.junkcode.template.MethodTemplate

plugins {
    id 'com.android.application'
    id 'io.github.kane313.android-junk-code' version '2.0.0-team.2'
}

androidJunkCode {
    variantConfig {
        release {
            packageBase = 'com.example.generated'
            packageCount = 2
            otherCountPerPackage = 10
            methodCountPerClass = 20
            // 删除此行即可从全部模板随机选择。
            methodTemplates = [MethodTemplate.STRING_REVERSE, MethodTemplate.ARRAY_SUM]
        }
    }
}
```

### 从原作者版本迁移

将插件 ID `io.github.qq549631030.android-junk-code` 替换为 `io.github.kane313.android-junk-code`，版本改为 `2.0.0-team.2`；不要同时应用两个插件。
如果使用 `libs.versions.toml`，修改对应插件条目即可；若根项目使用 `apply false` 声明版本，同步更新根项目声明，模块沿用不带版本的插件引用。
扩展名称仍是 `androidJunkCode`，Kotlin 包名仍是 `cn.hx.plugin.junkcode`，已有配置和自定义回调可以继续使用。
从 `buildscript classpath` 方式迁移时，也要移除旧插件依赖，避免新旧类同时出现在构建脚本 classpath 中。

## 模板行为说明

- 普通类和 Activity 的额外方法都使用此配置；Activity 的 `onCreate()` 不受影响。
- `typeGenerator` 优先于 `methodGenerator`，两者提供实现时跳过默认模板选择。
- 多选采用有放回随机抽取，不保证每个模板都出现；列表顺序不是执行顺序。使用不同枚举项避免重复项改变抽取概率。
- 方法数仍由 `methodCountPerClass` 控制。模板产生不同返回类型，方法名仍由原有命名规则控制。
- 模板选择属于 Gradle 任务输入，修改列表会使生成任务失效；未修改配置时，Gradle 可以跳过已是最新的任务，不保证每次构建都重新随机。
- 保留原来的 5 个 `LEGACY_*` 模板。`LEGACY_THROW_EXCEPTION` 被调用时会抛异常；这些方法不会自动接入业务调用链。默认随机池包含它，若需要执行生成的方法，请显式排除：
  `methodTemplates = MethodTemplate.values().filter { it != MethodTemplate.LEGACY_THROW_EXCEPTION }`。

完整枚举见 [MethodTemplate.kt](library/src/main/kotlin/cn/hx/plugin/junkcode/template/MethodTemplate.kt)。模板分布：原有 5、数值 25、位运算 15、字符串 30、数组 30、List 20、Map 20、Set 15、控制流程 20、矩阵 20。

运行 `bash gradlew :library:test` 可验证全部模板的 Java 8 编译、运行及选择规则。

其他原有配置可参考[上游 wiki](https://github.com/qq549631030/AndroidJunkCode/wiki)；下游插件坐标和模板参数以本文为准。
发布维护步骤见 [Maven Central 发布指南](docs/PUBLISHING.md)。

使用插件[methodCount](https://github.com/KeepSafe/dexcount-gradle-plugin)对比

|                               | 方法总数                             | 项目方法数                               |
| ----------------------------- |:--------------------------------:|:-----------------------------------:|
| 未加垃圾代码<br/><br/>项目代码占比 0.13%  | ![方法总数](images/before_total.jpg) | ![项目方法数](images/before_project.jpg) |
| 加了垃圾代码<br/><br/>项目代码占比 52.93% | ![方法总数](images/after_total.jpg)  | ![项目方法数](images/after_project.jpg)  |

原作者的相关项目：

[PriorityDialog](https://github.com/qq549631030/PriorityDialog)（带优先级对话框实现）  
[ActivityResultApi](https://github.com/qq549631030/ActivityResultApi)（Activity Result Api封装，支持免注册调用）
