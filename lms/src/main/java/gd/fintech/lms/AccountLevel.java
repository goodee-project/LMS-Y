package gd.fintech.lms;

/*
 * DB의 account 테이블에 존재하는 ENUM 타입의 account_level과 매칭하기 위한 자바 enum 타입
 * AccountLevel.ADMIN는 1, AccountLevel.MANAGER는 2, AccountLevel.TEACHER은 3, AccountLevel.STUDENT는 4의 값을 가짐
 * 
 * ex)
 * if (session.getAttribute("accountLevel") == AccountLevel.ADMIN) { ... }
 */

public enum AccountLevel {
	// DB내의 ENUM 순서가 변경되었을 경우 변경바람
	ADMIN(1),
	MANAGER(2),
	TEACHER(3),
	STUDENT(4);
	
	// 아래 코드는 ENUM의 순서 외에도 int값을 저장하기 위해 만든 코드
	private final int value;
	
	// 위의 ADMIN(), MANAGER(), ... 등에 집어넣은 괄호 안의 숫자가 아래 메서드에 들어감
	private AccountLevel(int value) {
		this.value = value;
	}
	
	// DB에 저장된 AccountLevel의 숫자값을 보고 싶을 때 호출 가능
	public int getValue() {
		return value;
	}
}