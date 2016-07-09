package com.plus.server.common.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class QRCodeUtil {
	private static final String CODE = "utf-8";
	private static final int BLACK = 0xff000000;
	private static final int WHITE = 0xFFFFFFFF;

	/**
	 * 生成RQ二维码
	 * 
	 * @author wuhongbo
	 * @param str
	 *            内容
	 * @param height
	 *            高度（px）
	 * 
	 */
	public static BufferedImage getRQ(String str, Integer height) {
		try {
			// 文字编码
			Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, CODE);
			BitMatrix bitMatrix = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, height, height, hints);
			return toBufferedImage(bitMatrix);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成一维码（128）
	 * 
	 * @author wuhongbo
	 * @param str
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage getBarcode(String str, Integer width, Integer height) {
//		if (width == null || width < 200) {
//			width = 200;
//		}
//		if (height == null || height < 50) {
//			height = 50;
//		}
		try {
			// 文字编码
			Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, CODE);
			BitMatrix bitMatrix = new MultiFormatWriter().encode(str, BarcodeFormat.CODE_128, width, height, hints);
			return toBufferedImage(bitMatrix);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 生成二维码，写到文件中
	 * 
	 * @author wuhongbo
	 * @param str
	 * @param height
	 * @param file
	 * @throws IOException
	 */
	public static void getRQWriteFile(String str, Integer height, File file) throws IOException {
		BufferedImage image = getRQ(str, height);
		ImageIO.write(image, "png", file);
	}

	/**
	 * 生成一维码，写到文件中
	 * 
	 * @author wuhongbo
	 * @param str
	 * @param height
	 * @param file
	 * @throws IOException
	 */
	public static void getBarcodeWriteFile(String str, Integer width, Integer height, File file) throws IOException {
		BufferedImage image = getBarcode(str, width, height);
		ImageIO.write(image, "png", file);
	}

	/**
	 * 转换成图片
	 * 
	 * @author wuhongbo
	 * @param matrix
	 * @return
	 */
	private static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	// /**
	// * 解码(二维、一维均可)
	// */
	// public static String read(File file)
	// {
	//
	// BufferedImage image;
	// try
	// {
	// if (file == null || file.exists() == false)
	// {
	// throw new Exception(" File not found:" + file.getPath());
	// }
	//
	// image = ImageIO.read(file);
	//
	// LuminanceSource source = new BufferedImageLuminanceSource(image);
	// BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
	//
	// Result result;
	//
	// // 解码设置编码方式为：utf-8，
	// Hashtable hints = new Hashtable();
	// hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
	//
	// result = new MultiFormatReader().decode(bitmap, hints);
	//
	// return result.getText();
	//
	// }
	// catch (Exception e)
	// {
	// e.printStackTrace();
	// }
	//
	// return null;
	// }

//	public static void main(String[] args) throws Exception {
//		File file = new File("c://1.png");
//		// RQUtil.getRQwriteFile("吴宏波中华人民共和国", 200, file);
//
//		// code39 大写字母、数字、-、
//		// code128
//		QRCodeUtil.getBarcodeWriteFile("12345678900-J_j", null, null, file);
//
//		System.out.println("-----成生成功----");
//		System.out.println();
//	}
}
