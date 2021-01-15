package gd.fintech.lms.teacher.service;

import java.io.BufferedInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import gd.fintech.lms.FilePath;
import gd.fintech.lms.dto.LectureArchiveForm;
import gd.fintech.lms.manager.mapper.LectureManagerMapper;
import gd.fintech.lms.manager.vo.Lecture;
import gd.fintech.lms.teacher.mapper.LectureArchiveFileMapper;
import gd.fintech.lms.teacher.mapper.LectureArchiveMapper;
import gd.fintech.lms.teacher.mapper.TeacherMapper;
import gd.fintech.lms.teacher.vo.LectureArchive;
import gd.fintech.lms.teacher.vo.LectureArchiveFile;

//강좌별 자료실 서비스

@Service
@Transactional
public class LectureArchiveService {
	// Looger
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// LectureArchiveMapper, FileMapper 객체 주입
	@Autowired private LectureArchiveMapper lectureArchiveMapper;
	@Autowired private LectureArchiveFileMapper lectureArchiveFileMapper;
	
	//검증 및 변수를 가져오는데 사용하는 메퍼 객제 주입
	@Autowired private TeacherMapper teacherMapper;
	@Autowired private LectureManagerMapper lectureManagerMapper;

	// 강좌 자료실 목록 및 페이징 메서드
	// 매개변수:강좌 고유번호
	// 리턴값:강좌 고유번호를 조회하여 자료실 목록을 반환
	public Map<String,Object> getLectureArchiveListByPage(int lectureNo, int currentPage,String lectureArchiveSearch) {
		// 현재 페이지 표시할 데이터 수
		int rowPerPage = 10;
		// 시작 페이지
		int beginRow = (currentPage - 1) * rowPerPage;
		// 전체 페이지 개수
		int lectureAchiveCount = lectureArchiveMapper.selectLectureArchiveCount(lectureNo,lectureArchiveSearch);
		// 마지막 페이지
		int lastPage = lectureAchiveCount / rowPerPage;
		// 10 미만의 개수의 데이터가 있는 페이지 표시
		if (lectureAchiveCount % rowPerPage != 0) {
			lastPage += 1;
		}
		// 전체 페이지가 0개이면 현재 페이지도 0으로 표시
		if (lastPage == 0) {
			currentPage = 0;
		}
		//페이지 네비바에 표시할 페이지 수
		int navPerPage = 10;
		//네비바 첫번째 페이지
		int navBeginPage = (currentPage-1)/navPerPage*navPerPage + 1;
		// 네비바 마지막 페이지
		int navLastPage = (navBeginPage + navPerPage) - 1;
		// 네비바의 마지막 페이지와 라스트페이지가 달라질 경우 같게 설정
		if (navLastPage > lastPage) {
			navLastPage = lastPage;
		}
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		//현재 페이지 표시할 데이터 수
		paramMap.put("rowPerPage", rowPerPage);
		//시작페이지
		paramMap.put("beginRow", beginRow);
		//강좌고유번호
		paramMap.put("lectureNo", lectureNo);
		//강좌자료실검색
		paramMap.put("lectureArchiveSearch", lectureArchiveSearch);
		
		List<LectureArchive> lectureArchiveList = lectureArchiveMapper.selectLectureArchiveListByPage(paramMap);
		//Logger 디버깅
		logger.trace(lectureArchiveList+"<---- lectureArchiveList");
		
		Map<String,Object>map = new HashMap<String,Object>();
		//마지막 페이지
		map.put("lastPage", lastPage);
		//강좌 자료실 목록
		map.put("lectureArchiveList", lectureArchiveList);
		//현재 네비바 표시할 데이터 수
		map.put("navPerPage", navPerPage);
		//베비바 시작페이지
		map.put("navBeginPage", navBeginPage);
		//네비바 마지막페이지
		map.put("navLastPage", navLastPage);
		
		return map;
	}
	
	//강좌별 자료실 상세보기 메서드
	//매개변수:강좌 자료실 고유번호
	//리턴값:목록출력
	public LectureArchive getLectureArchiveOne(int lectureArchiveNo) {
		LectureArchive lectureArchive = lectureArchiveMapper.selectLectureArchiveOne(lectureArchiveNo);
		return lectureArchive;
	}
	
	//강좌별 자료실 조회수 증가 메서드
	//강좌별 자료실 고유번호
	//리턴값:조회수 증가
	public int increaseLectureArchiveCount(int lectureArchiveNo){
		return lectureArchiveMapper.updateLectureArchiveCount(lectureArchiveNo);
	}
	
