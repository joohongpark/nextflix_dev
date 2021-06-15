package com.movie.common.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.movie.attach.vo.AttachVO;

@Component
public class MovieAttachUtils {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("#{app['file.upload.path']}")
	private String uploadPath;

	/** MultipartFile에서 VO 설정 및 업로드 파일 처리 후 리턴, 없는 경우 null */
	public AttachVO getAttachByMultipart(MultipartFile multipart, String category, String path) throws IOException {
		if (!multipart.isEmpty()) {
			// fileName : 기본 원본이름에서, 날짜형식 + 확장자, 랜덤하게 
			String fileName = UUID.randomUUID().toString();
			AttachVO vo = new AttachVO();
			vo.setAtchOriginalName(multipart.getOriginalFilename());
			vo.setAtchFileSize(multipart.getSize());
			vo.setAtchContentType(multipart.getContentType());
			vo.setAtchFileName(fileName);
			vo.setAtchCategory(category);
			vo.setAtchPath(path);
			vo.setAtchFancySize(MovieFileUtils.getFancySize(multipart.getSize()));
			String filePath = uploadPath + File.separatorChar + vo.getAtchPath();
			logger.debug("filePath = {}, fileName = {}", filePath, fileName);
			// multipart.transferTo(new File(filePath, fileName));
			FileUtils.copyInputStreamToFile(multipart.getInputStream(), new File(filePath, fileName));
			return vo;
		} else {
			return null;
		}
	}

	/** 다중 MultipartFile에서 VO 설정 및 업로드 파일 처리 후 List 리턴 */
	public List<AttachVO> getAttachListByMultiparts(MultipartFile[] multipartFiles, String category, String path)
			throws IOException {
		List<AttachVO> atchList = new ArrayList<AttachVO>();
		for (int i = 0; i < multipartFiles.length; i++) {
			MultipartFile multipart = multipartFiles[i];
			AttachVO vo = this.getAttachByMultipart(multipart, category, path);
			if (vo != null) {
				atchList.add(vo);
			}
		}
		return atchList;
	}
}
