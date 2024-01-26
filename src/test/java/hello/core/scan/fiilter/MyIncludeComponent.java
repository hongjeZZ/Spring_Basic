package hello.core.scan.fiilter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
// TYPE -> 클래스에 적용하는 애노테이션
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {
    // 이게 붙는 클래스는 컴포넌트 클래스에 추가한다.
}