	//DTO를 받아와 해당 강좌별 자료실의 입력
	//매개변수:
	//리턴값:
	public boolean createLectureArchive(LectureArchiveForm lectureArchiveForm,HttpSession session) {
		
		// lectureArchiveForm의 강좌 고유번호, 아이디, 작성자, 제목, 내용을 lectureArchive 객체에 넣어둠
		LectureArchive lectureArchive = new LectureArchive();
		lectureArchive.setLectureNo(lectureArchiveForm.getLectureNo());
		lectureArchive.setAccountId(lectureArchiveForm.getAccountId());
		lectureArchive.setLectureArchiveWriter(lectureArchiveForm.getLectureArchiveWriter());
		lectureArchive.setLectureArchiveTitle(lectureArchiveForm.getLectureArchiveTitle());
		lectureArchive.setLectureArchiveContent(lectureArchiveForm.getLectureArchiveContent());
		// Mapper를 통해 lectureArchive의 내용을 입력
		lectureArchiveMapper.insertLectureArchive(lectureArchive);
		
		
		//List<LectureArchiveFile> lectureArchiveFileList = null;
		
		//파일이 있을 경우 for문을 돌면서 MultipartFile을 VO로 변환 후 첨부파일 추가
		if(lectureArchiveForm.getLectureArchiveFileList() !=null) {
			for(MultipartFile mf: lectureArchiveForm.getLectureArchiveFileList()) {
				String fileNameUUID = UUID.randomUUID().toString().replaceAll("-", "");
				
				try {
					//물리적 파일을 생성(하드디스크)
					String fileName = FilePath.getFilePath()+fileNameUUID;
					mf.transferTo(new File(fileName));
					
					logger.debug("파일 생성됨:"+fileName);
				}catch (Exception e) {//해당 파일 생성 실패 시
					//원래 예외 메세지를 출력함
					e.printStackTrace();
					
					//Transactional 기능을 수행하는 Service 컴포넌트에게 예외 발생을 알려 작업 내역을 롤백하도록 유도함
					throw new RuntimeException(e);
				}
				LectureArchiveFile lectureArchiveFile = new LectureArchiveFile();
				lectureArchiveFile.setLectureArchiveFileUUID(fileNameUUID);
				lectureArchiveFile.setLectureArchiveFileOriginal(mf.getOriginalFilename());
				lectureArchiveFile.setLectureArchiveNo(lectureArchive.getLectureArchiveNo()); // selectKey 이용, 위에 추가한 댓글의 고유번호를 가져와서 등록
				lectureArchiveFile.setLectureArchiveFileSize(mf.getSize());
				lectureArchiveFile.setLectureArchiveFileType(mf.getContentType());
				lectureArchiveFileMapper.insertLectureArchiveFile(lectureArchiveFile);
				
			}
		}
		
		// lectureArchiveNo를 반환하여 페이지를 이동할 수 있도록 한다
		return true;
	}
	
