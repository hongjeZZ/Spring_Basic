package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
// @AllArgsConstructor 모든 변수가 매개변수로 필요한 생성자 생성.
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("name");
        System.out.println(helloLombok.getName());

        helloLombok.setAge(19);
        System.out.println("helloLombok = " + helloLombok);
    }
}
