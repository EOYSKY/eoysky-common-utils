# eoysky-common-utils


### `mvn` 发布：
- 在发布过程中，可能会提示签名 sign 之类的错误，这是由于 GPG 安装可能出现了问题，需要确保通过命令行的方式可以执行：`gpg –version` 。

- 一般打包方式，将密码存储在 `setting.xml` 中，注意`id`的值

```shell
mvn clean deploy -P release -Dmaven.test.skip=true
```

- 手动增加密码打包

```shell
mvn clean deploy -P release -Darguments="gpg.passphrase=YourPassword" -Dmaven.test.skip=true
```