	//파일 다운로드
	//매개변수:
	//#1:다운로드받을 파일 UUID
	//#2, #3: 다운로드를 진행하기 위한 서블릿 리퀘스트 및 리스폰스 객체
	//리턴값:다운로드 성공 여부
	public boolean downloadLectureArchiveFile(String lectureArchiveFileUUID,HttpServletRequest request,HttpServletResponse response) {
		//서버에 업로드된 파일을 가져옴
		String fileName = FilePath.getFilePath()+lectureArchiveFileUUID;
		File file = new File(fileName);
		
		//파일 컨텐츠를 읽어오기 위한 스트림 객체
		FileInputStream fis =null;
		BufferedInputStream bis = null;
		
		//다운로드할 파일의 컨텐츠를 채워주기 위한 스트림 객체
		ServletOutputStream sos = null;
		
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			
			//파일타입 및 원본 파일명을 받아오고, 브라우저별로 파일 이름이 제대로 인식되도록 처리함
			LectureArchiveFile lectureArchiveFile = lectureArchiveFileMapper.selectLectureArchiveFileList(lectureArchiveFileUUID);
			String fileContentType = lectureArchiveFile.getLectureArchiveFileType();
			String originalFileName = lectureArchiveFile.getLectureArchiveFileOriginal();
			if(request.getHeader("user-agent").indexOf("MSIE")!=-1 || request.getHeader("user-agent").indexOf("Trident")!=-1) {
				originalFileName = URLEncoder.encode(originalFileName,"UTF-8").replaceAll("\\+","%20");
			}else {
				originalFileName = new String(originalFileName.getBytes("UTF-8"), "ISO-8859-1");
			}
			
			//MIME 타입을 설정하고 첨부파일 형태로, 파일명은 originalFileName으로 설정함
			response.setContentType(fileContentType);
			response.setContentLength((int)file.length());
			response.setHeader("Content-Disposition", "attachment;filename=\""+originalFileName+"\";");
			response.setHeader("Content-Transfer-Encoding", "binary");
			
			//파일 컨텐츠를 작성하기 위해 스트림을 불러옴
			sos = response.getOutputStream();
			
			//서버에 업로드된 파일의 내용을 읽어, 다운로드할 파일의 컨텐츠를 채워넣음
			int read = 0;
			while ((read = bis.read()) != -1) {
				sos.write(read);
			}
			
			//파일 다운로드 횟수를 1 증가시킴
			lectureArchiveFileMapper.updateLectureArchiveFileCountIncrease(lectureArchiveFileUUID);
		}catch(Exception e) { //파일 다운로드 실패시
			//원래 예외 메세지를 출력
			e.printStackTrace();
			
			// Transactional 기능을 수행하는 Service 컴포넌트에게 예외 발생을 알려 작업 내역을 롤백하도록 유도함
			throw new RuntimeException(e);
		} finally {
			// 작업이 끝나는대로 스트림 객체의 리소스를 반환함
			try {
				sos.close();
				bis.close();
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	//DTO를 받아와 해당  해당 자료실의 게시글을 수정
	//매개변수:자료실 커맨드 객체(MultipartFile 포함 기능)
	public boolean modifyLectureArchive(LectureArchiveForm lectureArchiveForm,HttpSession session) {
		// lectureArchiveForm의 강좌 고유번호, 아이디, 작성자, 제목, 내용을 lectureArchive 객체에 넣어둠
		LectureArchive lectureArchive = new LectureArchive();
		lectureArchive.setLectureArchiveNo(lectureArchiveForm.getLectureArchiveNo());
		lectureArchive.setLectureNo(lectureArchiveForm.getLectureNo());
		lectureArchive.setAccountId(lectureArchiveForm.getAccountId());
		lectureArchive.setLectureArchiveWriter(lectureArchiveForm.getLectureArchiveWriter());
		lectureArchive.setLectureArchiveTitle(lectureArchiveForm.getLectureArchiveTitle());
		lectureArchive.setLectureArchiveContent(lectureArchiveForm.getLectureArchiveContent());
		// Mapper를 통해 lectureArchive의 내용을 수정
		lectureArchiveMapper.updateLectureArchive(lectureArchive);
		
		//파일이 있을 경우 for문을 돌면서 MultipartFile을 VO로 변환 후 첨부파일 추가
		if(lectureArchiveForm.getLectureArchiveFileList() !=null){
			for(MultipartFile mf : lectureArchiveForm.getLectureArchiveFileList()) {
				String fileNameUUID = UUID.randomUUID().toString().replaceAll("-", "");
				
				try {
					//물리적 파일을 생성(하드디스크)
					String fileName = FilePath.getFilePath()+fileNameUUID;
					mf.transferTo(new File(fileName));
					
					logger.debug("파일 생성됨:"+fileName);
				}catch(Exception e) {//해당 파일 생성 실패시
					//원래 예외 메세지를 출력함.
					e.printStackTrace();
					
					//Transactional 기능을 수행하는 Service 컴포넌트에게 예외 발생을 알려 작업 내역을 롤백하도록 유도함
					throw new RuntimeException(e);
				}
				
				LectureArchiveFile lectureArchiveFile = new LectureArchiveFile();
				lectureArchiveFile.setLectureArchiveFileUUID(fileNameUUID);
				lectureArchiveFile.setLectureArchiveFileOriginal(mf.getOriginalFilename());
				lectureArchiveFile.setLectureArchiveNo(lectureArchive.getLectureArchiveNo()); // selectKey 이용, 위에 추가한 댓글의 고유번호를 가져와서 등록
				lectureArchiveFile.setLectureArchiveFileSize(mf.getSize());
				lectureArchiveFile.setLectureArchiveFileType(mf.getContentType());
				lectureArchiveFileMapper.insertLectureArchiveFile(lectureArchiveFile);
			}
		}
		return true;
	}
	
	//UUID에 해당되는 첨부파일을 삭제
	//매개변수:삭제할 첨부파일의 UUID
	public void removeFile(String lectureArchiveFileUUID) {
		//물리적 파일(하드디스크에 존재하는 파일)제거
		String fileName = FilePath.getFilePath()+lectureArchiveFileUUID;
		
		//파일 경로, 이름지정
		File file = new File(fileName);
		//파일이 있는경우
		if(file.exists()) {
			file.delete();
		}
		lectureArchiveFileMapper.deleteLectureArchiveFile(lectureArchiveFileUUID);
	}
	
	//파일 목록
	public LectureArchiveFile getLectureArchiveFile(String lectureArchiveFileUUID){
		
		return lectureArchiveFileMapper.selectLectureArchiveFileList(lectureArchiveFileUUID);
	}
}