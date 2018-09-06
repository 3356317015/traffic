package com.jasper.designer;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.beanutils.BeanUtils;

import com.zhima.basic.exception.UserBusinessException;


/**
 * IReportManager概要说明：Ireport报表管理
 * @author lcy
 */
@SuppressWarnings({ "rawtypes"})
public class IReportManager {
	
	private static IReportManager iReportMananger;
	/**
	 * jasper文件路径
	 */
	private String filePath;
	/**
	 * 1/pdf文件;2/excel文件
	 */
	private String printType;

	/**
	 * 功能：静态方法得到报表引擎实例
	 * @param filePath jasper文件
	 * @param printType 格式类型
	 * @return
	 */
	public static IReportManager getInstance(String filePath,String printType){
		if(iReportMananger == null){
			iReportMananger = new IReportManager(filePath,printType);
		}else{
			iReportMananger.filePath = filePath;
			iReportMananger.printType = printType;
		}
		return iReportMananger;
	}
	
	/**
	 * 功能：构造函数
	 * @param filePath jasper文件
	 * @param printType 格式类型
	 */
	private IReportManager(String filePath,String printType){
		this.printType = printType;
		String patha = IReportManager.class.getResource("/").getPath();
		int index = patha.indexOf("bin");
		if(index > 0){
			String proPath = patha.substring(Integer.valueOf(patha.indexOf("/")+1),patha.indexOf("bin"));
			//项目路径
			String projectPath = proPath.replace("/", "\\");
			this.filePath = projectPath + filePath.replace("/","\\");
		}else{
			this.filePath = filePath;
		}
	}
	
	/**
	 * createJspaper方法描述：生成jasper
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-17 上午11:56:45
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param param 参数
	 * @param datas 数据
	 * @return JasperPrint
	 * @throws Exception
	 */
	public JasperPrint createJspaper(Object param, List datas) throws Exception{
		if (null != datas && datas.size()>0){
			Map paramMap = BeanUtils.describe(param);
			JasperPrint jasperPrint = JasperFillManager.fillReport(filePath, paramMap, new JRBeanCollectionDataSource(datas));
			return jasperPrint;
		}else{
			URL url = getClass().getResource("emptyreport.jrprint");
			JasperPrint jasperPrint = (JasperPrint) JRLoader.loadObject(url);
			return jasperPrint;
		}
		
	}
	
	/**
	 * outputListToPrinter方法描述：打印JasperPrint
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-17 上午11:58:57
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param param
	 * @param datas
	 * @throws UserBusinessException void
	 */
	public void outputListToPrinter(Object param,List datas) throws Exception{
		try {
			if (this.printType.equals("0")){
				JasperPrint jasperPrint = createJspaper(param,datas);
				JasperPrintManager.printReport(jasperPrint, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * outputListToFile方法描述：输出报表并打开显示
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-17 上午11:57:53
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param param
	 * @param datas
	 * @param destFile
	 * @throws UserBusinessException void
	 */
	public void outputListToFile(Object param,List datas,String destFile) throws Exception{
		//格式化为pdf
		if (this.printType.equals("1")){
			destFile = destFile+".pdf";
			exporterPdf(param,datas,destFile);
		}
		//格式化为excel
		if (this.printType.equals("2")){
			destFile = destFile+".xls";
			exporterXls(param,datas,destFile);
		}
		openFile(destFile);
	}
	
	
	/**
	 * exporterPdf方法描述：输出为PDF
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-17 下午12:00:55
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param param
	 * @param dtats
	 * @param destFile void
	 */
	private void exporterPdf(Object param, List dtats, String destFile){
		try {
			JasperPrint jasperPrint = createJspaper(param,dtats);
			//删除存在的文件
			File file = new File(destFile);
			if(file.exists()){
				file.delete();
			}
			JasperExportManager.exportReportToPdfFile(jasperPrint,destFile);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * exporterXls方法描述：输出为XLS
	 * 创  建  人 ：鲁承毅
	 * 创建时间：2013-8-17 下午12:01:25
	 * 修  改  人 ：(修改了该方法，请填上修改人的姓名)
	 * 修改时间：(请填上修改时间)
	 * @param param
	 * @param dtats
	 * @param destPath void
	 */
	private void exporterXls(Object param, List dtats, String destFile){
		try {
			JasperPrint jasperPrint = createJspaper(param,dtats);
			//删除存在的文件
			File file = new File(destFile);
			if(file.exists()){
				file.delete();
			}
			JRXlsExporter exporter = new JRXlsExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile);
			exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET,Boolean.FALSE);
			exporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED,Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS,Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);
			exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE,Boolean.TRUE);
			exporter.exportReport();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
    private void openFile(String url){
       try {
           Desktop.getDesktop().open(new File(url));
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

	
}
