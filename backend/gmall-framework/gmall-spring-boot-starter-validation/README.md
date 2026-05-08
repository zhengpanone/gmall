# gmall-spring-boot-starter-validation

`gmall` 的统一参数校验 Starter，提供：

- 自定义校验注解：`@Mobile`、`@Url`、`@FileType`、`@InEnum`
- 统一分组：`CreateGroup`、`UpdateGroup`、`DeleteGroup`、`QueryGroup`
- 全局校验异常处理：`GlobalValidationExceptionHandler`
- 自动配置：`ValidationAutoConfiguration`

## 1. 引入依赖

```xml
<dependency>
    <groupId>com.zp.gmall</groupId>
    <artifactId>gmall-spring-boot-starter-validation</artifactId>
    <version>${revision}</version>
</dependency>
```

如果项目已经引入 `gmall-spring-boot-starter-web`，会自动传递依赖本 starter。

## 2. 使用方式

### 2.1 分组校验

```java
import com.zp.gmall.framework.validation.group.CreateGroup;
import com.zp.gmall.framework.validation.group.UpdateGroup;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

public class UserDTO {

    @NotNull(message = "ID不能为空", groups = UpdateGroup.class)
    private String id;
}

@PostMapping("/create")
public Result<?> create(@RequestBody @Validated(CreateGroup.class) UserDTO dto) {
    return Result.ok();
}
```

### 2.2 自定义注解

```java
import com.zp.gmall.framework.validation.annotation.file.FileType;
import com.zp.gmall.framework.validation.annotation.format.Mobile;
import com.zp.gmall.framework.validation.annotation.network.Url;

public class ProfileDTO {

    @Mobile
    private String mobile;

    @Url(required = false)
    private String homepage;

    @FileType(value = {"jpg", "png"})
    private String avatarFileName;
}
```

### 2.3 枚举值校验

```java
import com.zp.gmall.framework.common.core.Valuable;
import com.zp.gmall.framework.validation.annotation.enumvalidation.InEnum;

public enum StatusEnum implements Valuable<Integer> {
    ENABLED(1), DISABLED(2);
    private final Integer value;
    StatusEnum(Integer value) { this.value = value; }
    public Integer getValue() { return value; }
}

public class RequestDTO {
    @InEnum(enumClass = StatusEnum.class)
    private Integer status;
}
```

## 3. 配置项

前缀：`gmall.validation`

```yaml
gmall:
  validation:
    xss-enabled: true
    sql-inject-enabled: true
```

> 说明：当前两个配置项为预留开关，后续可用于扩展 XSS/SQL 注入校验策略。

## 4. 异常返回

`GlobalValidationExceptionHandler` 会统一处理常见参数校验异常（如 `BindException`、`ConstraintViolationException`），并返回：

- code: `400`
- msg: `请求参数不正确:具体错误信息`

## 5. 迁移说明

旧分组 `com.zp.gmall.framework.common.validation.ValidateGroup` 建议迁移到：

- `com.zp.gmall.framework.validation.group.CreateGroup`
- `com.zp.gmall.framework.validation.group.UpdateGroup`
- `com.zp.gmall.framework.validation.group.DeleteGroup`
- `com.zp.gmall.framework.validation.group.QueryGroup`
