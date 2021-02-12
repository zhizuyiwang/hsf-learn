package com.hsf.learn.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;


public class FileUtil {
	private static Logger _logger = LoggerFactory.getLogger(FileUtil.class);

	public static final String FILE_TYPE_IMG = "img";

	public static final String FILE_TYPE_VIDEO = "video";

	public static final String FILE_TYPE_DOC = "doc";
	
	public static final String FILE_TYPE_AUDIO = "audio";
	
	public static final String[] imgtypes = { ".JPG", ".JPEG", ".PNG", ".GIF" };

	public static final String[] videotypes = { ".MPEG", ".MP4", ".AVI", ".FLV", ".RMVB" };
	
	public static final String[] audiotypes = { ".AVI", ".MP3",".MOV",".WMA"};

	public static final String[] doctypes = { ".DOC", ".DOCX", ".XLS", ".XLSX", ".PDF", ".PPT", ".PPTX", ".TXT", ".ZIP", ".RAR" ,".APK"};
	
	public static final String RETURN_WRONGTYPE = "wrongType";

	public static final String RETURN_CUTERROR = "cutError";

	public static final String RETURN_OVERSIZE = "overSize";

	public static final Integer FILE_MAX_SIZE = 1024 * 3;// 单个文件最大为3M
	


	/**
	 * 将文件按照指定尺寸缩放上传并生成新的文件名.
	 * 
	 * @param session
	 * @param file
	 *            上传的文件
	 * @param module
	 *            文件对应的模块
	 * @param needType
	 *            文件可以的类型
	 * @param width
	 *            缩放的宽度
	 * @param height
	 *            缩放的高度
	 * @return 新的文件名
	 */
	public String upload(HttpSession session, MultipartFile file, String module, String needType, Integer width, Integer height) {
		String extName = getFileExtName(file.getOriginalFilename());
		boolean isRightType = isRightType(extName, needType);
		if (!isRightType) {
			return RETURN_WRONGTYPE;
		}
		String imageFileName = getNewFileName() + getFileExtName(file.getOriginalFilename());
		String path = getUploadPath(module);
		String realPath = session.getServletContext().getRealPath("/");

		_logger.info(String.format("realPath is: %s", realPath));

		try {
			File p = new File(realPath + path);
			if (!p.exists()) {
				p.mkdirs();
			}
			File f = new File(realPath + path + imageFileName);
			FileCopyUtils.copy(file.getBytes(), f);
//			file.transferTo(f);
			if (needType.equals(FILE_TYPE_IMG)) {
				BufferedImage sourceImg = null;

				try {
					sourceImg = ImageIO.read(file.getInputStream());
				} catch (Exception e) {
					e.printStackTrace();
					return RETURN_WRONGTYPE;
				}
				if (null != width && null ==height) {
					deal(f.getPath(), sourceImg.getWidth(), width);
				} else if (null == width && null ==height) {
					deal(f.getPath());
				} else {
					System.out.println(f.getPath());
					deal(f.getPath(), sourceImg.getWidth(), sourceImg.getHeight(), width, height);
				}
			}

			_logger.info(String.format("upload ok: %s", path + imageFileName));

			return path + imageFileName;
		} catch (IOException e) {
			e.printStackTrace();
			_logger.error(e.getMessage());
		}
		return null;
	}

	public String upload(HttpSession session, MultipartFile file, String module, String needType) {
		return upload(session, file, module, needType, null, null);
	}

