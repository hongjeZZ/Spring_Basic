package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {

    /**
     * member 가 아닌 grade 를 파라미터로 사용해도 된다. 이후의 확장성까지 고려하여 member 변수를 사용한다.
     * @param member 회원
     * @param price 상품 가격
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);
}
