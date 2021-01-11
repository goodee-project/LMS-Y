package gd.fintech.lms.manager.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gd.fintech.lms.manager.service.TextbookService;

// 교재 정보를 입력 시에 ISBN을 검색하는 비동기 Controller

@RestController
public class TestbookRestController {
	// 교재 정보 관련 Service
	@Autowired TextbookService textbookService;
	
	// 교재 ISBN의 중복 여부를 확인하는 메소드
	// 매개변수: textbookISBN(교재 ISBN)
	// 리턴값: 중복(noPass) 또는 중복되지 않음(pass)
	@PostMapping("/manager/textbookISBNCheck")
	public String textbookISBNCheck(@RequestParam(value = "textbookISBN", required = true) String textbookISBN) {
		String ISBNCk = textbookService.getTextbookISBN(textbookISBN);
		// 만약 조회된 ISBN이 없다면 pass를 return
		if(ISBNCk == null) {
			return "Pass";
		}
		// 조회된 ISBN이 있다면 noPass를 return
		return "noPass";
	}
}
