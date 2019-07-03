package com.fhh.utils;

import com.fhh.bo.BookBO;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

/**
 * 生成pdf工具类,传入书对象
 * @author 小浩
 * @Date 2019/7/3 15:56
 */
public class PdfUtils {
	public static void generatePDF(BookBO bookBO) throws Exception {
		// 创建文件
		Document document = new Document(PageSize.A4, 20, 20, 20, 20);
		// 建立一个书写器
		PdfWriter writer = PdfWriter
				.getInstance(document, new FileOutputStream(bookBO.getBookPath() + bookBO.getBookName()));

		// 打开文件
		document.open();
		// 添加内容
		for (String pagePath : bookBO.getPagePathList()) {
			// 图片1
			Image image = Image.getInstance(pagePath);
			//统一按照宽度压缩
			int percent = getPercent2(image.getWidth());
			// 图片居中
			image.setAlignment(Image.MIDDLE);
			image.scalePercent(percent);

			Chapter chapter = new Chapter(1);
			chapter.add(image);
			// 将图片1添加到pdf文件中
			document.add(chapter);
		}

		// 关闭文档
		document.close();
		// 关闭书写器
		writer.close();
	}

	/**
	 * 统一按照宽度压缩
	 * @author 小浩
	 * @Date 2019/7/3 16:37
	 * @param width 宽度
	 * @return int
	 */
	private static int getPercent2(float width) {
		int p;
		float p2;
		p2 = 530 / width * 100;
		System.out.println("--" + p2);
		p = Math.round(p2);
		return p;
	}

}
