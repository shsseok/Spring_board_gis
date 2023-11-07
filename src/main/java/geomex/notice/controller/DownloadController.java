package geomex.notice.controller;




import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.rte.fdl.property.EgovPropertyService;


@Controller
public class DownloadController {
    @Autowired
    private ServletContext servletContext;
    @Resource(name = "propertiesService")
    private EgovPropertyService propertiesService;
    @GetMapping("/download.do")
    public void fileDownload(@RequestParam(name="fileName") String fileName, HttpServletResponse response, Model model) throws IOException  {	 	
    	if(!fileName.equals("")) {
        	File f = new File(propertiesService.getString("filePath"), fileName);

            String mimeType = servletContext.getMimeType(f.getAbsolutePath());
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            response.setContentLength((int) f.length());
            response.setHeader("Content-disposition", "attachment;filename=\"" + fileName + "\"");          
            OutputStream os = null;
            FileInputStream fis = null;
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(f);
                FileCopyUtils.copy(fis, os);
            } catch (IOException e) {         
                throw new IOException("파일 다운로드 중 문제가 생겼다", e);
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        } 
    }


