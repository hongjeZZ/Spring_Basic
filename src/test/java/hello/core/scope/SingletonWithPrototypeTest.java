package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest {

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean {

        private final PrototypeBean prototypeBean; // 생성 시점에 주입

        public int logic() {
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Getter
    @Scope("prototype")
    @Component
    static class PrototypeBean {

        private int count = 0;

        public void addCount() {
            count++;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(2);
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean2 {

        private final ObjectProvider<PrototypeBean> prototypeBeanProvider;
//        private final ObjectFactory<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Test
        //ObjectProvider or ObjectFactory 사용 -> but. 코드가 스프링에 의존적이다.
    void singletonClientUsePrototype2() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean2.class, PrototypeBean.class);

        ClientBean2 clientBean1 = ac.getBean(ClientBean2.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean2 clientBean2 = ac.getBean(ClientBean2.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean3 {

        private final Provider<PrototypeBean> prototypeBeanProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Test
        // Provider<> 사용      but.별도의 라이브러리가 필요함. 기능이 단순
    void singletonClientUsePrototype3() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean3.class, PrototypeBean.class);

        ClientBean3 clientBean1 = ac.getBean(ClientBean3.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean3 clientBean2 = ac.getBean(ClientBean3.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }
}
