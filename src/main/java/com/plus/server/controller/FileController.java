package com.plus.server.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.plus.server.common.util.QRCodeUtil;
import com.plus.server.common.vo.resp.BaseResp;
import com.plus.server.service.FileService;
import com.wordnik.swagger.annotations.Api;

@Controller
@Api("示例")
@RequestMapping(value = "/gtb/file")
public class FileController extends BaseController {
	private static final Logger log = LoggerFactory.getLogger(FileController.class);
	@Autowired
	private FileService fileService;

	/**
	 * 上传文件
	 * 
	 * @param fileName
	 *            文件参数名称
	 * @return
	 */
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public BaseResp uploadFile(@RequestParam(value = "fileName", required = false) String fileName,
			@RequestParam MultipartFile[] btnFile,
			HttpServletRequest httpServletRequest) {
		System.out.println("上传文件");
		BaseResp ret = new BaseResp();
		if (httpServletRequest instanceof MultipartRequest) {

			MultipartRequest multipartRequest = (MultipartRequest) httpServletRequest;
			MultipartFile multipartFile = multipartRequest.getFile(fileName);
			log.info("上传文件,上传的文件内容为:【" + fileName + "】");
			try {
				String filePath=fileService.uploadFileStream(multipartFile.getOriginalFilename(), multipartFile.getBytes());
				ret.setMsg(filePath);
			} catch (Exception e) {
				log.error("上传文件出异常",e);
				ret.setMsg("上传失败，" + e.getMessage());
				return ret;
			}

		}
		ret.setSuccess(true);
		return ret;
	}
	
	/**
	 * 下载文件
	 * 
	 * @param fileName
	 *            文件参数名称
	 * @return
	 */
	@RequestMapping(value = "/downloadFile")
	public void downloadFile(@RequestParam(value = "fileName") String fileName) {
		if(fileName == null || "".equals(fileName)){
			return ;
		}
		try {
			byte[] b = fileService.downloadFileStream(fileName);
			httpResponse.getOutputStream().write(b);
			httpResponse.setContentType("image/*");
		} catch (Exception e) {
			log.error("", e);
		}
	}
	
	/**
	 * 二维码打印页面
	 * @param model
	 * @param qrCode
	 * @return
	 */
	@RequestMapping(value = "/toQRCode")
	public ModelAndView toQRCode(Model model, String qrCode) {
		model.addAttribute("qrCode", qrCode);
		ModelAndView mv = new ModelAndView("printQRCode.ftl");
		return mv;
	}
	
	/**
	 * 二维码图片
	 * @param qrCode
	 * @return
	 */
	@RequestMapping(value = "/createQRCodeImg")
	public void createQRCodeImg(String qrCode,Integer height) {
		System.out.println("生成二维码图片，qrCode="+qrCode);
		ModelAndView mv = new ModelAndView("printQRCode.ftl");
		if(qrCode == null || "".equals(qrCode)){
			qrCode = "编码为空";
		}
		// 禁止图像缓存。
		httpResponse.setHeader("Pragma", "no-cache");
		httpResponse.setHeader("Cache-Control", "no-cache");
		httpResponse.setDateHeader("Expires", 0);
		httpResponse.setContentType("image/jpeg");
	    // 将图像输出到Servlet输出流中。
		ServletOutputStream sos = null;
	    try {
			sos = httpResponse.getOutputStream();
			BufferedImage buffImg = QRCodeUtil.getRQ(qrCode, height==null?200:height);
			ImageIO.write(buffImg, "jpeg", sos);
		} catch (IOException e) {
			log.error("", e);
		} finally {
			if(sos != null)
				try {
					sos.close();
				} catch (Exception e) {
					log.info("输出流关闭失败");
				}
		}
	}

}
