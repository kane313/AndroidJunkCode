<!-- Downstream modifications: template documentation added 2026-10-30; attribution added 2026-11-02. See MODIFICATIONS.md. -->

# Android垃圾代码生成插件

## 来源与许可

本版本基于 [qq549631030/AndroidJunkCode](https://github.com/qq549631030/AndroidJunkCode)，原发布配置中的开发者为 **huangx（qq549631030）**。
基线提交的发布配置声明 **Apache-2.0**；本版本补充了官方 [LICENSE](LICENSE) 全文，并保留上游作者及许可元数据。
来源核验依据及本地修改详见 [NOTICE](NOTICE) 和 [MODIFICATIONS.md](MODIFICATIONS.md)。这两份说明由下游新增，不代表上游作者认可本版本。
插件实现包和源码包会在 `META-INF/` 中携带许可、来源及修改说明。

此插件用于做马甲包时，减小马甲包与主包的代码相似度，避免被某些应用市场识别为马甲包。

使用方法见[wiki](https://github.com/qq549631030/AndroidJunkCode/wiki)


## 方法模板选择（源码版本）

内置 **200 个方法模板**，通过 `MethodTemplate` 枚举选择。此配置需要包含该功能的插件版本；仓库 demo 默认引用已发布的版本，测试本地修改时需使用本地插件产物。

```kotlin
import cn.hx.plugin.junkcode.template.MethodTemplate

androidJunkCode {
    variantConfig {
        create("release") {
            packageBase = "com.example.generated"
            packageCount = 2
            otherCountPerPackage = 10
            methodCountPerClass = 20

            // 单个枚举：所有默认生成的方法均使用此模板。
            methodTemplates = listOf(MethodTemplate.STRING_REVERSE)

            // 或指定多个模板：每个方法从这些模板中随机选一个。
            // methodTemplates = listOf(
            //     MethodTemplate.STRING_REVERSE,
            //     MethodTemplate.ARRAY_SUM,
            //     MethodTemplate.MAP_WORD_FREQUENCY
            // )

            // 不设置或设置 emptyList()：每个方法从全部 200 个模板中随机选择。
        }
    }
}
```

Groovy DSL 可写为 `methodTemplates = [MethodTemplate.STRING_REVERSE, MethodTemplate.ARRAY_SUM]`，并添加同样的 import。

- 普通类和 Activity 的额外方法都使用此配置；Activity 的 `onCreate()` 不受影响。
- `typeGenerator` 优先于 `methodGenerator`，两者提供实现时跳过默认模板选择。
- 多选采用有放回随机抽取，不保证每个模板都出现；列表顺序不是执行顺序。使用不同枚举项避免重复项改变抽取概率。
- 方法数仍由 `methodCountPerClass` 控制。模板产生不同返回类型，方法名仍由原有命名规则控制。
- 模板选择属于 Gradle 任务输入，修改列表会使生成任务失效；未修改配置时，Gradle 可以跳过已是最新的任务，不保证每次构建都重新随机。
- 保留原来的 5 个 `LEGACY_*` 模板。`LEGACY_THROW_EXCEPTION` 被调用时会抛异常；这些方法不会自动接入业务调用链。默认随机池包含它，若需要执行生成的方法，请显式排除：
  `methodTemplates = MethodTemplate.values().filter { it != MethodTemplate.LEGACY_THROW_EXCEPTION }`。

完整枚举见 [MethodTemplate.kt](library/src/main/kotlin/cn/hx/plugin/junkcode/template/MethodTemplate.kt)。模板分布：原有 5、数值 25、位运算 15、字符串 30、数组 30、List 20、Map 20、Set 15、控制流程 20、矩阵 20。

运行 `bash gradlew :library:test` 可验证全部模板的 Java 8 编译、运行及选择规则。

使用插件[methodCount](https://github.com/KeepSafe/dexcount-gradle-plugin)对比

|                               | 方法总数                             | 项目方法数                               |
| ----------------------------- |:--------------------------------:|:-----------------------------------:|
| 未加垃圾代码<br/><br/>项目代码占比 0.13%  | ![方法总数](images/before_total.jpg) | ![项目方法数](images/before_project.jpg) |
| 加了垃圾代码<br/><br/>项目代码占比 52.93% | ![方法总数](images/after_total.jpg)  | ![项目方法数](images/after_project.jpg)  |

安利我的两个新库：  
[PriorityDialog](https://github.com/qq549631030/PriorityDialog)（带优先级对话框实现）  
[ActivityResultApi](https://github.com/qq549631030/ActivityResultApi)（Activity Result Api封装，支持免注册调用）