	/**
	 * 上传Base64格式的图片按照指定尺寸缩放并生成新的文件名.
	 * 
	 * @param session
	 * @param bs64
	 *            base64格式的图片
	 * @param module
	 *            文件对应的模块
	 * @param needType
	 *            文件可以的类型
	 * @param width
	 *            缩放的宽度
	 * @param height
	 *            缩放的高度
	 * @return 新的文件名
	 */
	public String upload(HttpSession session, String bs64, String module, String needType, Integer width, Integer height) {
		if (bs64 == null) {
			return null;
		}
		Integer index = bs64.indexOf("base64,");
		byte[] b = Base64.decodeBase64(bs64.substring(index + 7));
		for (Integer i = 0; i < b.length; ++i) {
			if (b[i] < 0) {
				b[i] += 256;
			}
		}
		System.out.println(bs64);
		System.out.println(bs64.length());
		String extName = ".".concat(bs64.substring(bs64.indexOf("/") + 1, index - 1));
		boolean isRightType = isRightType(extName, needType);
		if (!isRightType) {
			return RETURN_WRONGTYPE;
		}
		String imageFileName = getNewFileName() + extName;
		String path = getUploadPath(module);
		String realPath = session.getServletContext().getRealPath("/");
		try {
			File p = new File(realPath + path);
			if (!p.exists()) {
				p.mkdirs();
			}
			File f = new File(realPath + path + imageFileName);
			FileCopyUtils.copy(b, f);
			if (needType.equals(FILE_TYPE_IMG)) {
				BufferedImage sourceImg = ImageIO.read(f);
				if(width!=null&&height!=null){
					deal(f.getPath(), sourceImg.getWidth(), sourceImg.getHeight(), width, height);
				}
			}
			return path + imageFileName;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将文件缩放到指定宽度和高度.
	 * 
	 * @param fpath
	 *            文件路径
	 * @param sw
	 *            文件原始宽度
	 * @param sh
	 *            文件原始高度
	 * @param w
	 *            需缩放到的宽度
	 * @param h
	 *            需缩放到的高度
	 */
	public void deal(String fpath, Integer sw, Integer sh, Integer w, Integer h) {
		IMOperation op = new IMOperation();
		op.addImage(fpath);
		if (1.0 * sw / sh >= 1.0 * w / h) {
			op.resize(null, h).gravity("center").extent(w, h);
		} else if (1.0 * sw / sh < 1.0 * w / h) {
			op.resize(w, null).gravity("center").extent(w, h);
		}
		op.addImage(fpath);
		// 水印
		// op.font("微软雅黑");
		// op.gravity("southeast");
		// op.pointsize(18).fill("#BCBFC8").draw("text 0,0 "+"DIANZAN.IT");
		ConvertCmd convert = new ConvertCmd(true);
		try {
			convert.run(op);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IM4JavaException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 将文件缩放到指定宽度.
	 * 
	 * @param fpath
	 *            文件路径
	 * @param sw
	 *            文件原始宽度
	 * @param w
	 *            需缩放到的宽度
	 */
	public void deal(String fpath, Integer sw, Integer w) {
		IMOperation op = new IMOperation();
		if (null != sw && null != w) {
			if (sw != w) {
				op.resize(w, null);
			}
		}
		op.addImage(fpath);
		ConvertCmd convert = new ConvertCmd(true);
		try {
			convert.run(op);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
		} catch (IM4JavaException e) {
		}
	}

	public void deal(String fpath) {
		deal(fpath, null, null);
	}

	/**
	 * 计算上传相对路径 (不含文件名).
	 * 
	 * @param module
	 *            模块名
	 * @return
	 */
	private String getUploadPath(String module) {
		StringBuffer name = new StringBuffer().append("/upload/");
		name.append(module).append("/");
		name.append(DateUtil.getStringByDate(Calendar.getInstance().getTime(), "yyyy/MM/dd"));
		name.append("/");
		return name.toString();
	}

	/**
	 * 生成随机文件名.
	 * 
	 * @return
	 */
	private String getNewFileName() {
		return System.currentTimeMillis() + "_" + new Random().nextInt(100);
	}

	/**
	 * 获得文件扩展名.
	 * 
	 * @param userFileName
	 *            文件名
	 * @return
	 */
	private String getFileExtName(String userFileName) {
		Integer pos = userFileName.lastIndexOf('.');
		if (pos == -1) {
			return "";
		}
		return userFileName.substring(pos);
	}

	/**
	 * 判断扩展名格式是否正确.
	 * 
	 * @param extName
	 *            文件扩展名
	 * @param type
	 *            文件类型
	 * @return
	 */
	private boolean isRightType(String extName, String type) {
		if (type.equals(FILE_TYPE_IMG)) {
			for (String s : imgtypes) {
				if (extName.toUpperCase().equals(s)) {
					return true;
				}
			}
		} else if (type.equals(FILE_TYPE_VIDEO)) {
			for (String s : videotypes) {
				if (extName.toUpperCase().equals(s)) {
					return true;
				}
			}
		} else if (type.equals(FILE_TYPE_DOC)) {
			for (String s : doctypes) {
				if (extName.toUpperCase().equals(s)) {
					return true;
				}
			}
		}else if(type.equals(FILE_TYPE_AUDIO)){
			for (String s : audiotypes) {
				if (extName.toUpperCase().equals(s)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 裁剪图片.
	 * 
	 * @param imagePath
	 *            源图片路径
	 * @param newPath
	 *            处理后图片路径
	 * @param dataX
	 *            起始X坐标
	 * @param dataY
	 *            起始Y坐标
	 * @param width
	 *            裁剪宽度
	 * @param height
	 *            裁剪高度
	 * @return 返回true说明裁剪成功,否则失败
	 */
	public boolean cutImage(String imagePath, String newPath, Integer dataX, Integer dataY, Integer width, Integer height) {
		boolean flag = false;
		try {

			IMOperation op = new IMOperation();
			op.addImage(imagePath);
			/** width：裁剪的宽度 * height：裁剪的高度 * x：裁剪的横坐标 * y：裁剪纵坐标 */
			op.crop(width, height, dataX, dataY);
			op.addImage(newPath);
			ConvertCmd convert = new ConvertCmd(true);
			convert.run(op);
			flag = true;
		} catch (IOException e) {
			System.out.println("文件读取错误!");
			flag = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			flag = false;
		} catch (IM4JavaException e) {
			e.printStackTrace();
			flag = false;
		} finally {

		}
		return flag;
	}

	/**
	 * 根据尺寸缩放图片[等比例缩放:参数height为null,按宽度缩放比例缩放;参数width为null,按高度缩放比例缩放].
	 * 
	 * @param imagePath
	 *            源图片路径
	 * @param newPath
	 *            处理后图片路径
	 * @param width
	 *            缩放后的图片宽度
	 * @param height
	 *            缩放后的图片高度
	 * @return 返回true说明缩放成功,否则失败
	 */
	public boolean zoomImage(String imagePath, String newPath, Integer width, Integer height) {
		boolean flag = false;
		try {
			IMOperation op = new IMOperation();
			op.addImage(imagePath);
			if (width == null) {// 根据高度缩放图片
				op.resize(null, height);
			} else if (height == null) {// 根据宽度缩放图片
				op.resize(width);
			} else {
				op.resize(width, height);
			}
			op.addImage(newPath);
			ConvertCmd convert = new ConvertCmd(true);
			convert.run(op);
			flag = true;
		} catch (IOException e) {
			System.out.println("文件读取错误!");
			flag = false;
		} catch (InterruptedException e) {
			flag = false;
		} catch (IM4JavaException e) {
			flag = false;
		} finally {

		}
		return flag;
	}
	
	/**
	 * 文件下载通用方法
	 * @param fileName 文件名
	 * @param file 文件
	 * @return
	 * @throws IOException
	 */
	public static ResponseEntity<byte[]> download(String fileName, File file) throws IOException {
		String dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", dfileName);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
	}
	
}
