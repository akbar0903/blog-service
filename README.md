# 个人博客项目后端

## 定义复合校验注解
在管理员接口中，有几个地方需要校验密码，比如管理员登录接口：

```java
package com.akbar.pojo.dto.admin;

import com.akbar.constant.MessageConstant;
import com.akbar.constant.RegexConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AdminLoginDto {

    @NotBlank(message = MessageConstant.USERNAME_CANT_BE_EMPTY)
    private String username;

    @NotBlank(message = MessageConstant.PASSWORD_CANT_BE_EMPTY)
    @Pattern(regexp = "^.{6,}$")
    private String password;
}
```
还有修改密码接口：
```java
package com.akbar.pojo.dto.admin;

import com.akbar.constant.MessageConstant;
import com.akbar.constant.RegexConstant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PasswordEditDto {
    private Integer id;

    @NotBlank(message = MessageConstant.PASSWORD_CANT_BE_EMPTY)
    @Pattern(regexp = "^.{6,}$")
    private String oldPassword;

    @NotBlank(message = MessageConstant.PASSWORD_CANT_BE_EMPTY)
    @Pattern(regexp = "^.{6,}$")
    private String newPassword;

    @NotBlank(message = MessageConstant.PASSWORD_CANT_BE_EMPTY)
    @Pattern(regexp = "^.{6,}$")
    private String confirmPassword;
}
```
很显然，下面这两行注解出现了多次：
```java
@NotBlank(message = MessageConstant.PASSWORD_CANT_BE_EMPTY)
@Pattern(regexp = "^.{6,}$")
```
**为了提高代码的复用性，我定义了自定义校验注解注解：**
```java
package com.akbar.annotation;

import com.akbar.constant.MessageConstant;
import com.akbar.constant.RegexConstant;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 密码校验注解
 */
@Target(ElementType.FIELD)  // 使用与类中的成员变量
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@NotBlank(message = MessageConstant.PASSWORD_CANT_BE_EMPTY)
@Pattern(regexp = RegexConstant.PASSWORD_PATTERN, message = MessageConstant.PASSWORD_TOO_SHORT)
public @interface ValidPassword {
    String message() default "Invalid password"; // 默认消息，可被覆盖
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
```
**注意点：**
- `@Target(ElementType.FIELD)`：表示该注解使用与类中的成员变量
- `@Constraint(validatedBy = {})`：是 Bean Validation 规范中的一个元注解，用于标记一个注解是自定义校验注解。
> 添加 @Constraint 后，明确告诉校验框架：“这是一个校验注解，请解析它的规则。”即使 validatedBy = {} 是空的，框架会自动处理 @NotBlank 和 @Pattern 的逻辑。
- `String message() default "Invalid password";`：
> message() 是校验注解的必备属性，用于定义校验失败时返回的错误消息。<br/>
default "Invalid password" 提供了一个默认值。如果你在使用注解时不指定 message，就会使用这个默认值。
可被覆盖：在使用注解时，可以通过 message 属性覆盖默认值。<br/><br/>
> Bean Validation 要求所有校验注解都定义 message()，因为它是校验失败时返回给用户或开发者的提示信息。<br/>
在上面的 @ValidPassword 中，message() 是形式上的要求，但实际错误消息已经被 @NotBlank 和 @Pattern 的 message 属性覆盖。
- `Class<?>[] groups() default {};`：
> groups() 允许你为校验规则指定分组。<br/>
> 通过 groups，你可以控制哪些校验在哪些场景下生效。<br/>
> Class<?>[] 表示一个类数组，通常是标记接口（marker interface），用来标识校验组。<br/>
> default {} 表示默认不指定任何分组，适用于所有校验场景。
- `Class<? extends Payload>[] payload() default {};`：
> payload() 用于为校验注解附加元数据（metadata），这些元数据可以被校验框架或应用程序读取。<br/>
Class<? extends Payload>[] 表示一个实现了 Payload 接口的类数组。<br/>
default {} 表示默认不携带任何元数据。<br/>
**然后使用这个注解：**
```java
package com.akbar.pojo.dto.admin;

import com.akbar.annotation.ValidPassword;
import com.akbar.constant.MessageConstant;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdminLoginDto {

    @NotBlank(message = MessageConstant.USERNAME_CANT_BE_EMPTY)
    private String username;

    @ValidPassword
    private String password;
}
```