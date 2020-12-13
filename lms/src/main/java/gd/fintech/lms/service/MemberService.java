package gd.fintech.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.mapper.MemberMapper;

@Service
@Transactional
public class MemberService {
	@Autowired private MemberMapper memberMapper;

}
