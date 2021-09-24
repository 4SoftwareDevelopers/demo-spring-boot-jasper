package com.example.demospringbootjasper.commons;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.example.demospringbootjasper.enums.TipoReporteEnum;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

/**
 * @author <a href="mailto:4softwaredevelopers@gmail.com">Jordy Rodríguezr</a>
 * @project demo-spring-boot-jasper
 * @class JasperComponent
 * @description 
 * @HU_CU_REQ 
 * @date 17 sep. 2021
 */
@Component
public class JasperReportManager {

	private static final String REPORT_FOLDER = "reports";

	private static final String JASPER = ".jasper";

	/**
	 * @author <a href="mailto:4softwaredevelopers@gmail.com">Jordy Rodríguezr</a> 
	 * @date 17 sep. 2021
	 * @description 
	 * @HU_CU_REQ 
	 * @param fileName
	 * @param tipoReporte
	 * @param params
	 * @param ds
	 * @return
	 * @throws JRException 
	 * @throws IOException 
	 */
	public ByteArrayOutputStream export(String fileName, String tipoReporte, Map<String, Object> params,
			Connection con) throws JRException, IOException {

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		ClassPathResource resource = new ClassPathResource(REPORT_FOLDER + File.separator + fileName + JASPER);
		
		InputStream inputStream = resource.getInputStream();
		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, params, con);
		if (tipoReporte.equalsIgnoreCase(TipoReporteEnum.EXCEL.toString())) {
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(stream));
			SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
			configuration.setDetectCellType(true);
			configuration.setCollapseRowSpan(true);
			exporter.setConfiguration(configuration);
			exporter.exportReport();
		} else {
			JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
		}

		return stream;
	}
	
	

}
