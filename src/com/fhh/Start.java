package com.fhh;

import com.fhh.bo.BookBO;
import com.fhh.bo.FolderBO;
import com.fhh.constant.Constants;
import com.fhh.utils.FileUtils;
import com.fhh.utils.PdfUtils;

import java.util.List;

/**
 * 主文件
 * @author 小浩
 * @Date 2019/7/3 15:55
 */
public class Start {

	public static void main(String[] args) {
		// 获取子文件夹或文件
		FolderBO folderBO = new FolderBO();
		folderBO.setFolderPath(Constants.inputFolderPath);
		FileUtils.getPackageFolder(folderBO);
		// 将子文件夹的文件生成pdf
		multiFolder(folderBO);
		// 将本文件夹文件生成 pdf
		oneFolder(folderBO);
		System.out.println("运行结束");
	}

	/**
	 * 取输入文件夹中图片生成pdf
	 * @author 小浩
	 * @Date 2019/7/3 15:55
	 * @param folderBO 文件夹
	 */
	private static void oneFolder(FolderBO folderBO) {
		List<String> filePathList = folderBO.getFilePathList();

		// 封装书
		BookBO bookBO = new BookBO();
		bookBO.setBookPath(Constants.outputFolderPath);

		// 封装书
		for (int i = 0; i < filePathList.size(); i++) {
			bookBO.setBookName(Constants.bookName + i + ".pdf");
			bookBO.getPagePathList().add(filePathList.get(i));
			try {
				// 生成书
				PdfUtils.generatePDF(bookBO);
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}
		}


	}

	/**
	 * 取输入文件夹的 子文件夹生成book,每 多少个子文件夹的文件合并一起
	 * @author 小浩
	 * @Date 2019/7/3 15:57
	 * @param folderBO 文件夹
	 */
	private static void multiFolder(FolderBO folderBO) {

		List<String> folderPathList = folderBO.getFolderPathList();
		int size = folderPathList.size();
		int n = size / Constants.folderNumPerBook;

		for (int i = 0; i < n; i++) {
			List<String> subList = folderPathList
					.subList(i * Constants.folderNumPerBook, (i + 1) * Constants.folderNumPerBook);

			// 封装书
			BookBO bookBO = new BookBO();
			bookBO.setBookPath(Constants.outputFolderPath);
			bookBO.setBookName(Constants.bookName + i + ".pdf");

			// 获取子文件夹下文件
			for (String subFolderPath : subList) {
				FolderBO subFolderBO = new FolderBO();
				subFolderBO.setFolderPath(subFolderPath);
				FileUtils.getPackageFolder(subFolderBO);
				List<String> filePathList = subFolderBO.getFilePathList();
				// 封装书
				bookBO.getPagePathList().addAll(filePathList);
			}
			try {
				// 生成书
				PdfUtils.generatePDF(bookBO);
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}

		}
	}
}
