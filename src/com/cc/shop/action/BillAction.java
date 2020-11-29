package com.cc.shop.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.cc.shop.pojo.BillItem;
import com.cc.shop.service.BillService;
import com.cc.shop.tool.ExportExcelTool;
import com.cc.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BillAction extends ActionSupport {
	private BillService billService;

	// 接收page参数
	private Integer page;

	public String findAll() {
		System.out.println("进去后台方法");
		PageBean<BillItem> pageBean = billService.findAll(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}

	// 导出excel表格
	public void exportExcel() {
		List<BillItem> billItemList = billService.findAllBillItem();
		String[][] title = new String[][] { { "商品编号", "商品名称", "销售数量", "销售总额" },
				{ "24", "24", "24", "24" } };

		String[][] data = new String[billItemList.size()][4];
		for (int i = 0; i < billItemList.size(); i++) {
			data[i][0] = billItemList.get(i).getProduct().getPid().toString();
			data[i][1] = billItemList.get(i).getProduct().getPname();
			data[i][2] = billItemList.get(i).getCount().toString();
			data[i][3] = billItemList.get(i).getPtotal().toString();
		}

		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);

		ExportExcelTool.getToExcel("销售信息", title, data, request, response);
	}

	public String toViewChart() throws Exception {
		ActionContext ac = ActionContext.getContext();
		ac.getSession().put("flag", "1");

		CategoryDataset dataset = getDataSet2();
		JFreeChart chart = ChartFactory.createBarChart("销售列表", // 图表标题
				"商品种类",// 目录轴的显示标签
				"销售数量",// 数值轴的显示标签
				dataset, // 数据集
				PlotOrientation.VERTICAL, // 图标方向：水平、垂直
				true, // 是否显示图例（对于简单的柱状图必须是false）
				false, // 是否生成工具
				false// 是否生成url链接
				);

		setForam(chart, dataset);
		
		String root = ServletActionContext.getServletContext().getRealPath(
				"/tubiao" + File.separator + "data.png");
		
		File file = new File(root);
		
		if (!file.exists()) {
			file.createNewFile();
		}
		try {
			ChartUtilities.saveChartAsPNG(file, chart, 520, 520);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("生成成功");
		return "billchart";
	}

	// 柱形图
	private  CategoryDataset getDataSet2() {
		
		List<BillItem> billItemList = billService.findAllBillItem();
		System.out.println("销售商品条数："+billItemList.size());
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (int i = 0; i < billItemList.size(); i++) {
			String pname = billItemList.get(i).getProduct().getPname();
			Integer count = billItemList.get(i).getCount();
			dataset.addValue(count, pname, pname);
		}
		
		

		return dataset;
	}

	public void setForam(JFreeChart jFreeChart, CategoryDataset dataset) {

		jFreeChart.getTitle().setFont(new Font("隶书", Font.ITALIC, 15));// 设置标题
		// 设置图例类别字体
		jFreeChart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 15));

		jFreeChart.setBackgroundPaint(Color.WHITE);
		CategoryPlot categoryPlot = jFreeChart.getCategoryPlot();// 用于设置显示特性
		categoryPlot.setBackgroundPaint(Color.WHITE);
		categoryPlot.setDomainGridlinePaint(Color.BLACK);// 分类轴网格线条颜色
		categoryPlot.setDomainGridlinesVisible(true);
		categoryPlot.setRangeGridlinePaint(Color.GREEN);// 数据轴网格线条颜色

		CategoryAxis domainAxis = categoryPlot.getDomainAxis(); // 水平底部列表
		domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
		domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
		ValueAxis rangeAxis = categoryPlot.getRangeAxis();// 获取柱状
		rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15)); // 设置柱状标题
	}



	public BillService getBillService() {
		return billService;
	}

	public void setBillService(BillService billService) {
		this.billService = billService;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

}
