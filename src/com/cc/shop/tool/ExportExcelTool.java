package com.cc.shop.tool;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExportExcelTool {
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";

	public static void getToExcel(String name, String[][] title,
			String[][] data, HttpServletRequest request,
			HttpServletResponse response) {
		String path = request.getRealPath(File.separator) + name + ".xls";
		try {
			// 打开文件
			WritableWorkbook book = Workbook.createWorkbook(new File(path));
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet(name, 0);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			WritableCellFormat timesBoldUnderline = null;
			// 20号 粗体
			WritableFont font20 = new WritableFont(WritableFont.TIMES, 20,
					WritableFont.BOLD, false);
			timesBoldUnderline = new WritableCellFormat(font20);
			// 水平居中
			timesBoldUnderline.setAlignment(jxl.format.Alignment.CENTRE);
			// 垂直居中
			timesBoldUnderline
					.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			// 全边框
			// timesBoldUnderline.setBorder(jxl.format.Border.ALL,jxl.format.BorderLineStyle.THIN);

			jxl.write.WritableCellFormat wcfF = new jxl.write.WritableCellFormat(
					timesBoldUnderline);

			Label label = new Label(0, 0, name + "一览", wcfF);
			// label.
			sheet.mergeCells(0, 0, title[1].length, 2);

			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);

			/*
			 * title = { "题目ID", "标题", "题目类型", "所属分类", "考点",
			 * "难易程度(0:简单;1:普通;2:困难)", "所属章节", "正确答案", "选项1", "选项2", "选项3",
			 * "选项4", "状态(0:正常;1:下架)", "标签字段", "解析人", "解析内容", "解析时间",
			 * "解析状态(0:通过;1:未审核;2:不通过)"};
			 */
			WritableCellFormat titleFontFormat = null;
			// 9号 粗体
			WritableFont titlefont12 = new WritableFont(WritableFont.TIMES, 15,
					WritableFont.BOLD, false);
			titleFontFormat = new WritableCellFormat(titlefont12);
			titleFontFormat.setWrap(true);
			// 水平居中
			titleFontFormat.setAlignment(jxl.format.Alignment.CENTRE);
			// 垂直居中
			titleFontFormat
					.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);

			Label label1 = new Label(0, 3, "序号", titleFontFormat);
			sheet.addCell(label1);
			for (int i = 0; i < title[1].length; i++) {
				label1 = new Label(i + 1, 3, title[0][i], titleFontFormat);
				sheet.setColumnView(i + 1, Integer.parseInt(title[1][i]));
				sheet.addCell(label1);
			}

			WritableCellFormat FontFormat = null;
			// 10号 粗体
			WritableFont font10 = new WritableFont(WritableFont.TIMES, 12,
					WritableFont.NO_BOLD, false);
			FontFormat = new WritableCellFormat(font10);

			// 水平居中
			FontFormat.setAlignment(jxl.format.Alignment.CENTRE);
			// 垂直居中
			FontFormat
					.setVerticalAlignment(jxl.format.VerticalAlignment.BOTTOM);

			int num = 0;
			int i = 0;
			int j = 0;
			for (String[] row : data) {

				i++;
				num = i + 3;
				j = 0;
				sheet.addCell(new Label(j, num, i + "", FontFormat));
				for (String cell : row) {
					j++;
					sheet.addCell(new Label(j, num, cell, FontFormat));
				}
			}
			// 写入数据并关闭文件
			book.write();
			book.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			downFile(path, name + ".xls", request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void downFile(String path, String fileName,
			HttpServletRequest req, HttpServletResponse response)
			throws IOException {

		response.setContentType(CONTENT_TYPE);
		// 创建file对象
		File file = new File(path);
		// 设置response的编码方式
		response.setContentType("application/x-msdownload");
		// 写明要下载的文件的大小
		response.setContentLength((int) file.length());
		// 解决中文乱码
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String(fileName.getBytes("utf-8"), "iso-8859-1"));
		// 读出文件到i/o流
		FileInputStream fis = null;
		fis = new FileInputStream(file);
		BufferedInputStream buff = new BufferedInputStream(fis);
		byte[] b = new byte[1024];// 相当于我们的缓存
		long k = 0;// 该值用于计算当前实际下载了多少字节
		// 从response对象中得到输出流,准备下载
		OutputStream myout = null;
		myout = response.getOutputStream();

		// 开始循环下载

		while (k < file.length()) {
			int j = 0;
			j = buff.read(b, 0, 1024);
			k += j;
			// 将b中的数据写到客户端的内存
			myout.write(b, 0, j);
		}
		// 将写入到客户端的内存的数据,刷新到磁盘
		myout.flush();
		myout.close();
		buff.close();
		fis.close();
		file.delete();

	}

}
