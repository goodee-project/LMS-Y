package gd.fintech.lms.account.vo;

import lombok.Data;

@Data
public class Account {
	private String accountId;
	private String accountPw;
	private String accountState;
	private int accountLevel;
	private String accountCreatedate;
	private String accountUpdatedate;
}
