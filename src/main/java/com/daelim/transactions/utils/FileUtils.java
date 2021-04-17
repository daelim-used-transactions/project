package com.daelim.transactions.utils;


import com.daelim.transactions.dto.AttachDTO;
import com.daelim.transactions.exception.AttachFileException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtils {

    private final String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));

    /** 업로드 경로 */
    //private final String uploadPath = Paths.get("src", "main", "resources","static","images","profile", today).toString();
    private final String uploadPath = Paths.get("c:", "fileUpload", "images","profile", today).toString();
    private final String uploadBoardPath = Paths.get("c:", "fileUpload", "images","board", today).toString();
    /**
     * 서버에 생성할 파일명을 처리할 랜덤 문자열 반환
     * @return 랜덤 문자열
     */
    private final String getRandomString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public String uploadFiles(MultipartFile file) {

        /* uploadPath에 해당하는 디렉터리가 존재하지 않으면, 부모 디렉터리를 포함한 모든 디렉터리를 생성 */
        File dir = new File(uploadPath);
        if (dir.exists() == false) {
            dir.mkdirs();
        }

        String saveName = "";
        /* 파일 개수만큼 forEach 실행 */
//        for (MultipartFile file : files) {
            try {
                /* 파일 확장자 */
                final String extension = FilenameUtils.getExtension(file.getOriginalFilename());

                /* 서버에 저장할 파일명 (랜덤 문자열 + 확장자) */
                saveName = getRandomString() + "." + extension;

                /* 업로드 경로에 saveName과 동일한 이름을 가진 파일 생성 */
                File target = new File(uploadPath, saveName);
                file.transferTo(target);


            } catch (IOException e) {
                throw new AttachFileException("[" + file.getOriginalFilename() + "] failed to save file...");

            } catch (Exception e) {
                throw new AttachFileException("[" + file.getOriginalFilename() + "] failed to save file...");
            }
//        } // end of for

        return "/images/profile/"+today+"/"+saveName;
    }

    /**
     * 게시글 파일 저장
     * @param files    - 파일 Array
     * @param boardIdx - 게시글 번호
     * @return 업로드 파일 목록
     */
    public List<AttachDTO> uploadBoardFiles(MultipartFile[] files, int boardIdx) {

        /* 파일이 비어있으면 비어있는 리스트 반환 */
        if (files[0].getSize() < 1) {
            return Collections.emptyList();
        }

        /* 업로드 파일 정보를 담을 비어있는 리스트 */
        List<AttachDTO> attachList = new ArrayList<>();

        /* uploadPath에 해당하는 디렉터리가 존재하지 않으면, 부모 디렉터리를 포함한 모든 디렉터리를 생성 */
        File dir = new File(uploadBoardPath);
        if (dir.exists() == false) {
            dir.mkdirs();
        }

        /* 파일 개수만큼 forEach 실행 */
        for (MultipartFile file : files) {
            try {
                /* 파일 확장자 */
                final String extension = FilenameUtils.getExtension(file.getOriginalFilename());
                /* 서버에 저장할 파일명 (랜덤 문자열 + 확장자) */
                final String saveName = getRandomString() + "." + extension;

                /* 업로드 경로에 saveName과 동일한 이름을 가진 파일 생성 */
                File target = new File(uploadBoardPath, saveName);
                file.transferTo(target);

                /* 파일 정보 저장 */
                AttachDTO attach = new AttachDTO();
                attach.setBoardIdx(boardIdx);
                //attach.setOriginalName(file.getOriginalFilename());
                attach.setSaveName(saveName);
                attach.setSize(file.getSize());
                if(attach.getSize() == 0){ //빈 파일을 보내왔을 경우
                    continue;
                }

                /* 파일 정보 추가 */
                attachList.add(attach);

            } catch (IOException e) {
                throw new AttachFileException("[" + file.getOriginalFilename() + "] failed to save file...");

            } catch (Exception e) {
                throw new AttachFileException("[" + file.getOriginalFilename() + "] failed to save file...");
            }
        } // end of for

        return attachList;
    }
}
